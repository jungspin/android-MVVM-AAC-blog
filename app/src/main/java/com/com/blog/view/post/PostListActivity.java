package com.com.blog.view.post;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.com.blog.R;
import com.com.blog.controller.PostController;
import com.com.blog.model.Post;
import com.com.blog.service.dto.CMRespDTO;
import com.com.blog.util.MyConvert;
import com.com.blog.view.CustomAppBarActivity;
import com.com.blog.view.InitActivity;
import com.com.blog.view.post.adapter.PostListAdapter;
import com.com.blog.viewModel.auth.AuthViewModel;
import com.com.blog.viewModel.post.PostListViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostListActivity extends CustomAppBarActivity implements InitActivity {

    private static final String TAG = "PostListActivity";
    private Context mContext = PostListActivity.this;

    private RecyclerView rvPostList;
    private RecyclerView.LayoutManager rvLayoutManager;
    private PostListAdapter postListAdapter;

    private PostListViewModel postsModel;
    //private AuthViewModel authModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        init();
        initLr();
        initSetting();
        initAdapter();

        initViewModel();
        //initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void init() {
        rvPostList = findViewById(R.id.rvPostList);
    }

    @Override
    public void initLr() {

    }

    @Override
    public void initSetting() {
        onAppBarSettings("Home");
    }

    @Override
    public void initAdapter() {
        rvLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        rvPostList.setLayoutManager(rvLayoutManager);

        postListAdapter = new PostListAdapter((PostListActivity) mContext);
        rvPostList.setAdapter(postListAdapter);
    }

    @Override
    public void initData() {

        //postsModel.findAll(authModel.getToken());
    }

    @Override
    public void initViewModel() {
        postsModel = new ViewModelProvider(this).get(PostListViewModel.class);
        postsModel.getPosts().observe(this, data -> {
            postListAdapter.addItems(postsModel.getPosts().getValue());
        });

        postsModel.subscribe().observe(this, data -> {
            Log.d(TAG, "initViewModel: 옵저버 발동");


            if (postsModel.subscribe().getValue().isLogin()) {
                Log.d(TAG, "initLr: 로그인 성공 : " + postsModel.subscribe().getValue().getToken());

            } else {
                Log.d(TAG, "initViewModel: 로그인 실패");
            }
        });
    }
}