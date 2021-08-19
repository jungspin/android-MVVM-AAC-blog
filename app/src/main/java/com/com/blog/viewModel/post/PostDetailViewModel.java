package com.com.blog.viewModel.post;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.com.blog.model.Post;
import com.com.blog.model.User;
import com.com.blog.service.PostService;
import com.com.blog.service.dto.AuthDTO;
import com.com.blog.service.dto.CMRespDTO;
import com.com.blog.service.dto.UpdateDTO;
import com.com.blog.viewModel.auth.AuthViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailViewModel extends AuthViewModel {

    private static final String TAG = "PostDetailViewModel";

    private MutableLiveData<CMRespDTO<Post>> mdPost = new MutableLiveData<>();
    private PostService postService = PostService.service;

    public MutableLiveData<CMRespDTO<Post>> getMdPost(){
        return mdPost;
    }



    public void findById(int postId){
        postService.findById(postId).enqueue(new Callback<CMRespDTO<Post>>() {
            @Override
            public void onResponse(Call<CMRespDTO<Post>> call, Response<CMRespDTO<Post>> response) {
                Log.d(TAG, "onResponse: " + response.body().getData().getTitle());
                mdPost.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CMRespDTO<Post>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void updateById(int postId, UpdateDTO updateDTO){
        postService.updateById(postId, updateDTO).enqueue(new Callback<CMRespDTO<Post>>() {
            @Override
            public void onResponse(Call<CMRespDTO<Post>> call, Response<CMRespDTO<Post>> response) {
                Log.d(TAG, "onResponse: " + response.body().getData().getTitle());
                mdPost.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CMRespDTO<Post>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    // delete 는 응답을 post로 안함!@@@!!!!!
    public void deleteById(int postId){

    }

    public void insert(Post post){
        postService.insert(post).enqueue(new Callback<CMRespDTO<Post>>() {
            @Override
            public void onResponse(Call<CMRespDTO<Post>> call, Response<CMRespDTO<Post>> response) {
                if(response.body().getCode() == 1){
                    mdPost.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<CMRespDTO<Post>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
