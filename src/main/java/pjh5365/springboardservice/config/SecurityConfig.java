package pjh5365.springboardservice.config;

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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();  // csrf 끄기
        http.authorizeRequests()    // 인가 요청이 오면
                .antMatchers("/api/register").permitAll()   // 회원가입 api 는 누구나 사용가능하게 설정 (이 설정이 앞에 있어야 정상적으로 작동함)
                .antMatchers("/api/**").authenticated() // api 는 권한 인증 받고 사용하게 설정
                .anyRequest().permitAll()   // 나머지 모든 요청은 누구나 허용
                .and()
                .formLogin()    // 로그인 폼은
                .loginPage("/login")    // 해당 url 을 사용
                .loginProcessingUrl("/api/login")   // 스프링 시큐리티가 해당 주소로 넘어오는 로그인을 가로채서 로그인한다. 즉 따로 서비스 만들 필요 없이 사용
                .failureUrl("/login/error")   // 로그인 실패시 나타낼 주소
                .defaultSuccessUrl("/");    // 정상일때? 권한인증받았을때 기본값인듯?

        return http.build();
    }

    // 패스워드 암호화
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
