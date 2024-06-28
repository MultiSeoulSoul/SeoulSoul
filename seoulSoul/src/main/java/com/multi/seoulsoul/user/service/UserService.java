package com.multi.seoulsoul.user.service;

import java.util.List;

import com.multi.seoulsoul.soulLog.model.dto.SLBoardDTO;
import com.multi.seoulsoul.soulLog.model.dto.SLReplyDTO;
import com.multi.seoulsoul.user.model.dto.UserDTO;
import com.multi.seoulsoul.user.model.dto.UserPageDTO;
import com.multi.seoulsoul.user.model.dto.UserProfileDTO;

public interface UserService {

	boolean isUserIdAvailable(String userId);

	boolean isUserNicknameAvailable(String nickname);

	void joinUser(UserDTO u) throws Exception;
	
	List<SLBoardDTO> selectSLBoardPage(UserPageDTO up);

	List<SLReplyDTO> selectSLReplyPage(UserPageDTO up);

	void userUpdate(UserDTO u) throws Exception;

	void userPwUpdate(UserDTO u) throws Exception;

	void userDelete(int userNo) throws Exception;

	void updateProfile(UserProfileDTO up) throws Exception;

	void updateCustomUserDetails(String userId);
}
