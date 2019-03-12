package com.johnny.vueboard.Entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="Board")
public class Board {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String userName;
	
	private String title;
	
	@Lob
	private String content;
	
	@CreationTimestamp
	private LocalDate writeDate;
	
	@UpdateTimestamp
	private LocalDate modifyDate;
	
	
	@Builder
	public Board(String userName, String title, String content) {
		this.setUserName(userName);
		this.setTitle(title);
		this.setContent(content);
	}
}
