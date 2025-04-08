package kr.co.ordermanagement.domain.order;

import java.util.List;

// implementation : 임플리멘테이션
public interface OrderRepository {
    Order add(Order order);

    Order findById(Long orderId);

    List<Order> findByState(State state);
}
