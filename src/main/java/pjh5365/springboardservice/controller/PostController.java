package pjh5365.springboardservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pjh5365.springboardservice.entity.Comment;
import pjh5365.springboardservice.entity.Post;
import pjh5365.springboardservice.service.CommentService;
import pjh5365.springboardservice.service.PostService;

import java.util.List;

@RequestMapping("/api")
@Controller
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/post-list")
    public String getList(Model model) {
        List<Post> postList = postService.postList();
        model.addAttribute("postList", postList);

        return "postList";
    }

    /* TODO
    *   줄바꿈 적용하려면 replace 로 치환 잘 해서 저장하고 꺼내올 때도 치환하면 될듯
    *   댓글도 동일하게 */

    @PostMapping("/post")
    public String posting(@ModelAttribute Post post) {
        // 스프링 시큐리티에서 사용자 정보 불러오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String username = userDetails.getUsername();
        // 불러온 사용자 정보 입력
        post.setCreatedBy(username);

        postService.savePost(post);

        return "redirect:/post-list";
    }

    @GetMapping("/post/{postId}")
    public String findById(@PathVariable Long postId, Model model) {
        // 스프링 시큐리티에서 사용자 정보 불러오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String username = userDetails.getUsername();

        Post post = postService.findById(postId);
        // 댓글 리스트를 함께 불러오기
        List<Comment> commentList = commentService.findAllByPostId(postId);
        model.addAttribute("post", post);
        // 현재 로그인한 사용자 이름 넘기기
        model.addAttribute("username", username);
        model.addAttribute("commentList", commentList);
        return "read";
    }

    @GetMapping("/post-update/{postId}")
    public String findByIdForUpdate(@PathVariable Long postId, Model model) {
        Post post = postService.findById(postId);
        model.addAttribute("post", post);

        return "update";
    }


    /* TODO
    *   보안을 위해 스프링 시큐리티를 설정한 이후 JWT 나 토큰을 사용하여 사용자 정보를 확인한 후 수정하도록 구현필요 */

    @PostMapping("/post/{postId}")
    public String updatePost(@PathVariable Long postId, @ModelAttribute Post post) {
        post.setId(postId); // 저장을 위해 id 값 동일하게 설정
        postService.updatePost(post);

        return "redirect:/post-list";
    }

    /* TODO
    *   DELETE 형식으로 변경하거나 POST 방식으로 변경하는게 필요함.
    *   자바스크립트에서 메소드 형식 선택의 편의를 위해 우선 GET 으로 이용
    *   DELETE 를 사용할 경우 ResponseEntity 를 이용하여 링크를 전달하여 자바스크립트에서 전달받은 링크를 연결하는 방법 사용 가능할듯?
    *   POST 방식의 경우 링크 자바스크립트에서 전달방법만 정하면 될듯
    *   ---------------------------
    *   보안을 위해 스프링 시큐리티를 설정한 이후 JWT 나 토큰을 사용하여 사용자 정보를 확인한 후 삭제하도록 구현필요 */

    @GetMapping("/post-delete/{postId}")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        commentService.deleteCommentsByPostId(postId);
        return "redirect:/post-list";
    }
}
