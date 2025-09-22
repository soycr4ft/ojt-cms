package com.ojt.cms.career;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ojt.cms.user.User;

@Component
public class CareerMapper {
	public Career toEntity(CareerDTO dto) {
		return Career.builder()
				.careerId(dto.getCareerId())
				.companyName(dto.getCompanyName())
				.rank(dto.getRank())
				.startDate(dto.getStartDate())
				.endDate(dto.getEndDate())
				.user(dto.getUserId()!=null? User.builder().userId(dto.getUserId()).build():null)
				.build();
	}
	
	public CareerDTO toDTO(Career entity) {
		return CareerDTO.builder()
				.careerId(entity.getCareerId())
				.companyName(entity.getCompanyName())
				.rank(entity.getRank())
				.startDate(entity.getStartDate())
				.endDate(entity.getEndDate())
				.userId(entity.getUser()!= null? entity.getUser().getUserId():null)
				.build();
	}
	
	public List<CareerDTO> toDTOList(List<Career> dtoList) {
	    return dtoList.stream()
	            .map(this::toDTO)
	            .toList();
	}
}
