package pjh5365.springboardservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pjh5365.springboardservice.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 페이징으로 불러오는데 역순으로 불러오기
    Page<Post> findAllByOrderByIdDesc(Pageable pageable);
}
