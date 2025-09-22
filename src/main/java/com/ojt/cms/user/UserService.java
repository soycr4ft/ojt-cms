package com.ojt.cms.user;

import com.ojt.cms.user.dto.UserJoinDTO;

public interface UserService {
	Boolean doubleIdCheck(String id) throws Exception;
	void join(UserJoinDTO userJoinDTO,String ipAddress) throws Exception;
}
