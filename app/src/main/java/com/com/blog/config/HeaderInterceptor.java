package com.com.blog.config;

import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

import com.com.blog.viewModel.auth.AuthViewModel;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    private static final String TAG = "HeaderInterceptor";



    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Log.d(TAG, "intercept: 실행됨");

        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "").build();
        return chain.proceed(request);
    }
}
