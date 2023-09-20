package com.septeam.metatraining.security.command.application.service;

import com.septeam.metatraining.member.command.application.dto.CreateMemberDTO;
import com.septeam.metatraining.member.command.application.dto.UpdateMemberDTO;
import com.septeam.metatraining.member.command.application.service.CreateMemberService;
import com.septeam.metatraining.member.command.application.service.UpdateMemberService;
import com.septeam.metatraining.member.command.domain.aggregate.entity.Member;
import com.septeam.metatraining.member.command.domain.aggregate.entity.enumtype.Role;
import com.septeam.metatraining.member.query.application.dto.FindMemberDTO;
import com.septeam.metatraining.member.query.application.service.FindMemberService;
import com.septeam.metatraining.security.Principal.UserPrincipal;
import com.septeam.metatraining.security.command.domain.provider.OAuth2UserInfo;
import com.septeam.metatraining.security.command.domain.provider.OAuth2UserInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private  final FindMemberService findMemberService;
    private  final CreateMemberService createMemberService;
    private final UpdateMemberService updateMemberService;

    @Autowired
    public CustomOAuth2UserService(FindMemberService findMemberService, CreateMemberService createMemberService, UpdateMemberService updateMemberService) {
        this.findMemberService = findMemberService;
        this.createMemberService = createMemberService;
        this.updateMemberService = updateMemberService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("oAuth2User = " + oAuth2User);


        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 소셜 정보 가져옴
        System.out.println("registrationId = " + registrationId);

        OAuth2UserInfo attributes = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, oAuth2User.getAttributes());

        System.out.println("attributes = " + attributes.getAttributes());
        UserPrincipal socialMember =  saveOrUpdate(attributes, registrationId);
        return socialMember;

    }

    private UserPrincipal saveOrUpdate(OAuth2UserInfo attributes, String provider) {
        FindMemberDTO member = findMemberService.findMemberBySub(attributes.getId());
        UserPrincipal oauthMember;
        if (member == null) {
            CreateMemberDTO createMemberDTO = new CreateMemberDTO(attributes.getId(), attributes.getName(), Role.MEMBER, attributes.getImageUrl(), attributes.getEmail(), provider.toUpperCase());
            Member newMember = createMemberService.createMember(createMemberDTO);
            oauthMember = UserPrincipal.create(newMember, attributes.getAttributes());
        } else {
            UpdateMemberDTO updateMemberDTO = new UpdateMemberDTO(attributes.getImageUrl(), attributes.getName());
            boolean updateMemberResult = updateMemberService.updateMember(member.getId(), updateMemberDTO);
            oauthMember = UserPrincipal.create(member, attributes.getAttributes());
        }
        return oauthMember;
    }
}
