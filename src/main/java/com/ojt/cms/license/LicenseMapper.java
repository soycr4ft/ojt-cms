package com.ojt.cms.license;

import com.ojt.cms.user.User;


public class LicenseMapper {
	public static License toEntity(LicenseDTO dto) {
		return License.builder()
				.licenseId(dto.getLicenseId())
				.kosa(dto.getKosa())
				.licenseName(dto.getLicenseName())
				.acDate(dto.getAcDate())
				.user(dto.getUserId()!=null? User.builder().userId(dto.getUserId()).build():null)
				.build();
	}
	
	public static LicenseDTO toDTO (License entity) {
		return LicenseDTO.builder()
				.licenseId(entity.getLicenseId())
				.kosa(entity.getKosa())
				.licenseName(entity.getLicenseName())
				.acDate(entity.getAcDate())
				.userId(entity.getUser()!= null? entity.getUser().getUserId():null)
				.build();
	}
}
