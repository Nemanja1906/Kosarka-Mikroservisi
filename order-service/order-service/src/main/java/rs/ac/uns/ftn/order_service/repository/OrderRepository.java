package rs.ac.uns.ftn.order_service.repository;

import rs.ac.uns.ftn.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}