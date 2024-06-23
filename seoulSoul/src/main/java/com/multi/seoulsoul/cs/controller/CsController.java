package com.multi.seoulsoul.cs.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.multi.seoulsoul.cs.model.dto.CsQuestionDTO;
import com.multi.seoulsoul.cs.service.CsService;

@Controller
@RequestMapping("cs")
public class CsController {

	private final CsService csService;
	private static final int PAGE_SIZE = 10;

	public CsController(CsService csService) {
		super();
		this.csService = csService;
	}
	
	@GetMapping("csMain")
	public void csMain() {

	}
	
    //문의글 전체 조회
	@GetMapping("/qnaAll")
    public String qnaAll(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        int totalQuestions = csService.getTotalQuestions();
        int totalPages = (int) Math.ceil((double) totalQuestions / PAGE_SIZE);

        List<CsQuestionDTO> questions = csService.getQuestionsByPage(page, PAGE_SIZE);

        model.addAttribute("questions", questions);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "cs/qnaAll";
    }
	
	//문의글 상세 조회
	@GetMapping("/qnaOne")
    public String getQuestionById(@RequestParam("id") int questionNo, Model model) {
		csService.increaseViewCount(questionNo);
		
		CsQuestionDTO question = csService.getQuestionById(questionNo);
        model.addAttribute("qna", question);
        return "cs/qnaOne";
    }
	
	//문의글 삭제
	@GetMapping("/qnaDelete")
    public String qnaDelete(@RequestParam("id") Integer questionNo) {
        csService.deleteQuestion(questionNo);
        return "redirect:/cs/qnaAll";
    }
	
	//문의글 작성: 1. 카테고리
	@GetMapping("/qnaInsert")
    public String qnaInsertForm(Model model) {
        model.addAttribute("categories", csService.getCategories());
        return "cs/qnaInsert";
    }
	//문의글 작성: 2. 문의글, 3. 첨부파일
    @PostMapping("/qnaInsert")
    public String qnaInsert(@RequestParam("title") String title, 
				            @RequestParam("content") String content, 
				            @RequestParam("multiFiles") List<MultipartFile> multiFiles,
				            HttpServletRequest request) {

		//첨부파일 저장 경로 설정
		String root = request.getSession().getServletContext().getRealPath("resources");
		String filePath = root + "\\uploadFiles";

		//List-Map 형식으로 첨부파일 담기
		List<Map<String, String>> files = new ArrayList<>();
		for (int i = 0; i < multiFiles.size(); i++) {
		
			//파일명 변경 처리
			String originalFileName = multiFiles.get(i).getOriginalFilename();
			String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
			String storedFileName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			//파일에 관한 정보 추출 후 보관
			Map<String, String> file = new HashMap<>();
			file.put("originalFileName", originalFileName);
			file.put("storedFileName", storedFileName);
			file.put("filePath", filePath);
			
			files.add(file);		
		}
		
		try {
			
			//첨부파일 저장
			for (int i = 0; i < multiFiles.size(); i++) {
				Map<String, String> file = files.get(i);				
				multiFiles.get(i).transferTo(new File(filePath + "\\" + file.get("savedName")));
			}
			
			//CsQuestionDTO에 정보 저장
			CsQuestionDTO question = new CsQuestionDTO();
			question.setTitle(title);
			question.setContent(content);
			question.setWriter(1); //작성자 (user_no) 1이라고 가정 //loginUser.getId()
			csService.insertQuestion(question, files);
		
		
		}catch (Exception e) {
			e.printStackTrace();
			
			//문의글 작성 실패시 첨부파일 삭제
			for (int i = 0; i < multiFiles.size(); i++) {
				Map<String, String> file = files.get(i);				
				new File(filePath + "\\" + file.get("savedName")).delete();
			}
		}
		return "redirect:/cs/qnaAll";
	}
    
    //문의글 수정
    @PostMapping("/qnaUpdate")
    public void qnaUpdate() {

	}
}
