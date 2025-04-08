package kr.co.ordermanagement.domain.product;

import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private Integer price;
    private Integer amount;

    public Product(Long id, String name, Integer price, Integer amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public Boolean sameId(Long id) {
        return this.id.equals(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    // this. 붙이면 가독성이 더 높아져요~~!!!!!@!@!!@!@!!
    public void checkEnoughAmount(Integer orderedAmount) {
        if (orderedAmount > this.amount) {
            throw new RuntimeException(id + "번 상품의 수량이 부족해요ㅠㅠㅠㅠ");
        }
    }

    public void decreaseAmount(Integer orderedAmount) {
        this.amount -= orderedAmount;
    }
}
