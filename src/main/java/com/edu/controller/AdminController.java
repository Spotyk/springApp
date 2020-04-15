package com.edu.controller;

import com.edu.domain.entity.Order;
import com.edu.domain.entity.Role;
import com.edu.domain.entity.product.ProductEntity;
import com.edu.domain.model.OrderStatusModel;
import com.edu.domain.model.admin.CategoryCreateModel;
import com.edu.domain.model.admin.CategoryUpdateModel;
import com.edu.domain.model.admin.LanguageCreateModel;
import com.edu.domain.model.admin.LanguageUpdateModel;
import com.edu.domain.model.admin.ProductCreationModel;
import com.edu.domain.model.admin.ProductUpdateModel;
import com.edu.domain.model.admin.UserUpdateForm;
import com.edu.domain.model.impl.RegistrationFormUserModel;
import com.edu.service.CategoryService;
import com.edu.service.LanguageService;
import com.edu.service.OrderService;
import com.edu.service.ProductService;
import com.edu.service.UserService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.edu.constant.ControllerConstant.HAS_ADMIN_AUTHORITY;

@Controller
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
public class AdminController {

    private final UserService userService;

    private final LanguageService languageService;

    private final CategoryService categoryService;

    private final ProductService productService;

    private final OrderService orderService;

    public AdminController(final LanguageService languageService, final UserService userService, final CategoryService categoryService, final ProductService productService,
                           final OrderService orderService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.orderService = orderService;
        this.languageService = languageService;
    }

    @GetMapping("/adminPanel")
    public String adminPanel() {
        return "adminPanel";
    }

    @PreAuthorize(HAS_ADMIN_AUTHORITY)
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users";
    }

    @GetMapping("/categoryUpdatePage")
    public String getCategoryUpdatePage() {
        return "categoryUpdatePage";
    }

    @GetMapping("/categoryCreationPage")
    public String getCategoryCreationPage() {
        return "categoryCreationPage";
    }

    @GetMapping("/productCreationPage")
    public String getProductCreationPage() {
        return "productCreationPage";
    }

    @GetMapping("/productUpdatePage")
    public String getProductUpdatePage() {
        return "productUpdatePage";
    }

    @GetMapping("/showProducts")
    public String getAllProducts() {
        return "showProducts";
    }

    @GetMapping("/languageCreationPage")
    public String langCreatePage() {
        return "languageCreationPage";
    }

    @GetMapping("/languageUpdatePage")
    public String langUpdatePage() {
        return "languageUpdatePage";
    }

    @GetMapping("/getCategories")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories(LocaleContextHolder.getLocale().getLanguage()));
    }

    @GetMapping("/countLanguages")
    public ResponseEntity<?> countLanguages() {
        return ResponseEntity.ok(languageService.countLanguages());
    }

    @GetMapping("/getLanguages")
    public ResponseEntity<?> getLanguages() {
        return ResponseEntity.ok(languageService.getAllLanguages());
    }

    @GetMapping("/showOrders")
    public String getOrderPage(Model model) {
        List<Order> orders = orderService.findAll();

        if (orders.isEmpty()) {
            model.addAttribute("orderError", "You have no orders");
            return "showOrders";
        }
        model.addAttribute("orders", orders);
        return "showOrders";
    }

    @GetMapping("/updateProduct/{productId}")
    public String editProductPage(@PathVariable ProductEntity productId, Model model) {
        model.addAttribute("currentProduct", productService.getProductEntityByProductIdAndLanguageName(productId.getId(), LocaleContextHolder.getLocale().getLanguage()));

        return "productUpdatePage";
    }

    @PreAuthorize(HAS_ADMIN_AUTHORITY)
    @GetMapping("/addManager")
    public String addManager() {
        return "addManager";
    }

    @PreAuthorize(HAS_ADMIN_AUTHORITY)
    @GetMapping("/addAdmin")
    public String addAdmin() {
        return "addAdmin";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@Valid ProductUpdateModel updateModel) throws IOException {
        productService.updateProduct(updateModel);
        return "showProducts";
    }

    @PostMapping("/createProduct")
    public String createProduct(
            @Valid ProductCreationModel productCreationModel,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
        }

        try {
            if (!productService.addProduct(productCreationModel)) {
                model.addAttribute("ProductNameError", "Product or Categiry Name not correct");
            }
        } catch (IOException e) {
            model.addAttribute("FileError", "Something went wrong with file.");
        }

        return "productCreationPage";
    }


    @PostMapping("/createCategory")
    public String createCategory(
            @Valid CategoryCreateModel categoryCreateModel,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "categoryCreationPage";
        }

        if (!categoryService.addCategory(categoryCreateModel)) {
            model.addAttribute("categoryNameError", "Name exists!");
        }

        return "categoryCreationPage";
    }

    @PostMapping("/changeUserInfo")
    public String adminUserUpdate(UserUpdateForm updateForm) {
        userService.updateUserByAdmin(updateForm);

        return "users";
    }

    @PostMapping("/createLanguage")
    public String createLanguage(LanguageCreateModel model) {
        languageService.addLanguage(model);

        return "adminPanel";
    }

    @PostMapping("/updateLanguage")
    public String updateLanguage(LanguageUpdateModel model) {
        languageService.updateLanguage(model);

        return "adminPanel";
    }

    @PostMapping("/changeOrderStatus")
    public String adminChangeOrderStatus(
            @Valid OrderStatusModel orderStatusModel,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "showOrders";
        }
        orderService.changeStatus(orderStatusModel);

        return "showOrders";
    }

    @PostMapping("/updateCategory")
    public String updateCategory(
            @Valid CategoryUpdateModel categoryUpdateModel,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "categoryUpdatePage";
        }

        if (!categoryService.updateCategory(categoryUpdateModel)) {
            model.addAttribute("categoryNameError", "Name exists!");
        }

        return "categoryUpdatePage";
    }

    @PreAuthorize(HAS_ADMIN_AUTHORITY)
    @PostMapping("/createManager")
    public String createManager(
            @Valid RegistrationFormUserModel registrationFormUserModel,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        return addUser(registrationFormUserModel, bindingResult, model, Role.MANAGER);
    }

    @PreAuthorize(HAS_ADMIN_AUTHORITY)
    @PostMapping("/createAdmin")
    public String createAdmin(
            @Valid RegistrationFormUserModel registrationFormUserModel,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        return addUser(registrationFormUserModel, bindingResult, model, Role.ADMIN);
    }

    private String addUser(
            @Valid RegistrationFormUserModel registrationFormUserModel,
            BindingResult bindingResult,
            Model model,
            Role role
    ) throws IOException {
        if (registrationFormUserModel.getPassword() != null && !registrationFormUserModel.getPassword().equals(registrationFormUserModel.getPassword2())) {
            model.addAttribute("passwordError", "Passwords are different!");
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        if (!userService.addUser(registrationFormUserModel, role)) {
            model.addAttribute("emailError", "Email exists!");
            return "registration";
        }

        return "redirect:/adminPanel";
    }
}
