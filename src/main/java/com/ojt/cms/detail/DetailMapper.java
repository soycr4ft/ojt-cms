package com.ojt.cms.detail;

import com.ojt.cms.user.User;

public class DetailMapper {
	public static Detail toEntity(DetailDTO dto) {
		return Detail.builder()
				.detailId(dto.getDetailId())
				.address(dto.getAddress())
				.profile(dto.getProfile())
				.status(dto.getStatus())
				.user(dto.getUserId()!=null? User.builder().userId(dto.getUserId()).build():null)
				.build();
	}
	
	public static DetailDTO toDTO(Detail entity) {
		return DetailDTO.builder()
				.detailId(entity.getDetailId())
				.address(entity.getAddress())
				.profile(entity.getProfile())
				.status(entity.getStatus())
				.userId(entity.getUser()!=null? entity.getUser().getUserId():null)
				.build();
	}
}
