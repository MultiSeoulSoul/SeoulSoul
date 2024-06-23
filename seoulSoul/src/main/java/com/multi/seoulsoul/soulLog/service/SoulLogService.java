package com.multi.seoulsoul.soulLog.service;

import java.util.List;

import com.multi.seoulsoul.soulLog.model.dto.PageDTO;
import com.multi.seoulsoul.soulLog.model.dto.SoulLogDTO;

public interface SoulLogService {

	List<SoulLogDTO> selectSoulLogList(PageDTO pageDTO) throws Exception;
	
}
