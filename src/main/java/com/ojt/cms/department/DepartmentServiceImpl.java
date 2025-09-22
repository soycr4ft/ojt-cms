package com.ojt.cms.department;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
	private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

	@Override
	public List<DepartmentDTO> getAllDepts() throws Exception {
		return departmentMapper.toDTOList(departmentRepository.findAll());
	}

}
