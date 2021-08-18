package com.com.blog.view;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.com.blog.R;
import com.com.blog.config.SessionUser;
import com.com.blog.view.auth.LoginActivity;
import com.com.blog.view.post.PostWriteActivity;
import com.com.blog.view.user.UserInfoActivity;

public class CustomAppBarActivity extends AppCompatActivity {

    private static final String TAG = "CustomAppBarActivity";

    protected void onAppBarSettings(boolean isBackButton, String title){
        Toolbar myToolbar = findViewById(R.id.mainToolBar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle(title);
        ab.setDisplayHomeAsUpEnabled(isBackButton);
    }

    protected void onAppBarSettings(String title){
        Toolbar myToolbar = findViewById(R.id.mainToolBar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.actLogout){
            Log.d(TAG, "onOptionsItemSelected: actLogout");
            SessionUser.token="";
            SessionUser.user=null;

            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            finish();
            startActivity(intent);

            return true;
        } else if (item.getItemId() == R.id.actWrite){
            Log.d(TAG, "onOptionsItemSelected: actWrite");
            Intent intent = new Intent(getBaseContext(), PostWriteActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.actUserInfo){
            Log.d(TAG, "onOptionsItemSelected: actUserInfo");
            Intent intent = new Intent(getBaseContext(), UserInfoActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
