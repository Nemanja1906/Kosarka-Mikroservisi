package rs.ac.uns.ftn.product_service.repository;

import rs.ac.uns.ftn.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}