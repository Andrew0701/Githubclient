package com.example.githubclient.ui.base;

import com.arellomobile.mvp.MvpView;

public interface BaseMvpView extends MvpView {

    void showLoading();

    void showLoading(String message);

    void hideLoading();
}
