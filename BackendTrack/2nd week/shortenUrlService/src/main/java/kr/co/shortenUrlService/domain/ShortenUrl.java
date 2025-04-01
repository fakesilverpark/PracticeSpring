package kr.co.shortenUrlService.domain;

import java.util.Random;

public class ShortenUrl {
    private String originalUrl;
    private String shortenUrlKey;
    private Long redirectCount;

    public ShortenUrl(String originalUrl, String shortenUrlKey) {
        this.originalUrl = originalUrl;
        this.shortenUrlKey = shortenUrlKey;
        this.redirectCount = 0L; // 나중에 지워도 됨
    }

    public static String generateShortenUrlKey() {

        String base56Characters = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";
        Random random = new Random();
        StringBuilder shortenUrlKey = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            char base56Character = base56Characters.charAt(random.nextInt(base56Characters.length()));
            // String builder 만 append
            shortenUrlKey.append(base56Character);
        }

        // String StringBuilder 차이
        // 두개에 다 "abc" 를 넣었디
        // String 이면 더 추가 하고 싶으면 + 사용
        // -> String 은 immutable 하므로 원래꺼 (abc) 는 그대로 두고
        // abcdef 를 새로 만들어서 그거를 가리키게 만든다
        // StringBuilder 는 mutable (객체 생성 이후에도 변경될 수 있음)
        return shortenUrlKey.toString();

        // return "12345";
    }

    public String getShortenUrlKey() {
        return shortenUrlKey;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public Long getRedirectCount() {
        return redirectCount;
    }

    public void increaseRedirectCount() {
        this.redirectCount++;
    }
}
