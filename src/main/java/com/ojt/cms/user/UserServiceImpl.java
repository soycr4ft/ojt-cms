package com.ojt.cms.user;

import org.springframework.stereotype.Service;

import com.ojt.cms.common.PasswordEncoder;
import com.ojt.cms.department.DepartmentRepository;
import com.ojt.cms.user.dto.UserJoinDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final DepartmentRepository departmentRepository;
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Boolean doubleIdCheck(String id) throws Exception {
		if (!userRepository.findByLoginId(id).isEmpty() ) {
			return true;
		} else return false;
	}

	@Override
	public void join(UserJoinDTO userJoinDTO, String ipAddress) throws Exception {
		//부서 조회
		departmentRepository.findById(userJoinDTO.getDeptId())
                .orElseThrow(() -> new IllegalArgumentException("해당 부서 없음"));
		//비밀번호 암호화
		String encodedPW = passwordEncoder.encrypt(userJoinDTO.getEmail()+"@"+userJoinDTO.getEmailDomain(), userJoinDTO.getPassword());
        userJoinDTO.setPassword(encodedPW);
		//dto -> 엔티티
		User entity  = userMapper.toEntity(userJoinDTO);
        //ip세팅
		entity.setIPandLoinDate(ipAddress);
		userRepository.save(entity);
	}

}
