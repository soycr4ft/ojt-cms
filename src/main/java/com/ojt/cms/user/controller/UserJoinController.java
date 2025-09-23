package com.ojt.cms.user.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ojt.cms.department.DepartmentDTO;
import com.ojt.cms.department.DepartmentService;
import com.ojt.cms.user.UserService;
import com.ojt.cms.user.dto.UserJoinDTO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserJoinController {
    private final DepartmentService departmentService;
    private final UserService userService;
    
	@GetMapping("/join")
    public String joinForm(Model model) throws Exception {
	    model.addAttribute("user", new UserJoinDTO()); 
	    //부서 가져오기
	    List<DepartmentDTO> departments = departmentService.getAllDepts();
	    model.addAttribute("departments", departments);
        return "user/join";
    }
	
	//아이디 중복체크
	@PostMapping("/doubleIdCheck")
	@ResponseBody
	public String doubleIdCheck(@RequestParam("id") String id) throws Exception {
		Boolean isDoubled = userService.doubleIdCheck(id);
		return String.valueOf(isDoubled);
    }
	
	//회원가입 폼 제출
	@PostMapping("/join")
	public String join(@ModelAttribute UserJoinDTO userJoinDTO,HttpServletRequest request) throws Exception {
		String ipAddress = request.getRemoteAddr();
		userService.join(userJoinDTO, ipAddress);
        return "redirect:/";
    }	
}
