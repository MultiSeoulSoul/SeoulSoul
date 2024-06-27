package com.multi.seoulsoul.cs.service;

import java.util.List;
import java.util.Map;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.seoulsoul.cs.model.dao.CsDAO;
import com.multi.seoulsoul.cs.model.dto.CsAnswerDTO;
import com.multi.seoulsoul.cs.model.dto.CsCategoryDTO;
import com.multi.seoulsoul.cs.model.dto.CsQuestionDTO;
import com.multi.seoulsoul.cs.model.dto.CsQuestionFileDTO;

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
    //문의글 상세 조회: 첨부파일 저장
    @Override
    public CsQuestionFileDTO getFileById(int fileNo) {
        return csDAO.selectFileById(sqlSession, fileNo);
    }
    //문의글 상세 조회: 조회수 증가
    @Override
    public void increaseViewCount(int questionNo) {
        csDAO.increaseViewCount(sqlSession, questionNo);
    }
    
  	//문의글 삭제
  	@Override
	public void deleteQuestion(Integer questionNo) {
		csDAO.deleteQuestion(sqlSession, questionNo);
	}
	
	//문의글 작성: 1. 카테고리
	@Override
	public List<CsCategoryDTO> getCategories() {
        return csDAO.getCategories(sqlSession);
    }
	// 문의글 작성: 2. 문의글, 3. 첨부파일
	@Override
    public void insertQuestion(CsQuestionDTO question, List<Map<String, String>> files) {
        csDAO.insertQuestion(sqlSession, question);
        if (!files.isEmpty()) {
            for (Map<String, String> file : files) {
                file.put("questionNo", String.valueOf(question.getQuestionNo()));
                csDAO.insertFile(sqlSession, file);
            }
        }
    }
    
  	//문의글 수정
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateQuestion(CsQuestionDTO question, List<Map<String, String>> files) throws Exception {
        csDAO.updateQuestion(sqlSession, question);

        // 기존 파일 삭제 및 새 파일 추가
        if (files != null && !files.isEmpty()) {
            for (Map<String, String> file : files) {
                file.put("questionNo", Integer.toString(question.getQuestionNo()));
                csDAO.insertFile(sqlSession, file);
            }
        }
    }
    @Override
    public void deleteFile(int fileNo) throws Exception {
        csDAO.deleteFile(sqlSession, fileNo);
    }
    
    //문의글 답변 작성
    @Override
    public void insertAnswer(int questionNo, String content, int writer) {
        CsAnswerDTO answer = new CsAnswerDTO();
        answer.setQuestionNo(questionNo);
        answer.setContent(content);
        answer.setWriter(writer);
        csDAO.insertAnswer(sqlSession, answer);
        csDAO.updateQuestionToAnswered(sqlSession, questionNo);
    }

}
