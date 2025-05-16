package com.kh.jpa.dto;

import com.kh.jpa.entity.Member;
import com.kh.jpa.enums.CommonEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
                    .userId(member.getUserId())
                    .userName(member.getUserName())
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
}
