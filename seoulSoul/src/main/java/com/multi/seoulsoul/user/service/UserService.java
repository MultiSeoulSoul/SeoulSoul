package com.multi.seoulsoul.user.service;

import java.util.List;

import com.multi.seoulsoul.user.model.dto.UserDTO;
import com.multi.seoulsoul.user.model.dto.UserPageDTO;
import com.multi.seoulsoul.user.model.dto.UserProfileDTO;
import com.multi.seoulsoul.user.tempDTO.AchievementDTO;
import com.multi.seoulsoul.user.tempDTO.SLBoardDTO;
import com.multi.seoulsoul.user.tempDTO.SLReplyDTO;

public interface UserService {
	
	void joinUser(UserDTO u) throws Exception;

	void userUpdate(UserDTO u) throws Exception;

	void userPwUpdate(UserDTO u) throws Exception;
	
	void updateProfile(UserProfileDTO up) throws Exception;
	
	void userDelete(int userNo) throws Exception;
	
	boolean isUserIdAvailable(String userId);

	boolean isUserNicknameAvailable(String nickname);
	
	void updateCustomUserDetails(String userId);
	
	List<SLBoardDTO> selectSLBoardPage(UserPageDTO up);

	List<SLReplyDTO> selectSLReplyPage(UserPageDTO up);

	List<?> selectEventReplyPage(UserPageDTO up);

	List<?> selectLikesPage(UserPageDTO up);

	List<?> selectHeartBtnPage(UserPageDTO up);

	List<?> selectCsQuestionPage(UserPageDTO up);

	List<?> selectReportPage(UserPageDTO up);

	List<AchievementDTO> getAchievement(int userNo);
	
}
