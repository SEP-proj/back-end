package com.septeam.metatraining.member.command.application.dto;

import com.septeam.metatraining.member.command.domain.aggregate.entity.enumtype.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class CreateMemberDTO {
    private final String sub;
    private final String name;
    private final Role role;
    private final String profileImage;
    private final String email;
    private final String platform;

    public static class UpdateMemberDTO {
    }
}
