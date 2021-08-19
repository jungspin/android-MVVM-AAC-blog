package com.com.blog.service;

import com.com.blog.model.User;
import com.com.blog.service.dto.CMRespDTO;
import com.com.blog.service.dto.LoginDTO;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("/login")
    Call<CMRespDTO<User>> login(@Body LoginDTO loginDTO);

    @POST("/join")
    Call<CMRespDTO<User>> join(@Body User user);

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://172.30.1.40:8080")
            .build();

    UserService service = retrofit.create(UserService.class);
}
