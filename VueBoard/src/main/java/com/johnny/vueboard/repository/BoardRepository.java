package com.johnny.vueboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnny.vueboard.Entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
