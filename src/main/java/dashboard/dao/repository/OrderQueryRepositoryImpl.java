package dashboard.dao.repository;

import dashboard.dao.model.OrderModel;
import dashboard.service.OrderCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
class OrderQueryRepositoryImpl implements OrderQueryRepository {

    private final OrderRepository orderRepository;

    public OrderQueryRepositoryImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<OrderModel> search(OrderCriteria criteria, Pageable pageable) {
        return orderRepository.findAll(new OrderSpecification(criteria), pageable);
    }
}
