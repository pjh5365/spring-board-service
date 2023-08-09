package pjh5365.springboardservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pjh5365.springboardservice.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByMemberId(String memberId);
}
