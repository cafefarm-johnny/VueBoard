package com.johnny.vueboard.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name="UserInfo")
public class UserInfo {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String userName;
	
	private String userId;
	
	private String userPwd;
	
	@Builder
	public UserInfo(String userName, String userId, String userPwd) {
		this.setUserName(userName);
		this.setUserId(userId);
		this.setUserPwd(userPwd);
	}
}
