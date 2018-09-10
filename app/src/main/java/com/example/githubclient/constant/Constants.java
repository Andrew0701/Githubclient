package com.example.githubclient.constant;

import com.example.githubclient.BuildConfig;

public interface Constants {

    String ACCEPT = "accept";
    String AUTHORIZATION = "Authorization";
    String CONNECTION = "Connection";
    String CACHE_CONTROL = "Cache-Control";

    String AUTH_URL = "https://github.com/login/oauth/authorize?client_id="
            + BuildConfig.CLIENT_ID + "&scope=user,repo,delete_repo";

    String CODE_URL_PARAMETER = "?code=";
}
