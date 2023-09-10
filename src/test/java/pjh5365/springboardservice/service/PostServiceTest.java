package pjh5365.springboardservice.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pjh5365.springboardservice.entity.Comment;
import pjh5365.springboardservice.entity.Post;

@DisplayName("게시글 테스트")
@SpringBootTest
class PostServiceTest {

    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    PostServiceTest(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
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

    @DisplayName("게시글 댓글과 함께 불러오기 테스트")
    @Test
    void postAndComments() {
        Post post = new Post();
        post.setTitle("제목");
        post.setContent("내용");
        post.setCreatedBy("작성자");

        postService.savePost(post);

        Comment comment = new Comment();
        comment.setContent("내용");
        comment.setCreatedBy("작성자");
        comment.setPostId(1L);

        commentService.saveComment(comment);

        Comment comment1 = new Comment();
        comment1.setContent("내용1");
        comment1.setCreatedBy("작성자1");
        comment1.setPostId(1L);

        commentService.saveComment(comment1);

        Comment comment2 = new Comment();
        comment2.setContent("내용2");
        comment2.setCreatedBy("작성자2");
        comment2.setPostId(1L);

        commentService.saveComment(comment2);

        postService.getCommentList(1L).forEach(System.out::println);

    }
}
