// package pjh5365.springboardservice.auth;
//
// import java.io.IOException;
//
// import javax.servlet.http.Cookie;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
//
// import org.springframework.security.core.Authentication;
// import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
// import org.springframework.stereotype.Component;
//
// import lombok.extern.slf4j.Slf4j;
//
// @Slf4j
// @Component
// public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//     private final JwtProvider jwtProvider;
//
//     public CustomAuthenticationSuccessHandler(JwtProvider jwtProvider) {
//         this.jwtProvider = jwtProvider;
//     }
//
//     @Override
//     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
//         String jwt = jwtProvider.createToken(authentication.getName());
//
//         log.info("토큰 발급 성공 {}", jwt);
//         Cookie tokenCookie = new Cookie("X-AUTH-TOKEN", jwt);
//         response.addCookie(tokenCookie);
//         response.sendRedirect("/"); // 루트 페이지로 이동
//     }
// }
