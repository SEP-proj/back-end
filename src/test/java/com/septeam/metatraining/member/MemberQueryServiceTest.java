package com.septeam.metatraining.member;

import com.septeam.metatraining.member.command.application.dto.CreateMemberDTO;
import com.septeam.metatraining.member.command.application.service.CreateMemberService;
import com.septeam.metatraining.member.command.domain.aggregate.entity.enumtype.Role;
import com.septeam.metatraining.member.query.application.FindMemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
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

    @DisplayName("find member test")
    @ParameterizedTest
    @MethodSource("getMember")
    void findMember(CreateMemberDTO createMemberDTO){
        Long memberId = createMemberService.createMember(createMemberDTO).getId();
        System.out.println(findMemberService.findMemberById(memberId));
        Assertions.assertNotNull( findMemberService.findMemberById(memberId));

    }
}
