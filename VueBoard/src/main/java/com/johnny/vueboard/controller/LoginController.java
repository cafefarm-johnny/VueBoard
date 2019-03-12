package com.johnny.vueboard.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnny.vueboard.Entity.UserInfo;
import com.johnny.vueboard.service.LoginService;

import lombok.AllArgsConstructor;

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class LoginController {

	private LoginService loginService;
	
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody UserInfo userInfo, HttpServletRequest req) {
		return loginService.enterLogin(userInfo, req);
	}
}
