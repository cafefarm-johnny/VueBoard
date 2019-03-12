package com.johnny.vueboard.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.johnny.vueboard.Entity.UserInfo;
import com.johnny.vueboard.repository.LoginRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginService {

	private LoginRepository loginRepo;
	
	public Map<String, Object> enterLogin(UserInfo userInfo, HttpServletRequest req) {
		Map<String, Object> returnMap = new HashMap<>();
		
		try 
		{
			Optional<UserInfo> searchUser = loginRepo.findByUserIdAndUserPwd(userInfo.getUserId(), userInfo.getUserPwd());
			if (searchUser.isPresent())
			{
				// req.getSession().setAttribute("loginInfo", searchUser);
				// req.getSession().setMaxInactiveInterval(60*60*60*60);
				returnMap.put("result", 1);
				returnMap.put("userName", searchUser.get().getUserName());
			}
			else
			{
				returnMap.put("result", -2);
				returnMap.put("msg", "존재하지 않는 사용자입니다.");
			}
		} 
		catch (NullPointerException | IllegalArgumentException e) 
		{
			// TODO: userId 혹은 userPwd가 null일 때
			e.printStackTrace();
			returnMap.put("result", -1);
			returnMap.put("msg", "로그인에 실패하였습니다. 다시 시도해주세요.");
		}
		
		return returnMap;
	}
}
