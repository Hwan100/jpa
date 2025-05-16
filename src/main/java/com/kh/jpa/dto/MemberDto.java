package com.kh.jpa.dto;

import com.kh.jpa.entity.Member;
import com.kh.jpa.enums.CommonEnums;
import lombok.*;

import java.time.LocalDateTime;

public class MemberDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create{
        private String user_id;
        private String user_pwd;
        private String user_name;
        private String email;
        private Member.Gender gender;
        private String phone;
        private String address;
        private Integer age;

        public Member toEntity() {
            return Member.builder()
                    .userId(this.user_id)
                    .userPwd(this.user_pwd)
                    .userName(this.user_name)
                    .email(this.email)
                    .gender(this.gender)
                    .phone(this.phone)
                    .address(this.address)
                    .age(this.age).build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private String user_id;
        private String user_name;
        private String email;
        private Member.Gender gender;
        private String phone;
        private String address;
        private Integer age;
        private LocalDateTime enrollDate;
        private LocalDateTime modifyDate;
        private CommonEnums.Status status;

        public static Response toDto(Member member) {
            return Response.builder()
                    .user_id(member.getUserId())
                    .user_name(member.getUserName())
                    .email(member.getEmail())
                    .gender(member.getGender())
                    .phone(member.getPhone())
                    .address(member.getAddress())
                    .age(member.getAge())
                    .enrollDate(member.getEnrollDate())
                    .modifyDate(member.getModifyDate())
                    .status(member.getStatus())
                    .build();

        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update{

        private String user_name;
        private String email;
        private Member.Gender gender;
        private String phone;
        private String address;
        private Integer age;

    }
}
