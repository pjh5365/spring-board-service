package pjh5365.springboardservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pjh5365.springboardservice.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
