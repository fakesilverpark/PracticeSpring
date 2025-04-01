package kr.co.shortenUrlService.application;

import kr.co.shortenUrlService.domain.LackOfShortenUrlKeyException;
import kr.co.shortenUrlService.domain.NotFoundShortenUrlException;
import kr.co.shortenUrlService.domain.ShortenUrl;
import kr.co.shortenUrlService.domain.ShortenUrlRepository;
import kr.co.shortenUrlService.presentation.ShortenUrlCreateRequestDto;
import kr.co.shortenUrlService.presentation.ShortenUrlCreateResponseDto;
import kr.co.shortenUrlService.presentation.ShortenUrlInformationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleShortenUrlService {

    private final ShortenUrlRepository shortenUrlRepository;

    @Autowired
    public SimpleShortenUrlService(ShortenUrlRepository shortenUrlRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
    }

    // 예외
    // 1. 유효하지 않은 키
    // 2. 동일한 key
    public ShortenUrlCreateResponseDto generateShortenUrl(
            ShortenUrlCreateRequestDto shortenUrlCreateRequestDto
    ){
        // 할 일 네가지
        // 1. 원래 URL 과 단축 URL 키 생성
        String originalUrl = shortenUrlCreateRequestDto.getOriginalUrl();
        String shortenUrlKey = getUniqueShortenUrlKey2();

        // 2. 원래 URL 과 단축 URL 키를 통해 ShortenUrl 도메인 객체 생성
        ShortenUrl shortenUrl = new ShortenUrl(originalUrl, shortenUrlKey);

        // 3. 생성된 ShortenUrl 을 레포를 통해 저장
        shortenUrlRepository.saveShortenUrl(shortenUrl);

        // 4. ShortenUrl 을 ShortenUrlCreateResponseDto 로 변환해 최종 반환
        ShortenUrlCreateResponseDto shortenCreateResponseDto = new ShortenUrlCreateResponseDto(shortenUrl);
        return shortenCreateResponseDto;
    }

    public ShortenUrlInformationDto getShortenUrlInformationByShortenUrlKey(String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);
        ShortenUrlInformationDto shortenUrlInformationDto = new ShortenUrlInformationDto(shortenUrl);
        return shortenUrlInformationDto;
    }

    public String getOriginalUrlByShortenUrlKey(String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);

        if (shortenUrl == null){
            throw new NotFoundShortenUrlException();
        }

        shortenUrl.increaseRedirectCount();

        shortenUrlRepository.saveShortenUrl(shortenUrl);
        return shortenUrl.getOriginalUrl();
    }

    private String getUniqueShortenUrlKey1(){

        String shortenUrlKey = ShortenUrl.generateShortenUrlKey();

        int count = 0;
        // upper snake case
        final int MAX_RETRY_COUNT = 5;
        while (count != MAX_RETRY_COUNT) {
            if (!shortenUrlRepository.isExistShortenKey(shortenUrlKey)){
                break;
            }
            if (count == 4){
                throw new LackOfShortenUrlKeyException();
            }
            count++;
            shortenUrlKey = ShortenUrl.generateShortenUrlKey();
        }

        return shortenUrlKey;
    }

    private String getUniqueShortenUrlKey2() {
        final int MAX_RETRY_COUNT = 5;
        int count = 0;

        while(count++ < MAX_RETRY_COUNT){
            String shortenUrlKey = ShortenUrl.generateShortenUrlKey();
            ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);

            if(shortenUrl == null){
                return shortenUrlKey;
            }
        }

        throw new LackOfShortenUrlKeyException();
    }
}
