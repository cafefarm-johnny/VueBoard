package com.johnny.vueboard.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnny.vueboard.Entity.UserInfo;
import com.johnny.vueboard.service.JoinService;

import lombok.AllArgsConstructor;

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class JoinController {

	private JoinService joinService;
	
	@PostMapping("/join")
	public Map<String, Object> join(@RequestBody UserInfo userinfo) {
		return joinService.insertUser(userinfo);
	}
}
