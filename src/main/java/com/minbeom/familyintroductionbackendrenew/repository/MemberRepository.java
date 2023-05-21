package com.minbeom.familyintroductionbackendrenew.repository;

import com.minbeom.familyintroductionbackendrenew.member.Member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByName(String name);
    Optional<Member> findById(Long id);
}
