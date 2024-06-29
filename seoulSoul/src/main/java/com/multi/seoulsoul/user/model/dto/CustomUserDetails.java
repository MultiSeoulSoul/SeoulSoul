package com.multi.seoulsoul.user.model.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    private int userNo;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String email;
    private char blacklistStatus;
    private Timestamp createdDate;
    private List<String> authorities;

    public CustomUserDetails(int userNo, String username, String password, String nickname, String phone, String email,
                             char blacklistStatus, Timestamp createdDate, List<String> authorities) {
        this.userNo = userNo;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.blacklistStatus = blacklistStatus;
        this.createdDate = createdDate;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream()
                          .map(SimpleGrantedAuthority::new)
                          .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return blacklistStatus != 'Y';
    }

    public int getUserNo() {
        return userNo;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public char getBlacklistStatus() {
        return blacklistStatus;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "CustomUserDetails [userNo=" + userNo + ", username=" + username + ", password=" + password
                + ", nickname=" + nickname + ", phone=" + phone + ", email=" + email
                + ", blacklistStatus=" + blacklistStatus + ", createdDate=" + createdDate + "]";
    }
}
