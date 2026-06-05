package rs.ac.uns.ftn.order_service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import rs.ac.uns.ftn.order_service.client.InventoryClient;
import rs.ac.uns.ftn.order_service.client.ProductClient;
import rs.ac.uns.ftn.order_service.model.Order;
import rs.ac.uns.ftn.order_service.repository.OrderRepository;
import rs.ac.uns.ftn.order_service.service.OrderService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private InventoryClient inventoryClient;

    @Mock
    private ProductClient productClient;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testCreateOrder() {
        Order order = new Order();
        order.setProductId(1L);
        order.setQuantity(2);

        // 1. "Treniramo" lažni InventoryClient da kaže da ima 10 komada na stanju (dovoljno)
        when(inventoryClient.getQuantity(1L)).thenReturn(10);

        // 2. "Treniramo" lažnu bazu
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // 3. Pozivamo pravu metodu
        Order savedOrder = orderService.createOrder(order);

        // 4. Provere
        assertNotNull(savedOrder);
        assertEquals("CONFIRMED", savedOrder.getStatus());
    }
}