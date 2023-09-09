package pjh5365.springboardservice.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    private final String secretKey = "qlalfqjsgh";  // 토큰의 시크릿키
    private final long tokenValidTime = 30 * 60 * 1000L;    // 유효기간 30분
    private final String issuer = "pjh5365";    // 토큰 발행자

    private final PrincipalDetailService principalDetailService;

    @Autowired
    public JwtProvider(PrincipalDetailService principalDetailService) {
        this.principalDetailService = principalDetailService;
    }

    // 토큰 생성 메서드
    public String createToken(String  userPk) {
        Date now = new Date();

        return Jwts.builder()
                .setSubject(userPk) // 토큰 제목
                .setIssuedAt(now)   // 토큰의 발행시간
                .setIssuer(issuer)  // 토큰 발행자
                .setExpiration(new Date(now.getTime() + tokenValidTime))    // 토큰 유효기간 설정
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 토큰에 사용할 알고리즘 설정
                .compact();
    }

    // 토큰에서 정보 가져오기
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // 토큰 정보 확인
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = principalDetailService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Request 의 Header 에서 token 값 가져오기 "X-AUTH-TOKEN" : "TOKEN 값'
    public String resolveToken(HttpServletRequest request) {
        String token = null;
        Cookie cookie = WebUtils.getCookie(request, "X-AUTH-TOKEN");
        log.info("쿠키 {}", cookie);
        if(cookie != null) token = cookie.getValue();
        return token;
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
