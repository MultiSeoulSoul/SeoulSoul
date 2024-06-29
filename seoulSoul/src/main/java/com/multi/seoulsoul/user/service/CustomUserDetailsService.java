package com.multi.seoulsoul.user.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.multi.seoulsoul.user.model.dao.UserDAO;
import com.multi.seoulsoul.user.model.dto.CustomUserDetails;
import com.multi.seoulsoul.user.model.dto.UserDTO;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserDAO userDAO;

	public CustomUserDetailsService() {
	}
	
	@Autowired
	public CustomUserDetailsService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
  
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Override
    public CustomUserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    	
        UserDTO userDTO = userDAO.findUserByUsername(sqlSession, userId);
        
        if (userDTO == null) {
            throw new UsernameNotFoundException("User not found with username: " + userId);
        }

        // 권한 정보 조회
        List<String> authorities = userDAO.findAuthoritiesByUserNo(sqlSession, userDTO.getUserNo());

        return new CustomUserDetails(
            userDTO.getUserNo(),
            userDTO.getUserId(),
            userDTO.getUserPw(),
            userDTO.getNickname(),
            userDTO.getPhone(),
            userDTO.getEmail(),
            userDTO.getBlacklistStatus(),
            userDTO.getCreatedDate(),
            userDTO.getProfileContent(),
            userDTO.getProfilePicName(),
            authorities
        );
    }
}