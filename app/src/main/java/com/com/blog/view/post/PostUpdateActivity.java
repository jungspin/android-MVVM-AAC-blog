package com.com.blog.view.post;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.com.blog.R;
import com.com.blog.service.dto.UpdateDTO;
import com.com.blog.view.CustomAppBarActivity;
import com.com.blog.view.InitActivity;
import com.com.blog.viewModel.post.PostDetailViewModel;


public class PostUpdateActivity extends CustomAppBarActivity implements InitActivity {

    private static final String TAG = "PostUpdateActivity";
    private Context mContext = PostUpdateActivity.this;

    private PostDetailViewModel model;

    private EditText tfTitle, tfWriter, tfContent;
    private Button btnUpdate;

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_update);

        init();
        initLr();
        initSetting();
        initViewModel();
        initData();
    }

    @Override
    public void init() {
        tfTitle = findViewById(R.id.tfTitle);
        tfWriter = findViewById(R.id.tfWriter);
        tfContent = findViewById(R.id.tfContent);
        btnUpdate = findViewById(R.id.btnUpdate);
    }

    @Override
    public void initLr() {
        int postId = getIntent().getIntExtra("postId", 0);

        btnUpdate.setOnClickListener(v->{
            String title = tfTitle.getText().toString();
            String content = tfContent.getText().toString();

            model.updateById(postId, new UpdateDTO(title, content));
            finish();
        });

    }

    @Override
    public void initSetting() {
        onAppBarSettings(true, "Update");
    }

    @Override
    public void initViewModel() {
        model = new ViewModelProvider(this).get(PostDetailViewModel.class);
        model.getMdPost().observe(this, data ->{
            tfTitle.setText(model.getMdPost().getValue().getData().getTitle());
            tfWriter.setText(model.getMdPost().getValue().getData().getUser().getUsername());
            tfContent.setText(model.getMdPost().getValue().getData().getContent());
        });
    }

    @Override
    public void initData() {
        int postId = getIntent().getIntExtra("postId", 0);
        model.findById(postId);
    }
}