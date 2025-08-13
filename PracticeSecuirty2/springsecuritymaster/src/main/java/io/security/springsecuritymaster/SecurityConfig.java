package io.security.springsecuritymaster;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //http 통신에 대한 인가 정책 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/anonymous").hasRole("GUEST")
                        .requestMatchers("/anonymousContext", "/authentication").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
//                        .loginPage("/loginPage") // 이렇게 지정하면 스프링 시큐리티가 제공하는 로그인 화면이 안 나옴
                        .loginProcessingUrl("/loginProc")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/failed")
                        .usernameParameter("usernId")
                        .passwordParameter("passwd")
/*
                        아래의 핸들러가 있다면 위에 defaultSuccessUrl, failureUrl 보다 아래 핸들러가 우선시

                        .successHandler((request, response, authentication) -> {
                            System.out.println("authentication : " + authentication);
                            response.sendRedirect("/home");
                        })
                        .failureHandler((request, response, exception) -> {
                            System.out.println("exception : " + exception);
                            response.sendRedirect("/login");
                        })
*/
                                .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe
//                        .alwaysRemember(true)
                        .tokenValiditySeconds(30)
                        .userDetailsService(userDetailsService())
                        .rememberMeParameter("remember")
                        .rememberMeCookieName("remember")
                        .key("security")
                )
                .anonymous(anonymous -> anonymous
                        .principal("guest")
                        .authorities("ROLE_GUEST")
                )
        ;
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
