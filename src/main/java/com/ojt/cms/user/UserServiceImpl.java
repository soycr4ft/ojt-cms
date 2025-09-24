package com.ojt.cms.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ojt.cms.common.PasswordEncoder;
import com.ojt.cms.department.DepartmentRepository;
import com.ojt.cms.detail.Detail;
import com.ojt.cms.detail.DetailRepository;
import com.ojt.cms.mail.MailHtmlSendDTO;
import com.ojt.cms.mail.MailSendService;
import com.ojt.cms.search.PageResponseDTO;
import com.ojt.cms.user.dto.ApprovedUserResponseDTO;
import com.ojt.cms.user.dto.ApprovedUserSearchDTO;
import com.ojt.cms.user.dto.UserInfoResponseDTO;
import com.ojt.cms.user.dto.UserJoinDTO;
import com.ojt.cms.user.dto.UserLoginDTO;
import com.ojt.cms.user.dto.UserLoginRequestDTO;
import com.ojt.cms.user.enums.ApprovedStatus;
import com.ojt.cms.user.enums.AuthRole;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final DepartmentRepository departmentRepository;
	private final UserRepository userRepository;
	private final DetailRepository detailRepository;
	
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
		String encodedPW = passwordEncoder.encrypt(userJoinDTO.getPassword());
		//dto -> 엔티티
		User entity  = userMapper.toEntity(userJoinDTO);
		entity.changePassword(encodedPW);
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
	
	//로그인
	@Override
	@Transactional
	public Map<String, Object> login(UserLoginRequestDTO dto, HttpSession session) throws Exception {
		Map<String, Object> result = new HashMap<>();
		
		Optional<User> optionalUser = userRepository.findByLoginId(dto.getLoginId());
        if (optionalUser.isEmpty()) {
            result.put("success", false);
            result.put("message", "아이디가 존재하지 않습니다.");
            return result;
        }
        User user = optionalUser.get();
 
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            result.put("success", false);
            result.put("message", "비밀번호가 일치하지 않습니다.");
            return result;
        }
        
        if (user.getApproved() == ApprovedStatus.REJECTED) {
            result.put("success", false);
            result.put("message", "관리자 승인이 거절되었습니다.");
            return result;
        }
        
        //성공 -> 세션 저장
        user.setIPandLoinDate(dto.getIpInfo());
        //userRepository.save(user); => 영속성 컨텍스트에 의해 불필요함
        
        UserLoginDTO loginUser = userMapper.toUserLoginDTO(user);
        session.setAttribute("loginUser", loginUser);
        String status;
        if (user.getApproved() == ApprovedStatus.WAITING) {
        	status = "waiting";
        } else {
        	status = "approval";
        }
        
        result.put("success", true);
        result.put("status", status);
        result.put("message", "로그인 성공");
        return result;
	}

	@Override
	public UserInfoResponseDTO getUserInfo(String loginId) throws Exception {
		User loingUser = userRepository.findByLoginId(loginId).orElseThrow(()->new Exception("해당 아이디를 가진 회원이 없습니다."));
		Detail detail = detailRepository.findByUser(loingUser).orElse(null);
        return userMapper.toUserInfoWithDetailDTO(loingUser, detail);
	}

	@Transactional
	@Override
	public void modifyUserInfo(UserInfoResponseDTO dto) throws Exception {
		//유저의 기본정보 수정
		User user = userRepository.findById(dto.getUserId()).orElseThrow(()->new Exception("해당 아이디를 가진 회원이 없습니다."));
		
		user.modifyUserInfo(dto);
		//유저의 userId로 유저 detail 수정 또는 삽입
		Detail detail = detailRepository.findByUser(user).orElse(null);
		if (detail==null) {
	        detail = Detail.builder()
                    .user(user)
                    .address1(dto.getAddress1())
                    .address2(dto.getAddress2())
                    .profile(dto.getProfile())
                    .build();
	        detailRepository.save(detail);
		} else {
			detail.modifyUserInfo(dto);
		}
	}

}
