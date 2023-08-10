package pjh5365.springboardservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pjh5365.springboardservice.entity.Post;
import pjh5365.springboardservice.service.PostService;

@RequestMapping("/api")
@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post-list")
    public void getList() {
        postService.postList();
    }

    @PostMapping("/posts")
    public void posting(@ModelAttribute Post post) {
        postService.savePost(post);
    }

    @GetMapping("/posts/{postId}")
    public void findById(@PathVariable Long postId) {
        postService.findById(postId);
    }

    @PostMapping
    @PutMapping("/posts/{postId}")
    public void updatePost(@PathVariable Long postId, @ModelAttribute Post post) {
        postService.savePost(post);
    }

    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}
