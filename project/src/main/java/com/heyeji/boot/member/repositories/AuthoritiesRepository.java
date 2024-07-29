package com.heyeji.boot.member.repositories;

import com.heyeji.boot.member.entities.Authorities;
import com.heyeji.boot.member.entities.AuthoritiesId;
import com.heyeji.boot.member.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;


public interface AuthoritiesRepository extends JpaRepository<Authorities, AuthoritiesId>, QuerydslPredicateExecutor<Authorities> {

    List<Authorities> findByMember(Member member);
}
