package com.multi.seoulsoul.achieve.service;

import java.util.List;

import com.multi.seoulsoul.achieve.model.dto.AchCateDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaDTO;

public interface AchieveService {

	int insertAchieveLoca(AchLocaDTO achLocaDTO);
	
	int insertAchieveCate(AchCateDTO achCateDTO);

	List<AchLocaDTO> achieveLocaList();
	
	List<AchCateDTO> achieveCateList();

	int deleteAchieveLoca(int achNo);
	
	int deleteAchieveCate(int achNo);

}
