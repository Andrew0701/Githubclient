package com.example.githubclient.ui.login;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.githubclient.R;
import com.example.githubclient.presentation.login.LoginFragmentPresenter;
import com.example.githubclient.presentation.login.LoginView;
import com.example.githubclient.ui.base.BaseFragment;
import com.example.githubclient.ui.main.router.MainActivityRouter;

import androidx.appcompat.app.ActionBar;
import butterknife.BindView;

public class LoginFragment extends BaseFragment implements LoginView {

    @BindView(R.id.web_view)
    WebView webView;

    @BindView(R.id.tv_login_error)
    TextView tvLoginError;

    @InjectPresenter
    LoginFragmentPresenter presenter;

    @ProvidePresenter
    LoginFragmentPresenter provideLoginFragmentPresenter() {
        return new LoginFragmentPresenter(getActivity(), new MainActivityRouter(getBaseActivity()));
    }

    public static LoginFragment newInstance() {

        return new LoginFragment();
    }

    @SuppressWarnings("SetJavaScriptEnabled")
    @Override
    public void setupWebView(WebViewClient webViewClient, String url) {
        webView.setWebViewClient(webViewClient);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    @Override
    public void showLoginError() {
        webView.setVisibility(View.GONE);
        tvLoginError.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();

        ActionBar actionBar = getBaseActivity().getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.login);
        }
    }
}
