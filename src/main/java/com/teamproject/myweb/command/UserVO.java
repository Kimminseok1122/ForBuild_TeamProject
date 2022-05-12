package com.teamproject.myweb.command;

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
	private String user_id;
	private String user_pw;
	private String user_name;
	private Integer user_age;
	private String user_gender;
	private String user_phone;
}
