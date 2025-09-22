package com.ojt.cms.department;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
	
	public Department toEntity(DepartmentDTO dto) {
		return Department.builder()
				.deptId(dto.getDeptId())
				.deptName(dto.getDeptName())
				.build();
	}
	
	public DepartmentDTO toDTO(Department department) {
		return DepartmentDTO.builder()
				.deptId(department.getDeptId())
				.deptName(department.getDeptName())
				.build();
	}
	
	public List<DepartmentDTO> toDTOList(List<Department> departments) {
	    return departments.stream()
	            .map(this::toDTO)
	            .toList();
	}
}
