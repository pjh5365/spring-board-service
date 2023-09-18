package pjh5365.springboardservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pjh5365.springboardservice.entity.Comment;
import pjh5365.springboardservice.entity.Post;
import pjh5365.springboardservice.repository.CommentRepository;
import pjh5365.springboardservice.repository.PostRepository;

import java.util.List;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public void updatePost(Post post) {
        postRepository.save(post);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Transactional(readOnly = true)
    public Page<Post> postList(Pageable pageable) {
        return postRepository.findAllByOrderByIdDesc(pageable);
    }

    @Transactional(readOnly = true)
    public Post findById(Long postId) {
        return postRepository.findById(postId).get();
    }

    // 댓글 리스트를 가져오는 메서드 optional 값이기 때문에 optional 에서 값을 빼주는 위의 메서드 findById 사용
    @Transactional(readOnly = true)
    public List<Comment> getCommentList(Long postId) {
        return findById(postId).getCommentList();
    }
}
