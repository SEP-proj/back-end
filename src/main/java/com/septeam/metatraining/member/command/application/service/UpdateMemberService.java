package com.septeam.metatraining.member.command.application.service;

import com.septeam.metatraining.member.command.application.dto.UpdateMemberDTO;
import com.septeam.metatraining.member.command.domain.aggregate.entity.Member;
import com.septeam.metatraining.member.command.domain.repository.MemberRepository;
import com.septeam.metatraining.member.command.domain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UpdateMemberService {

    private final MemberRepository memberRepository;
    private final MemberService memberService;


    @Autowired
    public UpdateMemberService(MemberRepository memberRepository, MemberService memberService) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }

    @Transactional
    public boolean updateMember(Long memberId,UpdateMemberDTO updateMemberDTO){
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isPresent()){
            Member updateMember = member.get();
            if(!updateMemberDTO.getProfileImage().isEmpty()){
                updateMember.setProfileImage(updateMemberDTO.getProfileImage());
            }
            if(!updateMemberDTO.getName().isEmpty()){
                updateMember.setName(updateMemberDTO.getName());
            }
            return true;
        }else return false;
    }
}
