package com.multi.seoulsoul.cs.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.multi.seoulsoul.cs.model.dto.CsCategoryDTO;
import com.multi.seoulsoul.cs.model.dto.CsQuestionDTO;
import com.multi.seoulsoul.cs.model.dto.CsQuestionFileDTO;

public interface CsService {

	//문의글 전체 조회
	int getTotalQuestions();
    List<CsQuestionDTO> getQuestionsByPage(int page, int pageSize);
    
    //문의글 상세 조회
    CsQuestionDTO getQuestionById(int questionNo);
    CsQuestionFileDTO getFileById(int fileNo);
    void increaseViewCount(int questionNo);
    
  	//문의글 삭제
    void deleteQuestion(Integer questionNo);
    
    //문의글 작성: 1. 카테고리
    List<CsCategoryDTO> getCategories();
    //문의글 작성: 2. 문의글, 3. 첨부파일
    void insertQuestion(CsQuestionDTO question, List<Map<String, String>> files);
    
    //문의글 수정
    void updateQuestion(CsQuestionDTO question, List<Map<String, String>> files) throws Exception;

    void deleteFile(int fileNo) throws Exception;
	
  	
}
