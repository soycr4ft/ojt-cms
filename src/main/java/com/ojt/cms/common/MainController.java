package com.ojt.cms.common;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ojt.cms.user.UserService;
import com.ojt.cms.user.dto.UserLoginRequestDTO;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
	
    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }
	
	//로그인 처리하기
	@PostMapping("/login")
	@ResponseBody 
	public ResponseEntity<Map<String, Object>> login(@RequestBody UserLoginRequestDTO dto,HttpSession session,HttpServletRequest request) {
		try {
			dto.setIpInfo(request.getRemoteAddr());
			Map<String, Object> result = userService.login(dto, session);
			return ResponseEntity.ok(result); 
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity
			        .status(HttpStatus.BAD_REQUEST)
			        .body(Map.of("success", false, "message", e.getMessage()));
		}
    }
}
