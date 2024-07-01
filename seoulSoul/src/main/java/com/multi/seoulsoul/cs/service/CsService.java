package com.multi.seoulsoul.cs.service;

import java.util.List;
import java.util.Map;

import com.multi.seoulsoul.cs.model.dto.CsAnswerDTO;
import com.multi.seoulsoul.cs.model.dto.CsCategoryDTO;
import com.multi.seoulsoul.cs.model.dto.CsQuestionDTO;
import com.multi.seoulsoul.cs.model.dto.CsQuestionFileDTO;

public interface CsService {

	//문의글 전체 조회: 페이징 처리된 전체 문의글
	int getTotalQuestions() throws Exception;
    List<CsQuestionDTO> getQuestions(int page, int pageSize) throws Exception;
    //문의글 전체 조회: 페이징 처리 된 사용자별 문의글
    int getTotalQuestionsByUser(int userNo) throws Exception;
    List<CsQuestionDTO> getQuestionsByUser(int userNo, int page, int pageSize) throws Exception;
    
    //문의글 상세 조회
    CsQuestionDTO getQuestionById(int questionNo) throws Exception;
    CsQuestionFileDTO getFileById(int fileNo) throws Exception;
    void increaseViewCount(int questionNo) throws Exception;
    
  	//문의글 삭제
    void deleteQuestion(Integer questionNo) throws Exception;
    
    //문의글 작성: 1. 카테고리
    List<CsCategoryDTO> getCategories() throws Exception;
    //문의글 작성: 2. 문의글, 3. 첨부파일
    void insertQuestion(CsQuestionDTO question, List<Map<String, String>> files) throws Exception;
    
    //문의글 수정
    void updateQuestion(CsQuestionDTO question, List<Map<String, String>> files) throws Exception;
    void deleteFile(int fileNo) throws Exception;
	
    //문의글 답변 작성
    void insertAnswer(int questionNo, CsAnswerDTO answer) throws Exception;
}
