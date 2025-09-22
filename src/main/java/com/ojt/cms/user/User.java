package com.ojt.cms.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ojt.cms.common.BaseEntity;
import com.ojt.cms.department.Department;
import com.ojt.cms.user.enums.ApprovedStatus;
import com.ojt.cms.user.enums.AuthRole;
import com.ojt.cms.user.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder //부모필드 Builder에서 제외하기
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;
    
    @Column(nullable = false, length = 50)
    private String loginId;
    
    @Column(nullable = false, length = 50)
    private String password;
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false, length = 50)
    private String phone;

    @Column(nullable = false, length = 50)
    private String email;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ApprovedStatus  approved; //대기 승인 거절

    @Column(nullable = false)
    private LocalDateTime logInfo; //최근 접속 일시

    @Column(nullable = false, length = 50)
    private String ipInfo; //최근 접속 ip

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="deptId")
    private Department department;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private AuthRole auth; //권한 memeber, admin, master, suspend

    @Column(nullable = false)
    private Boolean deleted; //0활성 1탈퇴
    
    //ip와 로그인 시간 세팅
    public void setIPandLoinDate(String ipAddress ) {
    	this.ipInfo=ipAddress;
    	this.logInfo=LocalDateTime.now();
    }
}
