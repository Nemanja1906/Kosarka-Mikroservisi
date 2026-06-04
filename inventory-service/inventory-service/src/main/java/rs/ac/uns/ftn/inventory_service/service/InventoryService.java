package rs.ac.uns.ftn.inventory_service.service;

import rs.ac.uns.ftn.inventory_service.model.Inventory;
import rs.ac.uns.ftn.inventory_service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Integer getQuantity(Long productId) {
        List<Inventory> list = inventoryRepository.findByProductId(productId);
        if (list == null || list.isEmpty()) {
            return 0;
        }
        // Uzimamo prvi na koji naiđemo ako ih ima više
        return list.get(0).getQuantity();
    }

    public void updateQuantity(Long productId, Integer quantity) {
        List<Inventory> list = inventoryRepository.findByProductId(productId);
        if (list != null && !list.isEmpty()) {
            // Ažuriramo prvi zapis, ostali (duplikati) ostaju kako jesu
            Inventory inventory = list.get(0);
            inventory.setQuantity(quantity);
            inventoryRepository.save(inventory);
        }
    }
}