package com.teamproject.myweb.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review_uploadVO {

	private Integer review_upload_no;
	private String review_filename;
	private String review_filepath;
	private String review_uuid;
	private Integer review_no;
	private String review_writer;
}
