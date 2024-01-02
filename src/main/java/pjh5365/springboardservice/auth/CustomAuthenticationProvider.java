// package pjh5365.springboardservice.auth;
//
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Component;
//
//
// @Slf4j
// @Component
// public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//     private final PrincipalDetailService principalDetailService;
//     private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//     @Autowired
//     public CustomAuthenticationProvider(PrincipalDetailService principalDetailService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//         this.principalDetailService = principalDetailService;
//         this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//     }
//
//     // 로그인 인증 실행부분
//     @Override
//     public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//         String username = authentication.getName();
//         String password = authentication.getCredentials().toString();
//
//         UserDetails member = principalDetailService.loadUserByUsername(username);
//         if(bCryptPasswordEncoder.matches(password, member.getPassword())) {
//             return new UsernamePasswordAuthenticationToken(username, password, member.getAuthorities());
//         }
//         else {
//             log.info("인증 실패");
//             throw new UsernameNotFoundException("아이디 혹은 비밀번호가 일치하지 않습니다!");
//         }
//     }
//
//     // 객체가 UsernamePasswordAuthenticationToken 클래스 또는 이 클래스의 하위 클래스로 인스턴스화될 수 있는지를 확인 (동작 여부를 제한)
//     @Override
//     public boolean supports(Class<?> authentication) {
//         return UsernamePasswordAuthenticationToken.class
//                 .isAssignableFrom(authentication);
//     }
// }
