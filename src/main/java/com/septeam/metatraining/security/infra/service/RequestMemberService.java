package com.septeam.metatraining.security.infra.service;

import com.septeam.metatraining.member.query.application.dto.FindMemberDTO;
import com.septeam.metatraining.member.query.application.service.FindMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestMemberService implements com.septeam.metatraining.security.command.domain.service.RequestMemberService {
    private final FindMemberService findMemberService;

    @Autowired
    public RequestMemberService(FindMemberService findMemberService) {
        this.findMemberService = findMemberService;
    }

    @Override
    public FindMemberDTO findMemberById(Long memberId) {
        return findMemberService.findMemberById(memberId);
    }
}
