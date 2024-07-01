package com.multi.seoulsoul.soulLog.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.multi.seoulsoul.soulLog.model.dto.CategoryDTO;
import com.multi.seoulsoul.soulLog.model.dto.DetailRequestDTO;
import com.multi.seoulsoul.soulLog.model.dto.FilesDTO;
import com.multi.seoulsoul.soulLog.model.dto.LikesDTO;
import com.multi.seoulsoul.soulLog.model.dto.LocationDTO;
import com.multi.seoulsoul.soulLog.model.dto.FilterDTO;
import com.multi.seoulsoul.soulLog.model.dto.RepliesDTO;
import com.multi.seoulsoul.soulLog.model.dto.SoulLogDTO;
import com.multi.seoulsoul.soulLog.model.dto.StatsDTO;

@Repository
public class SoulLogDAO {
	
	public int selectSoulLogCount(SqlSessionTemplate sqlSession, FilterDTO filterDTO) {
		
		return sqlSession.selectOne("soulLogMapper.selectSoulLogCount", filterDTO);
		
	}
	
	public List<SoulLogDTO> selectSoulLogList(SqlSessionTemplate sqlSession, FilterDTO filterDTO) {
		
		return sqlSession.selectList("soulLogMapper.selectSoulLogList", filterDTO);
	
	}

	public List<LocationDTO> selectLocationList(SqlSessionTemplate sqlSession) {
		
		return sqlSession.selectList("soulLogMapper.selectLocationList");
		
	}

	public List<CategoryDTO> selectCategoryList(SqlSessionTemplate sqlSession) {
		
		return sqlSession.selectList("soulLogMapper.selectCategoryList");
		
	}

	public int insertSoulLog(SqlSessionTemplate sqlSession, SoulLogDTO soulLogDTO) {
		
		return sqlSession.insert("soulLogMapper.insertSoulLog", soulLogDTO);
		
	}

	public int insertSoulLogFile(SqlSessionTemplate sqlSession, FilesDTO filesDTO) {
		
		return sqlSession.insert("soulLogMapper.insertSoulLogFile", filesDTO);
		
	}

	public SoulLogDTO soulLogDetail(SqlSessionTemplate sqlSession, DetailRequestDTO detailRequestDTO) {
		
		return sqlSession.selectOne("soulLogMapper.soulLogDetail", detailRequestDTO);
		
	}

	public int addViews(SqlSessionTemplate sqlSession, int soulLogNo) {
		
		return sqlSession.update("soulLogMapper.addViews", soulLogNo);
		
	}

	public int insertSoulLogReply(SqlSessionTemplate sqlSession, RepliesDTO repliesDTO) {
		
		return sqlSession.insert("soulLogMapper.insertSoulLogReply", repliesDTO);
		
	}

	public int deleteSoulLog(SqlSessionTemplate sqlSession, SoulLogDTO soulLogDTO) {
		
		return sqlSession.delete("soulLogMapper.deleteSoulLog", soulLogDTO);
		
	}

	public int deleteSoulLogReply(SqlSessionTemplate sqlSession, RepliesDTO repliesDTO) {
		
		return sqlSession.delete("soulLogMapper.deleteSoulLogReply", repliesDTO);
		
	}

	public SoulLogDTO updateDetail(SqlSessionTemplate sqlSession, int soulLogNo) {
		
		return sqlSession.selectOne("soulLogMapper.updateDetail", soulLogNo);
		
	}

	public int updateSoulLog(SqlSessionTemplate sqlSession, SoulLogDTO soulLogDTO) {

		return sqlSession.update("soulLogMapper.updateSoulLog", soulLogDTO);
		
	}

	public int updateImage(SqlSessionTemplate sqlSession, FilesDTO file) {
		
		return sqlSession.update("soulLogMapper.updateImage", file);
		
	}

	public int deleteImage(SqlSessionTemplate sqlSession, int fileNo) {

		return sqlSession.delete("soulLogMapper.deleteImage", fileNo);
		
	}

	public int insertImage(SqlSessionTemplate sqlSession, FilesDTO file) {

		return sqlSession.insert("soulLogMapper.insertImage", file);
		
	}

	public int updateSoulLogReply(SqlSessionTemplate sqlSession, RepliesDTO repliesDTO) {
		
		return sqlSession.update("soulLogMapper.updateSoulLogReply", repliesDTO);
		
	}

	public int insertLike(SqlSessionTemplate sqlSession, LikesDTO likesDTO) {
		
		return sqlSession.insert("soulLogMapper.insertLike", likesDTO);
		
	}

	public int deleteLike(SqlSessionTemplate sqlSession, LikesDTO likesDTO) {
		
		return sqlSession.delete("soulLogMapper.deleteLike", likesDTO);
		
	}

	public List<StatsDTO> selectStats(SqlSessionTemplate sqlSession, int userNo) {
		
		return sqlSession.selectList("soulLogMapper.selectStats", userNo);
		
	}

	

}
