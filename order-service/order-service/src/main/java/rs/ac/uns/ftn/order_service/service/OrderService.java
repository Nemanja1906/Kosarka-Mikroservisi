package rs.ac.uns.ftn.order_service.service;

import rs.ac.uns.ftn.order_service.client.ProductClient;
import rs.ac.uns.ftn.order_service.client.InventoryClient;
import rs.ac.uns.ftn.order_service.config.RabbitConfig; // OVO MI TREBA DA BI VIDEO QUEUE
import rs.ac.uns.ftn.order_service.model.Order;
import rs.ac.uns.ftn.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.core.RabbitTemplate; // OVO ZA RABBIT
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private RabbitTemplate rabbitTemplate; // DODATO

    public Order createOrder(Order order) {
        // 1. Provera stanja preko novog Inventory servisa
        Integer availableQuantity = inventoryClient.getQuantity(order.getProductId());

        // 2. Logika provere
        if (availableQuantity != null && availableQuantity >= order.getQuantity()) {
            order.setStatus("CONFIRMED");
            Order savedOrder = orderRepository.save(order);

            // DODATO: Slanje poruke kad je potvrdjeno
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE, "Nova porudžbina je potvrdjena: " + savedOrder.getId());

            return savedOrder;
        } else {
            order.setStatus("REJECTED");
            return order;
        }
    }

    public java.util.List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order updateOrder(Long id, Order orderDetails) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Porudžbina nije pronađena: " + id));

        order.setStatus(orderDetails.getStatus());
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}