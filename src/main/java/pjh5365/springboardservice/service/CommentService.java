package pjh5365.springboardservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pjh5365.springboardservice.entity.Comment;
import pjh5365.springboardservice.repository.CommentRepository;

import java.util.List;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional(readOnly = true)
    public List<Comment> getAllList() {
        return commentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Comment> findAllByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
