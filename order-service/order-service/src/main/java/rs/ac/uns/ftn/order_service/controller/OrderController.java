package rs.ac.uns.ftn.order_service.controller;

import rs.ac.uns.ftn.order_service.model.Order;
import rs.ac.uns.ftn.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }
    @GetMapping
    public java.util.List<Order> getAllOrders() {
        return orderService.findAll();
    }
}