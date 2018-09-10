package com.example.githubclient.data.remote.datastore;

import android.content.Context;

import com.example.githubclient.BuildConfig;
import com.example.githubclient.data.base.BaseDataStore;
import com.example.githubclient.data.remote.AccessTokenInterceptor;
import com.example.githubclient.data.remote.GithubApiFactory;
import com.example.githubclient.data.remote.model.Repository;
import com.example.githubclient.data.remote.model.TokenResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;

public class GithubDataStore extends BaseDataStore implements GithubDataStoreContract {

    public GithubDataStore(Context context) {
        super(context);
    }

    @Override
    public Observable<TokenResponse> getToken(String code) {
        return GithubApiFactory.getGithubApi()
                .getToken(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, code);
    }

    @Override
    public Observable<List<Repository>> getRepositories() {
        return GithubApiFactory.getGithubApi(new AccessTokenInterceptor(getContext()))
                .getRepositories();
    }

    @Override
    public Observable<Repository> editRepository(Repository oldRepository, Repository newRepository) {
        return GithubApiFactory.getGithubApi(new AccessTokenInterceptor(getContext()))
                .patchRepository(oldRepository.getOwner().getLogin(), oldRepository.getName(), newRepository);
    }

    @Override
    public Observable<Response<Integer>> deleteRepository(Repository repository) {
        return GithubApiFactory.getGithubApi(new AccessTokenInterceptor(getContext()))
                .deleteRepository(repository.getOwner().getLogin(), repository.getName());
    }
}
