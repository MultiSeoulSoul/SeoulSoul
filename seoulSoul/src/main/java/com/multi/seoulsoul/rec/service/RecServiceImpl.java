package com.multi.seoulsoul.rec.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.multi.seoulsoul.rec.model.dao.RecDAO;
import com.multi.seoulsoul.rec.model.dto.RecDTO;

@Service
public class RecServiceImpl implements RecService {

	@Autowired
	private RecDAO recDAO;

	@Override
	@Transactional
	public void recInsertForm(RecDTO recDTO) throws Exception {
		recDAO.insertRecommendation(recDTO);
	}

	@Override
	public List<RecDTO> selectAllRecommendations() throws Exception {
		List<RecDTO> recList = recDAO.selectAllRecommendations();
		for (RecDTO rec : recList) {
			System.out.println("Service Layer Rec: " + rec);
			System.out.println("Service Layer Image Path: " + rec.getImagePath());
		}
		return recList;
	}

	@Override
	@Transactional
	public void saveFile(int recommendationNo, String originalName, String savedName) throws Exception {
		recDAO.insertFile(recommendationNo, originalName, savedName);
	}

	@Override
	public RecDTO getRecommendationById(int recommendationNo) throws Exception {
		return recDAO.selectRecommendationById(recommendationNo);
	}
	

	@Override
	public void saveRecommendation(RecDTO recDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RecDTO selectRecommendationByNo(String recommendationNo) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
    public RecDTO selectRecommendationByNo(int recommendationNo) throws Exception {
        return recDAO.selectRecommendationById(recommendationNo);
    }

	
	@Override
    public void incrementViews(int recommendationNo) throws Exception {
        recDAO.incrementViews(recommendationNo);
    }

	@Override
	public void increaseViews(int recommendationNo) {
		// TODO Auto-generated method stub
		
	}
	@Override
    public RecDTO selectRecommendationById(int recommendationNo) throws Exception {
        return recDAO.selectRecommendationById(recommendationNo);
    }

    @Override
    public void deleteRecommend(int recommendationNo) {
        recDAO.deleteRecommend(recommendationNo); // 구현 추가
    }

	@Override
	public RecDTO selectRecByNo(int recommendationNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRec(RecDTO rec, MultipartFile image) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateRec(RecDTO rec) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertRecommendation(RecDTO recDTO) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRec(int recommendationNo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateRecommend(RecDTO recDTO) throws Exception {
	    recDAO.updateRecommend(recDTO);
	    if (recDTO.getImagePath() != null) {
	        Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("recommendationNo", recDTO.getRecommendationNo());
	        paramMap.put("originalName", recDTO.getImagePath());
	        paramMap.put("savedName", recDTO.getImagePath());
	        recDAO.updateFile(paramMap);
	    }
	}

	@Override
	public void updateFile(int recommendationNo, String originalName, String savedName) throws Exception {
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("recommendationNo", recommendationNo);
	    paramMap.put("originalName", originalName);
	    paramMap.put("savedName", savedName);
	    recDAO.updateFile(paramMap);
	}

	@Override
	public void updateFile(Map<String, Object> fileParams) throws Exception {
		// TODO Auto-generated method stub
		
	}
}