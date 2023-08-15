package pjh5365.springboardservice.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pjh5365.springboardservice.entity.Member;
import pjh5365.springboardservice.repository.MemberRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Autowired
    public PrincipalDetailService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member principal = memberRepository.findByMemberId(username)
                .orElseThrow(() -> {
                    // 로그인 실패 핸들러가 없어서 예외를 던져도 콘솔에 표시가 안나는듯
                   return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다." + username);
                });
        return new PrincipalDetails(principal);
    }
}
