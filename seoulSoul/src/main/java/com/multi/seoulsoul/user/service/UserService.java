package com.multi.seoulsoul.user.service;

import com.multi.seoulsoul.user.model.dto.UserDTO;

public interface UserService {

	boolean isUserIdAvailable(String userId);

	boolean isUserNicknameAvailable(String nickname);

	void joinUser(UserDTO u) throws Exception;

//	UserDTO loginUser(UserDTO u) throws Exception;

}
