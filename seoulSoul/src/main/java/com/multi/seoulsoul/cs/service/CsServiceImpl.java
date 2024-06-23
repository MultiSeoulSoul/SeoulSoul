package com.multi.seoulsoul.cs.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.seoulsoul.cs.model.dao.CsDAO;
import com.multi.seoulsoul.cs.model.dto.CsCategoryDTO;
import com.multi.seoulsoul.cs.model.dto.CsQuestionDTO;

@Service
public class CsServiceImpl implements CsService {

	private final CsDAO csDAO;
	private final SqlSessionTemplate sqlSession;
	
	@Autowired
    public CsServiceImpl(CsDAO csDAO, SqlSessionTemplate sqlSession) {
        this.csDAO = csDAO;
        this.sqlSession = sqlSession;
    }
	
	//문의글 전체 조회
	@Override
    public int getTotalQuestions() {
        return csDAO.getTotalQuestions(sqlSession);
    }
    @Override
    public List<CsQuestionDTO> getQuestionsByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return csDAO.getQuestionsByPage(sqlSession, offset, pageSize);
    }
    
    //문의글 상세 조회
    @Override
    public CsQuestionDTO getQuestionById(int questionNo) {
        CsQuestionDTO question = csDAO.selectQuestionById(sqlSession, questionNo);
        if (question != null) {
            question.setFiles(csDAO.selectFilesByQuestionId(sqlSession, questionNo));
            question.setAnswers(csDAO.selectAnswersByQuestionId(sqlSession, questionNo));
        }
        return question;
    }
    @Override
    public void increaseViewCount(int questionNo) {
        csDAO.increaseViewCount(sqlSession, questionNo);
    }
  	
  	//문의글 삭제
	public void deleteQuestion(Integer questionNo) {
		int result = csDAO.deleteQuestion(sqlSession, questionNo);
	}
	
	//문의글 작성: 1. 카테고리
	@Override
	public List<CsCategoryDTO> getCategories() {
		return csDAO.getCategories(sqlSession);
	}
	//문의글 작성: 2. 문의글, 3. 첨부파일
	@Override
	public void insertQuestion(CsQuestionDTO question, List<Map<String, String>> files) {
		csDAO.insertQuestion(sqlSession, question);
		
        for (Map<String, String> file : files) {
            csDAO.insertFile(sqlSession, question.getQuestionNo(), file);
        }
		
	}
	
	//문의글 수정
    
  	

}
