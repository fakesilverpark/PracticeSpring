package kr.co.ordermanagement.domain.order;

import kr.co.ordermanagement.domain.product.Product;

import java.util.List;

public class Order {
    private Long id;
    private List<Product> orderProducts;
    private Integer totalPrice;
    private String state;

    public Order(List<Product> orderProducts) {
        this.orderProducts = orderProducts;
        this.totalPrice = calculateTotalPrice(orderProducts);
        this.state = "CREATED";
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
        return orderProducts;
    }

    public Long getId() {
        return id;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public String getState() {
        return state;
    }

    // 함부로 사용하면 안되니 게터 세테는 필요해지면 추가
}
