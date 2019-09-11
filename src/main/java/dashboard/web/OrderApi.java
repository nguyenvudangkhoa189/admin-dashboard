package dashboard.web;

import dashboard.dao.model.OrderModel;
import dashboard.dao.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderApi {

    private final OrderRepository orderRepository;

    public OrderApi(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping("/search")
    public Page<OrderModel> search(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
}
