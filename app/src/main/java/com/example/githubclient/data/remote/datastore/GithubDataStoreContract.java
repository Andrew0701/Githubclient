package com.example.githubclient.data.remote.datastore;

import com.example.githubclient.data.remote.model.Repository;
import com.example.githubclient.data.remote.model.TokenResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;

public interface GithubDataStoreContract {

    Observable<TokenResponse> getToken(String code);

    Observable<List<Repository>> getRepositories();

    Observable<Repository> editRepository(Repository oldRepository, Repository newRepository);

    Observable<Response<Integer>> deleteRepository(Repository repository);
}
