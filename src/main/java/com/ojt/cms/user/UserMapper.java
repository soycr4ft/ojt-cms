package com.ojt.cms.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ojt.cms.department.Department;
import com.ojt.cms.detail.Detail;
import com.ojt.cms.user.dto.ApprovedUserResponseDTO;
import com.ojt.cms.user.dto.UserDTO;
import com.ojt.cms.user.dto.UserInfoResponseDTO;
import com.ojt.cms.user.dto.UserJoinDTO;
import com.ojt.cms.user.dto.UserLoginDTO;
import com.ojt.cms.user.enums.ApprovedStatus;
import com.ojt.cms.user.enums.AuthRole;

@Component
public class UserMapper {
	  //dto=> 엔티티
    public User toEntity(UserJoinDTO dto) {
        return User.builder()
                .loginId(dto.getLoginId())
                .password(dto.getPassword())
                .name(dto.getName())
                .gender(dto.getGender())
                .birthday(dto.getBirthday())
                .phone(dto.getPhone1() + "-" + dto.getPhone2() + "-" + dto.getPhone3())
                .email(dto.getEmail() + "@" + dto.getEmailDomain())
                .approved(ApprovedStatus.WAITING)
                .auth(AuthRole.SUSPENDED)
                .department(dto.getDeptId() != null
                        ? Department.builder().deptId(dto.getDeptId()).build()
                        : null)
                .deleted(false)
                .build();
    }

    //엔티티 -> dto
    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .loginId(user.getLoginId())
                .password(user.getPassword())
                .name(user.getName())
                .gender(user.getGender())
                .birthday(user.getBirthday())
                .phone(user.getPhone())
                .email(user.getEmail())
                .approved(user.getApproved())
                .logInfo(user.getLogInfo())
                .ipInfo(user.getIpInfo())
                .deptId(user.getDepartment() != null ? user.getDepartment().getDeptId() : null)
                .auth(user.getAuth())
                .deleted(user.getDeleted())
                .build();
    }
    
	public List<UserDTO> toDTOList(List<User> dtoList) {
	    return dtoList.stream()
	            .map(this::toDTO)
	            .toList();
	}
	
	public List<ApprovedUserResponseDTO> toApprovedDTOList(List<User> dtoList) {
		List<ApprovedUserResponseDTO> results = new ArrayList<>();;
		for (User user : dtoList) {
			results.add(ApprovedUserResponseDTO.builder()
					.deptName(user.getDepartment().getDeptName())
					.loginId(user.getLoginId())
					.name(user.getName())
					.createdAt(user.getCreatedAt())
					.approved(user.getApproved())
					.build());
		}
		return results;
	}
	
	public UserInfoResponseDTO toUserInfoWithDetailDTO(User user, Detail detail) {
		String[] emailParts = user.getEmail().split("@");
		String emailId = emailParts[0];
		String emailDomain = emailParts[1];
		
		String[] phoneNumber = user.getPhone().split("-");
		
		return UserInfoResponseDTO.builder()
				.userId(user.getUserId())
				.logInfo2(user.getLogInfo2())
				.ipInfo(user.getIpInfo())
				.name(user.getName())
				.role(user.getAuth())
				.lastUpdatedAt(detail !=null ? (user.getLastUpdatedAt().isAfter(detail.getUpdatedAt()) ?
						user.getLastUpdatedAt() : detail.getUpdatedAt()
						): (user.getLastUpdatedAt()!=null? user.getLastUpdatedAt(): user.getCreatedAt() ))
			    .address1(detail != null ? detail.getAddress1() : null)
			    .address2(detail != null ? detail.getAddress2() : null)
				.deptId(user.getDepartment().getDeptId())
				.phone1(phoneNumber[0])
				.phone2(phoneNumber[1])
				.phone3(phoneNumber[2])
				.emailId(emailId)
				.emailDomain(emailDomain)
				.profile(detail!=null?detail.getProfile():null)
				.loginId(user.getLoginId())
				.build();
	}
    
	public UserLoginDTO toUserLoginDTO(User user) {
		return UserLoginDTO.builder()
				.loginId(user.getLoginId())
				.logInfo2(user.getLogInfo2())
				.ipInfo(user.getIpInfo())
				.name(user.getName())
				.role(user.getAuth())
				.build();
	}
}
