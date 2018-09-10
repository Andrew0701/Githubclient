package com.example.githubclient.data.base;

import android.content.Context;

public abstract class BaseDataStore {

    private Context context;

    public BaseDataStore(Context context) {
        this.context = context;
    }

    protected Context getContext() {
        return context;
    }
}
