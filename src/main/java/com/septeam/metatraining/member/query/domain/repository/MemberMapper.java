package com.septeam.metatraining.member.query.domain.repository;

import com.septeam.metatraining.member.query.application.dto.FindMemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    FindMemberDTO findById(Long id);

    FindMemberDTO findBySub(String uid);
//    FindMemberDTO findByAccessToken(String accessToken);
    FindMemberDTO findByEmail(String email);

}
