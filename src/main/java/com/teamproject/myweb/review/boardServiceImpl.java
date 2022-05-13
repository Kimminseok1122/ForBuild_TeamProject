package com.teamproject.myweb.review;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.teamproject.myweb.command.MainVO;
import com.teamproject.myweb.command.Review_CategoryVO;
import com.teamproject.myweb.command.Review_Upload_CategoryVO;
import com.teamproject.myweb.command.Review_uploadVO;
import com.teamproject.myweb.command.UserCheckVO;
import com.teamproject.myweb.command.UserVO;
import com.teamproject.myweb.command.reviewVO;
import com.teamproject.myweb.util.review_Criteria;



@Service("boardService")
public class boardServiceImpl implements boardService{
	
	@Value("${project.upload.path}")
	private String uploadpath;
	
	@Autowired
	boardMapper boardmapper;
	
	@Transactional
	@Override
	public int reviewRegist(reviewVO vo, List<MultipartFile> list, HashMap<Integer, Review_CategoryVO> map) {
		
		int result = boardmapper.reviewRegist(vo);
		
		for(MultipartFile f : list) {
			
			String originname = f.getOriginalFilename();
			
			String filename = originname.substring( originname.lastIndexOf("\\")+1 );
			
			String filepath = filemaker(); 
			
			String uuid = UUID.randomUUID().toString();
			
			String savename = uploadpath + "\\" + filepath + "\\" + uuid + "_" + filename;
			
			
			File file = new File(savename);
			
			try {
				f.transferTo(file);
			}  catch (IOException e) {
				e.printStackTrace();
			}
			
			Review_uploadVO	uploadvo = Review_uploadVO.builder().review_filename(filename)
															 .review_filepath(filepath)
															 .review_uuid(uuid)
															 .review_writer(vo.getReview_writer())
															 .build();
			boardmapper.reviewFileRegist(uploadvo);
			
		}
	
		
		Review_CategoryVO vo0 = map.get(0);
		boardmapper.reviewCategoryRegist(vo0);
		
		Review_CategoryVO vo1 = map.get(1);
		boardmapper.reviewCategoryRegist(vo1);
		
		Review_CategoryVO vo2 = map.get(2);
		boardmapper.reviewCategoryRegist(vo2);
		
		
		return result;			
		
	}
	
	public String filemaker() {
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		String date = LocalDateTime.now().format(format);
		
		File file = new File(uploadpath + "\\" + date);
		if(file.exists() == false) {
			file.mkdir();
		}
		return date;
	}

	@Override
	public ArrayList<reviewVO> reviewList(review_Criteria cri) {
		return boardmapper.reviewList(cri);
	}

	@Override
	public int reviewTotal(review_Criteria cri) {
		return boardmapper.reviewTotal(cri);
	}

	@Override
	public reviewVO getDetail(int review_no) {
		return boardmapper.getDetail(review_no);
	}

	@Override
	public int updateReview(reviewVO vo, List<MultipartFile> list,HashMap<Integer, Review_Upload_CategoryVO> map) {
				
		
		int file_i = 0;
		for(MultipartFile f : list) {
			
			String originname = f.getOriginalFilename();
			
			String filename = originname.substring( originname.lastIndexOf("\\")+1 );
			
			String filepath = filemaker(); 
			
			String uuid = UUID.randomUUID().toString();
			
			String savename = uploadpath + "\\" + filepath + "\\" + uuid + "_" + filename;
			
			File file = new File(savename);
			
			try {
				f.transferTo(file);
			}  catch (IOException e) {
				e.printStackTrace();
			}
			
			ArrayList<Integer> file_list = boardmapper.getUploadPrimeKey(vo.getReview_no());
			
			Review_uploadVO	uploadvo = Review_uploadVO.builder().review_filename(filename)
															 .review_filepath(filepath)
															 .review_uuid(uuid)
															 .review_writer(vo.getReview_writer())
															 .review_no(vo.getReview_no())
															 .review_upload_no(file_list.get(file_i))
															 .build();
			boardmapper.reviewFileUpdate(uploadvo);
			file_i++;
			
		}
	
		Review_Upload_CategoryVO category = map.get(0);
		
		for(int i = 0 ; i < category.getReview_category_detail_lv().length ; i++) {
			
		ArrayList<Integer> Category_list = boardmapper.getCategoryPrimeKey(vo.getReview_no());
		Review_CategoryVO voi =	Review_CategoryVO.builder().review_group(category.getReview_group()[i])
								.review_category_lv(category.getReview_category_lv()[i])
								.review_category_detail_lv(category.getReview_category_detail_lv()[i])
								.review_category_nm(category.getReview_category_nm()[i])
								.review_category_detail_nm(category.getReview_category_detail_nm()[i])
								.review_category_parent_lv(category.getReview_category_parent_lv()[i])
								.review_category_detail_parent_lv(category.getReview_category_detail_parent_lv()[i])
								.review_no(vo.getReview_no())
								.review_writer(category.getReview_writer())
								.review_category_no(Category_list.get(i))
								.build();
		
			boardmapper.reviewCategoryUpdate(voi);
		}
		
		int result = boardmapper.updateReview(vo);
		
		return result;
	}

	@Override
	public int deleteReview(int review_no) {
		return boardmapper.deleteReview(review_no);
	}

	@Override
	public ArrayList<MainVO> getLocation() {
		ArrayList<MainVO> list = boardmapper.getLocation();
		
		
		ArrayList<MainVO> newlist =  new ArrayList<MainVO>();
		int i = 0;
		
		for(MainVO vo : list) {
			if(list.get(i).getReview_filename() == null) {
				String[] review_filenames  = {"",""};
				String[] review_filepaths  = {"",""};
				String[] review_fileuuids  = {"",""};
				MainVO newvo =	MainVO.builder().review_title(list.get(i).getReview_title())
						.review_lat(list.get(i).getReview_lat())
						.review_lng(list.get(i).getReview_lng())
						.review_category(list.get(i).getReview_category())
						.review_realaddress(list.get(i).getReview_realaddress())
						.review_filenames(review_filenames)
						.review_filepaths(review_filepaths)
						.review_uuids(review_fileuuids)
						.review_no(list.get(i).getReview_no())
						.build();
				newlist.add(newvo);
				i++;
				if(i == list.size())break;
				continue;
			} else {
				String[] review_filenames  = {list.get(i).getReview_filename() , list.get(i+1).getReview_filename()};
				String[] review_filepaths  = {list.get(i).getReview_filepath() , list.get(i+1).getReview_filepath()};
				String[] review_fileuuids  = {list.get(i).getReview_uuid() , list.get(i+1).getReview_uuid()};
				

				MainVO newvo =	MainVO.builder().review_title(list.get(i).getReview_title())
						.review_lat(list.get(i).getReview_lat())
						.review_lng(list.get(i).getReview_lng())
						.review_category(list.get(i).getReview_category())
						.review_realaddress(list.get(i).getReview_realaddress())
						.review_filenames(review_filenames)
						.review_filepaths(review_filepaths)
						.review_uuids(review_fileuuids)
						.review_no(list.get(i).getReview_no())
						.review_group(list.get(i).getReview_group())
						.build();				
				newlist.add(newvo);
			}
			

			i++;
			i++;
			if(i == list.size())break;
		}
		
		return newlist;
	}

	@Override
	public UserVO userCheckes(UserCheckVO vo) {
		return boardmapper.userCheckes(vo);
	}

	@Override
	public ArrayList<Review_uploadVO> getImg(int review_no) {
		return boardmapper.getImg(review_no);
	}

	@Override
	public int deletePhoto(Review_uploadVO vo) {
		return boardmapper.deletePhoto(vo);
	}

	@Override
	public int deleteCategory(Review_CategoryVO vo) {
		return boardmapper.deleteCategory(vo);
	}

	@Override
	public ArrayList<Review_uploadVO> getUploadList(int review_no) {
		return boardmapper.getUploadList(review_no);
	}

	@Override
	public ArrayList<Review_CategoryVO> getCategory(int review_no) {
		return boardmapper.getCategory(review_no);
	}

	@Override
	public ArrayList<MainVO> getPhoto_Category() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MainVO> getFirstCategory(String review_theme) {
		
		ArrayList<MainVO> list = boardmapper.getFirstCategory(review_theme);
		
		ArrayList<MainVO> newlist =  new ArrayList<MainVO>();
		int i = 0;
		
		for(MainVO vo : list) {
			String[] review_filenames  = {list.get(i).getReview_filename() , list.get(i+1).getReview_filename()};
			String[] review_filepaths  = {list.get(i).getReview_filepath() , list.get(i+1).getReview_filepath()};
			String[] review_fileuuids  = {list.get(i).getReview_uuid() , list.get(i+1).getReview_uuid()};
			
			
			MainVO newvo =	MainVO.builder().review_title(list.get(i).getReview_title())
					.review_lat(list.get(i).getReview_lat())
					.review_lng(list.get(i).getReview_lng())
					.review_category(list.get(i).getReview_category())
					.review_realaddress(list.get(i).getReview_realaddress())
					.review_filenames(review_filenames)
					.review_filepaths(review_filepaths)
					.review_uuids(review_fileuuids)
					.review_no(list.get(i).getReview_no())
					.review_group(list.get(i).getReview_group())
					.review_theme(review_theme)
					.build();				
			newlist.add(newvo);
			i++;
			i++;
			if(i == list.size())break;
		}
		
		return newlist;
	}

	@Override
	public ArrayList<MainVO> getSecondCategory(String review_theme, String[] review_groups) {
		ArrayList<MainVO> list = boardmapper.getSecondCategory( review_theme, Arrays.asList(review_groups)  );
		return list;
	}

	
	
	
	
	
}
