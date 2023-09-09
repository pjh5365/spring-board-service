package pjh5365.springboardservice.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtAuthenticationFilter extends GenericFilter {

    private final JwtProvider jwtTokenProvider;

    @Autowired
    public JwtAuthenticationFilter(JwtProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("JwtAuthenticationFilter 의 doFilter 메서드");

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 헤더에서 JWT 가져오기
//        token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        // 토큰이 유효한지 확인
//        if (token != null && jwtTokenProvider.validateToken(token)) {
//            log.info("토큰이 유효한지 확인하는 조건문");
//            // 토큰에서 유저의 정보 가져오기
//            Authentication authentication = jwtTokenProvider.getAuthentication(token);
//            // SecurityContext 에 Authentication 객체를 저장
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            log.info("토큰이 유효합니다. {}" , token);
//        }

        chain.doFilter(request, response);
        log.info("필터의 헤더값 시작 \n");

        // HttpServletResponse로부터 헤더 정보 가져오기
        Map<String, String> responseHeaders = getResponseHeaders(httpResponse);
        String findToken = responseHeaders.get("X-AUTH-TOKEN");
        if(findToken != null) {
            String token = findToken;
            ((HttpServletResponse) response).addHeader("X-AUTH-TOKEN", token);
        }
        log.info("필터의 헤더값 종료 \n");
        System.out.println("Response Headers:");
        responseHeaders.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });
    }
    private Map<String, String> getResponseHeaders(HttpServletResponse response) {
        Collection<String> headerNames = response.getHeaderNames();
        Map<String, String> responseHeaders = new HashMap<>();
        for (String headerName : headerNames) {
            responseHeaders.put(headerName, response.getHeader(headerName));
        }
        return responseHeaders;
    }
}
