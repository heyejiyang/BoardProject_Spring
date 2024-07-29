package com.heyeji.boot.member.repositories;

import com.heyeji.boot.member.entities.Member;
import com.heyeji.boot.member.entities.QMember;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {

    @EntityGraph(attributePaths = "authorities") //바로 조회 할 엔티티 명시, 즉시 로딩
    Optional<Member> findByEmail(String email);

    default boolean exists(String email){
        QMember member = QMember.member;

        return exists(member.email.eq(email));
    }
}
