package pjh5365.springboardservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjh5365.springboardservice.entity.Post;
import pjh5365.springboardservice.repository.PostRepository;

import java.util.List;

@Service
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

    public List<Post> postList() {
        return postRepository.findAll();
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId).get();
    }
}
