package com.multi.seoulsoul.soulLog.service;

import java.util.List;

import com.multi.seoulsoul.soulLog.model.dto.CategoryDTO;
import com.multi.seoulsoul.soulLog.model.dto.LocationDTO;
import com.multi.seoulsoul.soulLog.model.dto.PageDTO;
import com.multi.seoulsoul.soulLog.model.dto.SoulLogDTO;

public interface SoulLogService {
	
	int selectSoulLogCount() throws Exception;

	List<SoulLogDTO> selectSoulLogList(PageDTO pageDTO) throws Exception;

	List<LocationDTO> selectLocationList() throws Exception;

	List<CategoryDTO> selectCategoryList() throws Exception;

	
}
