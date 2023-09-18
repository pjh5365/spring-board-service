package pjh5365.springboardservice.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pjh5365.springboardservice.entity.Comment;
import pjh5365.springboardservice.entity.Member;
import pjh5365.springboardservice.entity.Post;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@DisplayName("JPA 연결 테스트")
class JpaRepositoryTest {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    JpaRepositoryTest(MemberRepository memberRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }


    @DisplayName("insert, select 테스트")
    @Test
    void insertAndSelectTest() {
        Member member = new Member();
        member.setMemberId("testId");
        member.setName("test");
        member.setEmail("test@email.com");
        member.setPassword("1234");
//        member.setCreatedAt(LocalDateTime.now());

        Post post = new Post();
        post.setTitle("제목");
        post.setContent("내용");
//        post.setCreatedAt(LocalDateTime.now());
        post.setCreatedBy("test");

        Comment comment = new Comment();
        comment.setContent("댓글 내용");
//        comment.setCreatedAt(LocalDateTime.now());
//        comment.setPostId(1L);
        comment.setCreatedBy("user");

        memberRepository.save(member);
        postRepository.save(post);
        commentRepository.save(comment);

        assertThat(memberRepository.findById(1L)).isEqualTo(Optional.of(member));
        assertThat(postRepository.findById(1L)).isEqualTo(Optional.of(post));
        assertThat(commentRepository.findById(1L)).isEqualTo(Optional.of(comment));
        System.out.println(memberRepository.findAll());
        System.out.println(postRepository.findAll());
        System.out.println(commentRepository.findAll());
    }

    @DisplayName("update 테스트")
    @Test
    void updateTest() {
        Member member = new Member();
        member.setMemberId("testId");
        member.setName("test");
        member.setEmail("test@email.com");
        member.setPassword("1234");
//        member.setCreatedAt(LocalDateTime.now());

        memberRepository.save(member);
        Optional<Member> member1 = memberRepository.findById(1L);
        Member memberUpdate = member1.get();
        memberUpdate.setMemberId("updateID");
        memberUpdate.setName("updateName");
        memberUpdate.setEmail("update@email.com");
        memberUpdate.setPassword("4321");

        memberRepository.save(memberUpdate);

        assertThat(memberRepository.findById(1L)).isEqualTo(Optional.of(memberUpdate));
        System.out.println(memberRepository.findAll());
    }

    @DisplayName("delete 테스트")
    @Test
    void deleteTest() {
        Member member = new Member();
        member.setMemberId("testId");
        member.setName("test");
        member.setEmail("test@email.com");
        member.setPassword("1234");
//        member.setCreatedAt(LocalDateTime.now());

        memberRepository.save(member);

        memberRepository.deleteById(1L);

        assertThat(memberRepository.findById(1L)).isEmpty();
        System.out.println(memberRepository.findAll());
    }
}