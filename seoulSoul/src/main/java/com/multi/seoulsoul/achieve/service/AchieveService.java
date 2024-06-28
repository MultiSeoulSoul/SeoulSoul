package com.multi.seoulsoul.achieve.service;

import java.util.List;

import com.multi.seoulsoul.achieve.model.dto.AchCateDTO;
import com.multi.seoulsoul.achieve.model.dto.AchCateIconsDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaIconsDTO;
import com.multi.seoulsoul.user.model.dto.UserDTO;

public interface AchieveService {

	int insertAchieveLoca(AchLocaDTO achLocaDTO) throws Exception ;
	
	int insertLocaIcons(AchLocaIconsDTO achLocaIconsDTO) throws Exception ;
	
	int insertAchieveCate(AchCateDTO achCateDTO) throws Exception ;
	
	int insertCateIcons(AchCateIconsDTO achCateIconsDTO) throws Exception ;

	List<AchLocaDTO> achieveLocaList();
	
	List<AchCateDTO> achieveCateList();

	int deleteAchieveLoca(int achNo);
	
	int deleteAchieveCate(int achNo);

	List<UserDTO> userList();

	AchLocaDTO getAchLocaById(int achNo);

}
