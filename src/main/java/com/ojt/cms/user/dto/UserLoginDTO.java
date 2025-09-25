package com.ojt.cms.user.dto;

import java.time.LocalDateTime;

import com.ojt.cms.user.enums.AuthRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserLoginDTO { //세션 저장용
	private String loginId;
	private LocalDateTime logInfo2;
	private String ipInfo;
	private String name;
	private AuthRole role;
}
