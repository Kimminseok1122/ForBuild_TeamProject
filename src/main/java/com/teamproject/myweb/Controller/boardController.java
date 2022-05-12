package com.teamproject.myweb.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.teamproject.myweb.command.CommentVO;
import com.teamproject.myweb.command.freeBoardVO;
import com.teamproject.myweb.comment.CommentService;
import com.teamproject.myweb.freeBoard.FreeBoardService;
import com.teamproject.myweb.review.boardService;
import com.teamproject.myweb.util.debate_Criteria;
import com.teamproject.myweb.util.debate_PageVO;
import com.teamproject.myweb.util.freeboard_Criteria;
import com.teamproject.myweb.util.freeboard_PageVO;
import com.teamproject.myweb.command.DebateVO;
import com.teamproject.myweb.command.Review_CategoryVO;
import com.teamproject.myweb.command.Review_Upload_CategoryVO;
import com.teamproject.myweb.debate.DebateService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.teamproject.myweb.command.Review_uploadVO;
import com.teamproject.myweb.command.reviewVO;
import com.teamproject.myweb.util.review_Criteria;
import com.teamproject.myweb.util.review_pageVO;


@Controller
@RequestMapping("/board")
public class boardController {
	
	@Autowired
	@Qualifier("freeboardService")
	private FreeBoardService freeBoardService; 
	
	@Autowired
	@Qualifier("CommentService")
	private CommentService commentService;
	
	@Autowired
	@Qualifier("debateService")
	private DebateService debateService;

	@Autowired
	private boardService boardservice;


	
	@GetMapping("/freeBoard")
	public String freeBoard(Model model, freeboard_Criteria cri, RedirectAttributes RA) {

		
		freeboard_PageVO pageVO = new freeboard_PageVO(cri, freeBoardService.getTotal(cri));
		cri.setLeftpage((cri.getPage() -1) * cri.getAmount());
		ArrayList<freeBoardVO> list = freeBoardService.getList(cri);
		
//		if(freeBoardService.getTotal(cri) == 0) {
//			RA.addFlashAttribute("msg", "검색 결과가 없습니다");
//			return "redirect:/board/freeBoard";
//		}
		//무한루프 발생됨

		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);

		return "board/freeBoard";
	}

	@PostMapping("/freeRegForm")
	public String freeRegForm(freeBoardVO vo, RedirectAttributes RA) {
		
		
		int result = freeBoardService.regist(vo);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "게시글이 등록되었습니다");
		} else {
			RA.addFlashAttribute("msg", "게시글 등록에 실패했습니다");
		}
		
		
		return "redirect:/board/freeBoard";
	}
	
	@GetMapping("/freeBoardReg")
	public String freeBoardReg() {
		return "board/freeBoardReg";
	}
	
	@GetMapping("/freeBoardDetail")
	public String freeBoardDetail(@RequestParam("free_bno") int free_bno, Model model) {
		
		freeBoardVO detail = freeBoardService.getDetail(free_bno);
		ArrayList<CommentVO> commentVO = commentService.commentList(free_bno);
		int total = commentService.commentTotal(free_bno);
		
		model.addAttribute("detail", detail);
		model.addAttribute("commentVO", commentVO);
		model.addAttribute("total", total);
																							
		return "board/freeBoardDetail";
	}
	
	@GetMapping("/freeBoardUpdate")
	public String freeBoardUpdate(@RequestParam("free_bno") int free_bno, Model model) {
			
		freeBoardVO detail = freeBoardService.getDetail(free_bno);
		model.addAttribute("detail", detail);
		
		return "board/freeBoardUpdate";
	}
	@PostMapping("/freeBoardUpdateForm")
	public String freeBoardUpdateForm(freeBoardVO vo,
								  RedirectAttributes RA) {
			
		int result = freeBoardService.update(vo);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "게시글이 수정되었습니다");
		}else {
			RA.addFlashAttribute("msg", "게시글 수정에 실패했습니다. 관리자에게 문의하세요");
		}
		
		return "redirect:/board/freeBoard";
	}
	
	@PostMapping("/freeBoardDelete")
	public String freeBoardDelete(@RequestParam("free_bno") int free_bno,
								  RedirectAttributes RA) {
	
		int result = freeBoardService.delete(free_bno);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "게시글이 삭제되었습니다");
		} else {
			RA.addFlashAttribute("msg", "게시글 삭제에 실패했습니다. 관리자에게 문의하세요");
		}
		return "redirect:/board/freeBoard";
	}
	
	@GetMapping("/commentReg")
	public String commentReg(CommentVO vo, RedirectAttributes RA) {
		
		int result = commentService.commentReg(vo);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "댓글이 등록되었습니다");
		} else {
			RA.addFlashAttribute("msg", "댓글 등록에 실패했습니다");
		}
	
		return "redirect:/board/freeBoardDetail?free_bno=" + vo.getFree_bno();
	}
	
	@GetMapping("/commentDelete")
	public String commentDelete(@RequestParam("cno") int cno,
								@RequestParam("free_bno") int free_bno,
								RedirectAttributes RA) {
		
			int result = commentService.commentDelete(cno);
			
			if(result == 1) {
				RA.addFlashAttribute("msg", "댓글이 삭제되었습니다");
			} else {
				RA.addFlashAttribute("msg", "댓글 삭제에 실패했습니다. 관리자에게 문의하세요");
			}
		
		return "redirect:/board/freeBoardDetail?free_bno=" + free_bno ;
	}
	

	@GetMapping("/reviewBoard")
	public String reviewBoard(Model model, review_Criteria cri) {

		
		
		cri.setLimitLeft((cri.getPage()-1)*cri.getAmount() );
		
		review_pageVO vo = new review_pageVO(cri , boardservice.reviewTotal(cri));
		
		ArrayList<reviewVO> reviewList =  boardservice.reviewList(cri);
		

		
		model.addAttribute("pageVO", vo);
		model.addAttribute("reviewVO", reviewList);
		return "board/reviewBoard";
	}
	
	
	@GetMapping("/reviewReg")
	public String reivewReg() {
		return "board/reviewReg";
	}
	
	@GetMapping("/reviewUpdate")
	public String reviewUpdate(@RequestParam("review_no") int review_no, Model model) {
		
		reviewVO vo =  boardservice.getDetail(review_no);
		ArrayList<Review_uploadVO> list = boardservice.getImg(review_no);
		
		model.addAttribute("UpdateVO", vo);
		model.addAttribute("Imglist", list);
		
		return "board/reviewUpdate";
	}
	
	@PostMapping("/reviewModfiy")
	public String reviewModfiy(reviewVO reviewvo, RedirectAttributes RA,@RequestParam("file") List<MultipartFile> list, Review_Upload_CategoryVO category) {

		
		reviewVO vo	=	reviewVO.builder().review_category(category.getReview_category_detail_nm()[0] + ">" + 
														   category.getReview_category_detail_nm()[1] + ">" +
														   category.getReview_category_detail_nm()[2])
												.review_content(reviewvo.getReview_content())
												.review_writer(reviewvo.getReview_writer())
												.review_lat(reviewvo.getReview_lat())
												.review_lng(reviewvo.getReview_lng())
												.review_title(reviewvo.getReview_title())
												.review_content(reviewvo.getReview_content())
												.review_realAddress(reviewvo.getReview_realAddress())
												.review_no(reviewvo.getReview_no())
												.build();
		HashMap<Integer, Review_Upload_CategoryVO> map = new HashMap<Integer, Review_Upload_CategoryVO>();


		map.put(0, category);


		list = list.stream().filter( f -> f.isEmpty() == false).collect( Collectors.toList());
		
		for(MultipartFile f : list) {
			if(f.getContentType().contains("image") == false) {
				RA.addFlashAttribute("msg","이미지파일을 넣으세요");
				return "redirect:/board/reviewBoard";
			}
		}
		
		int result = boardservice.updateReview(vo,list,map);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "수정성공");
		} else {
			RA.addFlashAttribute("msg", "수정실패");
		}
		
		return "redirect:/board/reviewBoard";
	}
	
	
	@PostMapping("/reviewDetele")
	public String reviewDetele(@RequestParam("review_no") int review_no,RedirectAttributes RA) {
		
		int result = boardservice.deleteReview(review_no);
		
		ArrayList<Review_uploadVO> list1 = boardservice.getUploadList(review_no);
		ArrayList<Review_CategoryVO> list2 = boardservice.getCategory(review_no);
		
		for(Review_uploadVO vo : list1) {
			boardservice.deletePhoto(vo);
		}
		
		for(Review_CategoryVO vo : list2) {
			boardservice.deleteCategory(vo);
		}
			
		
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "삭제성공");
		} else {
			RA.addFlashAttribute("msg", "삭제실패");
		}
		
		return "redirect:/board/reviewBoard";
	}
	
	@GetMapping("/reviewDetail")
	public String reviewdetail(@RequestParam("review_no") int review_no, Model model) {
		
		
		reviewVO vo =  boardservice.getDetail(review_no);
		ArrayList<Review_uploadVO> list = boardservice.getImg(review_no);
		
		model.addAttribute("detailVO", vo);
		model.addAttribute("Imglist", list);
		return "board/reviewDetail";
	}
	
	@PostMapping("/reviewForm")
	public String reviewform(reviewVO reviewvo, RedirectAttributes RA,@RequestParam("file") List<MultipartFile> list, Review_Upload_CategoryVO category) {
		
		reviewVO vo	=	reviewVO.builder().review_category(category.getReview_category_detail_nm()[0] + " > " + 
														   category.getReview_category_detail_nm()[1] + " > " +
														   category.getReview_category_detail_nm()[2])
										  .review_content(reviewvo.getReview_content())
										  .review_writer(reviewvo.getReview_writer())
										  .review_lat(reviewvo.getReview_lat())
										  .review_lng(reviewvo.getReview_lng())
										  .review_title(reviewvo.getReview_title())
										  .review_content(reviewvo.getReview_content())
										  .review_realAddress(reviewvo.getReview_realAddress())
										  .review_theme(reviewvo.getReview_theme())
										  .build();
		HashMap<Integer, Review_CategoryVO> map = new HashMap<Integer, Review_CategoryVO>();


		for(int i = 0 ; i < category.getReview_category_detail_lv().length ; i++) {
			Review_CategoryVO voi =	Review_CategoryVO.builder().review_group(category.getReview_group()[i])
																.review_category_lv(category.getReview_category_lv()[i])
																.review_category_detail_lv(category.getReview_category_detail_lv()[i])
																.review_category_nm(category.getReview_category_nm()[i])
																.review_category_detail_nm(category.getReview_category_detail_nm()[i])
																.review_category_parent_lv(category.getReview_category_parent_lv()[i])
																.review_category_detail_parent_lv(category.getReview_category_detail_parent_lv()[i])
																.review_no(null)
																.review_writer(category.getReview_writer())
																.build();
																map.put(i, voi);
		}

				
		list = list.stream().filter( f -> f.isEmpty() == false).collect( Collectors.toList());
		
		for(MultipartFile f : list) {
			if(f.getContentType().contains("image") == false) {
				RA.addFlashAttribute("msg","이미지파일을 넣으세요");
				return "redirect:/board/reviewBoard";
			}
		}
		int result = boardservice.reviewRegist(vo,list,map);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "등록성공");
		} else {
			RA.addFlashAttribute("msg", "등록실패");
		}
		
		return "redirect:/board/reviewBoard";
		

	}
	
	@GetMapping("/debateBoard")
	public String debateBoard(Model model,
							  debate_Criteria cri,
							  RedirectAttributes RA) {
		
		debate_PageVO pageVO = new debate_PageVO(cri, debateService.getTotal(cri));
		cri.setLeftpage((cri.getPage() -1) * cri.getAmount());
		ArrayList<DebateVO>list = debateService.getList(cri);
		
		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		
		return "board/debateBoard";
	}
	
	@GetMapping("/debateReg")
	public String debateReg() {
		
		return "board/debateReg";
	}
	
	@PostMapping("/debateForm")
	public String debateForm(DebateVO vo,
							 RedirectAttributes RA) {
		int result =  debateService.regist(vo);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "게시글이 등록되었습니다");
		} else {
			RA.addFlashAttribute("msg", "게시글 등록에 실패했습니다");
		}
		
		return "redirect:/board/debateBoard";
	}
	
	@GetMapping("/debateUpdate")
	public String debateUpdate(Model model,
							   @RequestParam("debate_no") int debate_no) {
		
		DebateVO debateVO = debateService.getDetail(debate_no);
		model.addAttribute("debateVO", debateVO);
		
//		ArrayList<DebateVO> list = debateService.getList();
//		model.addAttribute("list", list);
		
		return "board/debateUpdate";
	}
	
	@PostMapping("/updateForm")
	public String updateForm(DebateVO vo,
							 RedirectAttributes RA) {
		
		int result = debateService.update(vo);
		
		
		return "redirect:/board/debateBoard";
		
	}
	
	@PostMapping("/deleteForm")
	public String deleteForm(@RequestParam("debate_no") int debate_no,
							 RedirectAttributes RA) {
		
		int result = debateService.delete(debate_no);
		
		return "redirect:/board/debateBoard";
		
	}

	
	
}
