package com.septeam.metatraining.member;

import com.septeam.metatraining.member.command.application.dto.CreateMemberDTO;
import com.septeam.metatraining.member.command.application.service.CreateMemberService;
import com.septeam.metatraining.member.command.domain.aggregate.entity.enumtype.Role;
import com.septeam.metatraining.member.query.application.service.FindMemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@SpringBootTest
@Transactional
public class MemberQueryServiceTest {
    @Autowired
    private CreateMemberService createMemberService;
    @Autowired
    private FindMemberService findMemberService;

    private static Stream<Arguments> getMember() {
        return Stream.of(
                Arguments.of(
                        new CreateMemberDTO(
                                "2211ss",
                                "name2",
                                Role.MEMBER,
                                "email@test.com",
                                "profileImage",
                                "GOOGLE"
                        )
                )
        );

    }

    @DisplayName("find member by id test")
    @ParameterizedTest
    @MethodSource("getMember")
    void findMemberById(CreateMemberDTO createMemberDTO){
        Long memberId = createMemberService.createMember(createMemberDTO).getId();
        System.out.println(findMemberService.findMemberById(memberId));
        Assertions.assertNotNull( findMemberService.findMemberById(memberId));

    }

    @DisplayName("find member by sub test")
    @ParameterizedTest
    @MethodSource("getMember")
    void findMemberBySub(CreateMemberDTO createMemberDTO){
        String sub = createMemberService.createMember(createMemberDTO).getSub();
        System.out.println(findMemberService.findMemberBySub(sub));
        Assertions.assertNotNull( findMemberService.findMemberBySub(sub));

    }


    @DisplayName("find member by email test")
    @ParameterizedTest
    @MethodSource("getMember")
    void findMemberByEmail(CreateMemberDTO createMemberDTO){
        String email = createMemberService.createMember(createMemberDTO).getEmail();
        System.out.println(findMemberService.findMemberByEmail(email));
        Assertions.assertNotNull( findMemberService.findMemberByEmail(email));

    }
}
