package com.multi.seoulsoul.soulLog.service;

import java.util.List;

import com.multi.seoulsoul.soulLog.model.dto.CategoryDTO;
import com.multi.seoulsoul.soulLog.model.dto.DetailRequestDTO;
import com.multi.seoulsoul.soulLog.model.dto.FilesDTO;
import com.multi.seoulsoul.soulLog.model.dto.LocationDTO;
import com.multi.seoulsoul.soulLog.model.dto.PageDTO;
import com.multi.seoulsoul.soulLog.model.dto.RepliesDTO;
import com.multi.seoulsoul.soulLog.model.dto.SoulLogDTO;

public interface SoulLogService {
	
	int selectSoulLogCount() throws Exception;

	List<SoulLogDTO> selectSoulLogList(PageDTO pageDTO) throws Exception;

	List<LocationDTO> selectLocationList() throws Exception;

	List<CategoryDTO> selectCategoryList() throws Exception;

	int insertSoulLog(SoulLogDTO soulLogDTO) throws Exception;

	SoulLogDTO soulLogDetail(DetailRequestDTO detailRequestDTO) throws Exception;

	int addViews(int soulLogNo) throws Exception;

	int insertSoulLogReply(RepliesDTO repliesDTO) throws Exception;

	int deleteSoulLog(int soulLogNo) throws Exception;

	int deleteSoulLogReply(int replyNo) throws Exception;

	SoulLogDTO updateDetail(int soulLogNo) throws Exception;

	int updateSoulLog(SoulLogDTO soulLogDTO) throws Exception;

	int updateImage(FilesDTO file) throws Exception;

	int deleteImage(int fileNo) throws Exception;

	int insertImage(FilesDTO file) throws Exception;
	
}
