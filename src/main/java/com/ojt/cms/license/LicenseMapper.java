package com.ojt.cms.license;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ojt.cms.user.User;

@Component
public class LicenseMapper {
	public License toEntity(LicenseDTO dto) {
		return License.builder()
				.licenseId(dto.getLicenseId())
				.kosa(dto.getKosa())
				.licenseName(dto.getLicenseName())
				.acDate(dto.getAcDate())
				.user(dto.getUserId()!=null? User.builder().userId(dto.getUserId()).build():null)
				.build();
	}
	
	public LicenseDTO toDTO (License entity) {
		return LicenseDTO.builder()
				.licenseId(entity.getLicenseId())
				.kosa(entity.getKosa())
				.licenseName(entity.getLicenseName())
				.acDate(entity.getAcDate())
				.userId(entity.getUser()!= null? entity.getUser().getUserId():null)
				.build();
	}
	
	public List<LicenseDTO> toDTOList(List<License> dtoList) {
	    return dtoList.stream()
	            .map(this::toDTO)
	            .toList();
	}
}
