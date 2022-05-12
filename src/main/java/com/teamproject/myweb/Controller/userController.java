package com.teamproject.myweb.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teamproject.myweb.command.ExamineVO;
import com.teamproject.myweb.command.UserCheckVO;
import com.teamproject.myweb.command.UserVO;
import com.teamproject.myweb.review.boardService;
import com.teamproject.myweb.user.UserService;

@Controller
@RequestMapping("/user")
public class userController {
	//
	@Autowired
	boardService boardservice;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/myPage")
	public String myPage() {
		
		return "user/myPage";
	}
	
	@GetMapping("/myModify")
	public String myModify() {
		return "user/myModify";
	}
	
	@GetMapping("/userLogin")
	public String userLogin() {
		return "user/userLogin";
	}
	
	@PostMapping("/userCheck")
	public String userCheck(HttpSession session, UserCheckVO vo,Model model) {
		
		System.out.println(vo.toString());
		UserVO userVO = boardservice.userCheckes(vo);
		
		if(userVO == null) {
			model.addAttribute(vo);
			return "user/userLogin";
		} else {
			session.setAttribute("userVO", userVO);
			return "redirect:/main";
		}
		
	}
	
	@GetMapping("/userJoin")
	public String userJoin() {
		return "user/userJoin";
	}
	
	@GetMapping("/userFind")
	public String userFind() {
		return "user/userFind";
	}
	
	@GetMapping("/examine")
	public String examine() {
		return "user/examine";
	}
	
	@GetMapping("/examineForm")
	public String examineForm(ExamineVO vo, RedirectAttributes RA) {
		
		int result = userService.examine(vo);
		if(result == 1) {
			RA.addFlashAttribute("msg", "제출이 완료되었습니다 감사합니다");
		}
		System.out.println(vo.toString());
		return "redirect:/main";
	}
	
}
