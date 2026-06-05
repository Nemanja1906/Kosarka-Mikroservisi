package rs.ac.uns.ftn.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryClient {

    // Ovde dodajemo /api/inventory/ direktno u putanju
    // Ovo će naterati Feign da pozove: http://INVENTORY-SERVICE/api/inventory/{productId}
    @GetMapping("/api/inventory/{productId}")
    Integer getQuantity(@PathVariable("productId") Long productId);
}