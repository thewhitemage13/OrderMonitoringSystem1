package org.thewhitemage13.controller;

import org.springframework.web.bind.annotation.*;
import org.thewhitemage13.entity.Order;
import org.thewhitemage13.service.OrderService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public void createOrder(@RequestBody Order order) {
        order.setCreatedAt(LocalDateTime.now());
        orderService.createOrder(order);
    }

    @PutMapping("/update")
    public void updateOrderStatus(@RequestParam Long orderId, @RequestParam String status) {
        orderService.updateOrderStatus(orderId, status);
    }
}
