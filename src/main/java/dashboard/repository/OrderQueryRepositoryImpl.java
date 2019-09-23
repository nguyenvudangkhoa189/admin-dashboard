package dashboard.repository;

import dashboard.entity.Order;
import dashboard.service.OrderCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
class OrderQueryRepositoryImpl implements OrderQueryRepository {

    private final OrderRepository orderRepository;

    public OrderQueryRepositoryImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<Order> search(OrderCriteria criteria, Pageable pageable) {
        return orderRepository.findAll(new OrderSpecification(criteria), pageable);
    }

    /**
     *
     */
    static class OrderSpecification implements Specification<Order> {

        private final OrderCriteria criteria;

        OrderSpecification(OrderCriteria criteria) {
            this.criteria = criteria;
        }

        @Override
        public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
            List<Predicate> predicates = new ArrayList<>();

            addPredicateCountry(root, builder, predicates);
            addPredicateAgent(root, builder, predicates);
            addPredicateShipDate(root, builder, predicates);

            return builder.and(predicates.toArray(new Predicate[]{}));
        }

        private void addPredicateCountry(Root<Order> root, CriteriaBuilder builder, List<Predicate> predicates) {
            if (StringUtils.hasText(criteria.getCountry())) {
                Predicate predicate = builder.equal(root.get("country"), criteria.getCountry());
                predicates.add(predicate);
            }
        }

        private void addPredicateAgent(Root<Order> root, CriteriaBuilder builder, List<Predicate> predicates) {
            if (StringUtils.hasText(criteria.getAgent())) {
                Predicate predicate = builder.like(root.get("companyAgent"), criteria.getAgent() + "%");
                predicates.add(predicate);
            }
        }

        private void addPredicateShipDate(Root<Order> root, CriteriaBuilder builder, List<Predicate> predicates) {
            Date shipDateFrom = criteria.getShipDateFrom();
            Date shipDateTo = criteria.getShipDateTo();
            if (shipDateFrom != null || shipDateTo != null) {
                Predicate predicate;
                Path<Date> shipDate = root.get("shipDate");
                if (shipDateFrom != null && shipDateTo != null) {
                    predicate = builder.between(shipDate, shipDateFrom, shipDateTo);
                } else if (shipDateFrom != null) {
                    predicate = builder.greaterThanOrEqualTo(shipDate, shipDateFrom);
                } else {
                    predicate = builder.lessThanOrEqualTo(shipDate, shipDateTo);
                }
                predicates.add(predicate);
            }
        }

    }
}
