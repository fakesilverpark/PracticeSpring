package kr.co.ordermanagement.presentation.controller;

import kr.co.ordermanagement.application.SimpleOrderService;
import kr.co.ordermanagement.presentation.dto.OrderProductRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // JSON 으로 보내는게 당연함
public class OrderRestController {
    private SimpleOrderService simpleOrderService;

    @Autowired
    public OrderRestController(SimpleOrderService simpleOrderService) {
        this.simpleOrderService = simpleOrderService;
    }

    // 상품 주문 API - Post
    @PostMapping("/orders")
    // ResponseEntity<OrderResponseDto> -> OrderResponseDto 를 JSON 형식으로
    // 근데 안써도 RestController 어노테이션을 안써도 자동으로 JSON
    // + 무조건 상태코드가 200 일거 같다면 이렇게 쓰는게 좋음
    public OrderResponseDto createOrder(
            @RequestBody List<OrderProductRequestDto> orderProductRequestDtos
            ){
        OrderResponseDto orderResponseDto = simpleOrderService.createOrder(orderProductRequestDtos);

        return orderResponseDto;
    }
//    public ResponseEntity<OrderResponseDto> createOrder(
//            @RequestBody List<OrderProductRequestDto> orderProductRequestDtos
//            ){
//        OrderResponseDto orderResponseDto = simpleOrderService.createOrder(orderProductRequestDtos);
//
//        return ResponseEntity.ok(orderResponseDto);
//    }

    // 주문 상태 강제 변경 API

    // 주문 번호로 조회 API

    // 주문 상태로 조회 API

    // 주문 취소 API
}
