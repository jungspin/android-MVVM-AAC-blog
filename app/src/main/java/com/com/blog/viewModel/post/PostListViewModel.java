package com.com.blog.viewModel.post;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.com.blog.model.Post;
import com.com.blog.service.PostService;
import com.com.blog.service.dto.CMRespDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostListViewModel extends ViewModel {

    private static final String TAG = "PostListViewModel";

    private MutableLiveData<List<Post>> mdPosts = new MutableLiveData<>();
    private PostService postService = PostService.service;

    public MutableLiveData<List<Post>> getPosts(){
        return mdPosts;
    }

    public void findAll(){
        postService.findAll().enqueue(new Callback<CMRespDTO<List<Post>>>() {
            @Override
            public void onResponse(Call<CMRespDTO<List<Post>>> call, Response<CMRespDTO<List<Post>>> response) {
                List<Post> posts = response.body().getData();
                //Log.d(TAG, "onResponse: " + posts.get(0).getTitle());
                mdPosts.setValue(posts);
            }

            @Override
            public void onFailure(Call<CMRespDTO<List<Post>>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
