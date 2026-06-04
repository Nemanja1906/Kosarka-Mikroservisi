package rs.ac.uns.ftn.order_service.service;

import rs.ac.uns.ftn.order_service.client.ProductClient;
import rs.ac.uns.ftn.order_service.client.InventoryClient; // Dodaj ovo
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

    @Autowired
    private InventoryClient inventoryClient; // Injektuj novi client

    public Order createOrder(Order order) {
        // 1. Provera stanja preko novog Inventory servisa
        Integer availableQuantity = inventoryClient.getQuantity(order.getProductId());

        // 2. Logika provere
        if (availableQuantity != null && availableQuantity >= order.getQuantity()) {
            order.setStatus("CONFIRMED");
            return orderRepository.save(order);
        } else {
            order.setStatus("REJECTED");
            return order;
        }
    }

    public java.util.List<Order> findAll() {
        return orderRepository.findAll();
    }
}