package com.septeam.metatraining.member.command.domain.aggregate.entity;

import com.septeam.metatraining.member.command.domain.aggregate.entity.enumtype.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "MEMBER_TB")
@Entity
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(nullable = false)
    private String sub;

    @Column(length = 300, name = "profile_image", nullable = false)
    private String profileImage;

    @Column(nullable = false)
    private String platform;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;



    public Member(String name, String email, String sub, String profileImage, String platform, Role role) {
        this.name = name;
        this.email = email;
        this.sub = sub;
        this.profileImage = profileImage;
        this.platform = platform;
        this.role = role;
        this.createdDate = LocalDateTime.now();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
