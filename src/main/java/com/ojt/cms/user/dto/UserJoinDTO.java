package com.ojt.cms.user.dto;

import java.time.LocalDate;

import com.ojt.cms.user.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserJoinDTO {
    private String loginId;
    private String password;
    private String name;
    private Gender gender;
    private LocalDate birthday;
    private String phone1;
    private String phone2;
    private String phone3;
    private String email;
    private String emailDomain;
    private Long deptId; //대기 승인 거절

}
