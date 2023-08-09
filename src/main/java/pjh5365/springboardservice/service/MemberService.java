package pjh5365.springboardservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjh5365.springboardservice.entity.Member;
import pjh5365.springboardservice.repository.MemberRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void register(Member member) {
        if(memberRepository.findByMemberId(member.getMemberId()).isEmpty()) {
            member.setCreatedAt(LocalDateTime.now());
            memberRepository.save(member);
            log.info("회원가입 성공 : {}", member);
        }
        else {
            log.error("이미 존재하는 사용자입니다. {}", member);
        }
    }
}
