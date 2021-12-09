package com.inception.paycrypt.storage.impl;

import android.content.SharedPreferences;

import com.inception.paycrypt.storage.Storage;

import lombok.RequiredArgsConstructor;

/**
 * Service that implements the Storage
 */
@RequiredArgsConstructor
public class SharedPreferencesStorage implements Storage {


    private final String TOKEN_KEY = "Token_key";

    private final SharedPreferences sharedPreferences;

    @Override
    public void saveToken(String token) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply();
    }

    @Override
    public String getToken() {
        return sharedPreferences.getString(TOKEN_KEY, "");
    }
}
