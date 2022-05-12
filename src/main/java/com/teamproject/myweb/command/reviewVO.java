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
public class reviewVO {
	
	private LocalDateTime review_regDate;
	private Integer review_no;
	private String review_writer;
	private String review_category;
	private String review_lat;
	private String review_lng;
	private String review_title;
	private String review_content;
	private String review_realAddress;
	private String review_theme;
	
}
