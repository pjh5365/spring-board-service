package pjh5365.springboardservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pjh5365.springboardservice.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
