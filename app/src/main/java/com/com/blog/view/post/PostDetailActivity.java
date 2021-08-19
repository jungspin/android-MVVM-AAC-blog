package com.com.blog.view.post;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.com.blog.R;
import com.com.blog.view.CustomAppBarActivity;
import com.com.blog.view.InitActivity;
import com.com.blog.viewModel.post.PostDetailViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class PostDetailActivity extends CustomAppBarActivity implements InitActivity {

    private static final String TAG = "PostDetailActivity";

    private Context mContext = PostDetailActivity.this;

    private TextView tvTitle, tvWriter, tvContent;
    private Button btnUpdate, btnDelete;

    private PostDetailViewModel model;


    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        init();
        initLr();
        initSetting();
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
        tvTitle = findViewById(R.id.tvTitle);
        tvWriter = findViewById(R.id.tvWriter);
        tvContent = findViewById(R.id.tvContent);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

    }

    @Override
    public void initLr() {
        btnUpdate.setOnClickListener(v->{
            Intent intent = new Intent(mContext, PostUpdateActivity.class);
            intent.putExtra("postId", getIntent().getIntExtra("postId", 0));
            startActivity(intent);
        });

        btnDelete.setOnClickListener(v->{
            model.deleteById(getIntent().getIntExtra("postId", 0));
        });

    }

    @Override
    public void initSetting() {
        onAppBarSettings(true, "Detail");
    }

    @Override
    public void initViewModel() {
        model = new ViewModelProvider(this).get(PostDetailViewModel.class);
        model.getMdPost().observe(this, data -> {
            if (model.getMdPost().getValue().getData() != null){
                tvTitle.setText(model.getMdPost().getValue().getData().getTitle());
                tvWriter.setText(model.getMdPost().getValue().getData().getUser().getUsername());
                tvContent.setText(model.getMdPost().getValue().getData().getContent());
            }

        });
    }

    @Override
    public void initData() {
        int postId = getIntent().getIntExtra("postId", 0);
        model.findById(postId);
    }
}