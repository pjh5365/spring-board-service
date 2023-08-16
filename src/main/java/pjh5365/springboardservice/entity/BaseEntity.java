package pjh5365.springboardservice.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter // 없으면 값을 불러오지 못함
@MappedSuperclass   // JPA Entity 클래스들이 BaseEntity 를 상속할 때 createdAt, modifiedAt 도 컬럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class)  // Auditing 기능을 포함
public abstract class BaseEntity {

    @Column(name = "created_at", nullable = false, updatable = false)   // updatable 이 없다면 업데이트 할 때 생성일자가 null 로 변경됨
    @CreatedDate    // 생성일자 자동 설정
    private String createdAt;

    @LastModifiedDate   // 수정일자 자동 설정
    private String modifiedAt;

    // 날짜 포멧팅 해주기
    @PrePersist // 해당 엔티티를 저장하기 전에 실행
    public void onPrePersist(){
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        this.modifiedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
}
