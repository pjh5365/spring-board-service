package pjh5365.springboardservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pjh5365.springboardservice.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String memberId);
    Optional<Member> findByEmail(String email);

}
