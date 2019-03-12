package com.johnny.vueboard.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.johnny.vueboard.Entity.UserInfo;
import com.johnny.vueboard.repository.JoinRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JoinService {

	private JoinRepository joinRepo;
	
	public Map<String, Object> insertUser(UserInfo userInfo) {
		Map<String, Object> returnMap = new HashMap<>();
		
		try 
		{
			Optional<UserInfo> otherUser = joinRepo.findByUserId(userInfo.getUserId());
			if (otherUser.isPresent())
			{
				returnMap.put("result", -2);
				returnMap.put("msg", "이미 사용 중인 아이디입니다. 다른 아이디를 사용해주세요.");
			}
			else
			{
				Long id = joinRepo.save(userInfo).getId();
				
				if (id > 0)
				{
					returnMap.put("result", 1);
					returnMap.put("msg", "회원가입이 완료되었습니다. 축하합니다.");
				}
			}
		} 
		catch (NullPointerException e) 
		{
			// TODO: Handle null user condition.
			e.printStackTrace();
			returnMap.put("result", -1);
			returnMap.put("msg", "처리 중 문제가 발생했습니다. 다시 시도해주세요. :: NULLPointerException");
		}
		
		return returnMap;
	}
}
