package kr.co.shortenUrlService.presentation;

import jakarta.validation.Valid;
import kr.co.shortenUrlService.application.SimpleShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController // JSON 으로 주고 받아서
// 컨트롤러는 서비스를 의존한다 (컨트롤러는 서비스없이 못함)
public class ShortenUrlRestController {

    // 예전 방식
    /*
    @RequestMapping(value = "/shortenUrl", method = RequestMethod.POST)
    public ResponseEntity<?> createShortenUrl(){
        return ResponseEntity.ok().body(null);
    }

    @RequestMapping(value = "/{shortenUrlKey}", method = RequestMethod.GET)
    public ResponseEntity<?> redirectShortenUrl(){
        return ResponseEntity.ok().body(null);
    }

    @RequestMapping(value = "/shortenUrl/{shortenUrlKey}", method = RequestMethod.GET)
    public ResponseEntity<?> getShortenUrlInformation(){
        return ResponseEntity.ok().body(null);
    }
     */

    private final SimpleShortenUrlService simpleShortenUrlService;

    @Autowired
    public ShortenUrlRestController(SimpleShortenUrlService simpleShortenUrlService) {
        this.simpleShortenUrlService = simpleShortenUrlService;
    }

    // ShortenUrl 만들기
    @PostMapping("/shortenUrl")
    public ResponseEntity<ShortenUrlCreateResponseDto> createShortenUrl(
            @Valid @RequestBody ShortenUrlCreateRequestDto shortenUrlCreateRequestDto
            ){
        ShortenUrlCreateResponseDto shortenUrlCreateResponseDto = simpleShortenUrlService.generateShortenUrl(shortenUrlCreateRequestDto);
        return ResponseEntity.ok(shortenUrlCreateResponseDto);
    }

    // ShortenUrl 써서 원래 링크에 접속
    // redirectCount 는 부수적으로 증가하는 것이므로 patch 아님
    @GetMapping("/{shortenUrlKey}")
    public ResponseEntity<?> redirectShortenUrl(
            @PathVariable String shortenUrlKey
            ){
        String originalUrl = simpleShortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrlKey);

        // HTTP header 값에 넣기 위해 타입캐스팅 해준거임
        URI redirectUri = URI.create(originalUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        // 이 메서드 쓰려구
        httpHeaders.setLocation(redirectUri);

        return new ResponseEntity<>(httpHeaders,HttpStatus.MOVED_PERMANENTLY);
    }

    // ShortenUrl 에 대한 정보 Get
    @GetMapping(value = "/shortenUrl/{shortenUrlKey}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShortenUrlInformationDto> getShortenUrlInformation(
            @PathVariable String shortenUrlKey
            ){
        ShortenUrlInformationDto shortenUrlInformationDto =
                simpleShortenUrlService.getShortenUrlInformationByShortenUrlKey(shortenUrlKey);
        return ResponseEntity.ok().body(shortenUrlInformationDto);
    }
}
