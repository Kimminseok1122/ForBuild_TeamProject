package com.teamproject.myweb.command;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentVO {

	private Integer cno;
	private Integer free_bno;
	@NotBlank
	private String c_writer;
	@NotBlank
	private String c_content;
	private LocalDateTime c_regdate;
}
