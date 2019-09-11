package dashboard.dao.repository;

import dashboard.dao.model.OrderModel;
import dashboard.service.OrderCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class OrderSpecification implements Specification<OrderModel> {

    private final OrderCriteria criteria;

    public OrderSpecification(OrderCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<OrderModel> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        addPredicateCountry(root, builder, predicates);
        addPredicateAgent(root, builder, predicates);
        addPredicateShipDate(root, builder, predicates);

        return builder.and(predicates.toArray(new Predicate[]{}));
    }

    private void addPredicateCountry(Root<OrderModel> root, CriteriaBuilder builder, List<Predicate> predicates) {
        if (!StringUtils.isEmpty(criteria.getCountry())) {
            Predicate predicate = builder.equal(root.get("country"), criteria.getCountry());
            predicates.add(predicate);
        }
    }

    private void addPredicateAgent(Root<OrderModel> root, CriteriaBuilder builder, List<Predicate> predicates) {
        if (!StringUtils.isEmpty(criteria.getAgent())) {
            Predicate predicate = builder.like(root.get("companyAgent"), criteria.getAgent() + "%");
            predicates.add(predicate);
        }
    }

    private void addPredicateShipDate(Root<OrderModel> root, CriteriaBuilder builder, List<Predicate> predicates) {
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
