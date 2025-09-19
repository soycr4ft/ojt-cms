package com.ojt.cms.department;

public class DepartmentMapper {
	
	public static Department toEntity(DepartmentDTO dto) {
		return Department.builder()
				.deptId(dto.getDeptId())
				.deptName(dto.getDeptName())
				.build();
	}
	
	public static DepartmentDTO toDTO(Department department) {
		return DepartmentDTO.builder()
				.deptId(department.getDeptId())
				.deptName(department.getDeptName())
				.build();
	}
}
