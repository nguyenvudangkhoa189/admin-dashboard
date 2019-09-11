package dashboard.dao.repository;

import dashboard.dao.model.OrderModel;
import dashboard.service.OrderCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderQueryRepository {

    Page<OrderModel> search(OrderCriteria criteria, Pageable pageable);
}
