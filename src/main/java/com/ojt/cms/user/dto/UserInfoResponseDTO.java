package com.ojt.cms.user.dto;

import java.time.LocalDateTime;

import com.ojt.cms.user.enums.AuthRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoResponseDTO {
	private Long userId;
	private LocalDateTime logInfo;
	private String ipInfo;
	private String name;
	private AuthRole role;
	private LocalDateTime updatedAt;
	private String address1;
	private String address2;
	private Long deptId;
	private String phone1;
	private String phone2;
	private String phone3;
	private String emailId;
	private String emailDomain;
	private String profile;
	private String loginId;
	
}
