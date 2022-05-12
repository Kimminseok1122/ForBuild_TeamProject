package com.teamproject.myweb.util;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Data;

@Data
public class review_pageVO {

	private int start;
	private int end;
	private review_Criteria cri;
	private int realend;
	private int amount;
	private int total;
	private int page;

	
	private boolean next;
	private boolean prev;
	
	private List<Integer> pageList;
	
	public review_pageVO(review_Criteria cri , int total){
		this.total = total;
		this.amount = cri.getAmount();
		this.page = cri.getPage();
		this.cri = cri;
		
		this.end = (int)Math.ceil(this.page /10.0)*10;
		
		this.start =  this.end - 10 + 1;
		
		this.realend = (int)Math.ceil(total / (double)amount);
				
		
		if(this.end > this.realend) {
			this.end = this.realend;
		}
		
		this.next = this.realend > this.end ;
		
		this.prev = this.start > 1 ;
		
		this.pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
		

	}
	
}
