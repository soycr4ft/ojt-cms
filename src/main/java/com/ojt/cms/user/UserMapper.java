package com.ojt.cms.user;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ojt.cms.department.Department;
import com.ojt.cms.user.dto.UserDTO;
import com.ojt.cms.user.dto.UserJoinDTO;
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
    
}
