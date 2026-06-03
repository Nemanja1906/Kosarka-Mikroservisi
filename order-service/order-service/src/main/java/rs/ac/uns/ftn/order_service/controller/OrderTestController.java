package rs.ac.uns.ftn.order_service.controller;

import rs.ac.uns.ftn.order_service.client.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderTestController {

    @Autowired
    private ProductClient productClient;

    // Kad pozovemo ovo, order-service će pozvati product-service
    @GetMapping("/test-product/{id}")
    public Map<String, Object> testProductConnection(@PathVariable Long id) {
        return productClient.getProductById(id);
    }
}