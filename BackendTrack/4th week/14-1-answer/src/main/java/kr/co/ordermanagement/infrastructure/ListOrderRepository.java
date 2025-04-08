package kr.co.ordermanagement.infrastructure;

import kr.co.ordermanagement.domain.exception.EntityNotFoundException;
import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.OrderRepository;
import kr.co.ordermanagement.domain.order.State;
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

    @Override
    public Order findById(Long orderId) {
        return orders.stream()
                // 스트림에서 쓰는거 맵아니면 필터~~
                // 필터는 4명중에 몇명을 골라내는거
                // 맵은 4명은 그대로 있는데 얘네의 뭔가 속성을 변경
                // 아이디가 다른건 걸러내야 하므로 필터 쓰기!!
                // 필터 안에는 람다식을 적는다
                // stream chaining method 안에는 그냥 람다식을 적는다고 생각하기
                // .filter(order -> order.getId() == orderId) // order.getId() == orderId

                // equals: Long class 에서 제공하는 method
                // primitive - int, double
                // Integer, Double - 랩퍼 클래스 -> 내장 메서드 사용 가능 (특히 equals!! 가독성이 증가증가)
                //.filter(order -> order.getId().equals(orderId))

                // Order 라는 클래스에서 제공하는 메서드로 빼자
                // 자바빈 규약 - 도메인의 get, set method 를 가급적 드러내지 않는 것이 좋음
                // 캡슐화 encapsulation
                // 도메인 메서드 방식으로 처리
                // Tell, Not Ask 처리 -> 어떻게 처리하는지 물어볼 필요가 없다
                .filter(order -> order.sameId(orderId))

                .findFirst()
                // .orElse(null);
                .orElseThrow(() -> new EntityNotFoundException("Order를 찾지 못했습니다."));
    }

    @Override
    public List<Order> findByState(State state) {
        return orders.stream()
                .filter(order -> order.sameState(state)) // tell, don't ask
                .toList();
    }
}
