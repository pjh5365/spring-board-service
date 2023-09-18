package pjh5365.springboardservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_id")
    private Long postId;    // 게시글 id
    private String content; // 본문
    private String createdBy;   // 작성자
}
