package com.septeam.metatraining.member.query.application.service;

import com.septeam.metatraining.member.query.application.dto.FindMemberDTO;
import com.septeam.metatraining.member.query.domain.repository.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindMemberService {
    private final MemberMapper memberMapper;


    @Autowired
    public FindMemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public FindMemberDTO findMemberById(Long memberId){
        return memberMapper.findById(memberId);
    }
    public FindMemberDTO findMemberBySub(String sub){return memberMapper.findBySub(sub);}
    public FindMemberDTO findMemberByEmail(String email){return memberMapper.findByEmail(email);}
}
