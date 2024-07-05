package com.multi.seoulsoul.soulLog.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.seoulsoul.achieve.model.dao.AchieveDAO;
import com.multi.seoulsoul.achieve.model.dto.AchCateCountDTO;
import com.multi.seoulsoul.achieve.model.dto.AchCateDTO;
import com.multi.seoulsoul.achieve.model.dto.AchCateGetDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaCountDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaGetDTO;
import com.multi.seoulsoul.soulLog.model.dao.SoulLogDAO;
import com.multi.seoulsoul.soulLog.model.dto.CategoryDTO;
import com.multi.seoulsoul.soulLog.model.dto.DetailRequestDTO;
import com.multi.seoulsoul.soulLog.model.dto.FilesDTO;
import com.multi.seoulsoul.soulLog.model.dto.FilterDTO;
import com.multi.seoulsoul.soulLog.model.dto.LikesDTO;
import com.multi.seoulsoul.soulLog.model.dto.LocationDTO;
import com.multi.seoulsoul.soulLog.model.dto.RepliesDTO;
import com.multi.seoulsoul.soulLog.model.dto.SoulLogDTO;
import com.multi.seoulsoul.soulLog.model.dto.StatsDTO;

@EnableAspectJAutoProxy
@Transactional(rollbackFor = {Exception.class})
@Service
public class SoulLogServiceImpl implements SoulLogService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final SoulLogDAO soulLogDAO;
	private final AchieveDAO achieveDAO;
	
	public SoulLogServiceImpl(SoulLogDAO soulLogDAO, AchieveDAO achieveDAO) {
		this.soulLogDAO = soulLogDAO; 
		this.achieveDAO = achieveDAO;
	}

	
	@Override
	public int selectSoulLogCount(FilterDTO filterDTO) throws Exception {
		
		return soulLogDAO.selectSoulLogCount(sqlSession, filterDTO);
		
	}
	

	@Override
	public List<SoulLogDTO> selectSoulLogList(FilterDTO filterDTO) throws Exception {
		
		return soulLogDAO.selectSoulLogList(sqlSession, filterDTO);
		
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
		
		int result = 0;
		
		int logResult = soulLogDAO.insertSoulLog(sqlSession, soulLogDTO);
		System.out.println("로그 insert 후 리턴값은 >>>>> " + logResult);
			
		// insert 후 useGeneratedKeys="true" keyProperty="soulLogNo"로 인해 DTO에 insert한 soulLogNo가 담겨있다.
		int soulLogNo = soulLogDTO.getSoulLogNo();
			
		// insert한 soulLogDTO에 담겨 있는 files에 대한 리스트를 가져온다.
		List<FilesDTO> files = soulLogDTO.getFiles();
			
		int fileResult = 0;
						
		for(int i = 0; i < files.size(); i++) {
			// 그 files에 현재 soulLogNo가 모두 0으로 되어 있는데, 이를 insert한 soulLogNo로 set해준다.
			files.get(i).setSoulLogNo(soulLogNo);
			fileResult += soulLogDAO.insertSoulLogFile(sqlSession, files.get(i));
		}
			
		System.out.println("파일 insert 후 리턴값은 >>>>> " + fileResult);
		System.out.println("파일 size는 >>>>> " + files.size());
			
			
		if (logResult <= 0 || fileResult != files.size()) {
			System.out.println("insertSoulLog 오류 나서 결과 검사 탑니다!");
            throw new Exception("소울로그 작성에 실패했습니다. 트랜잭션이 롤백을 실행합니다.");
        }
		else {
			result = 1;
			
			int userNo = soulLogDTO.getWriter().getUserNo();
			
			achieveDAO.updateAchLocaCount(sqlSession, soulLogDTO);
			achieveDAO.updateAchCateCount(sqlSession, soulLogDTO);
			
			// 쓴 글의 locationCode를 파라미터로 넣어서(soulLogDTO) 해당 업적의 ach_no와 max_count를 AchLocaDTO로 받아옴
			AchLocaDTO achLocaDTO = achieveDAO.selectAchLoca(sqlSession, soulLogDTO);
			
			int locaMaxCount = achLocaDTO.getMaxCount();
			
			// 쓴 글의 userNo와 location를 파라미터로 넣어서(soulLogDTO) cur_count를 받아
			AchLocaCountDTO achLocaCountDTO = achieveDAO.selectLocaCurCount(sqlSession, soulLogDTO);
			
			int locaCurCount = achLocaCountDTO.getCurCount();
			
			// 그 둘을 비교하여 max_count 와 cur_count가 동일하면 location get 테이블에 status를 Y로 업데이트옴
			if(locaCurCount == locaMaxCount) {
				
				AchLocaGetDTO achLocaGetDTO = new AchLocaGetDTO();
				achLocaGetDTO.setUserNo(userNo);
				achLocaGetDTO.setAchCate(achLocaDTO);
				
				achieveDAO.updateAchLocaGet(sqlSession, achLocaGetDTO);
				
			}
			
			// 쓴 글의 categoryCode를 파라미터로 넣어서(soulLogDTO) 해당 업적의 ach_no와 max_count를 AchCateDTO로 받아오는 DAO 메서드 필요
			AchCateDTO achCateDTO = achieveDAO.selectAchCate(sqlSession, soulLogDTO);

			int cateMaxCount = achCateDTO.getMaxCount();
						
			// 쓴 글의 userNo와 category를 파라미터로 넣어서(soulLogDTO) cur_count를 받아오는 DAO 메서드 필요
			AchCateCountDTO achCateCountDTO = achieveDAO.selectCateCurCount(sqlSession, soulLogDTO);
						
			int cateCurCount = achCateCountDTO.getCurCount();
						
			// 그 둘을 비교하여 max_count 와 cur_count가 동일해지면 // category get 테이블에 status를 Y로 업데이트하는 메서드 필요 GET DTO
			if(cateCurCount == cateMaxCount) {
							
				AchCateGetDTO achCateGetDTO = new AchCateGetDTO();
				achCateGetDTO.setUserNo(userNo);
				achCateGetDTO.setAchCate(achCateDTO);
							
				achieveDAO.updateAchCateGet(sqlSession, achCateGetDTO);
							
			}			
			
		}
		
        return result;
		
	}


	@Override
	public SoulLogDTO soulLogDetail(DetailRequestDTO detailRequestDTO) throws Exception {
		
		return soulLogDAO.soulLogDetail(sqlSession, detailRequestDTO);
	
	}


	@Override
	public int addViews(int soulLogNo) throws Exception {
		
		return soulLogDAO.addViews(sqlSession, soulLogNo);
		
	}


	@Override
	public int insertSoulLogReply(RepliesDTO repliesDTO) throws Exception {
		
		return soulLogDAO.insertSoulLogReply(sqlSession, repliesDTO);
		
	}


	@Override
	public int deleteSoulLog(SoulLogDTO soulLogDTO) throws Exception {
		
		return soulLogDAO.deleteSoulLog(sqlSession, soulLogDTO);
		
	}


	@Override
	@Transactional(rollbackFor = {Exception.class})
	public int deleteSoulLogReply(RepliesDTO repliesDTO) throws Exception {
		
		return soulLogDAO.deleteSoulLogReply(sqlSession, repliesDTO);
		
	}


	@Override
	public SoulLogDTO updateDetail(int soulLogNo) throws Exception {
		
		return soulLogDAO.updateDetail(sqlSession, soulLogNo);
	}


	@Override
	public int updateSoulLog(SoulLogDTO soulLogDTO) throws Exception {
		
		return soulLogDAO.updateSoulLog(sqlSession, soulLogDTO);
		
	}


	@Override
	public int updateImage(FilesDTO file) throws Exception {

		return soulLogDAO.updateImage(sqlSession, file);
		
	}


	@Override
	public int deleteImage(int fileNo) throws Exception {

		return soulLogDAO.deleteImage(sqlSession, fileNo);
		
	}


	@Override
	public int insertImage(FilesDTO file) throws Exception {

		return soulLogDAO.insertImage(sqlSession, file);
		
	}


	@Override
	public int updateSoulLogReply(RepliesDTO repliesDTO) throws Exception {
		
		return soulLogDAO.updateSoulLogReply(sqlSession, repliesDTO);
		
	}


	@Override
	public int insertLike(LikesDTO likesDTO) throws Exception {
		
		return soulLogDAO.insertLike(sqlSession, likesDTO);
		
	}


	@Override
	public int deleteLike(LikesDTO likesDTO) throws Exception {
		
		return soulLogDAO.deleteLike(sqlSession, likesDTO);
		
	}


	@Override
	public List<StatsDTO> selectStats(int userNo) throws Exception {
		
		return soulLogDAO.selectStats(sqlSession, userNo);
		
	}
	
	
	
}
