package com.ojt.cms.military;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ojt.cms.user.User;

@Component
public class MilitaryMapper {
	public Military toEntity(MilitaryDTO dto) {
		return Military.builder()
				.militaryId(dto.getMilitaryId())
				.militaryInfo(dto.getMilitaryInfo())
				.militaryType(dto.getMilitaryType())
				.militaryRank(dto.getMilitaryRank())
				.dischargeType(dto.getDischargeType())
				.startMil(dto.getStartMil())
				.endMil(dto.getEndMil())
				.user(dto.getUserId()!=null? User.builder().userId(dto.getUserId()).build():null)
				.build();
	}
	
	public MilitaryDTO toDTO(Military entity) {
		return MilitaryDTO.builder()
				.militaryId(entity.getMilitaryId())
				.militaryInfo(entity.getMilitaryInfo())
				.militaryType(entity.getMilitaryType())
				.militaryRank(entity.getMilitaryRank())
				.dischargeType(entity.getDischargeType())
				.startMil(entity.getStartMil())
				.endMil(entity.getEndMil())
				.userId(entity.getUser()!= null? entity.getUser().getUserId():null)
				.build();
	}
	
	public List<MilitaryDTO> toDTOList(List<Military> dtoList) {
	    return dtoList.stream()
	            .map(this::toDTO)
	            .toList();
	}
}
