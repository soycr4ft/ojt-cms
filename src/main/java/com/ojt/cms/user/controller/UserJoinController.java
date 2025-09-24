package com.ojt.cms.user.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ojt.cms.department.DepartmentDTO;
import com.ojt.cms.department.DepartmentService;
import com.ojt.cms.user.UserService;
import com.ojt.cms.user.dto.UserJoinDTO;
import com.ojt.cms.user.dto.UserLoginDTO;
import com.ojt.cms.user.enums.ApprovedStatus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
	public String join(Model model, @ModelAttribute UserJoinDTO userJoinDTO,HttpServletRequest request) throws Exception {
		String ipAddress = request.getRemoteAddr();
		userService.join(userJoinDTO, ipAddress);
	    model.addAttribute("status", "joined"); 
        return "redirect:/user/join-complete/joined";
    }	
	
	//회원가입 완료 페이지
	@GetMapping("/join-complete/{status}")
    public String joinComplete(Model model, @PathVariable("status") String status, HttpSession session) throws Exception {
		if (status.equals("joined")) {
			
			model.addAttribute("status", status); 
	        return "user/join-complete";
		}
		
		if (ApprovedStatus.from(status)==ApprovedStatus.APPROVED || ApprovedStatus.from(status)==ApprovedStatus.WAITING) {
			UserLoginDTO user = (UserLoginDTO) session.getAttribute("loginUser");
			//로그인 완료 후 dto 뿌리기
			model.addAttribute("user", user); 
		}
		
		model.addAttribute("status", status); 
        return "user/join-complete";

    }
	
	//로그아웃
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
