package com.example.githubclient.presentation.repositories.edit;

import com.example.githubclient.ui.base.BaseMvpView;

public interface EditRepositoryView extends BaseMvpView {

    void setupRepositoryInfo(String name, String description);

    void showEmptyNameError();
}
