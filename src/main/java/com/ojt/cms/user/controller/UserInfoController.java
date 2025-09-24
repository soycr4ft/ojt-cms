package com.ojt.cms.user.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ojt.cms.common.FileService;
import com.ojt.cms.department.DepartmentDTO;
import com.ojt.cms.department.DepartmentService;
import com.ojt.cms.user.UserService;
import com.ojt.cms.user.dto.UserInfoResponseDTO;
import com.ojt.cms.user.dto.UserLoginDTO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserInfoController {
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
        return "user/user-info";
    }
    
    @PostMapping("/info-modify")
	@ResponseBody
    public ResponseEntity<?> userInfoModify( @ModelAttribute UserInfoResponseDTO dto,
    		@RequestParam(value = "profile", required = false) MultipartFile profile, 
    		HttpSession session) {

    	try {
    		if (profile!=null && !profile.isEmpty()) {
    			String fileName = fileService.storeFile(profile);
    			dto.setProfile(fileName);
    		}
    		userService.modifyUserInfo(dto);
    		//세션 갱신
    		session.setAttribute("loginUser", 
    			    new UserLoginDTO(
    			    		dto.getLoginId(),
    			    		dto.getLogInfo(),
    			    		dto.getIpInfo(),
    			    		dto.getName(),
    			    		dto.getRole())
    		);
            return ResponseEntity.ok("수정 성공");

		} catch (Exception e) {
			e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("요청 값이 잘못되었습니다.");		
        }
    }
}
