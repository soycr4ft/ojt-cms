package com.ojt.cms.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ojt.cms.common.PasswordEncoder;
import com.ojt.cms.department.DepartmentRepository;
import com.ojt.cms.mail.MailHtmlSendDTO;
import com.ojt.cms.mail.MailSendService;
import com.ojt.cms.search.PageResponseDTO;
import com.ojt.cms.user.dto.ApprovedUserResponseDTO;
import com.ojt.cms.user.dto.ApprovedUserSearchDTO;
import com.ojt.cms.user.dto.UserJoinDTO;
import com.ojt.cms.user.enums.ApprovedStatus;
import com.ojt.cms.user.enums.AuthRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final DepartmentRepository departmentRepository;
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final MailSendService mailSendService;


	@Override
	public Boolean doubleIdCheck(String id) throws Exception {
		if (!userRepository.findByLoginId(id).isEmpty() ) {
			return true;
		} else return false;
	}

    @Transactional
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
		
		//회원 저장 후 관리자에게 이메일 발송
        MailHtmlSendDTO dto = new MailHtmlSendDTO(
        		"s0ycr4ft@gmail.com",   // 수신자 이메일
                "회원가입 승인 요청",     // 제목
                "회원가입 요청 안내",     // 내용
                userJoinDTO.getName()    
    		);
        mailSendService.sendHtmlEmail(dto);

	}

    
    //master의 유저 서비
	@Override
	public PageResponseDTO<ApprovedUserResponseDTO> getWaitingUserList(ApprovedUserSearchDTO dto) throws Exception {
		Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize());
		Page<ApprovedUserResponseDTO> pageResult = userRepository.findUserByCondition(dto, pageable);

		return PageResponseDTO.<ApprovedUserResponseDTO>builder()
				.content(pageResult.getContent())
				.currentPage(pageResult.getNumber()+1)
				.totalPages(pageResult.getTotalPages())
				.totalElements(pageResult.getTotalElements())
				.build();
	}

	@Override
	@Transactional
	public Boolean userApprovalOrReject(Long userId, String type) throws Exception {
		User user = userRepository.findById(userId).orElseThrow(()-> new Exception("해당 회원을 찾을 수 없습니다."));
		ApprovedStatus status = ApprovedStatus.from(type);
		user.setApprove(status);
		if (status == ApprovedStatus.APPROVED) {
		    user.setAuth(AuthRole.MEMBER);
		} else {
		    user.setAuth(AuthRole.REJECTED);
		}
	    return true;
	}

	@Override
	@Transactional
	public void modifyUserAuth(Long userId, String newAuth) throws Exception {
		User user = userRepository.findById(userId).orElseThrow(()-> new Exception("해당 회원을 찾을 수 없습니다."));
		AuthRole status = AuthRole.from(newAuth);
		System.out.println(status);
		user.setAuth(status);
	}

}
