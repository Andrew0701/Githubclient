package com.example.githubclient.ui.base;

import android.content.Context;
import android.widget.Toast;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.example.githubclient.R;
import com.example.githubclient.ui.main.router.MainRouter;

import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends MvpView> extends MvpPresenter<V> {

    protected final Context context;
    protected final MainRouter router;

    protected Disposable disposable;

    protected BasePresenter(Context context, MainRouter router) {

        this.context = context;
        this.router = router;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (disposable != null) {
            disposable.dispose();
        }
    }

    protected void showErrorMessage() {
        Toast.makeText(context, R.string.error_message, Toast.LENGTH_SHORT).show();
    }
}
