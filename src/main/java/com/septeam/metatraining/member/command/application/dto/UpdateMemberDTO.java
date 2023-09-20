package com.septeam.metatraining.member.command.application.dto;

import com.septeam.metatraining.member.command.domain.aggregate.entity.enumtype.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMemberDTO {
    private String sub;
    private String name;
    private Role role;
    private String profileImage;
    private String platform;

    public UpdateMemberDTO(Role role) {
        this.role = role;
    }

    public UpdateMemberDTO(String name, String profileImage) {
        this.name = name;
        this.profileImage = profileImage;
    }

    public UpdateMemberDTO(String sub, String name, Role role, String profileImage, String platform) {
        this.sub = sub;
        this.name = name;
        this.role = role;
        this.profileImage = profileImage;
        this.platform = platform;
    }
}
