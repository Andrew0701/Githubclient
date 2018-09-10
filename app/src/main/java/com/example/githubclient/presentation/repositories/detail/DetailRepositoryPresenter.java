package com.example.githubclient.presentation.repositories.detail;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.example.githubclient.data.remote.model.Repository;
import com.example.githubclient.ui.base.BasePresenter;
import com.example.githubclient.ui.main.router.MainRouter;

@InjectViewState
public class DetailRepositoryPresenter extends BasePresenter<DetailRepositoryView> {

    private Repository repository;

    public DetailRepositoryPresenter(Context context, MainRouter router) {

        super(context, router);
    }

    public void onBtnEditClick() {
        router.navigateToEditRepository(repository);
    }

    public void setRepository(Repository repository) {
        this.repository = repository;

        bindView();
    }

    private void bindView() {
        getViewState().setRepositoryInfo(repository.getName(),
                repository.getDescription(), repository.getLanguage());
    }
}
