package rs.ac.uns.ftn.inventory_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.inventory_service.model.Inventory;
import rs.ac.uns.ftn.inventory_service.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // POST: Dodaj novi unos u inventar
    @PostMapping
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryService.save(inventory);
    }

    // GET: Provera stanja - ISPRAVLJENO sa eksplicitnim nazivom putanje
    @GetMapping("/{productId}")
    public Integer getQuantity(@PathVariable("productId") Long productId) {
        return inventoryService.getQuantity(productId);
    }

    // PUT: Ažuriranje količine
    @PutMapping("/{productId}/{quantity}")
    public void updateQuantity(@PathVariable("productId") Long productId, @PathVariable("quantity") Integer quantity) {
        inventoryService.updateQuantity(productId, quantity);
    }
}