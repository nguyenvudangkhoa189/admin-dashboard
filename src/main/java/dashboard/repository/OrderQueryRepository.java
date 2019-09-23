package dashboard.repository;

import dashboard.entity.Order;
import dashboard.service.OrderCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderQueryRepository {

    Page<Order> search(OrderCriteria criteria, Pageable pageable);
}
