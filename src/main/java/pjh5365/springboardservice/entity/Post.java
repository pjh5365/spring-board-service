package pjh5365.springboardservice.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private List<Comment> commentList;  // 댓글 정보

    private String title;   // 제목
    private String content; // 본문
    @Column(name = "created_by", nullable = false, updatable = false)   // updatable 이 없다면 업데이트 할 때 작성자가 null 로 변경됨
    private String createdBy;   // 작성자
}
