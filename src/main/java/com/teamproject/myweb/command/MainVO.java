package com.teamproject.myweb.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainVO {
	
	private String review_title;
	private String review_lat;
	private String review_lng;
	private String review_category;
	private String review_realaddress;
	private Integer review_no;
	private String review_filename;
	private String review_filepath;
	private String review_uuid;
	private String review_group;
	private String review_theme;
	private String[] review_filenames;
	private String[] review_filepaths;
	private String[] review_uuids;
}
