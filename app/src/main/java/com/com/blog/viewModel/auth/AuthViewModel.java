package com.com.blog.viewModel.auth;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.com.blog.config.SessionUser;
import com.com.blog.model.User;
import com.com.blog.service.UserService;
import com.com.blog.service.dto.AuthDTO;
import com.com.blog.service.dto.CMRespDTO;
import com.com.blog.service.dto.LoginDTO;
import com.com.blog.util.MyConvert;
import com.com.blog.util.MyToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private MutableLiveData<CMRespDTO> cmRespDTO = new MutableLiveData<>();
    private UserService userService = UserService.service;



    public MutableLiveData<CMRespDTO> getCmRespDTO(){
        return cmRespDTO;
    }


    public void join(User user){
        userService.join(user).enqueue(new Callback<CMRespDTO<User>>() {
            @Override
            public void onResponse(Call<CMRespDTO<User>> call, Response<CMRespDTO<User>> response) {
                Log.d(TAG, "onResponse:  " + response.body());
                if (response.body().getCode() == 1){
//                    AuthDTO authDTO = AuthDTO.builder().user(response.body().getData()).build();
//                    Log.d(TAG, "onResponse: auth : " + authDTO);
//                    mdAuthDTO.setValue(authDTO);
                    cmRespDTO.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CMRespDTO<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void login(LoginDTO loginDTO){
      userService.login(loginDTO).enqueue(new Callback<CMRespDTO<User>>() {
          @Override
          public void onResponse(Call<CMRespDTO<User>> call, Response<CMRespDTO<User>> response) {
              if (response.body().getCode() == 1){
//                    AuthDTO authDTO = AuthDTO.builder()
//                            .user(response.body().getData())
//                            .token(response.headers().get("Authorization"))
//                            .isLogin(true).build();
//                    mdAuthDTO.setValue(authDTO);
                  SessionUser.user = response.body().getData();
                  SessionUser.token = response.headers().get("Authorization");
                  cmRespDTO.setValue(response.body());


              } else {

              }
              //Log.d(TAG, "onResponse: " + authDTO.getToken());
          }

          @Override
          public void onFailure(Call<CMRespDTO<User>> call, Throwable t) {
            t.printStackTrace();

          }
      });

    }

}
