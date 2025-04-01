package kr.co.shortenUrlService.presentation;

import kr.co.shortenUrlService.application.SimpleShortenUrlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 이후에 등장하는 오토 와이어드에서 진짜를 주입시킬지 진짜 주입시킬지 헷갈리면 이거에 기반해서 동작해!!!
@Import(ShortenUrlRestControllerTest.MockServiceConfig.class)
// 톰캣이 필요함 ! (내장 톰캣이 잇다 -> 스프링을 띄워야함)
// 이거는 내장 톰켓 이용을 위해 스프링을 띄워야함 -> 대신 autowired 를 쓸 수 있음
// 스프링을 불러오면 통합 테스트임 (controller 테스트이다 (좀 독특ㅎ함))
@WebMvcTest(ShortenUrlRestController.class) // 소괄호 안에는 테스트 할 클래스명과 동일하게 작성
class ShortenUrlRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    // 진짜로 생성해서 나중에 필요하면 다시 가짜로 만든다
    private SimpleShortenUrlService simpleShortenUrlService; // 오류 -> 가짜를 선택할지 진짜를 할지

    @Test
    @DisplayName("원래의 URL 로 리다이렉트 되어야 한다")
    void redirectTest() throws Exception {
        // given
        String expectedOriginalUrl = "https://www.google.com"; // 여기 아무거나 들어가도 됨

        // when
        // 체이닝이 되기 직전 점 앞에서 \n 하면 가독성이 늘어남!!!!!!!
        // 컨트롤러를 테스트하고 잇으니 서비스는 무조건 잘된다고 가정하고 코드 작성
        when(simpleShortenUrlService.getOriginalUrlByShortenUrlKey(any()))
                .thenReturn(expectedOriginalUrl);

        // then
        mockMvc.perform(get("/any-key"))
                .andExpect(status().isMovedPermanently())
                .andExpect(header().string("Location", expectedOriginalUrl));
    }

    // 스프링을 통해서 위에서 autowired 를 실제 객체로 시켰었는데
    // 그 불러오는거를 스프링 컨테이더에 가짜 객체도 함ㄲㅔ 등록하는 코드
    static class MockServiceConfig{
        @Bean
        public SimpleShortenUrlService simpleShortenUrlService(){
            return mock(SimpleShortenUrlService.class);
        }
    }
}