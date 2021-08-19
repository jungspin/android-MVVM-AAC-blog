package com.com.blog.view.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.com.blog.R;
import com.com.blog.model.User;
import com.com.blog.view.CustomAppBarActivity;
import com.com.blog.view.InitActivity;
import com.com.blog.viewModel.auth.AuthViewModel;


public class JoinActivity extends CustomAppBarActivity implements InitActivity {

    private static final String TAG = "JoinActivity";
    private JoinActivity mContext = this;

    private EditText tfUsername, tfPassword, tfEmail;
    private Button btnJoin;
    private TextView tvLinkLogin;

    private AuthViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        init();
        initLr();
        initSetting();
        initViewModel();
    }

    @Override
    public void init() {
        tfUsername = findViewById(R.id.tfUsername);
        tfPassword = findViewById(R.id.tfPassword);
        tfEmail = findViewById(R.id.tfEmail);
        btnJoin = findViewById(R.id.btnJoin);
        tvLinkLogin = findViewById(R.id.tvLinkLogin);
    }

    @Override
    public void initLr() {
        tvLinkLogin.setOnClickListener(v->{
            Intent intent = new Intent(
                    mContext, LoginActivity.class
            );
            startActivity(intent);
        });

        btnJoin.setOnClickListener(v->{
            String username = tfUsername.getText().toString();
            String password = tfPassword.getText().toString();
            String email = tfEmail.getText().toString();

            User user = User.builder().username(username).password(password).email(email).build();
            model.join(user);


        });
    }

    @Override
    public void initSetting() {

    }

    @Override
    public void initViewModel() {
        model = new ViewModelProvider(this).get(AuthViewModel.class);
        model.subscribe().observe(this, changeData -> {
            if (model.subscribe().getValue() != null){
                Log.d(TAG, "initLr: 회원가입 성공");
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        });

    }
}