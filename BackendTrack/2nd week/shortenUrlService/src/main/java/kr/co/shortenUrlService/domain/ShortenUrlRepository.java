package kr.co.shortenUrlService.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface ShortenUrlRepository {
    void saveShortenUrl(ShortenUrl shortenUrl);

    ShortenUrl findShortenUrlByShortenUrlKey(String shortenUrlKey);

    boolean isExistShortenKey(String shortenUrlKey);
}
