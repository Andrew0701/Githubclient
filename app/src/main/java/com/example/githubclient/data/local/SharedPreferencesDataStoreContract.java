package com.example.githubclient.data.local;

public interface SharedPreferencesDataStoreContract {

    void saveAccessToken(String token);

    String getAccessToken();
}
