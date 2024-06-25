package com.multi.seoulsoul.soulLog.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.multi.seoulsoul.soulLog.model.dto.CategoryDTO;
import com.multi.seoulsoul.soulLog.model.dto.FilesDTO;
import com.multi.seoulsoul.soulLog.model.dto.LocationDTO;
import com.multi.seoulsoul.soulLog.model.dto.PageDTO;
import com.multi.seoulsoul.soulLog.model.dto.SoulLogDTO;
import com.multi.seoulsoul.soulLog.model.dto.WriterDTO;
import com.multi.seoulsoul.soulLog.service.SoulLogService;


@Controller
@RequestMapping("/soulLog")
public class SoulLogController {
	
	
	private final SoulLogService soulLogService;
	
	public SoulLogController(SoulLogService soulLogService) {
		this.soulLogService = soulLogService;
	}
	
    
	// 소울로그 메인 리스트 페이지로 이동
	@RequestMapping("/soulLogMain")
	public String soulLogMain(PageDTO pageDTO, Model model) {
		
		pageDTO.setStartAndStartIndex(pageDTO.getPage());
		
		System.out.println("시작 페이지는 >>>> " + pageDTO.getStart());
		
		try {
			
			List<SoulLogDTO> soulLogList = soulLogService.selectSoulLogList(pageDTO);
			
			System.out.println("가져온 리스트 >>>> " + soulLogList);
			
			model.addAttribute("soulLogList", soulLogList);
			
			int SoullogCount = soulLogService.selectSoulLogCount();
			
			// 글 수를 토대로 전체 페이지 수를 구한다.
			// 글 수가 15의 배수면 +1을 하지 않는다.
			int pages = SoullogCount / 15;

			// 글 수가 15의 배수가 아니면 +1을 한다.
			if (SoullogCount % 15 != 0) {
				pages += 1;
			}

			model.addAttribute("pages", pages);
			
			return "soulLog/soulLogMain";
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return "common/errorPage";
			
		}
			
	}
	
	
	// 페이징 버튼 눌렀을 때 소울로그 리스트를 조회 (ajax)
	@RequestMapping("/soulLogList")
	public String soulLogList(PageDTO pageDTO, Model model) {
		
		pageDTO.setStartAndStartIndex(pageDTO.getPage());
		
		System.out.println("시작 페이지는 >>>> " + pageDTO.getStart());
		
		try {
			
			List<SoulLogDTO> soulLogList = soulLogService.selectSoulLogList(pageDTO);
			
			System.out.println("가져온 리스트 >>>> " + soulLogList);
			
			model.addAttribute("soulLogList", soulLogList);
			
			return "soulLog/soulLogList";
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return "common/errorPage";
			
		}
		
	}
	
	
	// 소울로그 작성 폼으로 이동
	@RequestMapping("/soulLogInsertForm")
	public void soulLogInsertForm() {
	
	}
	
	
	// 자치구 리스트를 조회 (json, ajax)
	@RequestMapping("/locationList")
	@ResponseBody // json으로 요청한 곳으로 전달
	public List<LocationDTO> locationList() throws Exception {
			
		List<LocationDTO> locationList = soulLogService.selectLocationList();
		
		System.out.println("가져온 자치구 리스트는 >>>> " + locationList);
			
		return locationList;
		
	}
	
	
	// 카테고리 리스트를 조회 (json, ajax)
	@RequestMapping("/categoryList")
	@ResponseBody // json으로 요청한 곳으로 전달
	public List<CategoryDTO> categoryList() throws Exception {
				
		List<CategoryDTO> categoryList = soulLogService.selectCategoryList();
			
		System.out.println("가져온 카테고리 리스트는 >>>> " + categoryList);
				
		return categoryList;
			
	}
	
	
	// 소울로그 작성
	@PostMapping("/insertSoulLog")
	public String insertSoulLog(LocationDTO locationDTO, CategoryDTO categoryDTO, WriterDTO writerDTO, SoulLogDTO soulLogDTO, HttpServletRequest request, MultipartFile[] imgList, Model model) {
		
		System.out.println("locationDTO는 >>>>> " + locationDTO);
		System.out.println("categoryDTO는 >>>>> " + categoryDTO);
		System.out.println("writerDTO는 >>>>> " + writerDTO);
		
		soulLogDTO.setLocation(locationDTO);
		soulLogDTO.setCategory(categoryDTO);
		soulLogDTO.setWriter(writerDTO);
		
		System.out.println("soulLogDTO는 >>>>> " + soulLogDTO);
		
		HttpSession session = request.getSession();
		
		String root = session.getServletContext().getRealPath("resources");
		
		System.out.println("root : " + root);
		
		String filePath = root + "/uploadFiles";
		
		System.out.println("img 개수 >>>>> " + imgList.length);
		
		// img가 있을 때
		if(imgList.length > 0) {
					
			// java.io의 File을 import
			// filePath에 폴더가 없으면 폴더 생성
			File mkdir = new File(filePath);
			if(!mkdir.exists()) {
				mkdir.mkdirs();
			}
			
			List<FilesDTO> fileList = new ArrayList<>();
			
			for(MultipartFile img : imgList) {
				
				if (!img.isEmpty()) {
					
					// 파일명 변경 처리
					// 첨부 파일의 이름을 가져온다.
					String originFileName = img.getOriginalFilename();
					// 거기서 확장자만 잘라서 변수에 저장한다.
					String ext = originFileName.substring(originFileName.lastIndexOf("."));
					// 랜덤한 파일이름을 생성한 후 뒤에 확장자를 붙인다.
					String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
					
					FilesDTO file = new FilesDTO();
					file.setOriginalName(originFileName);
					file.setSavedName(savedName);
					
					fileList.add(file);
					
					try {
						
						img.transferTo(new File(filePath + "/" + savedName));
								
					} catch (Exception e) {
								
						e.printStackTrace();
	
						// 오류 발생 시 생성한 파일을 삭제한다.
						new File(filePath + "/" + savedName).delete();
								
						model.addAttribute("msg", "소울로그 작성 실패..");
	
						return "common/errorPage";
							
					}
				
				}
				
			}
			
			soulLogDTO.setFiles(fileList);
			
			System.out.println("soulLogDTO는 >>>>> " + soulLogDTO);
					
		} // img가 있을 때
		
		try {
			
			soulLogService.insertSoulLog(soulLogDTO);
			
			// mapper에서 useGeneratedKeys="true" keyProperty="soulLogNo" 해줬기 때문에 soulLogDTO에 insert한 게시글의 No(PK)가 담겨 있다.
			int soulLogNo = soulLogDTO.getSoulLogNo();
			
			return "redirect:/soulLog/soulLogDetail?soulLogNo="+soulLogNo;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			model.addAttribute("msg", "소울로그 작성 실패..");
			
			return "common/errorPage";
			
		}
		
		
	}
		

	
}
