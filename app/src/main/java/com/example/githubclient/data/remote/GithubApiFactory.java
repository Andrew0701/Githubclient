package com.example.githubclient.data.remote;

import com.example.githubclient.BuildConfig;
import com.example.githubclient.constant.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class GithubApiFactory {

    private static GithubApi authGithubApi = null;
    private static GithubApi githubApi = null;

    public static GithubApi getGithubApi() {
        if (authGithubApi == null) {
            String baseUrl = BuildConfig.HOST_DEFAULT;

            OkHttpClient client = makeOkHttpClientBuilder().build();

            Retrofit retrofit = makeRetrofit(client, makeGson(), baseUrl);

            authGithubApi = retrofit.create(GithubApi.class);
        }

        return authGithubApi;
    }

    public static GithubApi getGithubApi(Interceptor interceptor) {
        if (githubApi == null) {
            String baseUrl = BuildConfig.HOST_API;

            OkHttpClient client = makeOkHttpBuilderWithCustomInterceptor(interceptor).build();

            Retrofit retrofit = makeRetrofit(client, makeGson(), baseUrl);

            githubApi = retrofit.create(GithubApi.class);
        }

        return githubApi;
    }

    @NonNull
    private static Retrofit makeRetrofit(OkHttpClient client, Gson gson, String baseUrl) {
        return makeClient(client, baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @NonNull
    private static Retrofit.Builder makeClient(OkHttpClient client, String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client);
    }

    @NonNull
    private static Gson makeGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @NonNull
    private static Interceptor makeHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return httpLoggingInterceptor;
    }

    @NonNull
    private static OkHttpClient.Builder makeOkHttpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(300, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS);

        builder.addInterceptor(makeAcceptHeaderInterceptor());
        builder.addInterceptor(makeHttpLoggingInterceptor());
        builder.protocols(Collections.singletonList(Protocol.HTTP_1_1));

        return builder;
    }

    @NonNull
    private static Interceptor makeAcceptHeaderInterceptor() {
        return chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader(Constants.ACCEPT, "application/json")
                    .build();

            return chain.proceed(request);
        };
    }

    @NonNull
    private static OkHttpClient.Builder makeOkHttpBuilderWithCustomInterceptor(Interceptor interceptor) {
        return makeOkHttpClientBuilder()
                .addInterceptor(interceptor);
    }
}
