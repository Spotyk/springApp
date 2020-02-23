package com.edu.controller;

import com.edu.domain.entity.Order;
import com.edu.domain.entity.Product;
import com.edu.domain.model.OrderStatusModel;
import com.edu.domain.model.admin.CategoryCreateModel;
import com.edu.domain.model.admin.CategoryUpdateModel;
import com.edu.domain.model.admin.ProductCreationModel;
import com.edu.domain.model.admin.ProductUpdateModel;
import com.edu.domain.model.admin.UserUpdateForm;
import com.edu.service.CategoryService;
import com.edu.service.OrderService;
import com.edu.service.ProductService;
import com.edu.service.UserService;
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

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;

    private final CategoryService categoryService;

    private final ProductService productService;

    private final OrderService orderService;

    public AdminController(final UserService userService, final CategoryService categoryService, final ProductService productService,
                           final OrderService orderService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/adminPanel")
    public String adminPanel() {
        return "adminPanel";
    }

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

    @GetMapping("/getCategories")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
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

    // TODO: 2/17/2020
    //todo product update post

    @GetMapping("/updateProduct/{productId}")
    public String editProductPage(@PathVariable Product productId, Model model) {
        model.addAttribute("currentProduct", productId);

        return "productUpdatePage";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@Valid ProductUpdateModel updateModel) throws IOException {
        productService.updateProduct(updateModel);
        return "showProducts";
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
    public String adminUserApdate(UserUpdateForm updateForm) {
        userService.updateUserByAdmin(updateForm);

        return "users";
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
}
