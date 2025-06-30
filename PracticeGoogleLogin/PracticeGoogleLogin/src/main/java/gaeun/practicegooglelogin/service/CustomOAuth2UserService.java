package gaeun.practicegooglelogin.service;

import gaeun.practicegooglelogin.dto.CustomOAuth2User;
import gaeun.practicegooglelogin.dto.GoogleResponse;
import gaeun.practicegooglelogin.dto.OAuth2Response;
import gaeun.practicegooglelogin.entity.UserEntity;
import gaeun.practicegooglelogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public String getDomain(String email){
        return email.split("@")[1];
    }

    public String getRole(String email){
        String domain = getDomain(email);
        if (domain.equals("bssm.hs.kr")) {
            System.out.println("bsm");
            return "ROLE_BSM";
        }
        System.out.println("nobsm");
        return "ROLE_NOBSM";
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        String role = "";
        String email = "";
        if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
            email = oAuth2Response.getEmail();
            role = getRole(email);
        }else {
            return null;
        }

        String name = oAuth2Response.getName();
        UserEntity existData = userRepository.findByEmail(email);
        if (existData == null) {
            UserEntity userEntity = new UserEntity(role, name, name, email);
            userRepository.save(userEntity);
        }

        System.out.println("이미 가입한 유저");
        existData.updateRecentDate();

        return new CustomOAuth2User(oAuth2Response, role);
    }
}
