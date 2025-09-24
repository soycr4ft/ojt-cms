package com.ojt.cms.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModifyUserInfoDTO {
	private Long userId;
	private String name;
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
