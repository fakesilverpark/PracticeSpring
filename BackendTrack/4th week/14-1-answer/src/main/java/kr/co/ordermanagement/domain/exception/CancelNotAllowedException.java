package kr.co.ordermanagement.domain.exception;

public class CancelNotAllowedException extends RuntimeException {
    public CancelNotAllowedException(String message) {
        super(message);
    }
}
