package com.septeam.metatraining.member.command.application.service;

import com.septeam.metatraining.member.command.application.dto.CreateMemberDTO;
import com.septeam.metatraining.member.command.domain.aggregate.entity.Member;
import com.septeam.metatraining.member.command.domain.repository.MemberRepository;
import com.septeam.metatraining.member.command.domain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateMemberService {

    private final MemberRepository memberRepository;
    private final MemberService memberService;


    @Autowired
    public CreateMemberService(MemberRepository memberRepository, MemberService memberService) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }


    public Member createMember(CreateMemberDTO createMemberDTO){
        Member newMember = memberService.toMember(createMemberDTO);
        return memberRepository.save(newMember);
    }
}
