package pjh5365.springboardservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pjh5365.springboardservice.entity.Member;
import pjh5365.springboardservice.repository.MemberRepository;

@Slf4j
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public int register(Member member) {
        // 이메일과 아이디가 하나만 존재할 때 회원가입 성공
        if(memberRepository.findByMemberId(member.getMemberId()).isEmpty() & memberRepository.findByEmail(member.getEmail()).isEmpty()) {
            String rawPassword = member.getPassword();
            String encPassword = bCryptPasswordEncoder.encode(rawPassword);
            member.setPassword(encPassword);    // 패스워드 암호화
            member.setRole("ROLE_USER");

            memberRepository.save(member);
            log.info("회원가입 성공 : {}", member);
            return 1;
        }
        // 이미 존재하는 아이디, 이메일이라면 회원가입 실패
        else {
            log.error("이미 존재하는 사용자입니다. {}", member);
            return 0;
        }
    }
}
