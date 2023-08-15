package pjh5365.springboardservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;    // 게시글 id
    private String content; // 본문
    private LocalDateTime createdAt;    // 작성일자
    private String createdBy;   // 작성자
    private String modifiedAt;  // 수정일자
}
