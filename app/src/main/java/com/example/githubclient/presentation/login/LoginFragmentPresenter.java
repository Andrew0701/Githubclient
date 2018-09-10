package com.example.githubclient.presentation.login;

import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.arellomobile.mvp.InjectViewState;
import com.example.githubclient.constant.Constants;
import com.example.githubclient.data.local.SharedPreferencesDataStore;
import com.example.githubclient.data.remote.datastore.GithubDataStore;
import com.example.githubclient.data.remote.datastore.GithubDataStoreContract;
import com.example.githubclient.data.remote.model.TokenResponse;
import com.example.githubclient.ui.base.BasePresenter;
import com.example.githubclient.ui.main.router.MainRouter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class LoginFragmentPresenter extends BasePresenter<LoginView> {

    public LoginFragmentPresenter(Context context, MainRouter router) {

        super(context, router);

        init();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    private void init() {
        setupWebView();
    }

    private void setupWebView() {
        WebViewClient webViewClient = getWebViewClient();

        getViewState().setupWebView(webViewClient, Constants.AUTH_URL);
    }

    private WebViewClient getWebViewClient() {
        return new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (doesUrlContainCodePart(url)) {
                        getTokenByCode(parseCodeFromUrl(url));

                        return true;
                    }

                    return super.shouldOverrideUrlLoading(view, url);
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);

                    getViewState().showLoading();
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);

                    if (!doesUrlContainCodePart(url)) {
                        getViewState().hideLoading();
                    }
                }
            };
    }

    private String parseCodeFromUrl(String url) {
        return url.substring(url.lastIndexOf(Constants.CODE_URL_PARAMETER)
                + Constants.CODE_URL_PARAMETER.length());
    }

    private void getTokenByCode(String code) {
        getViewState().showLoading();

        GithubDataStoreContract githubDataStore = new GithubDataStore(context);

        disposable = githubDataStore
                .getToken(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::processToken, this::handleError,
                        () -> getViewState().hideLoading());
    }

    private void handleError(Throwable throwable) {
        getViewState().showLoginError();
        getViewState().hideLoading();
        showErrorMessage();
    }

    private void processToken(TokenResponse tokenResponse) {
        if (tokenResponse.getAccessToken() == null) {
            getViewState().showLoginError();

            return;
        }

        SharedPreferencesDataStore sharedPreferencesDataStore = new SharedPreferencesDataStore(context);
        sharedPreferencesDataStore.saveAccessToken(tokenResponse.getAccessToken());

        router.navigateToRepositories();
    }

    private boolean doesUrlContainCodePart(String url) {
        return url.contains(Constants.CODE_URL_PARAMETER);
    }

}
