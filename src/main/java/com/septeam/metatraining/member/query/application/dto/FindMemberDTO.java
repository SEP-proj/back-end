package com.septeam.metatraining.member.query.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FindMemberDTO {
    private long id;

    private String name;

    private String sub;

    private String profileImage;

    private String platform;

    private String role;

    private String email;
}
