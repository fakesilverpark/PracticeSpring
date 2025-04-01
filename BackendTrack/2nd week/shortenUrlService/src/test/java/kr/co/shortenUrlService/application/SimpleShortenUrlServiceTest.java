package kr.co.shortenUrlService.application;

import kr.co.shortenUrlService.presentation.ShortenUrlCreateRequestDto;
import kr.co.shortenUrlService.presentation.ShortenUrlCreateResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// 테스트 만드는 단축키: cmd + shift + t

// 통합 테스트 진행시 스프링의 힘을 빌려야하므로
// 아래의 어노테이션이 필요함!!!
// 단위테스트 일때는 안붙임
@SpringBootTest
class SimpleShortenUrlServiceTest {

    // 스프링 컨테이너로부터 실제 서비스 객체를 불러와 연결시키니느 코드
    // 아래의 서비스에 @Service 어노테이션이 붙어있어서 스프링에 등록되어 있어서 가능
    @Autowired
    private SimpleShortenUrlService simpleShortenUrlService;

    @Test
    // 설정해야 뜸
    @DisplayName("URL 을 단축한 후 단축된 URL 키로 조회하면 원래 URL 이 조회되어야 함")
    void shortenUrlAddTest(){
        // 테스트 코드 작성 단계
        // 1. given: ~가 주어졌을 때
        // 2. when: 주어진 걸로 ~를 했을 때
        // 3. then: 기대하는 결과가 잘 나왔니? (나왔으면 통과)

        // 1. given
        String expectedOriginalUrl = "https://www.google.com";
        // 생성자를 dto 가서 안만들고 그냥 new 를 붙이면 익명 이너클래스를 만들겠다는 것임
        // 근데 여기서는 그렇게 안할거다
        ShortenUrlCreateRequestDto shortenUrlCreateRequestDto = new ShortenUrlCreateRequestDto(expectedOriginalUrl);

        // 2. when
        ShortenUrlCreateResponseDto shortenUrlCreateResponseDto = simpleShortenUrlService.generateShortenUrl(shortenUrlCreateRequestDto);
        String shortenUrlKey = shortenUrlCreateResponseDto.getShortenUrlKey();
        String originalUrl = simpleShortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrlKey);

        // 3. then
        // expectedOriginalUrl 과 originalUrl 이 같아야함
        // 체이닝 가능, 가독성도 더 뛰어감, 제약조건도 걸려있어서 안정성도 더 높은
        assertThat(originalUrl).isEqualTo(expectedOriginalUrl); //assertJ - 이거 제일 많이 씀!!!!!!!!!
        //expected, actual 체이닝이 지원안함 (메서드를 계속 추가하는 것)
        // assertEquals(expectedOriginalUrl, originalUrl); //junit
        // 어디에 기대값을 넣고 어디에 실제값을 넣어야하는지 개발자마다 달라서 혼란 + 가독성이 떨어짐
        // 심지어 반대로 적어도 오류가 안남@!!!
        // assertTrue(originalUrl.equals(expectedOriginalUrl)); //junit
    }

}