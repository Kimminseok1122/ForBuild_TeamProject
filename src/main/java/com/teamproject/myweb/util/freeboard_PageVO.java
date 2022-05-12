package com.teamproject.myweb.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class freeboard_PageVO {

	private int start;
	private int end;
	private boolean prev;
	private boolean next;
	
	private int page;
	private int amount;
	private int total;
	private int realEnd;
	
	
	
	private freeboard_Criteria cri;
	
	private List<Integer> pageList;

	public freeboard_PageVO(freeboard_Criteria cri, int total) {
		this.page = cri.getPage();
		this.amount = cri.getAmount();
		this.total = total;
		this.cri = cri;
		
		this.end = (int)Math.ceil( this.page / 10.0) * 10;
		this.start = this.end -10 + 1;
		this.realEnd = (int)Math.ceil( this.total / (double)this.amount );
		
		if(this.end > this.realEnd) {
			this.end = this.realEnd;
		}
		
		this.prev = this.start > 1;
		this.next = this.realEnd > this.end;
		
//		for(int i = this.start; i <= this.end; i++) {
//			this.pageList.add(i); 
//		}
		this.pageList = IntStream.rangeClosed(this.start, this.end).boxed().collect( Collectors.toList() ) ;
		
		
	}
	
	//생성자
	
	
	
}
