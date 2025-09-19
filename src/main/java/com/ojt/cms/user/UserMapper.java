package com.ojt.cms.user;

import com.ojt.cms.department.Department;

public class UserMapper {
	  //dto=> 엔티티
    public static User toEntity(UserDTO dto) {
        return User.builder()
                .userId(dto.getUserId())
                .loginId(dto.getLoginId())
                .password(dto.getPassword())
                .name(dto.getName())
                .gender(dto.getGender())
                .birthday(dto.getBirthday())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .approved(dto.getApproved())
                .logInfo(dto.getLogInfo())
                .ipInfo(dto.getIpInfo())
                .department(dto.getDeptId() != null
                        ? Department.builder().deptId(dto.getDeptId()).build()
                        : null)
                .auth(dto.getAuth())
                .deleted(dto.getDeleted())
                .build();
    }

    //엔티티 -> dto
    public static UserDTO toDTO(User user) {
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
    
}
