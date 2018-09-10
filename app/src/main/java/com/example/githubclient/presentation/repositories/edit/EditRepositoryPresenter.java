package com.example.githubclient.presentation.repositories.edit;

import android.content.Context;
import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.example.githubclient.R;
import com.example.githubclient.data.remote.datastore.GithubDataStore;
import com.example.githubclient.data.remote.datastore.GithubDataStoreContract;
import com.example.githubclient.data.remote.model.Repository;
import com.example.githubclient.ui.base.BasePresenter;
import com.example.githubclient.ui.main.router.MainRouter;

import androidx.appcompat.app.AlertDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

@InjectViewState
public class EditRepositoryPresenter extends BasePresenter<EditRepositoryView> {

    private Repository repository;

    public EditRepositoryPresenter(Context context, MainRouter router) {

        super(context, router);
    }

    public void setRepository(Repository repository) {
        this.repository = repository;

        bindView();
    }

    private void bindView() {
        getViewState().setupRepositoryInfo(repository.getName(), repository.getDescription());
    }

    public void onBtnDeleteClick() {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(R.string.delete_repository_question)
                .setPositiveButton(R.string.delete, (dialogInterface, i) -> deleteRepository())
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> dialogInterface.cancel())
                .create();

        dialog.show();
    }

    private void deleteRepository() {
        getViewState().showLoading();

        GithubDataStoreContract githubDataStore = new GithubDataStore(context);

        disposable = githubDataStore
                .deleteRepository(repository)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onRepositoryDeleted, this::handleError,
                        () -> getViewState().hideLoading());
    }

    private void onRepositoryDeleted(Response<Integer> response) {
        router.navigateToRepositories();
    }

    public void onBtnSaveClick(String name, String description) {
        if (validate(name)) {
            saveRepository(name, description);
        }
    }

    private boolean validate(String name) {
        if (TextUtils.isEmpty(name)) {
            getViewState().showEmptyNameError();

            return false;
        }

        return true;
    }

    private void saveRepository(String name, String description) {
        getViewState().showLoading();

        GithubDataStoreContract githubDataStore = new GithubDataStore(context);

        disposable = githubDataStore
                .editRepository(repository, new Repository(name, description))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onRepositorySaved, this::handleError,
                        () -> getViewState().hideLoading());
    }

    private void handleError(Throwable throwable) {
        getViewState().hideLoading();
        showErrorMessage();
    }

    private void onRepositorySaved(Repository repository) {
        getViewState().hideLoading();

        router.navigateBackToDetailRepository(repository);
    }
}
