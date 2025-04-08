package kr.co.ordermanagement.presentation.controller;

import kr.co.ordermanagement.application.SimpleOrderService;
import kr.co.ordermanagement.domain.order.State;
import kr.co.ordermanagement.presentation.dto.ChangeOrderStateRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderProductRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PatchMapping("/orders/{orderId}")
    // 도메인 전체를 바꾸면 - update~~(), 도메인 일부를 바꾸면 - change~~()
    public OrderResponseDto changeOrderById(
            @PathVariable Long orderId,
            @RequestBody ChangeOrderStateRequestDto changeOrderStateRequestDto
    ) {
        // cmd + option + n -> 변수 인라인화
        return simpleOrderService.changeState(orderId, changeOrderStateRequestDto);
    }

    // 주문 번호로 조회 API
    @GetMapping("/orders/{orderId}")
    public OrderResponseDto getOrderById(@PathVariable Long orderId){
        // ByOrderId -> explicit

        // find 다음에 목적어를 생략한 이유 -> OrderService 이므로 당연히 order 에 관한것임
        // 그냥 변수명을 dto -> implicit
        // OrderResponseDto orderResponseDto = simpleOrderService.findById(orderId);
        // return orderResponseDto;
        return simpleOrderService.findById(orderId);
    }

    // 주문 상태로 조회 API
    @GetMapping("/orders")
    public List<OrderResponseDto> getOrdersByState(@RequestParam State state){
        return simpleOrderService.findByState(state);
    }

    // 주문 취소 API
    @PatchMapping("/orders/{orderId}/cancel")
    public OrderResponseDto cancelOrderById(@PathVariable Long orderId){
        return simpleOrderService.cancelById(orderId);
    }
}
