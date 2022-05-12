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
public class MessageVO {

//		create table tbl_message(
//			m_no int primary key auto_increment,
//		    m_receiver varchar(50) not null,
//		    m_sender varchar(50) not null,
//		    m_content varchar(2000) not null,
//		    m_ok char(1) not null,
//		    m_senddate timestamp default now()
//		);
	
	private Integer mno;
	private String mreceiver;
	private String msender;
	private String mcontent;
	private LocalDateTime msenddate;
	private Integer mcheck;
}
