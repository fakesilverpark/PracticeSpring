package kr.co.shortenUrlService.presentation;

import kr.co.shortenUrlService.domain.LackOfShortenUrlKeyException;
import kr.co.shortenUrlService.domain.NotFoundShortenUrlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 코드가 너무 길어지면 엔터 쳐주는게 관례!!
    @ExceptionHandler(NotFoundShortenUrlException.class)
    public ResponseEntity<?> handleNotFoundShortenUrlException(
            NotFoundShortenUrlException e
    ) {
        return new ResponseEntity<>("Shorten URL Not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LackOfShortenUrlKeyException.class)
    public ResponseEntity<?> handleLackOfShortenUrlKeyException(
            LackOfShortenUrlKeyException e
    ){
        return new ResponseEntity<>("You are so LUCKY!!", HttpStatus.NOT_FOUND);
    }
}
