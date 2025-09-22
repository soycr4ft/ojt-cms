package com.ojt.cms.project;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ojt.cms.career.Career;

@Component
public class ProjectMapper {
	public Project toEntity(ProjectDTO dto) {
		return Project.builder()
				.projectId(dto.getProjectId())
				.projectName(dto.getProjectName())
				.startDate(dto.getStartDate())
				.endDate(dto.getEndDate())
				.customer(dto.getCustomer())
				.role(dto.getRole())
				.os(dto.getOs())
				.lang(dto.getLang())
				.db(dto.getDb())
				.tool(dto.getTool())
				.etc(dto.getEtc())
				.career(dto.getCareerId()!=null? Career.builder().careerId(dto.getCareerId()).build():null)
				.build();
	}
	
	public ProjectDTO toDTO(Project entity) {
		return ProjectDTO.builder()
				.projectId(entity.getProjectId())
				.projectName(entity.getProjectName())
				.startDate(entity.getStartDate())
				.endDate(entity.getEndDate())
				.customer(entity.getCustomer())
				.role(entity.getRole())
				.os(entity.getOs())
				.lang(entity.getLang())
				.db(entity.getDb())
				.tool(entity.getTool())
				.etc(entity.getEtc())
				.careerId(entity.getCareer()!= null? entity.getCareer().getCareerId():null)
				.build();
	}
	
	public List<ProjectDTO> toDTOList(List<Project> dtoList) {
	    return dtoList.stream()
	            .map(this::toDTO)
	            .toList();
	}
}
