package pjh5365.springboardservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pjh5365.springboardservice.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
