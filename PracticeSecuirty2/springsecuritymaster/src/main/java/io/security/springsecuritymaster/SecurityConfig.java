package io.security.springsecuritymaster;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated()) //http 통신에 대한 인가 정책 설정
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
//    설정 파일에서 유저 추가하는 방식과 컨피크 파일에서 유저 추가하는 방식 중 컨피크 방식이 우선으로 적용됨
    public UserDetailsService userDetailsService() { // UserDetailsService, User, InMemoryUserDetailsManager 다 가능
        UserDetails user = User.withUsername("eunbb")
                .password("{noop}1234")
                .roles("USER")
                .build();
/*
        유저 여러 명도 가능

        UserDetails user2 = User.withUsername("eunbb")
                .password("{noop}1234")
                .roles("USER")
                .build();

        UserDetails user3 = User.withUsername("eunbb")
                .password("{noop}1234")
                .roles("USER")
                .build();
*/

        return new InMemoryUserDetailsManager(user);
//        return new InMemoryUserDetailsManager(user2. user3);
    }
}
