package com.ojt.cms.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModifyPasswordRequestDTO {
	private Long userId;
	private String password;
	private String newPassword;
	private String confirmPassword;
}
