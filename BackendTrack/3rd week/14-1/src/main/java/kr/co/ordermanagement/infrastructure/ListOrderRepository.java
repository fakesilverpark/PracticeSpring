package kr.co.ordermanagement.infrastructure;

import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ListOrderRepository implements OrderRepository {
    List<Order> orders = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);

    @Override
    public Order add(Order order){
        // ArrayList 멀티 스레드 환경에서 동시성 문제
        order.setId(sequence.getAndAdd(1L));

        orders.add(order);
        return order;
    }
}
