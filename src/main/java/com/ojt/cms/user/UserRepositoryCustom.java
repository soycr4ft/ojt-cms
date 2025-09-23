package com.ojt.cms.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ojt.cms.user.dto.ApprovedUserResponseDTO;
import com.ojt.cms.user.dto.ApprovedUserSearchDTO;

public interface UserRepositoryCustom {
	//master의 유저 권한
	Page<ApprovedUserResponseDTO> findUserByCondition(ApprovedUserSearchDTO dto, Pageable pageable) throws Exception;
}
