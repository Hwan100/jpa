package com.kh.jpa.entity;

import com.kh.jpa.dto.MemberDto;
import com.kh.jpa.enums.CommonEnums;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 스펙상 필수 + 외부 생성 방지
@Entity
@Builder
@DynamicInsert // insert시에 null이 아닌 필드만 쿼리에 포함, default값 활용
@DynamicUpdate // 변경된 필드만 update문에 포함
public class Member {

    @Id
    @Column(name = "user_id", length = 30)
    private String userId;

    @Column(name = "user_pwd", length = 100, nullable = false)
    private String userPwd;

    @Column(name = "user_name", length = 15, nullable = false)
    private String userName;

    @Column(name = "email", length = 254)
    private String email;

    @Column(name = "gender", length = 1)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;

    @Column(length = 13)
    private String phone;

    @Column(length = 100)
    private String address;

    @Column(name = "enroll_date")
    private LocalDateTime enrollDate;

    @Column(name = "modify_date")
    private LocalDateTime modifyDate;

    @Column(length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    private CommonEnums.Status status;

    public enum Gender {
        M, F
    }

    public void updateMemberInfo(String userName,String email, Gender gender, String phone, String address, Integer age) {
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.age = age;
    }

    //엔티티가 영속성 컨텍스트에 저장되기 전(em.persist())에 실행되는 메서드
    //초기설정을 해두는 용도로 사용
    @PrePersist
    public void prePersist() {
        this.enrollDate = LocalDateTime.now();
        this.modifyDate = LocalDateTime.now();

        if(this.status == null) {
            this.status = CommonEnums.Status.Y;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.modifyDate = LocalDateTime.now();
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", unique = true)
    private Profile profile;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notice> notices = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reply> replies = new ArrayList<>();
}
