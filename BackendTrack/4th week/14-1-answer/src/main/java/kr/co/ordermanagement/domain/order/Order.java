package kr.co.ordermanagement.domain.order;

import kr.co.ordermanagement.domain.product.Product;

import java.util.List;

import static kr.co.ordermanagement.domain.order.State.CANCELED;
import static kr.co.ordermanagement.domain.order.State.CREATED;

public class Order {
    private Long id;
    private List<Product> orderProducts;
    private Integer totalPrice;
    private State state;

    public Order(List<Product> orderProducts) {
        this.orderProducts = orderProducts;
        this.totalPrice = calculateTotalPrice(orderProducts);
        this.state = CREATED;
    }

    private Integer calculateTotalPrice(List<Product> orderProducts) {
        return orderProducts
                .stream()
                .mapToInt(orderProduct -> orderProduct.getPrice() * orderProduct.getAmount())
                // .reduce(0, (a, b) -> a + b);
                // .reduce(0, Integer::sum);
                .sum();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getOrderedProducts() {
        return this.orderProducts;
    }

    public Long getId() {
        return this.id;
    }

    public Integer getTotalPrice() {
        return this.totalPrice;
    }

    public State getState() {
        return this.state;
    }

    public boolean sameId(Long orderId) {
        return this.id.equals(orderId);
    }

    public void changeStateForce(State state) {
        this.state = state;
    }

    public boolean sameState(State state) {
        return this.state.equals(state);
    }

    public void cancel() {
        this.state = CANCELED;
    }

    // 함부로 사용하면 안되니 게터 세테는 필요해지면 추가
}
