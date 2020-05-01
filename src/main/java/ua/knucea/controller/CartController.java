package ua.knucea.controller;

import ua.knucea.domain.entity.User;
import ua.knucea.domain.entity.product.ProductEntity;
import ua.knucea.service.OrderDetailsService;
import ua.knucea.service.OrderService;
import ua.knucea.service.ProductService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class CartController {

    private final ProductService productService;

    private final OrderService orderService;

    private final OrderDetailsService orderDetailsService;

    public CartController(final ProductService productService, final OrderService orderService, final OrderDetailsService orderDetailsService) {
        this.productService = productService;
        this.orderService = orderService;
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping("/cart")
    public String getCartPage() {
        return "cart";
    }

    @GetMapping("/getItemsById")
    public ResponseEntity<?> getCartItemsById(@RequestParam(name = "items[]") Long[] ids) {
        return ResponseEntity.ok(productService.getProductsById(convertToSetFromArray(ids), LocaleContextHolder.getLocale().getLanguage()));
    }

    @PostMapping("/createOrder")
    public String createOrder(@AuthenticationPrincipal User user,
                              @RequestParam(name = "items[]") Long[] ids) {
        Long orderId = orderService.createOrder(user);

        orderDetailsService.createOrderDetails(orderId, countProductQuantity(ids));
        orderService.setOrderTotalSum(orderId);

        return "order";
    }

    private Set<Long> convertToSetFromArray(Long[] ids) {
        return new HashSet<>(Arrays.asList(ids));
    }

    private Map<ProductEntity, Long> countProductQuantity(Long[] ids) {
        Map<ProductEntity, Long> productMap = new HashMap<>();

        for (Long productId : convertToSetFromArray(ids)) {
            long count = 0;

            for (Long id : ids) {
                if (productId.equals(id)) {
                    count++;
                }
            }
            productMap.put(productService.findById(productId), count);
        }
        return productMap;
    }
}
