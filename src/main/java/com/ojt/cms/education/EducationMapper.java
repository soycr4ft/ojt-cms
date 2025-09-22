package com.ojt.cms.education;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ojt.cms.user.User;

@Component
public class EducationMapper {
	public Education toEntity(EducationDTO dto) {
		return Education.builder()
				.eduId(dto.getEduId())
				.exam(dto.getExam())
				.schoolName(dto.getSchoolName())
				.level(dto.getLevel())
				.major(dto.getMajor())
				.entDate(dto.getEntDate())
				.graduDate(dto.getGraduDate())
				.eduStatus(dto.getEduStatus())
				.user(dto.getUserId()!=null? User.builder().userId(dto.getUserId()).build():null)
				.build();
	}
			
	public EducationDTO toDTO(Education entity) {
		return EducationDTO.builder()
				.eduId(entity.getEduId())
				.exam(entity.getExam())
				.schoolName(entity.getSchoolName())
				.level(entity.getLevel())
				.major(entity.getMajor())
				.entDate(entity.getEntDate())
				.graduDate(entity.getGraduDate())
				.eduStatus(entity.getEduStatus())
				.userId(entity.getUser()!= null? entity.getUser().getUserId():null)
				.build();
	}
	
	public List<EducationDTO> toDTOList(List<Education> dtoList) {
	    return dtoList.stream()
	            .map(this::toDTO)
	            .toList();
	}
}
