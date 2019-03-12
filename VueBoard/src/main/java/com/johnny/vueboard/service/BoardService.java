package com.johnny.vueboard.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.johnny.vueboard.Entity.Board;
import com.johnny.vueboard.repository.BoardRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardService {

	private BoardRepository boardRepo;

	public Map<String, Object> selectAllBoard() {
		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("list", boardRepo.findAll());

		return returnMap;
	}

	public Map<String, Object> insertBoard(Board board) {
		Map<String, Object> returnMap = new HashMap<>();

		try 
		{
			Long boardId = boardRepo.save(board).getId();
			
			if (boardId > 0) 
			{
				returnMap.put("result", 1);
				returnMap.put("msg", "게시글이 등록되었습니다.");
				returnMap.put("boardId", boardId);
			}
		} 
		catch (NullPointerException e) 
		{
			returnMap.put("result", -1);
			returnMap.put("msg", "게시글 등록 중 문제가 발생하였습니다. :: NullPointerException");
		}
		
		
		return returnMap;
	}
}
