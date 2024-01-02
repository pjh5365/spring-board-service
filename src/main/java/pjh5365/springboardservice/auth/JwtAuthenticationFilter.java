package pjh5365.springboardservice.auth;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtTokenProvider;

	@Autowired
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
			AuthenticationException {
		String username = obtainUsername(request);
		String password = obtainPassword(request);

		System.out.println(username);

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password, null);
		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String username = authResult.getName();

		System.out.println("사용자 정보 출력");
		System.out.println(username);
		String token = jwtTokenProvider.createToken(authResult.getName());

		response.addHeader("Authorization", "Bearer " + token);
		System.out.println("토큰 발급 : " + token);

		Cookie cookie = new Cookie("auth", token);
		response.addCookie(cookie);
		super.successfulAuthentication(request, response, chain, authResult);
		System.out.println("로그인 성공");
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.setStatus(401);
		// super.unsuccessfulAuthentication(request, response, failed);
	}

	/*@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
			IOException,
			ServletException {
		log.info("JwtAuthenticationFilter 의 doFilter 메서드");

		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpServletRequest httpRequest = (HttpServletRequest)request;

		// if (httpRequest.getCookies() != null) {
		// 	Cookie[] cookies = httpRequest.getCookies();
		// 	for (Cookie cookie : cookies) {
		// 		System.out.println(cookie.getName());
		// 		System.out.println(cookie.getValue());
		// 	}
		//
		// 	String token =
		// }

		// 헤더에서 JWT 가져오기
		String token = jwtTokenProvider.resolveToken((HttpServletRequest)request);

		// 토큰이 유효한지 확인
		if (token != null && jwtTokenProvider.validateToken(token)) {
			log.info("토큰이 유효한지 확인하는 조건문");
			// 토큰에서 유저의 정보 가져오기
			Authentication authentication = jwtTokenProvider.getAuthentication(token);
			// SecurityContext 에 Authentication 객체를 저장
			SecurityContextHolder.getContext().setAuthentication(authentication);
			log.info("토큰이 유효합니다. {}", token);
		}
		chain.doFilter(request, response);
	}*/
}
