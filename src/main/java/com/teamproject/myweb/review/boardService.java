package com.teamproject.myweb.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.teamproject.myweb.command.MainVO;
import com.teamproject.myweb.command.Review_CategoryVO;
import com.teamproject.myweb.command.Review_Upload_CategoryVO;
import com.teamproject.myweb.command.Review_uploadVO;
import com.teamproject.myweb.command.UserCheckVO;
import com.teamproject.myweb.command.UserVO;
import com.teamproject.myweb.command.reviewVO;
import com.teamproject.myweb.util.review_Criteria;



public interface boardService {
	
	public int reviewRegist(reviewVO vo, List<MultipartFile> list,HashMap<Integer, Review_CategoryVO> map);
	
	public ArrayList<reviewVO> reviewList(review_Criteria cri);
	
	public ArrayList<reviewVO> myreviewList(String user_name);
	
	public int reviewTotal(review_Criteria cri);
	
	public reviewVO getDetail(int review_no);
	
	public int updateReview(reviewVO vo, List<MultipartFile> list,HashMap<Integer, Review_Upload_CategoryVO> map);
	
	public int deleteReview(int review_no);
	
	public int deletePhoto(Review_uploadVO vo);
	
	public int deleteCategory(Review_CategoryVO vo);
	
	public ArrayList<MainVO> getLocation();
	
	public ArrayList<MainVO> getPhoto_Category();
	
	public ArrayList<Review_uploadVO> getImg(int review_no);
	
	public ArrayList<Review_uploadVO> getUploadList(int review_no);
	
	public ArrayList<Review_CategoryVO> getCategory(int review_no);
	
	public ArrayList<MainVO> getFirstCategory(String review_theme);
	
	public ArrayList<MainVO> getSecondCategory(String review_theme ,String[] review_groups);
}
