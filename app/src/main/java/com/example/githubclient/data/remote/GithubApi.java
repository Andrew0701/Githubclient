package com.example.githubclient.data.remote;

import com.example.githubclient.data.remote.model.Repository;
import com.example.githubclient.data.remote.model.TokenResponse;
import com.example.githubclient.data.remote.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GithubApi {

    String TOKEN = "login/oauth/access_token";
    String USER = "user";
    String REPOSITORIES = USER + "/repos";
    String PATH_REPOSITORIES = "/repos/{owner}/{repo}";
    String DELETE_REPOSITORIES = "/repos/{owner}/{repo}";

    @FormUrlEncoded
    @POST(TOKEN)
    Observable<TokenResponse> getToken(@Field("client_id") String clientId,
                                       @Field("client_secret") String clientSecret,
                                       @Field("code") String code);

    @GET(USER)
    Observable<User> getUser();

    @GET(REPOSITORIES)
    Observable<List<Repository>> getRepositories();

    @PATCH(PATH_REPOSITORIES)
    Observable<Repository> patchRepository(@Path("owner") String owner,
                                           @Path("repo") String repo,
                                           @Body Repository repository);

    @DELETE(DELETE_REPOSITORIES)
    Observable<Response<Integer>> deleteRepository(@Path("owner") String owner,
                                          @Path("repo") String repo);
}
