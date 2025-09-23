package com.ojt.cms.user;

import com.ojt.cms.search.PageResponseDTO;
import com.ojt.cms.user.dto.ApprovedUserResponseDTO;
import com.ojt.cms.user.dto.ApprovedUserSearchDTO;
import com.ojt.cms.user.dto.UserJoinDTO;

public interface UserService {
	Boolean doubleIdCheck(String id) throws Exception;
	void join(UserJoinDTO userJoinDTO,String ipAddress) throws Exception;
	
	//master의 유저서비스
	PageResponseDTO<ApprovedUserResponseDTO> getWaitingUserList(ApprovedUserSearchDTO dto) throws Exception ;
	Boolean userApprovalOrReject(Long userId, String type) throws Exception;
	void modifyUserAuth(Long userId, String newAuth)throws Exception;
}
