package dashboard.web;

import dashboard.dao.model.OrderModel;
import dashboard.dao.repository.OrderQueryRepository;
import dashboard.dao.repository.OrderRepository;
import dashboard.service.OrderCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderApi {

    private static final Logger logger = LoggerFactory.getLogger(OrderApi.class);

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    public OrderApi(OrderRepository orderRepository, OrderQueryRepository orderQueryRepository) {
        this.orderRepository = orderRepository;
        this.orderQueryRepository = orderQueryRepository;
    }

    @PostMapping("/search")
    public Page<OrderModel> search(OrderCriteriaForm criteria, Pageable pageable) {
        logger.debug("criteria: {}", criteria);
        logger.debug("pagable: {}", pageable);
        return orderQueryRepository.search(criteria.toCriteria(), pageable);
    }

    static class OrderCriteriaForm {

        private String country;
        private String agent;
        private Long shipDateFrom;
        private Long shipDateTo;
        private String status;
        private String type;

        public OrderCriteria toCriteria() {
            OrderCriteria criteria = new OrderCriteria();
            criteria.setCountry(country);
            criteria.setAgent(agent);
            criteria.setShipDateFrom(Optional.ofNullable(shipDateFrom).map(Date::new).orElse(null));
            criteria.setShipDateTo(Optional.ofNullable(shipDateTo).map(Date::new).orElse(null));
            criteria.setStatus(status);
            criteria.setType(type);
            return criteria;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getAgent() {
            return agent;
        }

        public void setAgent(String agent) {
            this.agent = agent;
        }

        public Long getShipDateFrom() {
            return shipDateFrom;
        }

        public void setShipDateFrom(Long shipDateFrom) {
            this.shipDateFrom = shipDateFrom;
        }

        public Long getShipDateTo() {
            return shipDateTo;
        }

        public void setShipDateTo(Long shipDateTo) {
            this.shipDateTo = shipDateTo;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "OrderCriteriaForm{" +
                    "country='" + country + '\'' +
                    ", agent='" + agent + '\'' +
                    ", shipDateFrom=" + shipDateFrom +
                    ", shipDateTo=" + shipDateTo +
                    ", status='" + status + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }
}
