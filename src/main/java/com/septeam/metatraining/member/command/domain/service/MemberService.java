package com.septeam.metatraining.member.command.domain.service;

import com.septeam.metatraining.member.command.application.dto.CreateMemberDTO;
import com.septeam.metatraining.member.command.application.dto.UpdateMemberDTO;
import com.septeam.metatraining.member.command.domain.aggregate.entity.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    public Member toMember(CreateMemberDTO createMemberDTO){
        return new Member(createMemberDTO.getName(), createMemberDTO.getEmail(), createMemberDTO.getSub(), createMemberDTO.getProfileImage(), createMemberDTO.getPlatform(), createMemberDTO.getRole());
    }
}
