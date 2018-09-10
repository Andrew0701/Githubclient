package com.example.githubclient.presentation.repositories.list;

import com.example.githubclient.ui.base.BaseMvpView;

import androidx.recyclerview.widget.RecyclerView;

public interface RepositoriesListView extends BaseMvpView {

    void setRepositoriesAdapter(RecyclerView.Adapter adapter);

    void showEmptyRepositoriesMessage();
}
