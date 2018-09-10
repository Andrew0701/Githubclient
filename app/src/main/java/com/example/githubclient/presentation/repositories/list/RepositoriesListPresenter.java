package com.example.githubclient.presentation.repositories.list;

import android.content.Context;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.githubclient.data.remote.datastore.GithubDataStore;
import com.example.githubclient.data.remote.datastore.GithubDataStoreContract;
import com.example.githubclient.data.remote.model.Repository;
import com.example.githubclient.presentation.repositories.list.adapter.RepositoriesAdapter;
import com.example.githubclient.ui.base.BasePresenter;
import com.example.githubclient.ui.main.router.MainRouter;
import com.example.githubclient.util.CollectionUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class RepositoriesListPresenter extends BasePresenter<RepositoriesListView> {

    public RepositoriesListPresenter(Context context, MainRouter router) {

        super(context, router);
    }

    public void init() {
        loadRepositories();
    }

    private void loadRepositories() {
        getViewState().showLoading();

        GithubDataStoreContract githubDataStore = new GithubDataStore(context);

        disposable = githubDataStore
                .getRepositories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onRepositoriesLoaded, this::handleError,
                        () -> getViewState().hideLoading());
    }

    private void handleError(Throwable throwable) {
        getViewState().hideLoading();
        showErrorMessage();
    }

    private void onRepositoriesLoaded(List<Repository> repositories) {
        if (CollectionUtils.notNullAndNotEmpty(repositories)) {
            RepositoriesAdapter adapter = new RepositoriesAdapter(context, repositories, this::onRepositoryClick);

            getViewState().setRepositoriesAdapter(adapter);
        } else {
            getViewState().showEmptyRepositoriesMessage();
        }
    }

    private void onRepositoryClick(Repository repository) {
        router.navigateToDetailRepository(repository);
    }
}
