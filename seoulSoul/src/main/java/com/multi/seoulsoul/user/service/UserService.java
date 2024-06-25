package com.multi.seoulsoul.user.service;

import java.util.List;

import com.multi.seoulsoul.soulLog.model.dto.SLBoardDTO;
import com.multi.seoulsoul.user.model.dto.UserDTO;
import com.multi.seoulsoul.user.model.dto.UserPageDTO;

public interface UserService {

	boolean isUserIdAvailable(String userId);

	boolean isUserNicknameAvailable(String nickname);

	void joinUser(UserDTO u) throws Exception;

	List<SLBoardDTO> selectSLBoardPage(UserPageDTO up);

	int selectCount();
}
