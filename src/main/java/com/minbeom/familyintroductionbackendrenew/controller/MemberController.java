package com.minbeom.familyintroductionbackendrenew.controller;

import com.minbeom.familyintroductionbackendrenew.AppConfig;
import com.minbeom.familyintroductionbackendrenew.member.Member;
import com.minbeom.familyintroductionbackendrenew.repository.MemberRepository;
import com.minbeom.familyintroductionbackendrenew.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members/new")
    @ResponseBody
    public String create() {

        return "OK";
    }

    @GetMapping("/minbeom")
    @ResponseBody
    public String minbeom() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        Member member = new Member();
        member.setName("minbeom");

        memberRepository.save(member);
//        Member member1 = memberRepository.findById(member.getId());
        System.out.println("id = " + member.getId());
//        Assertions.assertThat(member).isEqualTo(member1);
        return "minbeom";
    }
}
