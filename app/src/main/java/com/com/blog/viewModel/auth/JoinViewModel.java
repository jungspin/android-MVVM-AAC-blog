package com.com.blog.viewModel.auth;

import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.com.blog.model.User;
import com.com.blog.service.UserService;
import com.com.blog.service.dto.CMRespDTO;
import com.com.blog.view.auth.JoinActivity;
import com.com.blog.view.auth.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinViewModel extends ViewModel {

    private static final String TAG = "JoinViewModel";

    private MutableLiveData<CMRespDTO> mdResp = new MutableLiveData<>();
    private UserService userService = UserService.service;


    public MutableLiveData<CMRespDTO> getMdResp(){
        return mdResp;
    }


    public void join(User user){
        userService.join(user).enqueue(new Callback<CMRespDTO<User>>() {
            @Override
            public void onResponse(Call<CMRespDTO<User>> call, Response<CMRespDTO<User>> response) {
                Log.d(TAG, "onResponse:  " + response.body());
                if (response.body().getCode() == 1){
                    getMdResp().setValue(response.body());
                }


            }

            @Override
            public void onFailure(Call<CMRespDTO<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }



}
