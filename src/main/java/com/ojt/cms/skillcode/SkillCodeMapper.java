package com.ojt.cms.skillcode;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SkillCodeMapper {
	public SkillCode toEntity(SkillCodeDTO dto) {
		return SkillCode.builder()
				.skillCodeId(dto.getSkillCodeId())
				.skillName(dto.getSkillName())
				.keywords(dto.getKeywords())
				.build();
	}
	
	public SkillCodeDTO toDTO(SkillCode entity) {
	    return SkillCodeDTO.builder()
	            .skillCodeId(entity.getSkillCodeId())
	            .skillName(entity.getSkillName())
	            .keywords(entity.getKeywords())
	            .build();
	}
	
	public List<SkillCodeDTO> toDTOList(List<SkillCode> dtoList) {
	    return dtoList.stream()
	            .map(this::toDTO)
	            .toList();
	}
}
