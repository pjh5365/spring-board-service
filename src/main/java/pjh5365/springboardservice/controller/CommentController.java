package pjh5365.springboardservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pjh5365.springboardservice.entity.Comment;
import pjh5365.springboardservice.service.CommentService;

@RequestMapping("/api")
@Controller
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public void getAllList() {
        commentService.getAllList();
    }

    @GetMapping("/comments/{postId}")
    public void getListById(@PathVariable Long postId) {
        commentService.getListByPostId(postId);
    }

    @PostMapping("/comments/{postId}")
    public void saveComment(@PathVariable Long postId, @ModelAttribute Comment comment) {
        commentService.saveComment(comment);
    }

    @PatchMapping
    @PutMapping("/comments/{postId}")
    public void updateComment(@ModelAttribute Comment comment) {
        commentService.saveComment(comment);
    }

    @DeleteMapping("/comments/{postId}")
    public void deleteComment(@PathVariable Long postId) {
        commentService.deleteComment(postId);
    }

}
