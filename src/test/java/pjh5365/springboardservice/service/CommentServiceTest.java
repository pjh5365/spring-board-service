package pjh5365.springboardservice.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pjh5365.springboardservice.entity.Comment;

@DisplayName("댓글 API 테스트")
@SpringBootTest
class CommentServiceTest {

    private final CommentService commentService;

    @Autowired
    CommentServiceTest(CommentService commentService) {
        this.commentService = commentService;
    }

    @DisplayName("댓글 저장 테스트")
    @Test
    void saveTest() {
        Comment comment = new Comment();
        comment.setContent("댓글");
        comment.setCreatedBy("댓글작성자");

        commentService.saveComment(comment);
    }

    @DisplayName("댓글 수정 테스트")
    @Test
    void updateTest() {
        Comment comment = new Comment();
        comment.setContent("댓글");
        comment.setCreatedBy("댓글작성자");

        commentService.saveComment(comment);
        comment.setContent("수정");
        commentService.saveComment(comment);
    }

    @DisplayName("댓글 삭제 테스트")
    @Test
    void deleteTest() {
        Comment comment = new Comment();
        comment.setContent("댓글");
        comment.setCreatedBy("댓글작성자");

        commentService.saveComment(comment);
        commentService.deleteComment(1L);
    }
}