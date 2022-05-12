package com.teamproject.myweb.review;

import java.util.ArrayList;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.teamproject.myweb.command.MainVO;
import com.teamproject.myweb.command.Review_CategoryVO;
import com.teamproject.myweb.command.Review_uploadVO;
import com.teamproject.myweb.command.UserCheckVO;
import com.teamproject.myweb.command.UserVO;
import com.teamproject.myweb.command.reviewVO;
import com.teamproject.myweb.util.review_Criteria;



@Mapper
public interface boardMapper {
	
	public int reviewRegist(reviewVO vo);
	
	public int reviewFileRegist(Review_uploadVO	uploadvo);
	
	public int reviewCategoryRegist(Review_CategoryVO vo);
	
	public ArrayList<reviewVO> reviewList(review_Criteria cri);
	
	public int reviewTotal(review_Criteria cri);
	
	public reviewVO getDetail(int review_no);
	
	public int updateReview(reviewVO vo);
	
	public int reviewFileUpdate(Review_uploadVO	uploadvo);
	
	public int reviewCategoryUpdate(Review_CategoryVO vo);
	
	public int deleteReview(int review_no);
	
	public int deletePhoto(Review_uploadVO vo);
	
	public int deleteCategory(Review_CategoryVO vo);
	
	public ArrayList<MainVO> getLocation();
	
	public ArrayList<MainVO> getPhoto_Category();
	
	public ArrayList<Review_uploadVO> getImg(int review_no);
	
	public ArrayList<Review_uploadVO> getUploadList(int review_no);
	
	public ArrayList<Review_CategoryVO> getCategory(int review_no);
	
	public ArrayList<Integer> getUploadPrimeKey(int review_no);
	
	public ArrayList<Integer> getCategoryPrimeKey(int review_no);
	
	public ArrayList<MainVO> getFirstCategory(String review_theme);
	
	public ArrayList<MainVO> getSecondCategory(@Param("review_theme") String review_theme ,@Param("review_groups") String[] review_groups);
}
