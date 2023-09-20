package com.septeam.metatraining.member.command.domain.aggregate.entity.enumtype;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "손님"),
    MEMBER("ROLE_MEMBER", "일반 사용자"),
    EXPERT("ROLE_EXPERT", "우수 사용자"),
    BAN("ROLE_BAN", "차단 사용자"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}
