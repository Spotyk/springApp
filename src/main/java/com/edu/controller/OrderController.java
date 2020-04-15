package com.edu.controller;

import com.edu.domain.entity.Order;
import com.edu.domain.entity.OrderStatus;
import com.edu.domain.entity.User;
import com.edu.domain.model.OrderStatusModel;
import com.edu.service.OrderDetailsService;
import com.edu.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    private final OrderService orderService;

    private final OrderDetailsService orderDetailsService;

    public OrderController(final OrderService orderService, final OrderDetailsService orderDetailsService) {
        this.orderService = orderService;
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping("/order")
    public String getOrderPage(@AuthenticationPrincipal User currentUser, Model model) {
        List<Order> orders = orderService.findAllOrdersByUserId(currentUser.getId());
        if (orders.isEmpty()) {
            model.addAttribute("orderError", "You have no orders");
            return "order";
        }

        model.addAttribute("orders", orders);
        return "order";
    }

    @GetMapping("/getOrderDetailsByOrderId")
    public ResponseEntity<?> getOrderDetailsByOrderId(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(orderDetailsService.getOrderDetailsByOrderId(id));
    }

    @PostMapping("/cancelOrder")
    public String adminChangeOrderStatus(
            @Valid OrderStatusModel orderStatusModel,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "order";
        }
        if (!orderStatusModel.getOrderStatus().equals(OrderStatus.CANCELED)) {
            model.addAttribute("InputError", "Not correct input value");
            return "order";
        }
        orderService.changeStatus(orderStatusModel);

        return "order";
    }
}
