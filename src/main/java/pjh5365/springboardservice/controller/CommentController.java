package pjh5365.springboardservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pjh5365.springboardservice.entity.Comment;
import pjh5365.springboardservice.service.CommentService;

import java.util.List;

@RequestMapping("/api")
@Controller
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    @ResponseBody   // JSON 으로 반환
    public List<Comment> getAllList() {
        List<Comment> commentList = commentService.getAllList();

        return commentList;
    }

    @PostMapping("/comment/{postId}")
    public String saveComment(@PathVariable Long postId, @ModelAttribute Comment comment) {
        comment.setPostId(postId);
        commentService.saveComment(comment);

        return "redirect:/post/" + postId;
    }

    @PatchMapping
    @PutMapping("/comment/{commentId}")
    public void updateComment(@PathVariable Long commentId, @ModelAttribute Comment comment) {
        comment.setId(commentId);
        commentService.saveComment(comment);
    }

    /* TODO
     *   DELETE 형식으로 변경하거나 POST 방식으로 변경하는게 필요함.
     *   자바스크립트에서 메소드 형식 선택의 편의를 위해 우선 GET 으로 이용
     *   DELETE 를 사용할 경우 ResponseEntity 를 이용하여 링크를 전달하여 자바스크립트에서 전달받은 링크를 연결하는 방법 사용 가능할듯?
     *   POST 방식의 경우 링크 자바스크립트에서 전달방법만 정하면 될듯
     *   ---------------------------
     *   보안을 위해 스프링 시큐리티를 설정한 이후 JWT 나 토큰을 사용하여 사용자 정보를 확인한 후 삭제하도록 구현필요 */

    @GetMapping("/comment/{postId}/{commentId}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);

        return "redirect:/post/" + postId;
    }

}
