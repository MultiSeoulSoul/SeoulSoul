package com.multi.seoulsoul.user.service;

import com.multi.seoulsoul.user.model.dto.UserDTO;

public interface UserService {

	UserDTO loginUser(UserDTO u) throws Exception;

}
