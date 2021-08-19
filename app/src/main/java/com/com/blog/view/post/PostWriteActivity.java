package com.com.blog.view.post;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.com.blog.R;
import com.com.blog.config.SessionUser;
import com.com.blog.model.Post;
import com.com.blog.view.CustomAppBarActivity;
import com.com.blog.view.InitActivity;
import com.com.blog.viewModel.post.PostDetailViewModel;


public class PostWriteActivity extends CustomAppBarActivity implements InitActivity {

    private static final String TAG = "PostWriteActivity";
    private Context mContext = PostWriteActivity.this;


    private EditText tfTitle, tfWriter, tfContent;
    private Button btnWrite;

    private PostDetailViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_write);

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
        btnWrite = findViewById(R.id.btnWrite);

    }

    @Override
    public void initLr() {
        btnWrite.setOnClickListener(v->{
            String title = tfTitle.getText().toString();
            String content = tfContent.getText().toString();

            Post post = Post.builder().title(title).content(content).build();
            model.insert(post);
        });

    }

    @Override
    public void initSetting() {
        onAppBarSettings(true, "Write");
    }

    @Override
    public void initViewModel() {
        model = new ViewModelProvider(this).get(PostDetailViewModel.class);
        model.getMdPost().observe(this, data -> {
            if (model.getMdPost().getValue().getCode()==1){
                Intent intent = new Intent(mContext, PostDetailActivity.class);
                intent.putExtra("postId", model.getMdPost().getValue().getData().getId());
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void initData() {
        tfWriter.setText(SessionUser.user.getUsername());
    }
}