package dashboard.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dashboard.dao.model.OrderModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<OrderModel, Integer>, JpaSpecificationExecutor<OrderModel> {

}
