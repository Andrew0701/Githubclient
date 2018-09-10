package com.example.githubclient.presentation.login;

import android.webkit.WebViewClient;

import com.example.githubclient.ui.base.BaseMvpView;

public interface LoginView extends BaseMvpView {

    void setupWebView(WebViewClient webViewClient, String url);

    void showLoginError();
}
