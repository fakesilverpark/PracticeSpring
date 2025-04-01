package kr.co.ordermanagement.application;

import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.OrderRepository;
import kr.co.ordermanagement.domain.product.Product;
import kr.co.ordermanagement.domain.product.ProductRepository;
import kr.co.ordermanagement.presentation.dto.OrderProductRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleOrderService {

    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    @Autowired
    public SimpleOrderService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public OrderResponseDto createOrder(List<OrderProductRequestDto> orderProductRequestDtos){
        // OrderProductRequestDto 의 상품 번호에 해당하는 주문이 있는지 해당 수량이 이쓴지 확인
        // 없다면 -> 예외 발생, 있으면 -> 해당 프로덕트 List 를 생성
        // stream 쓰면 체이닝 가능 !, 근데 forEach 만 할거면 그냥 그것만 쓰고 체이닝 할거면 스트림!!
        List<Product> orderProducts = makeOrderProducts(orderProductRequestDtos);

        // 해당 상품의 상품 재고를 차감
        // 롤백이 안되니 다 확인하고 차감
        decreaseProductsAmount(orderProducts);

        // 차감된 정보를 바탕으로 Order
        Order order = new Order(orderProducts);
        orderRepository.add(order);

        // 생성된 주문을 OrderResponseDto 변환해서 컨트롤러에 반환
        OrderResponseDto orderResponseDto = OrderResponseDto.toDto(order);
        return orderResponseDto;
    }

    private void decreaseProductsAmount(List<Product> orderProducts) {
        orderProducts.forEach(orderProduct -> {
            Long productId = orderProduct.getId();
            Product product = productRepository.findById(productId);

            // Setter 를 사용하는건 안좋은 코드ㅠㅠㅠㅠ
            // 핵심 비지니스 로직은 Product 에서 구현
//            Integer orderedAmount = orderProduct.getAmount();
//            Integer stockAmount = product.getAmount();

            // product.setAmount(stockAmount - orderedAmount);

            Integer orderedAmount = orderProduct.getAmount();
            product.decreaseAmount(orderedAmount);

            productRepository.update(product);
        });
    }

    private List<Product> makeOrderProducts(List<OrderProductRequestDto> orderProductRequestDtos) {
        return orderProductRequestDtos
                .stream()
                .map(orderProductRequestDto -> {
                    Long productId = orderProductRequestDto.getId();
                    // 못찾으면 Repo 에서 오류 발생
                    Product product = productRepository.findById(productId);

                    Integer orderedAmount = orderProductRequestDto.getAmount();
                    product.checkEnoughAmount(orderedAmount);

                    return new Product(
                            productId,
                            product.getName(),
                            product.getPrice(),
                            //product.getAmount() // 이거 아님
                            orderedAmount
                    );
                }).toList();
    }
}
