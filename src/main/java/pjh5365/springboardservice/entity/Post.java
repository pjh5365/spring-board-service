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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // Fetch타입이 EAGER(즉시로딩)이면 게시글과 댓글리스트를 한번에 긁어오므로 SELECT 쿼리 1번 없으면 @OneToMany의 기본값인 LAZY(지연로딩)이므로 게시글에 들어가서 댓글리스트를 불러오므로 쿼리가 2번 날라간다.
    // 해당 게시글이 삭제되면 모든 영속성 전이로 해당 게시글에 달린 댓글도 함께 삭제
    @JoinColumn(name = "post_id")
    // 해당 어노테이션으로 지정해주지 않으면 중간에 테이블이 하나 더 생성된다.
    private List<Comment> commentList;  // 댓글 정보

    private String title;   // 제목
    private String content; // 본문
    @Column(name = "created_by", nullable = false, updatable = false)   // updatable 이 없다면 업데이트 할 때 작성자가 null 로 변경됨
    private String createdBy;   // 작성자
}
