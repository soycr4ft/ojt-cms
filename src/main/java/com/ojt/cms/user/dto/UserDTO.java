package com.ojt.cms.user.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ojt.cms.user.enums.ApprovedStatus;
import com.ojt.cms.user.enums.AuthRole;
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
public class UserDTO {
    private Long userId;
    private String loginId;
    private String password;
    private String name;
    private Gender gender;
    private LocalDate birthday;
    private String phone;
    private String email;
    private ApprovedStatus approved; //대기 승인 거절
    private LocalDateTime logInfo; //최근 접속 일시
    private String ipInfo; //최근 접속 ip
    private Long deptId; //부서번호
    private AuthRole auth; //권한 memeber, admin, master, suspend
    private Boolean deleted; //0활성 1탈퇴

}
