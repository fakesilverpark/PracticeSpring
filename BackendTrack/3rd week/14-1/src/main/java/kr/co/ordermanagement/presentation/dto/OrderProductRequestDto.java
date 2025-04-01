package kr.co.ordermanagement.presentation.dto;

public class OrderProductRequestDto {

    private Long id; // id 에 해당하는 자료형은 관례적으로 Long!!@
    private Integer amount;

    public Long getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }
}
