package com.teamproject.myweb.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teamproject.myweb.command.MessageVO;
import com.teamproject.myweb.message.MessageService;
import com.teamproject.myweb.util.message_Criteria;
import com.teamproject.myweb.util.message_PageVO;

@Controller
@RequestMapping("/message")
public class messageController {
	
	@Autowired
	@Qualifier("messageService")
	private MessageService messageService;

	//메시지 목록
	@GetMapping("/messageReceiveList")
	public String messageReceiveList(Model model, HttpSession session, message_Criteria cri) {
		
		message_PageVO receivepageVO = new message_PageVO(cri, messageService.getReceiveTotal("세션에아이디"));
		cri.setPagee((cri.getPage() - 1) * cri.getAmount());
		
		
		//세션의 값을 얻어서 리스트의 매개변수로 전달.

		ArrayList<MessageVO> re_list = messageService.re_getList("세션에아이디", cri);
		
		model.addAttribute("receiveList", re_list);
		model.addAttribute("receivepageVO", receivepageVO);
		
		return "message/messageReceiveList";
	}
	
	@GetMapping("/messageSendList")
	public String messageSendList(Model model, message_Criteria cri) {
		
		message_PageVO sendpageVO = new message_PageVO(cri, messageService.getSendTotal("세션에아이디"));
		cri.setPagee((cri.getPage() - 1) * cri.getAmount());
		ArrayList<MessageVO> list = messageService.getList("세션에아이디", cri);
		
		System.out.println(cri.toString());
		model.addAttribute("senderList", list);
		model.addAttribute("sendpageVO", sendpageVO);
		
		return "message/messageSendList";
	}
	
	//보낸메시지 상세
	@GetMapping("/messageDetail")
	public String messageDetail(@RequestParam("mno") int mno,
								Model model) {
		
		MessageVO mesVO = messageService.getDetail(mno);
		model.addAttribute("mesVO", mesVO);
		
		return "message/messageDetail";
	}
	
	@GetMapping("/messageReceive")
	public String messageReceive(@RequestParam("mno") int mno,
								 Model model) {
		
		MessageVO mesVO = messageService.getDetail(mno);
		model.addAttribute("mesVO", mesVO);
		
		return "message/messageReceive";
	}
	
	@GetMapping("/messageSend")
	public String messageSend() {
		return "message/messageSend";
	}
	
	@GetMapping("/messageReply")
	public String messageReply(@ModelAttribute("mreceiver") String mreceiver) {
		
		//System.out.println(mreceiver);
		return "message/messageReply";
	}
	
	//메시지 보내기폼
	@PostMapping("/sendForm")
	public String sendForm(MessageVO vo) {
		
		messageService.write(vo);
		
		return "redirect:/message/messageList";
	}
	
	//삭제
	@GetMapping("/deleteReceiveMessage")
	public String deleteReceiveMessage(@RequestParam("mno") int mno,
										RedirectAttributes RA) {
		int result = messageService.delete(mno);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "삭제 되었습니다");
		} else {
			RA.addFlashAttribute("msg", "삭제에 실패했습니다");
		}
		
		return "redirect:/message/messageReceiveList";
	}
	//삭제
	@GetMapping("/deleteSendMessage")
	public String deleteSendMessage(@RequestParam("mno") int mno,
									RedirectAttributes RA) {
		
		int result = messageService.delete(mno);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "삭제 되었습니다");
		} else {
			RA.addFlashAttribute("msg", "삭제에 실패했습니다");
		}
		
		return "redirect:/message/messageSendList";
	}
	
}