package com.multi.seoulsoul.cs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.multi.seoulsoul.cs.model.dto.CsQuestionDTO;
import com.multi.seoulsoul.cs.model.dto.CsQuestionFileDTO;
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
	
	@GetMapping("/csMain")
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
    //문의글 상세 조회: 파일 다운로드
    @GetMapping("/download")
    public void downloadFile(@RequestParam("fileNo") int fileNo, @RequestParam("questionNo") int questionNo, HttpServletRequest request, HttpServletResponse response) {
        CsQuestionDTO question = csService.getQuestionById(questionNo);
        List<CsQuestionFileDTO> files = question.getFiles();

        CsQuestionFileDTO file = null;
        for (CsQuestionFileDTO f : files) {
            if (f.getFileNo() == fileNo) {
                file = f;
                break;
            }
        }

        if (file != null) {
            String filePath = file.getFilePath() + File.separator + file.getStoredFileName();
            File downloadFile = new File(filePath);

            if (downloadFile.exists()) {
                response.setContentType("application/octet-stream");
                response.setContentLength((int) downloadFile.length());
                try {
                    String encodedFileName = URLEncoder.encode(file.getOriginalFileName(), "UTF-8").replace("+", "%20");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\";");

                    FileInputStream inStream = new FileInputStream(downloadFile);
                    OutputStream outStream = response.getOutputStream();

                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    while ((bytesRead = inStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, bytesRead);
                    }

                    inStream.close();
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
	// 문의글 작성: 2. 문의글만 작성도 되고, 3. 첨부파일이 존재하는 경우, 문의글+첨부파일까지 작성도 되야 함
    @PostMapping("/qnaInsert")
    public String qnaInsert(@RequestParam("title") String title, 
                            @RequestParam("content") String content, 
                            @RequestParam("category_code") int categoryCode,
                            @RequestParam("writer") int writer,
                            @RequestParam("multiFiles") MultipartFile[] multiFiles,
                            HttpServletRequest request) {
        
        CsQuestionDTO question = new CsQuestionDTO();
        question.setTitle(title);
        question.setContent(content);
        question.setCategoryCode(categoryCode);
        question.setWriter(writer); // 작성자 (user_no)
        
        // 첨부파일 저장 경로 설정
        String root = request.getSession().getServletContext().getRealPath("resources");
        String filePath = root + "\\uploadFiles";
        
        // 첨부파일이 존재하는 경우
        List<Map<String, String>> files = new ArrayList<>();
        if (multiFiles.length > 0) {
            
            // 디렉터리 없는 경우 디렉터리 생성
            File mkdir = new File(filePath);
            if (!mkdir.exists()) {
                mkdir.mkdirs();
            }
            
            // 첨부파일 처리
            for (MultipartFile file : multiFiles) {
                if (!file.isEmpty()) {
                	
                	System.out.println("===========첨부 파일 있음===========");
                	System.out.println(file);
                	
                	
                    // 파일명 변경 처리
                    String originalFileName = file.getOriginalFilename();
                    
                    System.out.println("originalFileName: "+originalFileName);
                    
                    String ext = "";
                    int dotIndex = originalFileName.lastIndexOf(".");
                    if (dotIndex != -1) {
                        ext = originalFileName.substring(dotIndex);
                    }
                    String storedFileName = UUID.randomUUID().toString().replace("-", "") + ext;
                    
                    System.out.println("===========파일명 변경 처리===========");
                    System.out.println("storedFileName: "+storedFileName);

                    // 파일에 관한 정보 추출 후 보관
                    Map<String, String> fileInfo = new HashMap<>();
                    fileInfo.put("originalFileName", originalFileName);
                    fileInfo.put("storedFileName", storedFileName);
                    fileInfo.put("filePath", filePath);

                    files.add(fileInfo);
                    
                    System.out.println("===========파일에 관한 정보 추출 후 보관===========");
                    System.out.println("files: "+files);

                    try {
                        // 파일 저장
                        file.transferTo(new File(filePath + "\\" + storedFileName));
                        
                        System.out.println("===========파일 저장됨===========");
                    } catch (IOException e) {
                        e.printStackTrace();
                        
                        System.out.println("===========파일 저장 안 됨 !!===========");
                    }
                }
            }
        }

        try {
            // 문의글 DB에 저장
            csService.insertQuestion(question, files);
            
            System.out.println("===========문의글 DB에 파일 저장됨===========");
            
        } catch (Exception e) {
            e.printStackTrace();
            
            // 문의글 작성 실패시 첨부파일 삭제
            for (Map<String, String> fileInfo : files) {
                String storedFileName = fileInfo.get("storedFileName");
                new File(filePath + "\\" + storedFileName).delete();
            }
            
            System.out.println("===========문의글 작성 실패로 첨부파일 삭제 됨 !!===========");
        }
        return "redirect:/cs/qnaAll";
    }

    
    //문의글 수정: 1. 페이지 이동
    @GetMapping("/qnaUpdate")
    public String qnaUpdateForm(@RequestParam("id") int questionNo, Model model) {
        CsQuestionDTO question = csService.getQuestionById(questionNo);
        model.addAttribute("qna", question);
        model.addAttribute("categories", csService.getCategories());
        return "cs/qnaUpdate";
    }
    //문의글 수정: 2. 게시물 정보 수정 처리
    @PostMapping("/qnaUpdate")
    public String qnaUpdate(@RequestParam("id") int questionNo,
				            @RequestParam("title") String title, 
				            @RequestParam("content") String content, 
				            @RequestParam("category_code") int categoryCode,
				            @RequestParam("writer") int writer,
                            @RequestParam(name = "deleteFiles", required = false) List<Integer> deleteFiles,
                            @RequestParam("multiFiles") MultipartFile[] multiFiles,
                            HttpServletRequest request) throws Exception {

        CsQuestionDTO question = new CsQuestionDTO();
        question.setQuestionNo(questionNo);
        question.setTitle(title);
        question.setContent(content);
        question.setCategoryCode(categoryCode);
        question.setWriter(writer); // 작성자 (user_no)

    	
        // 기존 첨부파일 삭제
        if (deleteFiles != null && !deleteFiles.isEmpty()) {
            for (Integer fileNo : deleteFiles) {
                try {
                    csService.deleteFile(fileNo);
                } catch (IOException e) {
                    e.printStackTrace();
                    // 파일 삭제 실패 시 예외 처리
                }
            }
        }

        // 새로운 첨부파일 처리
        List<Map<String, String>> files = new ArrayList<>();
        if (multiFiles != null && multiFiles.length > 0) {
            String root = request.getSession().getServletContext().getRealPath("resources");
            String filePath = root + File.separator + "uploadFiles";

            // 디렉터리가 없는 경우 생성
            File directory = new File(filePath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 첨부파일 처리
            for (MultipartFile file : multiFiles) {
                if (!file.isEmpty()) {
                	// 파일명 변경 처리
                    String originalFileName = file.getOriginalFilename();
                    String ext = "";
                    int dotIndex = originalFileName.lastIndexOf(".");
                    if (dotIndex != -1) {
                        ext = originalFileName.substring(dotIndex);
                    }
                    String storedFileName = UUID.randomUUID().toString().replace("-", "") + ext;

                    // 파일에 관한 정보 추출 후 보관
                    Map<String, String> fileInfo = new HashMap<>();
                    fileInfo.put("originalFileName", originalFileName);
                    fileInfo.put("storedFileName", storedFileName);
                    fileInfo.put("filePath", filePath);

                    files.add(fileInfo);

                    try {
                    	// 파일 저장
                        file.transferTo(new File(filePath + "\\" + storedFileName));
                    } catch (IOException e) {
                        e.printStackTrace();
                        // 파일 저장 실패 시 예외 처리
                    }
                }
            }
        }

        try {
        	// 문의글 DB에 수정 저장
            csService.updateQuestion(question, files);
        } catch (Exception e) {
            e.printStackTrace();
            // 문의글 수정 실패 시 예외 처리
        }
        return "redirect:/cs/qnaAll";
    }
    //문의글 수정: 3. 첨부파일 삭제
    @PostMapping("deleteFile")
    public String deleteFile(@ModelAttribute CsQuestionDTO question,
    						 @RequestParam("fileNo") int fileNo,
                             @RequestParam("questionNo") int questionNo,
                             HttpServletRequest request) throws Exception {

        try {
            csService.deleteFile(fileNo);
        } catch (IOException e) {
            e.printStackTrace();
            // 파일 삭제 실패 시 예외 처리
        }

        return "redirect:/cs/qnaAll";
    }
}
