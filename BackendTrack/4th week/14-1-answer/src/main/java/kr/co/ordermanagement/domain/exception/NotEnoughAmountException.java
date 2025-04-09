package kr.co.ordermanagement.domain.exception;

public class NotEnoughAmountException extends RuntimeException {
    // super() -> 부모클래스 생성자를 호출
    public NotEnoughAmountException(String message) {
        super(message);
    }
}
