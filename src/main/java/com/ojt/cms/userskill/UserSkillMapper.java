package com.ojt.cms.userskill;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ojt.cms.skillcode.SkillCode;
import com.ojt.cms.user.User;

@Component
public class UserSkillMapper {
	public UserSkill toEntity(UserSkillDTO dto) {
		return UserSkill.builder()
				.userSkillId(dto.getUserSkillId())
				.skillCode(dto.getSkillCodeId()!=null? SkillCode.builder().skillCodeId(dto.getSkillCodeId()).build():null)
				.user(dto.getUserId()!=null? User.builder().userId(dto.getUserId()).build():null)
				.build();
	}
	
	public UserSkillDTO toDTO(UserSkill entity) {
		return UserSkillDTO.builder()
				.userSkillId(entity.getUserSkillId())
				.skillCodeId(entity.getSkillCode() !=null? entity.getSkillCode().getSkillCodeId():null)
				.userId(entity.getUser()!= null? entity.getUser().getUserId():null)
				.build();
	}
	
	public List<UserSkillDTO> toDTOList(List<UserSkill> dtoList) {
	    return dtoList.stream()
	            .map(this::toDTO)
	            .toList();
	}
}
