package rs.ac.uns.ftn.order_service.service;

import rs.ac.uns.ftn.order_service.client.ProductClient;
import rs.ac.uns.ftn.order_service.model.Order;
import rs.ac.uns.ftn.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    public Order createOrder(Order order) {
        // 1. Poziv product-service preko Feign-a
        Map<String, Object> product = productClient.getProductById(order.getProductId());

        Object quantityObj = product.get("quantity");
        int availableQuantity = (quantityObj instanceof Number) ? ((Number) quantityObj).intValue() : 0;

        // 2. Logika provere
        if (availableQuantity >= order.getQuantity()) {
            order.setStatus("CONFIRMED");
            return orderRepository.save(order);
        } else {
            order.setStatus("REJECTED");
            return order; // Vraćamo sa statusom REJECTED
        }
    }
    public java.util.List<Order> findAll() {
        return orderRepository.findAll();
    }
}