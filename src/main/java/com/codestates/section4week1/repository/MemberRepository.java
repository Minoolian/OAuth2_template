package com.codestates.section4week1.repository;

import com.codestates.section4week1.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);

    Member findByUserId(String userId);
}
