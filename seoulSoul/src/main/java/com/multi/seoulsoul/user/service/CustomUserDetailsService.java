package com.multi.seoulsoul.user.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.multi.seoulsoul.user.model.dto.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final DataSource dataSource;

	public CustomUserDetailsService(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try (Connection connection = dataSource.getConnection()) {
			String query = "SELECT user_no, user_id, user_pw, nickname, phone, email, blacklist_status, created_date FROM USERS WHERE user_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				int userNo = rs.getInt("user_no");
				String userId = rs.getString("user_id");
				String password = rs.getString("user_pw");
				String nickname = rs.getString("nickname");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				char blacklistStatus = rs.getString("blacklist_status").charAt(0);
				Timestamp createdDate = rs.getTimestamp("created_date");

				return new CustomUserDetails(userNo, userId, password, nickname, phone, email, blacklistStatus,
						createdDate);
			} else {
				throw new UsernameNotFoundException("User not found");
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException("Database error", e);
		}
	}
}
