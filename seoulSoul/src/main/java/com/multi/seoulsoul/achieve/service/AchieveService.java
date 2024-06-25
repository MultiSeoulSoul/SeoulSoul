package com.multi.seoulsoul.achieve.service;

import java.util.List;

import com.multi.seoulsoul.achieve.model.dto.AchieveDTO;

public interface AchieveService {

	int insertAchieveLoca(AchieveDTO achieveDTO);
	
	int insertAchieveCate(AchieveDTO achieveDTO);

	List<AchieveDTO> achieveLocaList();
	
	List<AchieveDTO> achieveCateList();

	int deleteAchieveLoca(int achNo);
	
	int deleteAchieveCate(int achNo);

}
