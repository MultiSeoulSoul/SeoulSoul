package com.multi.seoulsoul.soulLog.service;

import java.util.List;

import com.multi.seoulsoul.soulLog.model.dto.CategoryDTO;
import com.multi.seoulsoul.soulLog.model.dto.DetailRequestDTO;
import com.multi.seoulsoul.soulLog.model.dto.FilesDTO;
import com.multi.seoulsoul.soulLog.model.dto.LikesDTO;
import com.multi.seoulsoul.soulLog.model.dto.LocationDTO;
import com.multi.seoulsoul.soulLog.model.dto.FilterDTO;
import com.multi.seoulsoul.soulLog.model.dto.RepliesDTO;
import com.multi.seoulsoul.soulLog.model.dto.SoulLogDTO;
import com.multi.seoulsoul.soulLog.model.dto.StatsDTO;

public interface SoulLogService {
	
	int selectSoulLogCount(FilterDTO filterDTO) throws Exception;

	List<SoulLogDTO> selectSoulLogList(FilterDTO filterDTO) throws Exception;

	List<LocationDTO> selectLocationList() throws Exception;

	List<CategoryDTO> selectCategoryList() throws Exception;

	int insertSoulLog(SoulLogDTO soulLogDTO) throws Exception;

	SoulLogDTO soulLogDetail(DetailRequestDTO detailRequestDTO) throws Exception;

	int addViews(int soulLogNo) throws Exception;

	int insertSoulLogReply(RepliesDTO repliesDTO) throws Exception;

	int deleteSoulLog(SoulLogDTO soulLogDTO) throws Exception;

	int deleteSoulLogReply(RepliesDTO repliesDTO) throws Exception;

	SoulLogDTO updateDetail(int soulLogNo) throws Exception;

	int updateSoulLog(SoulLogDTO soulLogDTO) throws Exception;

	int updateImage(FilesDTO file) throws Exception;

	int deleteImage(int fileNo) throws Exception;

	int insertImage(FilesDTO file) throws Exception;

	int updateSoulLogReply(RepliesDTO repliesDTO) throws Exception;

	int insertLike(LikesDTO likesDTO) throws Exception;

	int deleteLike(LikesDTO likesDTO) throws Exception;

	List<StatsDTO> selectStats(int userNo) throws Exception;
	
}
