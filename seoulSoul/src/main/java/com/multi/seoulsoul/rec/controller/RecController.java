package com.multi.seoulsoul.rec.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.multi.seoulsoul.rec.model.dto.RecDTO;
import com.multi.seoulsoul.rec.service.RecService;

@Controller
@RequestMapping("/rec")
public class RecController {

	@Autowired
	private RecService recService;

	@GetMapping("recMain")
	public String recMain(Model model) {
		try {
			List<RecDTO> recList = recService.selectAllRecommendations();
			model.addAttribute("recList", recList);

			// 디버깅용 로그 출력
			System.out.println("Rec List Size: " + recList.size());
			for (RecDTO rec : recList) {
				System.out.println("Controller Layer Rec: " + rec);
				System.out.println("Controller Layer Title: " + rec.getTitle());
				System.out.println("Controller Layer Content: " + rec.getContent());
				System.out.println("Controller Layer Image Path: " + rec.getImagePath());
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "추천 정보를 불러오는 중 오류가 발생했습니다.");
			return "common/errorPage";
		}
		return "rec/recMain";
	}

	@GetMapping("recInsertForm")
	public String recInsertForm() {
		return "rec/recInsertForm";
	}

	@PostMapping("recInsertForm")
	public ModelAndView insertRecommendation(@RequestParam("title") String title,
			@RequestParam("content") String content, @RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws Exception {
		RecDTO recDTO = new RecDTO();
		recDTO.setTitle(title);
		recDTO.setContent(content);
		recDTO.setViews(0);

		try {
			// 추천 글 저장
			recService.recInsertForm(recDTO);
			int recommendationNo = recDTO.getRecommendationNo();
			System.out.println("Inserted recommendationNo: " + recommendationNo); // 디버깅용 로그 출력

			// 파일 저장
			if (!file.isEmpty()) {
				// 프로젝트 내 경로 설정
				String uploadDir = request.getSession().getServletContext().getRealPath("/resources/uploadFiles/");
				File dir = new File(uploadDir);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				String originalFilename = file.getOriginalFilename();
				String savedFilename = UUID.randomUUID().toString() + "_" + originalFilename;
				File saveFile = new File(uploadDir + savedFilename);
				file.transferTo(saveFile);

				System.out.println("Original Filename: " + originalFilename); // 디버깅용 로그 출력
				System.out.println("Saved Filename: " + savedFilename); // 디버깅용 로그 출력
				System.out.println("Saved File Path: " + saveFile.getAbsolutePath()); // 파일 경로 확인 로그

				recService.saveFile(recommendationNo, originalFilename, savedFilename);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return new ModelAndView("redirect:/rec/recInsertForm");
		}

		return new ModelAndView("redirect:/rec/recMain");
	}

	@GetMapping("/recDetail")
	public String recDetail(@RequestParam("recommendationNo") int recommendationNo, Model model) {
		try {
			int userNo = 1; // 로그인 됐다는 가정으로 하드코딩 임의설정함.
			// 조회수 증가
			recService.incrementViews(recommendationNo);

			RecDTO recDTO = recService.selectRecommendationByNo(recommendationNo);
			model.addAttribute("rec", recDTO);
			boolean isHearted = recService.isHearted(userNo, recommendationNo); // 찜 상태 확인
			model.addAttribute("isHearted", isHearted); // 모델에 찜 상태 추가

			// 디버깅용 로그 출력
			System.out.println("Detail Layer RecDTO: " + recDTO);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "추천 상세 정보를 불러오는 중 오류가 발생했습니다.");
			return "common/errorPage";
		}
		return "rec/recDetail";
	}

	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws Exception {
		if (!file.isEmpty()) {
			try {
				String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
				String uploadDir = "src/main/webapp/resources/uploadFiles/";
				File destFile = new File(uploadDir + filename);

				if (!destFile.getParentFile().exists()) {
					destFile.getParentFile().mkdirs();
				}

				file.transferTo(destFile);
				RecDTO recDTO = new RecDTO();
				recDTO.setImagePath("uploadFiles/" + filename); // 경로 수정
				recService.saveRecommendation(recDTO);

				redirectAttributes.addFlashAttribute("message", "File uploaded successfully");
				return "redirect:/recMain";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		redirectAttributes.addFlashAttribute("message", "File upload failed");
		return "redirect:/recMain";
	}

	@GetMapping("/editRec")
	public String editRec(@RequestParam("recommendationNo") int recommendationNo, Model model) throws Exception {
		RecDTO rec = recService.selectRecommendationById(recommendationNo);
		model.addAttribute("rec", rec);
		return "rec/editRec";
	}

	@PostMapping("/updateRecommend")
	public String updateRecommend(HttpServletRequest request, @RequestParam("recommendationNo") int recommendationNo,
			@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("image") MultipartFile image, RedirectAttributes redirectAttributes) throws Exception {
		RecDTO rec = recService.selectRecommendationById(recommendationNo);
		rec.setTitle(title);
		rec.setContent(content);

		if (!image.isEmpty()) {
			String saveDirectory = request.getSession().getServletContext().getRealPath("/resources/uploadFiles/");
			String originalFilename = image.getOriginalFilename();
			String savedFilename = UUID.randomUUID().toString() + "_" + originalFilename;
			File file = new File(saveDirectory, savedFilename);
			try {
				image.transferTo(file);
				rec.setImagePath(savedFilename);
				recService.updateFile(recommendationNo, originalFilename, savedFilename); // 파일 정보를 저장하는 메서드 호출
			} catch (IOException e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("message", "이미지 업로드 중 오류가 발생했습니다.");
				return "redirect:/rec/editRec?recommendationNo=" + recommendationNo;
			}
		}

		recService.updateRecommend(rec);
		redirectAttributes.addFlashAttribute("message", "추천이 수정되었습니다.");
		return "redirect:/rec/recDetail?recommendationNo=" + recommendationNo;
	}

	@PostMapping("/deleteRecommend")
	public String deleteRecommend(@RequestParam("recommendationNo") int recommendationNo,
			RedirectAttributes redirectAttributes) throws Exception {
		recService.deleteRecommend(recommendationNo);
		redirectAttributes.addFlashAttribute("message", "추천이 삭제되었습니다.");
		return "redirect:/rec/recMain";
	}

	@PostMapping("/toggleHeart")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> toggleHeart(@RequestParam("userNo") int userNo,
			@RequestParam("recommendationNo") int recommendationNo) {
		Map<String, Object> response = new HashMap<>();
		try {
			boolean isHearted = recService.toggleHeart(userNo, recommendationNo);
			response.put("success", true);
			response.put("isHearted", isHearted);
		} catch (Exception e) {
			response.put("success", false);
			response.put("error", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}
}