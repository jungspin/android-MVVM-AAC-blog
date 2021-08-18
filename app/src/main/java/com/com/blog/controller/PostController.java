package com.com.blog.controller;

import android.util.Log;

import com.com.blog.service.PostService;
import com.com.blog.service.dto.CMRespDTO;

import retrofit2.Call;

public class PostController {

    private static final String TAG = "PostController";
    private PostService postService = PostService.service;

//    public Call<CMRespDTO> findAll(){
//        Log.d(TAG, "findAll: 1");
//        return postService.findAll();
//    }
}
