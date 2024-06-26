package com.multi.seoulsoul.rec.service;

import com.multi.seoulsoul.rec.model.dao.RecDAO;
import com.multi.seoulsoul.rec.model.dto.RecDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public RecDTO selectRecommendationById(int recommendationNo) throws Exception {
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
	public void insertRecommendation(RecDTO recDTO) {
		// TODO Auto-generated method stub
		
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
}