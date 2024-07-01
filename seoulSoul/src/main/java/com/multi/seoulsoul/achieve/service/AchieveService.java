package com.multi.seoulsoul.achieve.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.multi.seoulsoul.achieve.model.dto.AchCateCountDTO;
import com.multi.seoulsoul.achieve.model.dto.AchCateDTO;
import com.multi.seoulsoul.achieve.model.dto.AchCateIconsDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaCountDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaIconsDTO;
import com.multi.seoulsoul.achieve.model.dto.AdminUserListDTO;
import com.multi.seoulsoul.user.model.dto.UserDTO;

public interface AchieveService {

	int insertAchieveLoca(AchLocaDTO achLocaDTO) throws Exception ;
	
	int insertLocaIcons(AchLocaIconsDTO achLocaIconsDTO) throws Exception ;
	
	int insertAchieveCate(AchCateDTO achCateDTO) throws Exception ;
	
	int insertCateIcons(AchCateIconsDTO achCateIconsDTO) throws Exception ;

	List<AchLocaDTO> achieveLocaList() throws Exception ;
	
	List<AchCateDTO> achieveCateList() throws Exception ;

	int deleteAchieveLoca(int achNo) throws Exception ;
	
	int deleteAchieveCate(int achNo) throws Exception ;

	List<AdminUserListDTO> userList() throws Exception ;
	
	List<UserDTO> blackList() throws Exception ;

	AchLocaDTO getAchLocaById(int achNo) throws Exception ;

	AchLocaIconsDTO getAchLocaIconsByAchNo(int achNo) throws Exception ;

	void updateAchLoca(AchLocaDTO achLocaDTO, AchLocaIconsDTO achLocaIconsDTO, MultipartFile singleFile,
			HttpServletRequest request) throws Exception ;
	
	AchCateDTO getAchCateById(int achNo) throws Exception ;

	AchCateIconsDTO getAchCateIconsByAchNo(int achNo) throws Exception ;
	
	void updateAchCate(AchCateDTO achCateDTO, AchCateIconsDTO achCateIconsDTO, MultipartFile singleFile,
			HttpServletRequest request) throws Exception ;

	void updateBlacklistStatus(int userNo, char status) throws Exception ;
}
