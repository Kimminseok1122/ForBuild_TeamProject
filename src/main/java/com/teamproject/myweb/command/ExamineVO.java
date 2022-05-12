package com.teamproject.myweb.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamineVO {

	private Integer exam_no;
	private String USER_ID;
	private String USER_NAME;
	private String exam1;
	private String exam2;
	private String exam3;
	private String exam4;
	private String exam5;
	private String exam6;
	private String exam7;

}