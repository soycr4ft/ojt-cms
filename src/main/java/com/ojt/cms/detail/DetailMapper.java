package com.ojt.cms.detail;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ojt.cms.user.User;

@Component
public class DetailMapper {
	public Detail toEntity(DetailDTO dto) {
		return Detail.builder()
				.detailId(dto.getDetailId())
				.address(dto.getAddress())
				.profile(dto.getProfile())
				.status(dto.getStatus())
				.user(dto.getUserId()!=null? User.builder().userId(dto.getUserId()).build():null)
				.build();
	}
	
	public DetailDTO toDTO(Detail entity) {
		return DetailDTO.builder()
				.detailId(entity.getDetailId())
				.address(entity.getAddress())
				.profile(entity.getProfile())
				.status(entity.getStatus())
				.userId(entity.getUser()!=null? entity.getUser().getUserId():null)
				.build();
	}
	public List<DetailDTO> toDTOList(List<Detail> dtoList) {
	    return dtoList.stream()
	            .map(this::toDTO)
	            .toList();
	}
}
