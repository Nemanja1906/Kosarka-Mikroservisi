package rs.ac.uns.ftn.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE") // Ime servisa sa Eureke
public interface InventoryClient {

    @GetMapping("/api/inventory/{productId}")
    Integer getQuantity(@PathVariable("productId") Long productId);
}