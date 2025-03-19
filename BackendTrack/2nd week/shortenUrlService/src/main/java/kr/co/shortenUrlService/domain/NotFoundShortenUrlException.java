package kr.co.shortenUrlService.domain;

public class NotFoundShortenUrlException extends RuntimeException {
  public NotFoundShortenUrlException(String message) {
    super(message);
  }
}
