package com.com.blog.service.dto;

import com.com.blog.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {

    private User user;
    private String token;
    private boolean isLogin;
}
