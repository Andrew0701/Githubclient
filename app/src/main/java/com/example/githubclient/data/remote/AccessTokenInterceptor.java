package com.example.githubclient.data.remote;

import android.content.Context;

import com.example.githubclient.constant.Constants;
import com.example.githubclient.constant.PrefConstants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AccessTokenInterceptor implements Interceptor {

    private Context context;

    public AccessTokenInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        String token = context.getSharedPreferences(PrefConstants.AUTH_PREFERENCES, Context.MODE_PRIVATE)
                .getString(PrefConstants.ACCESS_TOKEN, "");

        Request request = chain.request().newBuilder()
                .addHeader(Constants.AUTHORIZATION, "token " + token)
                .addHeader(Constants.CONNECTION, "close")
                .addHeader(Constants.CACHE_CONTROL, "no-cache")
                .build();

        return chain.proceed(request);
    }
}
