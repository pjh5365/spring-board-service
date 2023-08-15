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
    @PutMapping("/comment/{postId}")
    public void updateComment(@ModelAttribute Comment comment) {
        commentService.saveComment(comment);
    }

    @DeleteMapping("/comment/{postId}")
    public void deleteComment(@PathVariable Long postId) {
        commentService.deleteComment(postId);
    }

}
