package com.com.blog.view.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.com.blog.R;
import com.com.blog.config.SessionUser;
import com.com.blog.service.dto.LoginDTO;
import com.com.blog.util.MyToast;
import com.com.blog.view.InitActivity;
import com.com.blog.view.post.PostListActivity;
import com.com.blog.viewModel.auth.AuthViewModel;


public class LoginActivity extends AppCompatActivity implements InitActivity {

    private static final String TAG = "LoginActivity";

    private Context mContext = LoginActivity.this;

    private EditText tfUsername, tfPassword;
    private Button btnLogin;
    private TextView tvLinkJoin;

    private AuthViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        initLr();
        initViewModel();
    }

    @Override
    public void init() {
        tvLinkJoin = findViewById(R.id.tvLinkJoin);
        tfUsername = findViewById(R.id.tfUsername);
        tfPassword = findViewById(R.id.tfPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    @Override
    public void initLr() {
        // 회원가입으로 가기
        tvLinkJoin.setOnClickListener(v->{
            Intent intent = new Intent(
                    mContext, JoinActivity.class
            );
            startActivity(intent);
        });

        btnLogin.setOnClickListener(v->{
            String username = tfUsername.getText().toString().trim();
            String password = tfPassword.getText().toString().trim();


            // 공백있을 시 로그인 불가
            if (username.equals("") || password.equals("")){
                MyToast myToast = new MyToast();
                myToast.toast(mContext, "아이디와 비밀번호룰 입력해주세요");
            }
            model.login(new LoginDTO(username, password));

        });

    }

    @Override
    public void initSetting() {

    }

    @Override
    public void initViewModel() {
        model = new ViewModelProvider(this).get(AuthViewModel.class);
        model.getCmRespDTO().observe(this, data ->{

            if (model.getCmRespDTO().getValue().getCode() == 1){
                Log.d(TAG, "initLr: 로그인 성공 : "+ model.getCmRespDTO().getValue().getMsg());

                Intent intent = new Intent(mContext, PostListActivity.class);
                startActivity(intent);
                finish();
            } else {
                Log.d(TAG, "initViewModel: 로그인 실패");
            }

        });

    }
}