package pjh5365.springboardservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pjh5365.springboardservice.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostId(Long postId);

    /* TODO
    *   트랜잭션 어노테이션이 없으면 에러 발생하던데 스프링 JPA 에 대한 공부 필요
    *   연관관계 생각해서 미리 설정해놓으면 트랜잭션 필요 없을듯? */
    @Transactional
    void deleteAllByPostId(Long postId);
}
