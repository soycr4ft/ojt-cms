package com.ojt.cms.user.controller;


import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ojt.cms.common.FileService;
import com.ojt.cms.department.DepartmentDTO;
import com.ojt.cms.department.DepartmentService;
import com.ojt.cms.user.UserService;
import com.ojt.cms.user.dto.ModifyPasswordRequestDTO;
import com.ojt.cms.user.dto.ModifyUserInfoDTO;
import com.ojt.cms.user.dto.UserInfoResponseDTO;
import com.ojt.cms.user.dto.UserLoginDTO;
import com.ojt.cms.user.dto.UserLoginRequestDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserInfoController {

    private final ModelMapper modelMapper;
	private final UserService userService;
    private final DepartmentService departmentService;
    private final FileService fileService;
    
    @GetMapping("/user-info")
    public String userInfo(Model model, HttpSession session) {
    	UserLoginDTO loginUser = (UserLoginDTO) session.getAttribute("loginUser");
    	try {
			UserInfoResponseDTO user = userService.getUserInfo(loginUser.getLoginId());
		    List<DepartmentDTO> departments = departmentService.getAllDepts();
		    model.addAttribute("departments", departments);
			model.addAttribute("user",user);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "user/user-tabs";
    }
    
    @PostMapping("/info-modify")
	@ResponseBody
    public ResponseEntity<?> userInfoModify( @ModelAttribute ModifyUserInfoDTO dto,
    		@RequestParam(value = "profileFile", required = false) MultipartFile profileFile, 
    		HttpSession session) {


    	try {
    		if (profileFile!=null && !profileFile.isEmpty()) {
    			System.out.println(profileFile.getOriginalFilename()+"sdlnfhohef");
    			String fileName = fileService.storeFile(profileFile);
    			System.out.println(fileName);
    			dto.setProfile(fileName);
    		}
    		if (profileFile.isEmpty() && profileFile!=null) {
    			dto.setProfile(null);
    		}
    		userService.modifyUserInfo(dto);
    		UserLoginDTO loginUser= userService.getUserLoginDTO(dto.getUserId());
    		
    		//세션 갱신
    		session.setAttribute("loginUser", loginUser);
            return ResponseEntity.ok("수정 성공");

		} catch (Exception e) {
			e.printStackTrace();
	        return ResponseEntity.badRequest().body("요청 처리 중 오류가 발생했습니다.");

        }
    }
    
    //탈퇴하기
	@PutMapping("/withdraw")
	@ResponseBody
	public String userReject(@RequestParam("userId") Long userId,HttpSession session) throws Exception {
		Boolean isSuccess = userService.userWithdraw(userId);
		if (isSuccess) {
	        session.invalidate();
		}
		return String.valueOf(isSuccess);
    }
	
	//비밀번호 변경
	@PostMapping("/modify-password")
	@ResponseBody 
	public ResponseEntity<Map<String, Object>> modifyPassword(@RequestBody ModifyPasswordRequestDTO dto,HttpSession session,HttpServletRequest request) {
		try {

			Map<String, Object> result = userService.modifyPassword(dto);
	        session.invalidate();
			return ResponseEntity.ok(result); 
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity
			        .status(HttpStatus.BAD_REQUEST)
			        .body(Map.of("success", false, "message", e.getMessage()));
		}
    }
}
