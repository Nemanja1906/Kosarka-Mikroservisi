package rs.ac.uns.ftn.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.inventory_service.model.Inventory;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    // Ova metoda nam treba da bi našli zalihe za tačno određeni proizvod
    Optional<Inventory> findByProductId(Long productId);
}