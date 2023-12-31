package com.septeam.metatraining.post.command.domain.aggregate.vo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberVO {
    @Column(nullable = false, name = "member_id")
    private Long id;

    @Builder
    public MemberVO(long memeberId){
        this.id=memeberId;
    }
}
