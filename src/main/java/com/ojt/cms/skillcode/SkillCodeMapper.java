package com.ojt.cms.skillcode;

public class SkillCodeMapper {
	public static SkillCode toEntity(SkillCodeDTO dto) {
		return SkillCode.builder()
				.skillCodeId(dto.getSkillCodeId())
				.skillName(dto.getSkillName())
				.keywords(dto.getKeywords())
				.build();
	}
	
	public static SkillCodeDTO toDTO(SkillCode entity) {
	    return SkillCodeDTO.builder()
	            .skillCodeId(entity.getSkillCodeId())
	            .skillName(entity.getSkillName())
	            .keywords(entity.getKeywords())
	            .build();
	}
}
