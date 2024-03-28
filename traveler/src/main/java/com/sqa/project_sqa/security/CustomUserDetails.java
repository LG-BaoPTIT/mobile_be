package com.sqa.project_sqa.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sqa.project_sqa.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private int userId;
    private String name;
    private String email;
    private String userName;
    @JsonIgnore
    private String password;
    private String phone;

    private GrantedAuthority authority;

    public CustomUserDetails() {
    }


    public static CustomUserDetails mapUserToUserDetail(User user) {
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
        return new CustomUserDetails(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getUserName(),
                user.getPassword(),
                user.getPhone(),
                authority
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(this.authority);
    }

    @Override
    public String getUsername() {
        return this.userName;
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
        return true;

    }


}
