package dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dashboard.entity.Order;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {

}
