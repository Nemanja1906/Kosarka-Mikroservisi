package rs.ac.uns.ftn.inventory_service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.uns.ftn.inventory_service.model.Inventory;
import rs.ac.uns.ftn.inventory_service.repository.InventoryRepository;
import rs.ac.uns.ftn.inventory_service.service.InventoryService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    void testGetQuantity() {
        Inventory inv = new Inventory();
        inv.setQuantity(10);

        // Ispravka: servis koristi listu, pa moramo vratiti listu
        when(inventoryRepository.findByProductId(1L)).thenReturn(List.of(inv));

        Integer qty = inventoryService.getQuantity(1L);

        // Proveravamo da li vraća 10 kako smo i namestili
        assertEquals(10, qty);
    }
}