package pjh5365.springboardservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pjh5365.springboardservice.entity.Comment;
import pjh5365.springboardservice.entity.Post;
import pjh5365.springboardservice.repository.CommentRepository;
import pjh5365.springboardservice.repository.PostRepository;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public void updatePost(Post post) {
        postRepository.save(post);
    }

    public void deletePost(Long postId) {
        commentRepository.deleteAllByPostId(postId);
        postRepository.deleteById(postId);
    }

    public Page<Post> postList(Pageable pageable) {
        return postRepository.findAllByOrderByIdDesc(pageable);
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId).get();
    }

    // 댓글 리스트를 가져오는 메서드 optional 값이기 때문에 optional 에서 값을 빼주는 위의 메서드 findById 사용
    public List<Comment> getCommentList(Long postId) {
        return findById(postId).getCommentList();
    }
}
