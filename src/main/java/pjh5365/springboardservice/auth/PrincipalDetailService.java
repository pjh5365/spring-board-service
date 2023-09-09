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
                // 핸들러를 사용하지 않아 에러메시지를 확인할 수 는 없음
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자 [" + username +  "] 의 정보를 찾을 수 없습니다. 아이디 혹은 비밀번호를 확인해주세요."));
        return new PrincipalDetails(principal);
    }
}
