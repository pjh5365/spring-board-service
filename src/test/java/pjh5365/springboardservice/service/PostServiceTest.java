package pjh5365.springboardservice.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pjh5365.springboardservice.entity.Post;

@DisplayName("게시글 테스트")
@SpringBootTest
class PostServiceTest {

    private final PostService postService;

    @Autowired
    PostServiceTest(PostService postService) {
        this.postService = postService;
    }

    @DisplayName("게시글 작성 테스트")
    @Test
    void savePost() {
        Post post = new Post();
        post.setTitle("제목");
        post.setContent("내용");
        post.setCreatedBy("작성자");

        postService.savePost(post);

    }

    @DisplayName("게시글 업데이트 테스트")
    @Test
    void updatePost() {
        Post post = new Post();
        post.setTitle("제목");
        post.setContent("내용");
        post.setCreatedBy("작성자");

        postService.savePost(post);

        post.setTitle("수정");
        post.setContent("수정내용");

        postService.updatePost(post);
    }

    @DisplayName("게시글 삭제 테스트")
    @Test
    void deletePost() {
        Post post = new Post();
        post.setTitle("제목");
        post.setContent("내용");
        post.setCreatedBy("작성자");

        postService.savePost(post);

        postService.deletePost(1L);
        postService.savePost(post);
    }
}