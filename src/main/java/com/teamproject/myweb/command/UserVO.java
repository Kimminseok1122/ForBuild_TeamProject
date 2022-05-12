package com.teamproject.myweb.command;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO {

	private Integer user_no;
	
	@NotBlank
	private String user_id;
	
	@NotBlank
	private String user_pw;
	
	@NotBlank
	private String user_name;
	
	@NotBlank
	private Integer user_age;
	
	@NotBlank
	private String user_gender;
	
	@NotBlank
	private String user_phone;
}
