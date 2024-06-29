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
	
	//문의글 전체 조회: 페이징 처리된 전체 문의글
	@GetMapping("/qnaAll")
    public String qnaAll(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        int totalQuestions;
        
		try {
			totalQuestions = csService.getTotalQuestions();
			int totalPages = (int) Math.ceil((double) totalQuestions / PAGE_SIZE);

	        List<CsQuestionDTO> questions = csService.getQuestions(page, PAGE_SIZE);

	        model.addAttribute("questions", questions);
	        model.addAttribute("currentPage", page);
	        model.addAttribute("totalPages", totalPages);
	        
		} catch (Exception e) {
			e.printStackTrace();
			
			//문의글 전체 조회 실패 시 예외 처리: 에러 페이지 이동
			model.addAttribute("msg", "문의글 전체 조회 과정에서 문제가 발생했습니다.");
			return "common/errorPage";
		}
        return "cs/qnaAll";
        
    }
	//문의글 전체 조회: 페이징 처리 된 사용자별 문의글
	@RequestMapping("/qnaAllUser")
    public String getQnaAllUser(@RequestParam(value = "page", defaultValue = "1") int page, Model model/*, HttpSession session*/) {
        
		//int userNo = (int) session.getAttribute("userNo"); // 로그인한 사용자의 번호를 세션에서 가져옴
        
		int userNo = 3; //테스트용
		
        int totalQuestions;
		try {
			totalQuestions = csService.getTotalQuestionsByUser(userNo);
			int totalPages = (int) Math.ceil((double) totalQuestions / PAGE_SIZE);

	        List<CsQuestionDTO> questions = csService.getQuestionsByUser(userNo, page, PAGE_SIZE);
	        
	        model.addAttribute("questions", questions);
	        model.addAttribute("currentPage", page);
	        model.addAttribute("totalPages", totalPages);
	        
		} catch (Exception e) {
			e.printStackTrace();
			//문의글 전체 조회 실패 시 예외 처리: 에러 페이지 이동
			model.addAttribute("msg", "문의글 전체 조회 과정에서 문제가 발생했습니다.");
			return "common/errorPage";
		}
        return "cs/qnaAllUser";
    }
	
	//문의글 상세 조회: 전체 문의글
	@GetMapping("/qnaOne")
    public String getQuestionById(@RequestParam("id") int questionNo, Model model) {
		try {
			csService.increaseViewCount(questionNo);
			CsQuestionDTO question = csService.getQuestionById(questionNo);
			model.addAttribute("qna", question);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//문의글 상세 조회 실패 시 예외 처리: 에러 페이지 이동
			model.addAttribute("msg", "문의글 조회 과정에서 문제가 발생했습니다.");
			return "common/errorPage";
		}
        return "cs/qnaOne";
        
    }
    // 문의글 상세 조회: 사용자별 문의글
    @GetMapping("/qnaOneUser")
    public String getQuestionByUserId(@RequestParam("id") int questionNo, Model model) {
        try {
            csService.increaseViewCount(questionNo);
            CsQuestionDTO question = csService.getQuestionById(questionNo);
            model.addAttribute("qna", question);
            
        } catch (Exception e) {
            e.printStackTrace();
            
            //문의글 상세 조회 실패 시 예외 처리: 에러 페이지 이동
            model.addAttribute("msg", "문의글 조회 과정에서 문제가 발생했습니다.");
            return "common/errorPage";
        }
        return "cs/qnaOneUser";
    }
    //문의글 상세 조회: 파일 다운로드
    @GetMapping("/download")
    public void downloadFile(@RequestParam("fileNo") int fileNo, 
				             @RequestParam("questionNo") int questionNo, 
				             HttpServletRequest request, 
				             HttpServletResponse response, 
				             Model model) {
        CsQuestionDTO question;
		try {
			question = csService.getQuestionById(questionNo);
			
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
	                    
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    
	                    //컴파일 오류 가능성 없는 경우 //파일 다운로드 시 예외 처리: 에러 페이지 이동
	                    model.addAttribute("msg", "파일 다운로드 과정에서 문제가 발생했습니다.");
	                    request.getRequestDispatcher("/common/errorPage").forward(request, response); 
	                }
	            }
	        }

		} catch (Exception e) { //Exception e: 다운로드 과정에서 발생할 수 있는 모든 예외를 포착
		    e.printStackTrace();
		    model.addAttribute("msg", "파일 다운로드 과정에서 문제가 발생했습니다.");
		    
		    //컴파일 오류 가능성 있는 경우 //중첩된 try/catch 블록을 사용해 포워딩 과정에서 발생할 수 있는 예외를 처리
		    try {
		        request.getRequestDispatcher("/common/errorPage").forward(request, response); //에러 페이지로 포워딩
		        
		    } catch (Exception ex) { //Exception ex: 포워딩 과정에서 발생할 수 있는 모든 예외를 포착
		        ex.printStackTrace();
		    }
		}
    }
	
	//문의글 삭제
	@GetMapping("/qnaDelete")
    public String qnaDelete(@RequestParam("id") Integer questionNo, Model model) {
        try {
			csService.deleteQuestion(questionNo);
		} catch (Exception e) {
			e.printStackTrace();
			
			//문의글 문의글 삭제 실패 시 예외 처리: 에러 페이지 이동
			model.addAttribute("msg", "문의글 삭제 과정에서 문제가 발생했습니다.");
			return "common/errorPage";
		}
        return "redirect:/cs/qnaAllUser";
        
    }
	
	//문의글 작성: 1. 카테고리
	@GetMapping("/qnaInsert")
	public String qnaInsertForm(Model model) {
        try {
			model.addAttribute("categories", csService.getCategories());
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//문의글 작성 중 카테고리 가져오기 실패 시 예외 처리: 에러 페이지 이동
            model.addAttribute("msg", "문의글 작성 과정에서 문제가 발생했습니다.");
			return "common/errorPage";
		}
        return "cs/qnaInsert";
    }
	//문의글 작성: 2. 문의글만 작성도 되고, 3. 첨부파일이 존재하는 경우, 문의글+첨부파일까지 작성도 되야 함
    @PostMapping("/qnaInsert")
    public String qnaInsert(@RequestParam("title") String title, 
                            @RequestParam("content") String content, 
                            @RequestParam("category_code") int categoryCode,
                            @RequestParam("writer") int writer,
                            @RequestParam("multiFiles") MultipartFile[] multiFiles,
                            HttpServletRequest request, Model model) {
        
        CsQuestionDTO question = new CsQuestionDTO();
        question.setTitle(title);
        question.setContent(content);
        question.setCategoryCode(categoryCode);
        question.setWriter(writer); //작성자 (user_no)
        
        //첨부파일 저장 경로 설정
        String root = request.getSession().getServletContext().getRealPath("resources");
        String filePath = root + "\\uploadFiles";
        
        //첨부파일이 존재하는 경우
        List<Map<String, String>> files = new ArrayList<>();
        if (multiFiles.length > 0) {
            
            //디렉터리 없는 경우 디렉터리 생성
            File mkdir = new File(filePath);
            if (!mkdir.exists()) {
                mkdir.mkdirs();
            }
            
            //첨부파일 처리
            for (MultipartFile file : multiFiles) {
                if (!file.isEmpty()) {
                	
                    //파일명 변경 처리
                    String originalFileName = file.getOriginalFilename();
                    
                    String ext = "";
                    int dotIndex = originalFileName.lastIndexOf(".");
                    if (dotIndex != -1) {
                        ext = originalFileName.substring(dotIndex);
                    }
                    String storedFileName = UUID.randomUUID().toString().replace("-", "") + ext;;

                    //파일에 관한 정보 추출 후 보관
                    Map<String, String> fileInfo = new HashMap<>();
                    fileInfo.put("originalFileName", originalFileName);
                    fileInfo.put("storedFileName", storedFileName);
                    fileInfo.put("filePath", filePath);

                    files.add(fileInfo);

                    try {
                        //파일 저장
                        file.transferTo(new File(filePath + "\\" + storedFileName));
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                        
                        //첨부파일 처리 실패 시 예외 처리: 에러 페이지 이동
                        model.addAttribute("msg", "문의글 작성 과정에서 문제가 발생했습니다.");
            			return "common/errorPage";
                    }
                }
            }
        }

        try {
            //문의글 DB에 저장
            csService.insertQuestion(question, files);
            
        } catch (Exception e) {
            e.printStackTrace();
            
            //문의글 작성 실패시 첨부파일 삭제
            for (Map<String, String> fileInfo : files) {
                String storedFileName = fileInfo.get("storedFileName");
                new File(filePath + "\\" + storedFileName).delete();
            }
            
            //문의글 작성 실패 시 예외 처리: 에러 페이지 이동
            model.addAttribute("msg", "문의글 작성 과정에서 문제가 발생했습니다.");
			return "common/errorPage";
        }
        return "redirect:/cs/qnaAllUser";
    }

    
    //문의글 수정: 1. 페이지 이동
    @GetMapping("/qnaUpdate")
    public String qnaUpdateForm(@RequestParam("id") int questionNo, Model model) {
        CsQuestionDTO question;
		try {
			question = csService.getQuestionById(questionNo);
			
			model.addAttribute("qna", question);
	        model.addAttribute("categories", csService.getCategories());
	        
		} catch (Exception e) {
			e.printStackTrace();
			
			//문의글 수정 중 페이지 이동 실패 시 예외 처리: 에러 페이지 이동
            model.addAttribute("msg", "문의글 수정 과정에서 문제가 발생했습니다.");
			return "common/errorPage";
		} 
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
                            HttpServletRequest request, Model model) throws Exception {

        CsQuestionDTO question = new CsQuestionDTO();
        question.setQuestionNo(questionNo);
        question.setTitle(title);
        question.setContent(content);
        question.setCategoryCode(categoryCode);
        question.setWriter(writer); // 작성자 (user_no)

    	
        //기존 첨부파일 삭제
        if (deleteFiles != null && !deleteFiles.isEmpty()) {
            for (Integer fileNo : deleteFiles) {
                try {
                    csService.deleteFile(fileNo);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    
                    // 파일 삭제 실패 시 예외 처리: 에러 페이지 이동
                    model.addAttribute("msg", "문의글 수정 과정에서 문제가 발생했습니다.");
        			return "common/errorPage";
                }
            }
        }

        //새로운 첨부파일 처리
        List<Map<String, String>> files = new ArrayList<>();
        if (multiFiles != null && multiFiles.length > 0) {
            String root = request.getSession().getServletContext().getRealPath("resources");
            String filePath = root + File.separator + "uploadFiles";

            //디렉터리가 없는 경우 생성
            File directory = new File(filePath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            //첨부파일 처리
            for (MultipartFile file : multiFiles) {
                if (!file.isEmpty()) {
                	//파일명 변경 처리
                    String originalFileName = file.getOriginalFilename();
                    String ext = "";
                    int dotIndex = originalFileName.lastIndexOf(".");
                    if (dotIndex != -1) {
                        ext = originalFileName.substring(dotIndex);
                    }
                    String storedFileName = UUID.randomUUID().toString().replace("-", "") + ext;

                    //파일에 관한 정보 추출 후 보관
                    Map<String, String> fileInfo = new HashMap<>();
                    fileInfo.put("originalFileName", originalFileName);
                    fileInfo.put("storedFileName", storedFileName);
                    fileInfo.put("filePath", filePath);

                    files.add(fileInfo);

                    try {
                    	//파일 저장
                        file.transferTo(new File(filePath + "\\" + storedFileName));
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                        
                        //파일 저장 실패 시 예외 처리: 에러 페이지 이동
                        model.addAttribute("msg", "문의글 수정 과정에서 문제가 발생했습니다.");
            			return "common/errorPage";
                    }
                }
            }
        }

        try {
        	//문의글 DB에 수정 저장
            csService.updateQuestion(question, files);
            
        } catch (Exception e) {
            e.printStackTrace();
            //문의글 수정 실패 시 예외 처리: 에러 페이지 이동
            model.addAttribute("msg", "문의글 수정 과정에서 문제가 발생했습니다.");
			return "common/errorPage";
        }
        return "redirect:/cs/qnaOneUser?id=" + questionNo;
    }
    //문의글 수정: 3. 첨부파일 삭제
    @PostMapping("deleteFile")
    public String deleteFile(@ModelAttribute CsQuestionDTO question,
    						 @RequestParam("fileNo") int fileNo,
                             @RequestParam("questionNo") int questionNo,
                             HttpServletRequest request, Model model) throws Exception {

        try {
            csService.deleteFile(fileNo);
            
        } catch (Exception e) {
            e.printStackTrace();
            
            //파일 삭제 실패 시 예외 처리: 에러 페이지 이동
            model.addAttribute("msg", "문의글 수정 과정에서 문제가 발생했습니다.");
			return "common/errorPage";  
        }
        return "redirect:/cs/qnaOneUser?id=" + questionNo;
    }
    
    //문의글 답변 작성
    @PostMapping("/answerInsert")
    public String insertAnswer(@RequestParam("qnaId") int questionNo,
                               @RequestParam("content") String content, Model model) {
        
        int writer = 1; //테스트용: 관리자 writer(user_no)를 하드코딩된 값 1로 설정
        
        try {
			csService.insertAnswer(questionNo, content, writer);
		} catch (Exception e) {
			e.printStackTrace();
			
			//문의글 답변 작성 실패 시 예외 처리: 에러 페이지 이동
			model.addAttribute("msg", "문의글 답변 작성 과정에서 문제가 발생했습니다.");
			return "common/errorPage";
		}
        return "redirect:/cs/qnaOne?id=" + questionNo;  
    }
}
