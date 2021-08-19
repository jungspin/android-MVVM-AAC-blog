package com.com.blog.service;

import com.com.blog.config.HeaderInterceptor;
import com.com.blog.model.Post;
import com.com.blog.service.dto.CMRespDTO;
import com.com.blog.service.dto.UpdateDTO;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostService {

    @GET("/post")
    Call<CMRespDTO<List<Post>>> findAll();

    @GET("/post/{id}")
    Call<CMRespDTO<Post>> findById(@Path("id")int postId/*, @Header("Authorization")String token*/);

    @DELETE("/post/{id}")
    Call<CMRespDTO> deleteById(@Path("id")int postId/*, @Header("Authorization")String token*/);

    @PUT("/post/{id}")
    Call<CMRespDTO<Post>> updateById(@Path("id")int postId, @Body UpdateDTO updateDTO/*, @Header("Authorization")String token*/);

    @POST("/post")
    Call<CMRespDTO<Post>> insert(@Body Post post/*, @Header("Authorization")String token*/);

    OkHttpClient client = new OkHttpClient.Builder().
            addInterceptor(new HeaderInterceptor()).build();

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://172.30.1.40:8080")
            .client(client)
            .build();

    PostService service = retrofit.create(PostService.class);
}
