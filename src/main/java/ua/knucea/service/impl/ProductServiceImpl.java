package ua.knucea.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.knucea.domain.entity.Language;
import ua.knucea.domain.entity.category.CategoryEntity;
import ua.knucea.domain.entity.category.CategoryLocalization;
import ua.knucea.domain.entity.product.ProductEntity;
import ua.knucea.domain.entity.product.ProductLocalization;
import ua.knucea.domain.model.admin.ProductCreationModel;
import ua.knucea.domain.model.admin.ProductModel;
import ua.knucea.domain.model.admin.ProductUpdateModel;
import ua.knucea.domain.model.dto.Product;
import ua.knucea.repository.CategoryEntityRepository;
import ua.knucea.repository.CategoryRepository;
import ua.knucea.repository.LanguageRepository;
import ua.knucea.repository.ProductLocalizationRepository;
import ua.knucea.repository.ProductRepository;
import ua.knucea.service.ProductService;
import ua.knucea.util.FileSaver;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final CategoryRepository categoryRepository;

    private final CategoryEntityRepository categoryEntityRepository;

    private final ProductRepository productRepository;

    private final ProductLocalizationRepository productLocalizationRepository;

    private final ModelMapper modelMapper;

    private final LanguageRepository languageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public ProductServiceImpl(final CategoryEntityRepository categoryEntityRepository, final ProductLocalizationRepository productLocalizationRepository, final LanguageRepository languageRepository, final ModelMapper modelMapper, final CategoryRepository categoryRepository, final ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.languageRepository = languageRepository;
        this.productLocalizationRepository = productLocalizationRepository;
        this.categoryEntityRepository = categoryEntityRepository;
    }

    @Override
    public boolean addProduct(ProductCreationModel productCreationModel) throws IOException {
        CategoryLocalization currentCategory = categoryRepository.findByName(productCreationModel.getCategoryName());

        if (currentCategory == null) {
            return false;
        }

        ProductEntity newProduct = extractProductFromModel(productCreationModel, currentCategory.getCategoryEntity());
        productRepository.save(newProduct);

        ProductLocalization productLocalizationRu = new ProductLocalization();
        productLocalizationRu.setProduct(newProduct);

        productLocalizationRu.setName(productCreationModel.getProductNameRu());
        productLocalizationRu.setDescription(productCreationModel.getDescriptionRu());
        productLocalizationRu.setLanguage(languageRepository.findByName("ru"));

        ProductLocalization productLocalizationEn = new ProductLocalization();
        productLocalizationEn.setProduct(newProduct);

        productLocalizationEn.setName(productCreationModel.getProductNameEn());
        productLocalizationEn.setDescription(productCreationModel.getDescriptionEn());
        productLocalizationEn.setLanguage(languageRepository.findByName("en"));

        productLocalizationRepository.save(productLocalizationEn);
        productLocalizationRepository.save(productLocalizationRu);

        return true;
    }

    @Override
    public void updateProduct(ProductUpdateModel model) throws IOException {
        ProductEntity productEntity = productRepository.getOne(model.getId());
        CategoryEntity currentCategory = categoryRepository.findByName(model.getCategoryName()).getCategoryEntity();
        ProductLocalization productLocalizationRu = productLocalizationRepository.findByProductIdAndLanguageId(productEntity.getId(), languageRepository.findByName("ru").getId());
        ProductLocalization productLocalizationEn = productLocalizationRepository.findByProductIdAndLanguageId(productEntity.getId(), languageRepository.findByName("en").getId());

        productEntity.setCategory(categoryEntityRepository.getOne(currentCategory.getId()));
        productEntity.setQuantity(model.getQuantity());
        productEntity.setPrice(model.getPrice());

        productLocalizationEn.setName(model.getProductNameEn());
        productLocalizationEn.setDescription(model.getDescriptionEn());

        productLocalizationRu.setName(model.getProductNameRu());
        productLocalizationRu.setDescription(model.getDescriptionRu());

        MultipartFile productImg = model.getProductImg();

        if (productImg != null && !productImg.getOriginalFilename().isEmpty()) {
            FileSaver fileSaver = new FileSaver();
            productEntity.setImgPath(fileSaver.saveFile(productImg, uploadPath));
        }
        productRepository.save(productEntity);
        productLocalizationRepository.save(productLocalizationEn);
        productLocalizationRepository.save(productLocalizationRu);
    }

    private ProductEntity extractProductFromModel(ProductCreationModel model, CategoryEntity currentCategory) throws IOException {
        ProductEntity newProduct = new ProductEntity();

        newProduct.setPrice(model.getPrice());
        newProduct.setQuantity(model.getQuantity());
        newProduct.setCategory(currentCategory);
        newProduct.setExpireDate(model.getExpireDate());

        MultipartFile avatar = model.getProductImg();
        if (avatar != null && !avatar.getOriginalFilename().isEmpty()) {
            FileSaver fileSaver = new FileSaver();
            newProduct.setImgPath(fileSaver.saveFile(avatar, uploadPath));
        }

        return newProduct;
    }

    @Override
    public List<Product> getProductsById(Set ids, String langName) {
        List<ProductEntity> productEntityList = productRepository.findAllById(ids);
        return productEntityList.stream().map(elem -> getLocalizedProduct(elem, languageRepository.findByName(langName).getId())).collect(Collectors.toList());

    }


    @Override
    public List<Product> getAllProducts(String languageName) {
        Language language = languageRepository.findByName(languageName);
        List<ProductLocalization> ls = productLocalizationRepository.findAllByLanguageId(language.getId());
        if (ls == null) {
            return null;
        }
        return ls.stream()
                .map(this::convertToProduct)
                .collect(Collectors.toList());
    }

    private Product convertToProduct(ProductLocalization productLocalization) {
        Product product = new Product();
        ProductEntity productEntity = productLocalization.getProduct();

        product.setCategory(productEntity.getCategory());
        product.setDescription(productLocalization.getDescription());
        product.setName(productLocalization.getName());
        product.setImgPath(productEntity.getImgPath());
        product.setPrice(productEntity.getPrice());
        product.setQuantity(productEntity.getQuantity());
        product.setId(productEntity.getId());

        return product;
    }

    @Override
    public List<Product> getProductsByCategoryName(String categoryName, String langName) {
        CategoryLocalization categoryLocalization = categoryRepository.findByName(categoryName);
        List<ProductEntity> productEntityList = productRepository.findByCategoryId(categoryLocalization.getCategoryEntity().getId());
        return productEntityList.stream().map(elem -> getLocalizedProduct(elem, languageRepository.findByName(langName).getId())).collect(Collectors.toList());
    }

    private Product getLocalizedProduct(ProductEntity productEntity, Long langId) {
        Product product = new Product();

        ProductLocalization localizedProduct = productLocalizationRepository.findByProductIdAndLanguageId(productEntity.getId(), langId);
        product.setName(localizedProduct.getName());
        product.setDescription(localizedProduct.getDescription());
        product.setQuantity(productEntity.getQuantity());
        product.setPrice(productEntity.getPrice());
        product.setImgPath(productEntity.getImgPath());
        product.setCategory(productEntity.getCategory());
        product.setId(productEntity.getId());

        return product;
    }

    @Override
    public ProductEntity findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public ProductModel getProductEntityByProductIdAndLanguageName(Long id, String langName) {
        ProductModel currentProduct = new ProductModel();
        ProductEntity currentProductEntity = productRepository.getOne(id);

        CategoryLocalization categoryLocalization = categoryRepository.findByCategoryEntityAndLanguageId(currentProductEntity.getCategory(), languageRepository.findByName(langName).getId());

        ProductLocalization productLocalizationRu = productLocalizationRepository.findByProductIdAndLanguageId(id, languageRepository.findByName("ru").getId());
        ProductLocalization productLocalizationEn = productLocalizationRepository.findByProductIdAndLanguageId(id, languageRepository.findByName("en").getId());

        currentProduct.setId(id);
        currentProduct.setCategoryName(categoryLocalization.getName());
        currentProduct.setPrice(currentProductEntity.getPrice());
        currentProduct.setProductImg(currentProductEntity.getImgPath());
        currentProduct.setQuantity(currentProductEntity.getQuantity());

        currentProduct.setProductNameEn(productLocalizationEn.getName());
        currentProduct.setDescriptionEn(productLocalizationEn.getDescription());
        currentProduct.setProductNameRu(productLocalizationRu.getName());
        currentProduct.setDescriptionRu(productLocalizationRu.getDescription());

        return currentProduct;
    }

    @Override
    public ProductLocalization getProductByProductIdAndLanguageName(Long id, String langName) {
        return productLocalizationRepository.findByProductIdAndLanguageId(id, languageRepository.findByName(langName).getId());
    }

    @Override
    @Modifying
    @Transactional
    public boolean deleteProduct(ProductEntity entity) {
        productLocalizationRepository.deleteByProductId(entity.getId());
        productRepository.deleteById(entity.getId());
        return true;
    }
}
