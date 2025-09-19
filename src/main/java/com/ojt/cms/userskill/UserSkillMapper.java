package com.ojt.cms.userskill;

import com.ojt.cms.skillcode.SkillCode;
import com.ojt.cms.user.User;

public class UserSkillMapper {
	public static UserSkill toEntity(UserSkillDTO dto) {
		return UserSkill.builder()
				.userSkillId(dto.getUserSkillId())
				.skillCode(dto.getSkillCodeId()!=null? SkillCode.builder().skillCodeId(dto.getSkillCodeId()).build():null)
				.user(dto.getUserId()!=null? User.builder().userId(dto.getUserId()).build():null)
				.build();
	}
	
	public static UserSkillDTO toDTO(UserSkill entity) {
		return UserSkillDTO.builder()
				.userSkillId(entity.getUserSkillId())
				.skillCodeId(entity.getSkillCode() !=null? entity.getSkillCode().getSkillCodeId():null)
				.userId(entity.getUser()!= null? entity.getUser().getUserId():null)
				.build();
	}
}
