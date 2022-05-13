package com.teamproject.myweb.command;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCheckVO {
	
	@NotBlank(message = "아이디/비밀번호를 확인해주세요")
	private String user_id;
	
	@NotBlank(message = "아이디/비밀번호를 확인해주세요")
	private String user_pw;
	
}
