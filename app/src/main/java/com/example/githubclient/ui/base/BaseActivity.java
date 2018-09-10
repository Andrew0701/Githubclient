package com.example.githubclient.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.githubclient.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    private boolean allowCommit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initProgressDialog();
    }

    @Override
    protected void onResume() {
        allowCommit = true;

        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        allowCommit = false;

        super.onSaveInstanceState(outState);
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
    }

    protected abstract int getFragmentContainerId();

    protected void showLoading() {
        if (allowCommit && !progressDialog.isShowing()) {
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.show();
        }
    }

    protected void showLoading(String message) {
        if (allowCommit && !progressDialog.isShowing()) {
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    protected void hideLoading() {
        if (allowCommit && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }
}
