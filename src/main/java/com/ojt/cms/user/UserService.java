package com.ojt.cms.user;

import java.util.Map;

import com.ojt.cms.search.PageResponseDTO;
import com.ojt.cms.user.dto.ApprovedUserResponseDTO;
import com.ojt.cms.user.dto.ApprovedUserSearchDTO;
import com.ojt.cms.user.dto.ModifyUserInfoDTO;
import com.ojt.cms.user.dto.UserInfoResponseDTO;
import com.ojt.cms.user.dto.UserJoinDTO;
import com.ojt.cms.user.dto.UserLoginDTO;
import com.ojt.cms.user.dto.UserLoginRequestDTO;

import jakarta.servlet.http.HttpSession;

public interface UserService {
	Boolean doubleIdCheck(String id) throws Exception;
	void join(UserJoinDTO userJoinDTO,String ipAddress) throws Exception;
	
	//master의 유저서비스
	PageResponseDTO<ApprovedUserResponseDTO> getWaitingUserList(ApprovedUserSearchDTO dto) throws Exception ;
	Boolean userApprovalOrReject(Long userId, String type) throws Exception;
	void modifyUserAuth(Long userId, String newAuth)throws Exception;
	//로그인
	Map<String, Object> login(UserLoginRequestDTO dto, HttpSession session) throws Exception;
	//회원의 기본정보 탭
	UserInfoResponseDTO getUserInfo(String loginId) throws Exception;
	//회원 기본정보 수정
	void modifyUserInfo(ModifyUserInfoDTO dto) throws Exception;
	UserLoginDTO getUserLoginDTO(Long userId) throws Exception;
}
