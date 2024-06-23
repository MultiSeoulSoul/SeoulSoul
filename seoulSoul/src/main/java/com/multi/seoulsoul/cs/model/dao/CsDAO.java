package com.multi.seoulsoul.cs.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.multi.seoulsoul.cs.model.dto.CsAnswerDTO;
import com.multi.seoulsoul.cs.model.dto.CsCategoryDTO;
import com.multi.seoulsoul.cs.model.dto.CsQuestionDTO;
import com.multi.seoulsoul.cs.model.dto.CsQuestionFileDTO;

@Repository
public class CsDAO {

	//문의글 전체 조회: 1. 전체 문의글 수
	public int getTotalQuestions(SqlSessionTemplate sqlSession) {
        return sqlSession.selectOne("csMapper.getTotalQuestions");
    }
	//문의글 전체 조회: 2. 페이징 처리된 문의글
    public List<CsQuestionDTO> getQuestionsByPage(SqlSessionTemplate sqlSession, int offset, int pageSize) {
        Map<String, Integer> params = new HashMap<>();
        params.put("offset", offset);
        params.put("pageSize", pageSize);
        return sqlSession.selectList("csMapper.getQuestionsByPage", params);
    }
    
    //문의글 상세 조회: 1. 문의글 상세 가져오기
    public CsQuestionDTO selectQuestionById(SqlSessionTemplate sqlSession, int questionNo) {
        return sqlSession.selectOne("csMapper.selectQuestionById", questionNo);
    }
    //문의글 상세 조회: 2. 첨부파일 가져오기
    public List<CsQuestionFileDTO> selectFilesByQuestionId(SqlSessionTemplate sqlSession, int questionNo) {
        return sqlSession.selectList("csMapper.selectFilesByQuestionId", questionNo);
    }
    //문의글 상세 조회: 3. 답변 가져오기
    public List<CsAnswerDTO> selectAnswersByQuestionId(SqlSessionTemplate sqlSession, int questionNo) {
        return sqlSession.selectList("csMapper.selectAnswersByQuestionId", questionNo);
    }
    //문의글 상세 조회: 4. 조회수 증가시키기
    public void increaseViewCount(SqlSessionTemplate sqlSession, int questionNo) {
        sqlSession.update("csMapper.increaseViewCount", questionNo);
    }
    	
  	//문의글 삭제
    public int deleteQuestion(SqlSessionTemplate sqlSession, Integer questionNo) {
		return sqlSession.delete("csMapper.deleteQuestion", questionNo);
	}
	
	//문의글 작성: 1. 카테고리
	public List<CsCategoryDTO> getCategories(SqlSessionTemplate sqlSessionTemplate) {
		return sqlSessionTemplate.selectList("csMapper.getCategories");
    }
	//문의글 작성: 2. 문의글
	public int insertQuestion(SqlSessionTemplate sqlSessionTemplate, CsQuestionDTO question) { //문의글
		return sqlSessionTemplate.insert("csMapper.insertQuestion", question);
	}
	//문의글 작성: 3. 첨부파일
	public int insertFile(SqlSessionTemplate sqlSession, int questionNo, Map<String, String> file) { //파일첨부
	    Map<String, Object> param = new HashMap<>();
	    param.put("questionNo", questionNo);
	    param.put("originalFileName", file.get("originalFileName"));
	    param.put("storedFileName", file.get("storedFileName"));
	    param.put("filePath", file.get("filePath"));
	    
	    return sqlSession.insert("csMapper.insertFile", param);
	}
	
  	//문의글 수정
	
}
