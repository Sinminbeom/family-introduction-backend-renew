package com.minbeom.familyintroductionbackendrenew.repository;

import com.minbeom.familyintroductionbackendrenew.AppConfig;
import com.minbeom.familyintroductionbackendrenew.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MysqlMemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Test
    void save() {
        Member member = new Member();
        member.setName("sinminbeom");

        memberRepository.save(member);
        Member member1 = memberRepository.findById(member.getId()).get();

        Assertions.assertThat(member.getName()).isEqualTo(member1.getName());
    }

}