package com.teamproject.myweb.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teamproject.myweb.command.ExamineVO;
import com.teamproject.myweb.command.UserCheckVO;
import com.teamproject.myweb.command.UserVO;
import com.teamproject.myweb.command.reviewVO;
import com.teamproject.myweb.review.boardService;
import com.teamproject.myweb.user.UserService;
import com.teamproject.myweb.util.review_Criteria;

@Controller
@RequestMapping("/user")
public class userController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("boardService")
	private boardService boardservice;

	@GetMapping("/myPage")
	public String myPage(@RequestParam("user_no") int user_no,
						 @RequestParam("user_name") String user_name,
						 review_Criteria cri,
						 Model model) {

		ArrayList<reviewVO> reviewVO = boardservice.myreviewList(user_name);
		UserVO userVO = userService.myPage(user_no);
		
		model.addAttribute("reviewVO", reviewVO);
		model.addAttribute("userVO", userVO);

		return "user/myPage";
	}

	@GetMapping("/myModify")
	public String myModify() {


		return "user/myModify";
	}

	@PostMapping("/modify")
	public String modify(UserVO vo,
						 UserCheckVO cvo,
						 HttpSession session,
						 RedirectAttributes RA) {
		
		int result = userService.modify(vo);
		
		if(result == 1) {
			//정보수정시 세션 다시저장
			session.removeAttribute("userVO");
			UserVO userVO = userService.userCheckes(cvo);
			session.setAttribute("userVO", userVO);
			RA.addFlashAttribute("msg", "정보가 수정되었습니다. 다시 로그인 해주세요");
			return "redirect:/main";
		} else {
			RA.addFlashAttribute("msg", "수정 실패");
			return "redirect:/user/myModify";
		}
		
	}

	@GetMapping("/userLogin")
	public String userLogin() {
		return "user/userLogin";
	}

	@PostMapping("/userCheck")
	public String userCheck(UserCheckVO vo,
							HttpSession session,
							Model model) {

		
		UserVO userVO = userService.userCheckes(vo);
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
		System.out.println("회원가입창");
		return "user/userJoin";
	}

	@PostMapping("/joinform")
	public String joinform(UserVO vo) {
		int result = userService.join(vo);
		System.out.println(result);
		return "redirect:/user/userLogin";
	}

	@GetMapping("/userFind")
	public String userFind() {
		return "user/userFind";
	}

	@PostMapping("/userDelete")
	public String userDelete(@RequestParam("user_no") int user_no, RedirectAttributes RA, HttpSession session) {

		int result = userService.userDelete(user_no);

		if(result == 1) {
			RA.addFlashAttribute("msg", "탈퇴 되었습니다");
			session.invalidate();
		} else {
			RA.addFlashAttribute("msg", "탈퇴에 실패 했습니다");
		}

		return "redirect:/main";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "redirect:/main";
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
