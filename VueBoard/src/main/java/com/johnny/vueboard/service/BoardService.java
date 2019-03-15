package com.johnny.vueboard.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.johnny.vueboard.Entity.Board;
import com.johnny.vueboard.repository.BoardRepository;


@Service
public class BoardService {

	@Value("${board.pagesize}")
	private int pagesize;

	@Autowired
	private BoardRepository boardRepo;

	// 게시글 조회
	public Map<String, Object> selectAllBoard(int pageNo) {
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("list", boardRepo.findAll(PageRequest.of(pageNo, pagesize, Sort.by(Direction.DESC, "id"))));

		return returnMap;
	}

	// 게시글 작성
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

	// 게시글 상세
	public Map<String, Object> selectOneBoard(Long id) {
		Map<String, Object> returnMap = new HashMap<>();
		
		Optional<Board> getBoard = boardRepo.findById(id);
		
		if (getBoard.isPresent())
		{
			returnMap.put("result", 1);
			returnMap.put("board", getBoard);
		}
		else
		{
			returnMap.put("result", -2);
			returnMap.put("msg", "존재하지 않는 게시글입니다.");
		}
		
		return returnMap;
	}

	// 게시글 수정
	public Map<String, Object> updateBoard(Board board) {
		Map<String, Object> returnMap = new HashMap<>();
		
		try 
		{
			if (boardRepo.existsById(board.getId()))
			{
				Optional<Board> getBoard = boardRepo.findById(board.getId());
				
				if (getBoard.isPresent())
				{
					getBoard.get().setTitle(board.getTitle());
					getBoard.get().setContent(board.getContent());
					getBoard.get().setUserName(board.getUserName());
					
					Long result = boardRepo.save(getBoard.get()).getId();
					
					if (result > 0)
					{
						returnMap.put("msg", "게시글이 수정되었습니다.");
						returnMap.put("result", 1);
					}
					else
					{
						returnMap.put("msg", "게시글 수정 중 문제가 발생하였습니다.");
						returnMap.put("result", -1);
					}
				}
			}
			else
			{
				returnMap.put("msg", "존재하지 않는 게시글입니다.");
				returnMap.put("result", -2);
			}
		} 
		catch (IllegalArgumentException e) 
		{
			e.printStackTrace();
			
			returnMap.put("msg", "처리 중 문제가 발생하였습니다. :: IllegalArgumentException");
			returnMap.put("result", -3);
		}
		
		return returnMap;
	}

	// 게시글 삭제
	public Map<String, Object> deleteBoard(Board board) {
		Map<String, Object> returnMap = new HashMap<>();
		
		try 
		{
			if (boardRepo.existsById(board.getId()))
			{
				boardRepo.deleteById(board.getId());
				
				returnMap.put("msg", "게시글이 삭제되었습니다.");
				returnMap.put("result", 1);
			}
			else
			{
				returnMap.put("msg", "존재하지 않는 게시글입니다.");
				returnMap.put("result", -1);
			}
		} 
		catch (IllegalArgumentException e) 
		{
			returnMap.put("msg", "처리 중 문제가 발생하였습니다. :: IllegalArgumentException");
			returnMap.put("result", -2);
		}
		
		return returnMap;
	}
}
