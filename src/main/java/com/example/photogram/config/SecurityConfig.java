package com.example.photogram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((authorizeRequests) -> // 인증, 인가 설정
                        authorizeRequests
                                .requestMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**").authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin((formLogin) -> // 폼 기반 로그인 설정
                        formLogin
                                .loginPage("/auth/signin") // get
                                .loginProcessingUrl("/auth/signin") // post -> 스프링 시큐리티가 로그인 프로세스 진행
                                .defaultSuccessUrl("/")
                )
                .csrf((csrfConfig) -> // csrf 비활성화
                        csrfConfig.disable()
                )
                .build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests((authorizeRequests) -> // 3. 인증, 인가 설정
//                        authorizeRequests
//                                .requestMatchers("/login", "/signup", "/user").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .formLogin((formLogin) -> // 4. 폼 기반 로그인 설정
//                        formLogin
//                                .loginPage("/login")
//                                .defaultSuccessUrl("/articles")
//                )
//                .logout((logoutConfig) -> // 5. 로그아웃 설정
//                        logoutConfig
//                                .logoutSuccessUrl("/login")
//                                .invalidateHttpSession(true) // 세션 무효화
//                )
//                .csrf((csrfConfig) -> // 6.csrf 비활성화
//                        csrfConfig.disable()
//                )
//                .build();
//    }

//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web) -> web.ignoring()
////                .requestMatchers(toH2Console())
//                .requestMatchers("/static/**");
//    }


}
