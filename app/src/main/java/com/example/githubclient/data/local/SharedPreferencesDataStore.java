package com.example.githubclient.data.local;

import android.content.Context;

import com.example.githubclient.constant.PrefConstants;
import com.example.githubclient.data.base.BaseDataStore;

public class SharedPreferencesDataStore extends BaseDataStore
        implements SharedPreferencesDataStoreContract {

    public SharedPreferencesDataStore(Context context) {
        super(context);
    }

    @Override
    public void saveAccessToken(String token) {
        getContext()
                .getSharedPreferences(PrefConstants.AUTH_PREFERENCES, Context.MODE_PRIVATE)
                .edit()
                .putString(PrefConstants.ACCESS_TOKEN, token)
                .apply();
    }

    @Override
    public String getAccessToken() {
        return getContext()
                .getSharedPreferences(PrefConstants.AUTH_PREFERENCES, Context.MODE_PRIVATE)
                .getString(PrefConstants.ACCESS_TOKEN, "");
    }
}
