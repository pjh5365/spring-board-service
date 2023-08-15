package pjh5365.springboardservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    // 루트 페이지
    @RequestMapping("/")
    public String main() {
        return "index";
    }

    // 게시글 리스트를 보여주기 위해 api 를 호출
    @RequestMapping("/post-list")
    public String postList() {
        return "forward:/api/post-list";
    }

    // 게시글 작성창 열기
    @RequestMapping("/post")
    public String post() {
        return "post";
    }

    // 게시글 수정창 열기
    @RequestMapping("/post-update/{postId}")
    public String update(@PathVariable Long postId) {
        return "forward:/api/post-update/" + postId;
    }

/*    // 게시글 삭제를 위한 이동
    @RequestMapping("/post-delete/{postId}")
    public String delete(@PathVariable Long postId) {
        return "forward:/api/post-delete/" + postId;
    }*/

    // 게시글 단일 조회
    @RequestMapping("/post/{postId}")
    public String openPosting(@PathVariable Long postId) {
        return "forward:/api/post/" + postId;
    }

    // 회원가입 페이지 열기
    @RequestMapping("/register")
    public String register() {
        return "register";
    }
}
