package dashboard.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dashboard.dao.model.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel, Integer> {

}
