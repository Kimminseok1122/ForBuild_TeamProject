package com.teamproject.myweb.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review_Upload_CategoryVO {

	private Integer review_category_no;
	private String[] review_group;
	private Integer[] review_category_lv;
	private Integer[] review_category_detail_lv;
	private String[] review_category_nm;
	private String[] review_category_detail_nm;
	private Integer[] review_category_parent_lv;
	private Integer[] review_category_detail_parent_lv;
	private String review_writer;
	private Integer review_no;
}
