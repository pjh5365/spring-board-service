package pjh5365.springboardservice.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import pjh5365.springboardservice.entity.Member;
import pjh5365.springboardservice.repository.MemberRepository;

public class JwtFilter extends OncePerRequestFilter {

	private final JwtProvider jwtProvider;
	private final MemberRepository memberRepository;

	public JwtFilter(JwtProvider jwtProvider, MemberRepository memberRepository) {
		this.jwtProvider = jwtProvider;
		this.memberRepository = memberRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		// String authorization = request.getHeader("Authorization");	// 값이 정상적으로 전달되지 않아 쿠키로 받기
		if (request.getCookies() != null) {
			for (Cookie c : request.getCookies()) {
				System.out.println(c.getName());
				System.out.println(c.getValue());
			}
		}

		if (request.getCookies() == null) {
			filterChain.doFilter(request, response);
			return;
		}
		// if (authorization == null || !authorization.startsWith("Bearer ")) {
		// 	System.out.println("token is null");
		// 	// response.sendRedirect("/");
		//
		// 	filterChain.doFilter(request, response);
		// 	return;
		// }

		// String token = authorization.split("Bearer ")[1];

		String token = request.getCookies()[0].getValue();

		if (!jwtProvider.validateToken(token)) {
			System.out.println("token is expired");
			filterChain.doFilter(request, response);
		}

		String username = jwtProvider.getUserPk(token);
		// String role = jw

		Member member = memberRepository.findByMemberId(username).orElseThrow();
		member.setRole("ROLE_USER");
		// UserEntity userEntity = new UserEntity();
		// userEntity.setUsername(username);
		// userEntity.setPassword("a");
		// userEntity.setRole(role);

		Cookie cookie = new Cookie("auth", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		PrincipalDetails principalDetails = new PrincipalDetails(member);
		// CustomUserDetails userDetails = new CustomUserDetails(userEntity);

		Authentication authentication =  new UsernamePasswordAuthenticationToken(member, null, principalDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println(SecurityContextHolder.getContext().getAuthentication());
		filterChain.doFilter(request, response);
	}
}
