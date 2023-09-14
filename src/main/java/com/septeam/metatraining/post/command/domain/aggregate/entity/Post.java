package com.septeam.metatraining.post.command.domain.aggregate.entity;

import com.septeam.metatraining.post.command.domain.aggregate.entity.enumType.CategoryEnum;
import com.septeam.metatraining.post.command.domain.aggregate.vo.MemberVO;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "POST_TB")
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title; // 글 제목

    @Enumerated(EnumType.STRING)
    @Column
    private CategoryEnum category; // 글 카테고리

    @Embedded
    private MemberVO memberId;

    @Column
    private String introduction; // 서론

    @Column
    private String body; // 본론

    @Column
    private String conclusion; // 결론

    @Column(columnDefinition = "MEDIUMTEXT")
    private String content; // 글 전체 내용

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate; // 글 생성 날짜

    @Column
    private boolean published; // 글 공개 여부

    public Post(String title, CategoryEnum category, MemberVO memberId) {
        this.title = title;
        this.category = category;
        this.memberId = memberId;
        this.createdDate = LocalDateTime.now();

    }

    public Post(CategoryEnum category, MemberVO memberId) {
        this.category = category;
        this.memberId = memberId;
    }

    public Post(String title, CategoryEnum category, MemberVO memberId, String introduction, String body, String conclusion, String content, boolean published) {
        this.title = title;
        this.category = category;
        this.memberId = memberId;
        this.introduction = introduction;
        this.body = body;
        this.conclusion = conclusion;
        this.content = content;
        this.createdDate = LocalDateTime.now();
        this.published = published;
    }

    public Post(Long id, String title, CategoryEnum category, MemberVO memberId, String introduction, String body, String conclusion, String content) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.memberId = memberId;
        this.introduction = introduction;
        this.body = body;
        this.conclusion = conclusion;
        this.content = content;
        this.createdDate = LocalDateTime.now();
    }

    public Post(Long id, String title, CategoryEnum category, MemberVO memberId, String introduction, String body, String conclusion, String content, boolean published) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.memberId = memberId;
        this.introduction = introduction;
        this.body = body;
        this.conclusion = conclusion;
        this.content = content;
        this.createdDate =  LocalDateTime.now();
        this.published = published;
    }

    public Post() {

    }

    public Long getId() {
        return id;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }


    // test용 toString method
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", introduction='" + introduction + '\'' +
                ", body='" + body + '\'' +
                ", conclusion='" + conclusion + '\'' +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", published=" + published +
                '}';
    }

    public void setContent(String content) {
        this.content = content;
    }
}
