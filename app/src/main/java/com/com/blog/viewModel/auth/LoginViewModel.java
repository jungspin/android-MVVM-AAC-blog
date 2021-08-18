package com.com.blog.viewModel.auth;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.com.blog.config.SessionUser;
import com.com.blog.model.User;
import com.com.blog.service.UserService;
import com.com.blog.service.dto.CMRespDTO;
import com.com.blog.service.dto.LoginDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private static final String TAG = "LoginViewModel";

    private MutableLiveData<CMRespDTO> mdResp = new MutableLiveData<>();
    private UserService userService = UserService.service;

    public MutableLiveData<CMRespDTO> getMdResp(){
        return mdResp;
    }

    public void login(LoginDTO loginDTO){
      userService.login(loginDTO).enqueue(new Callback<CMRespDTO<User>>() {
          @Override
          public void onResponse(Call<CMRespDTO<User>> call, Response<CMRespDTO<User>> response) {
              if (response.body().getCode() == 1){
                  SessionUser.token = response.headers().get("Authorization");
                  SessionUser.user = response.body().getData();
                  mdResp.setValue(response.body());
              }

          }

          @Override
          public void onFailure(Call<CMRespDTO<User>> call, Throwable t) {
            t.printStackTrace();
          }
      });
    }
}
