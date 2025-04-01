package kr.co.shortenUrlService.application;

import kr.co.shortenUrlService.domain.LackOfShortenUrlKeyException;
import kr.co.shortenUrlService.domain.NotFoundShortenUrlException;
import kr.co.shortenUrlService.domain.ShortenUrl;
import kr.co.shortenUrlService.domain.ShortenUrlRepository;
import kr.co.shortenUrlService.presentation.ShortenUrlCreateRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// 단위테스트 -> 목 객체를 만드어야함
@ExtendWith(MockitoExtension.class)
class SimpleShortenUrlServiceUnitTest {

    @Mock
    // 가짜 레포지포리 만듦
    private ShortenUrlRepository shortenUrlRepository;

    @InjectMocks
    // 목을 주입받는 실제 서비스 객체
    private SimpleShortenUrlService simpleShortenUrlService;

    @Test
    @DisplayName("단축 URL 이 계속 중복되면 LackOfShortenUrlKeyException 예외가 발생해야 함")
    void throwLackOfShortenUrlKeyExceptionTest(){
        // given
        // 요청하는게 주어져야함
        // 예외를 발생시키는 것이 주목적이니까 굳이 새로 안만듦
        ShortenUrlCreateRequestDto shortenUrlCreateRequestDto = new ShortenUrlCreateRequestDto(null);

        // when: 목 객체의 수행 흐름을 적어줘야 함
        when(shortenUrlRepository.findShortenUrlByShortenUrlKey(any()))
                .thenReturn(new ShortenUrl(null, null));

        // then
        // exception 이 발생하면 참
        Assertions.assertThrows(LackOfShortenUrlKeyException.class, () -> {
            simpleShortenUrlService.generateShortenUrl(shortenUrlCreateRequestDto);
        });
    }

    @Test
    @DisplayName("단축 URL 을 레포지토리에서 찾을 수 없다면 NotFoundShortenUrlException이 발생해야함")
    void throwNotFoundShortenUrlExceptionTest(){
        // given
        String shortenUrlKey = "shortenUrlKey";

        // when
        when(shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey)).thenReturn(null);

        // then
        // 실행 결과 NotFoundShortenUrlException이 발생하는지 assertThrows()를 통해 검증
        assertThrows(NotFoundShortenUrlException.class, () -> {
            simpleShortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrlKey);
        });
    ;}
}