package kr.co.ordermanagement.presentation.dto;

import kr.co.ordermanagement.domain.order.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderResponseDto {
    private Long id;
    private List<ProductDto> orderedProducts;
    private Integer totalPrice;
    private String state;

    public OrderResponseDto(Long id, List<ProductDto> orderedProductDtos, Integer totalPrice, String state) {
        this.id = id;
        this.orderedProducts = orderedProductDtos;
        this.totalPrice = totalPrice;
        this.state = state;
    }

    public static OrderResponseDto toDto(Order order) {
        List<ProductDto> orderedProductDtos = order.getOrderedProducts()
                .stream()
                .map(orderedProduct -> ProductDto.toDto(orderedProduct))
                .toList();

        OrderResponseDto orderResponseDto = new OrderResponseDto(
                order.getId(),
                orderedProductDtos,
                order.getTotalPrice(),
                order.getState()
        );

        return orderResponseDto;
    }

    public Long getId() {
        return id;
    }

    public List<ProductDto> getOrderedProducts() {
        return orderedProducts;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public String getState() {
        return state;
    }
}
