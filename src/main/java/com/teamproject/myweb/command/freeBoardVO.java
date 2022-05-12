package com.teamproject.myweb.command;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class freeBoardVO {

	private Integer free_bno;
	private String free_writer;
	private String free_title;
	private String free_content;
	private LocalDateTime free_regDate;
}
