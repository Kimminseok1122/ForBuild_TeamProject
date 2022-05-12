package com.teamproject.myweb.util;

import lombok.Data;

@Data
public class Criteria {

	private int page;
	private int amount;
	private int pagee;
	
	public Criteria() {
		this(1, 5);
	}

	public Criteria(int page, int amount) {
		super();
		this.page = page;
		this.amount = amount;
	}
	
	
}

