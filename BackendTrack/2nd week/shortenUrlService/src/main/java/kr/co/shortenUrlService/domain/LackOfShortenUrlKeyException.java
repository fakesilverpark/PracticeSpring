package kr.co.shortenUrlService.domain;

public class LackOfShortenUrlKeyException extends RuntimeException {
  public LackOfShortenUrlKeyException(String message) {
    super(message);
  }
}
