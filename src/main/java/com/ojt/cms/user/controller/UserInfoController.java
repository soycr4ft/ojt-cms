package com.ojt.cms.user.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ojt.cms.common.FileService;
import com.ojt.cms.department.DepartmentDTO;
import com.ojt.cms.department.DepartmentService;
import com.ojt.cms.user.UserService;
import com.ojt.cms.user.dto.ModifyUserInfoDTO;
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
    public ResponseEntity<?> userInfoModify( @ModelAttribute ModifyUserInfoDTO dto,
    		@RequestParam(value = "profileFile", required = false) MultipartFile profileFile, 
    		HttpSession session) {

    	try {
    		if (profileFile!=null && !profileFile.isEmpty()) {
    			System.out.println(profileFile.getOriginalFilename());
    			String fileName = fileService.storeFile(profileFile);
    			System.out.println(fileName);
    			dto.setProfile(fileName);
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
}
