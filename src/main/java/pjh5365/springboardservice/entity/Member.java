package pjh5365.springboardservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberId;    // 사용자 아이디
    private String name;    // 사용자 이름
    private String email;   // 사용자 이메일
    private String password;    // 사용자 비밀번호
}
