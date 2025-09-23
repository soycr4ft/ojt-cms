package com.ojt.cms.user.dto;

import java.time.LocalDateTime;

import com.ojt.cms.user.enums.ApprovedStatus;
import com.ojt.cms.user.enums.AuthRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApprovedUserResponseDTO {
	private String deptName;
	private Long userId;
	private String loginId;
	private String name;
	private LocalDateTime createdAt;
	private ApprovedStatus approved;
	private AuthRole auth;
}
