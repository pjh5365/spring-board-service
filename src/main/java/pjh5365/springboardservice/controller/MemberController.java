package pjh5365.springboardservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pjh5365.springboardservice.entity.Member;
import pjh5365.springboardservice.service.MemberService;

@Controller
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public String  register(@ModelAttribute Member member) {
        int result = memberService.register(member);

        // 로그인 실패했을때 실패했다는 알림을 보내기 위해 페이지 이동
        if(result == 0) {
            return "registerFail";
        }
        // 로그인 성공 후 메인 페이지로 이동
        else {
            return "redirect:/";
        }
    }
}
