package com.multi.seoulsoul.achieve.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.multi.seoulsoul.achieve.model.dto.AchCateDTO;
import com.multi.seoulsoul.achieve.model.dto.AchCateIconsDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaIconsDTO;
import com.multi.seoulsoul.achieve.service.AchieveService;
import com.multi.seoulsoul.report.model.dto.ReportDTO;
import com.multi.seoulsoul.report.service.ReportService;
import com.multi.seoulsoul.soulLog.model.dto.CategoryDTO;
import com.multi.seoulsoul.soulLog.model.dto.LocationDTO;
import com.multi.seoulsoul.soulLog.service.SoulLogService;
import com.multi.seoulsoul.user.model.dto.UserDTO;


@Controller
@RequestMapping("/admin")
public class AchieveController {
	
	private final AchieveService achieveService;
	private final ReportService reportService;
	private final SoulLogService soulLogService;
	@Autowired
	public AchieveController(AchieveService achieveService, ReportService reportService, SoulLogService soulLogService) {
		this.achieveService = achieveService;
		this.reportService = reportService;
		this.soulLogService = soulLogService;
	}
	
	@GetMapping("/adminMain")
	public String adminMainPage(Model model) throws Exception {
		System.out.println("관리자 메인 페이지 호출 성공.");
		
		List<AchLocaDTO> achieveLocaList = achieveService.achieveLocaList();
		List<AchCateDTO> achieveCateList = achieveService.achieveCateList();
		
		List<ReportDTO> reportList = reportService.reportList();
		List<UserDTO> userList = achieveService.userList();
		
		List<UserDTO> blackList = achieveService.blackList();
        
        model.addAttribute("achieveLocaList", achieveLocaList);
        model.addAttribute("achieveCateList", achieveCateList);
        
        model.addAttribute("reportList", reportList);
        model.addAttribute("userList", userList);
        
        model.addAttribute("blackList", blackList);
        
		return "achieve/adminMain";
	}
	
	@PostMapping("/blacklistUser")
	@ResponseBody
	public String blacklistUser(int userNo) {
		try {
            achieveService.updateBlacklistStatus(userNo, 'Y');
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
	}
	
	@GetMapping("/achLocaInsertForm")
	public String achLocaInsertForm(Model model) throws Exception {
		List<LocationDTO> locationList = soulLogService.selectLocationList();
        model.addAttribute("locationList", locationList);
		return "achieve/achLocaInsertForm";
	}
	
	@GetMapping("/achCateInsertForm")
	public String achCateInsertForm(Model model) throws Exception {
		List<CategoryDTO> categoryList = soulLogService.selectCategoryList();
        model.addAttribute("categoryList", categoryList);
		return "achieve/achCateInsertForm";
	}
	
	@PostMapping("/achLocaInsertForm")
    public String achLocaInsertForm(AchLocaDTO achLocaDTO,
                                    AchLocaIconsDTO achLocaIconsDTO,
                                    HttpServletRequest request, 
                                    MultipartFile singleFile, 
                                    Model model) throws Exception {
        
        System.out.println("Post >> achLocaInsertForm.");
        System.out.println("Post >> " + achLocaDTO);
        
        try {
            achieveService.insertAchieveLoca(achLocaDTO);
            
            System.out.println("업적 생성 성공.");
            
            System.out.println("singleFile : " + singleFile);
            
            String root = request.getSession().getServletContext().getRealPath("resources");
            
            System.out.println("root : " + root);
            
            String filePath = root + "/uploadFiles";
            
            File mkdir = new File(filePath);
            if (!mkdir.exists()) {
                mkdir.mkdirs();
            }
            
            String originFileName = singleFile.getOriginalFilename();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
            
            saveFile(singleFile, filePath, savedName);
            
            model.addAttribute("savedName", savedName);
            
            System.out.println("img넣기 전 >> " + achLocaIconsDTO);
            achLocaIconsDTO.setAchLoca(achLocaDTO);  
            achLocaIconsDTO.setOriginalName(originFileName);
            achLocaIconsDTO.setSavedName(savedName);
            System.out.println("img넣은 후 >> " + achLocaIconsDTO);
            
            achieveService.insertLocaIcons(achLocaIconsDTO);
            model.addAttribute("achLocaIconsDTO", achLocaIconsDTO);
            System.out.println("insertMovie 후 >> " + achLocaIconsDTO);
            
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "업적 생성 실패: " + e.getMessage());
            return "/common/errorPage";
        }
        
        return "redirect:/admin/adminMain";
    }

    private void saveFile(MultipartFile file, String filePath, String fileName) throws Exception {
        try {
            file.transferTo(new File(filePath + "/" + fileName));
        } catch (Exception e) {
            new File(filePath + "/" + fileName).delete();
            throw new Exception("파일 업로드 실패");
        }
    }
	
    @PostMapping("/achCateInsertForm")
    public String achCateInsertForm(AchCateDTO achCateDTO,
                                    AchCateIconsDTO achCateIconsDTO,
                                    HttpServletRequest request, 
                                    MultipartFile singleFile, 
                                    Model model) throws Exception {
        
        System.out.println("Post >> achCateInsertForm.");
        System.out.println("Post >> " + achCateDTO);
        
        try {
            achieveService.insertAchieveCate(achCateDTO);
            
            System.out.println("업적 생성 성공.");
            
            System.out.println("singleFile : " + singleFile);
            
            String root = request.getSession().getServletContext().getRealPath("resources");
            
            System.out.println("root : " + root);
            
            String filePath = root + "/uploadFiles";
            
            File mkdir = new File(filePath);
            if (!mkdir.exists()) {
                mkdir.mkdirs();
            }
            
            String originFileName = singleFile.getOriginalFilename();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
            
            saveFile(singleFile, filePath, savedName);
            
            model.addAttribute("savedName", savedName);
            
            System.out.println("img넣기 전 >> " + achCateIconsDTO);
            achCateIconsDTO.setAchCate(achCateDTO);  
            achCateIconsDTO.setOriginalName(originFileName);
            achCateIconsDTO.setSavedName(savedName);
            System.out.println("img넣은 후 >> " + achCateIconsDTO);
            
            achieveService.insertCateIcons(achCateIconsDTO);
            model.addAttribute("achLocaIconsDTO", achCateIconsDTO);
            System.out.println("insertMovie 후 >> " + achCateIconsDTO);
            
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "업적 생성 실패: " + e.getMessage());
            return "/common/errorPage";
        }
        
        return "redirect:/admin/adminMain";
    }
	
    @GetMapping("/achLocaUpdateForm")
    public String achieveUpdateForm(@RequestParam("achNo") int achNo, Model model) throws Exception {
        // 기존 업적 데이터 가져오기
        AchLocaDTO achLoca = achieveService.getAchLocaById(achNo);
        AchLocaIconsDTO achLocaIcons = achieveService.getAchLocaIconsByAchNo(achNo);
        List<LocationDTO> locationList = soulLogService.selectLocationList();
        
        // 모델에 데이터 추가
        model.addAttribute("achLoca", achLoca);
        model.addAttribute("achLocaIcons", achLocaIcons);
        model.addAttribute("locationList", locationList);
        
        return "achieve/achLocaUpdateForm";
    }
	
	@GetMapping("/achCateUpdateForm")
	public String achCateUpdateForm(@RequestParam("achNo") int achNo, Model model) throws Exception {
		
		AchCateDTO achCate = achieveService.getAchCateById(achNo);
        AchCateIconsDTO achCateIcons = achieveService.getAchCateIconsByAchNo(achNo);
        List<CategoryDTO> categoryList = soulLogService.selectCategoryList();
        
        model.addAttribute("achCate", achCate);
        model.addAttribute("achCateIcons", achCateIcons);
        model.addAttribute("categoryList", categoryList);
        
		return "achieve/achCateUpdateForm";
	}
	
	@PostMapping("/achLocaUpdateForm")
    public String achieveUpdate(AchLocaDTO achLocaDTO, 
                                AchLocaIconsDTO achLocaIconsDTO,
                                @RequestParam("singleFile") MultipartFile singleFile,
                                HttpServletRequest request, 
                                Model model) throws Exception {
        try {
            achieveService.updateAchLoca(achLocaDTO, achLocaIconsDTO, singleFile, request);
            return "redirect:/admin/adminMain";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "업적 수정 실패");
            return "/common/errorPage";
        }
    }
	
	@GetMapping("/deleteLoca")
	public String achieveDeleteLoca(AchLocaDTO achLocaDTO) throws Exception {
		System.out.println("Request >> achieveDelete.");
		System.out.println("Request >> " + achLocaDTO);
		
		int result = achieveService.deleteAchieveLoca(achLocaDTO.getAchNo());
		
		if (result > 0) {
			System.out.println("업적 삭제 성공.");
		} else {
			System.out.println("업적 삭제 실패.");
		}
		
		return "redirect:/admin/adminMain";
	}
	
	@GetMapping("/deleteCate")
	public String achieveDeleteCate(AchCateDTO achCateDTO) throws Exception {
		System.out.println("Request >> achieveDelete.");
		System.out.println("Request >> " + achCateDTO);
		
		int result = achieveService.deleteAchieveCate(achCateDTO.getAchNo());
		
		if (result > 0) {
			System.out.println("업적 삭제 성공.");
		} else {
			System.out.println("업적 삭제 실패.");
		}
		
		return "redirect:/admin/adminMain";
	}

}
