package com.example.githubclient.presentation.repositories.detail;

import com.arellomobile.mvp.MvpView;

public interface DetailRepositoryView extends MvpView {

    void setRepositoryInfo(String name, String description, String language);
}
