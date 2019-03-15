package com.johnny.vueboard.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.johnny.vueboard.Entity.Board;
import com.johnny.vueboard.service.BoardService;

import lombok.AllArgsConstructor;

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class BoardController {

	private BoardService boardService;

	@GetMapping("/list/{pageNo}")
	public Map<String, Object> list(@PathVariable int pageNo) {
		return boardService.selectAllBoard(pageNo);
	}

	@PostMapping("/write")
	public Map<String, Object> write(@RequestBody Board board) {
		return boardService.insertBoard(board);
	}
	
	@GetMapping("/content/{id}")
	public Map<String, Object> content(@PathVariable Long id) {
		return boardService.selectOneBoard(id);
	}
	
	@PostMapping("/modify")
	public Map<String, Object> modify(@RequestBody Board board) {
		return boardService.updateBoard(board);
	}
	
	@PostMapping("/delete")
	public Map<String, Object> delete(@RequestBody Board board) {
		return boardService.deleteBoard(board);
	}
}
