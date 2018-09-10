package com.example.githubclient.ui.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.example.githubclient.R;

import androidx.annotation.NonNull;

public abstract class BaseRouter {

    private BaseActivity activity;

    public BaseRouter(BaseActivity activity) {
        this.activity = activity;
    }

    protected void show(Fragment fragment, String backStackTag) {
        buildTransaction(fragment, backStackTag)
                .commit();
    }

    protected void popBackStack() {
        activity.getFragmentManager().popBackStack();
    }

    protected void popBackStack(String tag) {
        activity.getFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    protected void popAllBackStack() {
        FragmentManager fragmentManager = activity.getFragmentManager();

        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
    }

    @NonNull
    private FragmentTransaction buildTransaction(Fragment fragment, String backStackTag) {
        FragmentTransaction transaction = activity.getFragmentManager()
                .beginTransaction();

        transaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right,
                R.animator.slide_in_left, R.animator.slide_out_right);

        transaction.replace(activity.getFragmentContainerId(), fragment, fragment.getClass().getSimpleName());

        if (backStackTag != null) {
            transaction.addToBackStack(backStackTag);
        }

        return transaction;
    }
}
