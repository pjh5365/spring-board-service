package pjh5365.springboardservice.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pjh5365.springboardservice.entity.Member;

import java.time.LocalDateTime;

@DisplayName("회원가입 API 테스트")
@SpringBootTest
class MemberServiceTest {

    private final MemberService memberService;

    @Autowired
    MemberServiceTest(MemberService memberService) {
        this.memberService = memberService;
    }

    @DisplayName("회원 중복 확인 및 회원가입")
    @Test
    void registerTest() {
        Member member = new Member();
        member.setMemberId("testID");
        member.setName("TestName");
//        member.setCreatedAt(LocalDateTime.now());
        member.setEmail("TestEmail");
        member.setPassword("PW");

        memberService.register(member);
        memberService.register(member);
    }
}
