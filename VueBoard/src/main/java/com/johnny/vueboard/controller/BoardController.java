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

	@GetMapping("/list")
	public Map<String, Object> list() {
		return boardService.selectAllBoard();
	}


	@PostMapping("/write")
	public Map<String, Object> write(@RequestBody Board board) {
		return boardService.insertBoard(board);
	}
}
