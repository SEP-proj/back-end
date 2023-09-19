package com.septeam.metatraining.member;

import com.septeam.metatraining.member.command.application.dto.CreateMemberDTO;
import com.septeam.metatraining.member.command.application.service.CreateMemberService;
import com.septeam.metatraining.member.command.domain.aggregate.entity.enumtype.Role;
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
public class MemberCommandServiceTest {
    @Autowired
    private CreateMemberService createMemberService;


    private static Stream<Arguments> getMember() {
        return Stream.of(
                Arguments.of(
                        new CreateMemberDTO(
                                "1122ss",
                                "name1",
                                Role.MEMBER,
                                "email@test.com",
                                "profileImage",
                                "GOOGLE"
                        )
                ),
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


    @DisplayName("멤버 생성 테스트")
    @ParameterizedTest
    @MethodSource("getMember")

    void createMember(CreateMemberDTO createMemberDTO) {
        Assertions.assertDoesNotThrow(()->createMemberService.createMember(createMemberDTO));
    }
}
