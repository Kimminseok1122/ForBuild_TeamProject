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
import com.teamproject.myweb.util.Criteria;
import com.teamproject.myweb.util.PageVO;

@Controller
@RequestMapping("/message")
public class messageController {
   
   @Autowired
   @Qualifier("messageService")
   private MessageService messageService;

   //메시지 목록
   @GetMapping("/messageReceiveList")
   public String messageReceiveList(@RequestParam("user_id") String user_id,
		   							Model model, HttpSession session, Criteria cri) {
      System.out.println(user_id);
      PageVO receivepageVO = new PageVO(cri, messageService.getReceiveTotal(user_id));
      cri.setPagee((cri.getPage() - 1) * cri.getAmount());
      
      
      //세션의 값을 얻어서 리스트의 매개변수로 전달.

      ArrayList<MessageVO> re_list = messageService.re_getList(user_id, cri);
      System.out.println(session.toString());
      
      model.addAttribute("receiveList", re_list);
      model.addAttribute("receivepageVO", receivepageVO);
      
      return "message/messageReceiveList";
   }
   
   @GetMapping("/messageSendList")
   public String messageSendList(@RequestParam("user_id") String user_id,
		   						  Model model, Criteria cri) {
      
      PageVO sendpageVO = new PageVO(cri, messageService.getSendTotal(user_id));
      cri.setPagee((cri.getPage() - 1) * cri.getAmount());
      ArrayList<MessageVO> list = messageService.getList(user_id, cri);
      
      System.out.println(list.toString());
      model.addAttribute("senderList", list);
      model.addAttribute("sendpageVO", sendpageVO);
      
      return "message/messageSendList";
   }
   
   //보낸메시지 상세
   @GetMapping("/messageSendDetail")
   public String messageDetail(@RequestParam("mno") int mno,
                        Model model) {
      
      MessageVO mesVO = messageService.getSendDetail(mno);
      model.addAttribute("mesVO", mesVO);
      
      return "message/messageDetail";
   }
   
   @GetMapping("/messageReceiveDetail")
   public String messageReceive(@RequestParam("mno") int mno,
                         Model model) {
      
      messageService.update(mno);
      
      MessageVO mesVO = messageService.getReceiveDetail(mno);
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
   public String sendForm(@RequestParam("user_id") String user_id,
		   				  MessageVO vo) {
      
      messageService.write(vo);
      
      return "redirect:/message/messageSendList?user_id=" + user_id;
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
         RA.addFlashAttribute("msg", "메시지가 삭제 되었습니다");
      } else {
         RA.addFlashAttribute("msg", "메시지 삭제에 실패했습니다");
      }
      
      System.out.println(result);
      
      return "redirect:/message/messageSendList";
   }

   
}