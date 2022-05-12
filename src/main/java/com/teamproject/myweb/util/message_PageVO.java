package com.teamproject.myweb.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class message_PageVO {

	private int start; 
	private int end; 
	private boolean prev; 
	private boolean next; 
	
	private int page; 
	private int amount; 
	private int total; 
	private int realEnd;
	
	private message_Criteria cri;
	
	private List<Integer> pageList;
	
	public message_PageVO(message_Criteria cri, int total) {
		
		this.page = cri.getPage();
		this.amount = cri.getAmount();
		this.total = total;
		this.cri = cri;
		
		this.end = (int)Math.ceil(this.page / 5.0) * 5;
		
		this.start = this.end - 5 + 1;
		
		this.realEnd = (int)Math.ceil(this.total / (double)this.amount);
		
		if(this.end > this.realEnd) {
			this.end = this.realEnd;
		}
		
		this.prev = this.start > 1;
		
		this.next = this.realEnd > this.end;
		
		this.pageList = IntStream.rangeClosed(this.start, this.end).boxed().collect(Collectors.toList());
	}
		
}
