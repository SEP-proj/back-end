package com.septeam.metatraining.security.command.domain.service;

import com.septeam.metatraining.member.query.application.dto.FindMemberDTO;


public interface RequestMemberService {
    FindMemberDTO findMemberById(Long memberId);
}
