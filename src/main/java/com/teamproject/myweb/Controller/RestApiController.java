package com.teamproject.myweb.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamproject.myweb.command.MainVO;
import com.teamproject.myweb.command.reviewVO;
import com.teamproject.myweb.review.boardService;

@RestController
public class RestApiController {
	
	@Value("${project.upload.path}")
	private String uploadpath;
	
	@Autowired
	boardService boardservice;
	
	@GetMapping("/getLocation")
	public ArrayList<MainVO> getLocation() {
			
		ArrayList<MainVO> list= boardservice.getLocation();
		return list;
	}
	
	@GetMapping("/getFirstCategory")
	public ArrayList<MainVO> getFirstCategory(@RequestParam("review_theme") String review_theme) {
		
		ArrayList<MainVO> list = boardservice.getFirstCategory(review_theme);

		return list;
	}
	
	@GetMapping("/getSecondCategory")
	public ArrayList<MainVO> getSecondCategory(@RequestParam("review_theme") String review_theme,
											   @RequestParam("review_groups") String[] review_groups) {
		
		ArrayList<MainVO> list = boardservice.getSecondCategory(review_theme, review_groups);

		return null;
	}
	
	@GetMapping("/display")
	public byte[] display(@RequestParam("review_filename") String filename,
						  @RequestParam("review_uuid") String uuid,
						  @RequestParam("review_filepath") String filepath) {
		
		//System.out.println(filepath + "\\" + uuid + "_" + filename);
		byte[] result = null;
		

		if(filename == "") {
			File file = new File(uploadpath + "\\upload_fail\\upload.jpg");
			
			try {
				result = FileCopyUtils.copyToByteArray(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}  else {			
			File file = new File(uploadpath + "\\" + filepath + "\\" + uuid + "_" + filename);
			
			try {
				result = FileCopyUtils.copyToByteArray(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return result;
	}
}
