package com.example.githubclient.ui.main;

import android.os.Bundle;

import com.example.githubclient.R;
import com.example.githubclient.ui.base.BaseActivity;
import com.example.githubclient.ui.main.router.MainActivityRouter;
import com.example.githubclient.ui.main.router.MainRouter;

public class MainActivity extends BaseActivity {

    private MainRouter router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        router = new MainActivityRouter(this);

        showFirstScreen();
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fl_fragment_container;
    }

    @Override
    public boolean onSupportNavigateUp() {
        router.navigateBack();

        return true;
    }

    private void showFirstScreen() {
        router.navigateToLogin();
    }

}
