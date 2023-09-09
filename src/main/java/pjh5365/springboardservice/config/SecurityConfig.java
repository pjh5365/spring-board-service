package pjh5365.springboardservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pjh5365.springboardservice.auth.CustomAuthenticationProvider;
import pjh5365.springboardservice.auth.CustomAuthenticationSuccessHandler;
import pjh5365.springboardservice.auth.JwtAuthenticationFilter;
import pjh5365.springboardservice.auth.JwtProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtProvider jwtProvider;
    private final CustomAuthenticationProvider authenticationProvider;
    private final CustomAuthenticationSuccessHandler successHandler;


    @Autowired
    public SecurityConfig(JwtProvider jwtProvider, CustomAuthenticationProvider authenticationProvider, CustomAuthenticationSuccessHandler successHandler) {
        this.jwtProvider = jwtProvider;
        this.authenticationProvider = authenticationProvider;
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();  // csrf 끄기
        http.authorizeRequests()    // 인가 요청이 오면
                .antMatchers("/api/register", "/api/login").permitAll()   // 회원가입 및 로그인 api 는 누구나 사용가능하게 설정 (이 설정이 권한인증설정 보다 앞에 있어야 정상적으로 작동함)
                .antMatchers("/api/**").authenticated() // api 는 권한 인증 받고 사용하게 설정
                .anyRequest().permitAll()   // 나머지 모든 요청은 누구나 허용
                .and()
                .formLogin()    // 로그인 폼은
                .loginPage("/login")    // 해당 url 을 사용
                .loginProcessingUrl("/api/login")   // 스프링 시큐리티가 해당 주소로 넘어오는 로그인을 가로채서 로그인한다. 즉 따로 서비스 만들 필요 없이 사용
                .failureUrl("/login/error")   // 로그인 실패시 나타낼 주소
                .successHandler(successHandler) // 로그인 성공 핸들러를 사용하여 헤더에 JWT 토큰 값을 추가함
                .and()
                .logout()   // 로그아웃은
                .logoutUrl("/logout")   // 해당 url 로 처리하며
                .logoutSuccessUrl("/") // 로그아웃 성공하면 루트페이지로 이동
                .and()
                .authenticationProvider(authenticationProvider) // 커스텀된 AuthenticationProvider 사용
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class); // JwtAuthenticationFilter 를 UsernamePasswordAuthenticationFilter 전에 사용

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);    // 토큰을 사용하므로 세션이 불필요 따라서 세션을 STATELESS 설정

        return http.build();
    }
}
