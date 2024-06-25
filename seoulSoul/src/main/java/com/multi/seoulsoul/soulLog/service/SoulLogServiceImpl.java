package com.multi.seoulsoul.soulLog.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.seoulsoul.soulLog.model.dao.SoulLogDAO;
import com.multi.seoulsoul.soulLog.model.dto.CategoryDTO;
import com.multi.seoulsoul.soulLog.model.dto.FilesDTO;
import com.multi.seoulsoul.soulLog.model.dto.LocationDTO;
import com.multi.seoulsoul.soulLog.model.dto.PageDTO;
import com.multi.seoulsoul.soulLog.model.dto.SoulLogDTO;

@Transactional(rollbackFor = {Exception.class}) // Transaction annotation 방식
@Service
public class SoulLogServiceImpl implements SoulLogService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final SoulLogDAO soulLogDAO;
	
	public SoulLogServiceImpl(SoulLogDAO soulLogDAO) {
		this.soulLogDAO = soulLogDAO; 
	}

	
	@Override
	public int selectSoulLogCount() throws Exception {
		
		return soulLogDAO.selectSoulLogCount(sqlSession);
		
	}
	

	@Override
	public List<SoulLogDTO> selectSoulLogList(PageDTO pageDTO) throws Exception {
		
		return soulLogDAO.selectSoulLogList(sqlSession, pageDTO);
		
	}


	@Override
	public List<LocationDTO> selectLocationList() throws Exception {
		
		return soulLogDAO.selectLocationList(sqlSession);
		
	}


	@Override
	public List<CategoryDTO> selectCategoryList() throws Exception {
		
		return soulLogDAO.selectCategoryList(sqlSession);
	}


	@Override
	public int insertSoulLog(SoulLogDTO soulLogDTO) throws Exception {
		
		int result = soulLogDAO.insertSoulLog(sqlSession, soulLogDTO);
		
		// insert 후 useGeneratedKeys="true" keyProperty="soulLogNo"로 인해 DTO에 insert한 soulLogNo가 담겨있다.
		int soulLogNo = soulLogDTO.getSoulLogNo();
		
		if (result == 0) {
            throw new Exception("소울로그 작성에 실패했습니다. 트랜잭션이 롤백을 실행합니다.");
        }
		else if (result == 1) {
			
			// insert한 soulLogDTO에 담겨 있는 files에 대한 정보를 가져온다.
			List<FilesDTO> files = soulLogDTO.getFiles();
			
			for(int i = 0; i < files.size(); i++) {
				// 그 files에 현재 soulLogNo가 모두 0으로 되어 있는데, 이를 insert한 soulLogNo로 set해준다.
				files.get(i).setSoulLogNo(soulLogNo);
			}
			
			
			
		}
		
        return result;
		
	}
	
	
	
}
