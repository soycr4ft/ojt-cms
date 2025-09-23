package com.ojt.cms.user.controller;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ojt.cms.search.PageResponseDTO;
import com.ojt.cms.user.UserService;
import com.ojt.cms.user.dto.ApprovedUserResponseDTO;
import com.ojt.cms.user.dto.ApprovedUserSearchDTO;
import com.ojt.cms.user.enums.ApprovedStatus;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/master")
@RequiredArgsConstructor
public class MasterUserController {

	private final UserService userService;
	
	
	@GetMapping("/user-approval")
    public String userApprovalList(Model model,
    	    @RequestParam(name = "page", defaultValue = "1") int page,
    	    @RequestParam(name = "size", defaultValue = "5") int size,
    	    @RequestParam(name = "keyword", required = false) String keyword,
    	    @RequestParam(name = "type", required = false) String type,
    	    @RequestParam(name = "approved", defaultValue = "ALL") ApprovedStatus approved

		    ) throws Exception {
		
		ApprovedUserSearchDTO dto = new ApprovedUserSearchDTO();
		dto.setSize(size);
		dto.setPage(page-1);
		dto.setKeyword(keyword);
		dto.setType(type);
		dto.setApproved(approved);
		
		PageResponseDTO<ApprovedUserResponseDTO> response = userService.getWaitingUserList(dto);
		
	    model.addAttribute("response", response);
	    model.addAttribute("currentPage", response.getCurrentPage());
	    model.addAttribute("totalPages", response.getTotalPages());
	    model.addAttribute("keyword", dto.getKeyword());
	    model.addAttribute("type", dto.getType());
	    model.addAttribute("approved", dto.getApproved());
		return "admin/user-approval";
    }
	
	//회원가입 승인
	@PutMapping("/userApproval")
	@ResponseBody
	public String userApproval(@RequestParam("userId") Long userId) throws Exception {
		Boolean isSuccess = userService.userApprovalOrReject(userId, "approval");
		return String.valueOf(isSuccess);
    }
	
	//회원가입 거절
	@PutMapping("/userReject")
	@ResponseBody
	public String userReject(@RequestParam("userId") Long userId) throws Exception {
		Boolean isSuccess = userService.userApprovalOrReject(userId,"reject");
		return String.valueOf(isSuccess);
    }
	
	//회원의 권한 변경
	@PutMapping("/userAuth/{userId}")
	@ResponseBody
	public String userAuth(@PathVariable("userId") Long userId,@RequestBody Map<String, String> body) throws Exception {
	    String newAuth = body.get("auth").toString();
	    System.out.println(newAuth+"jdflsiflsjlehfil/");
	    userService.modifyUserAuth(userId, newAuth);
	    return "success";
    }
}
