package com.inception.paycrypt.storage;

public interface Storage {

    void saveToken(String token);

    String getToken();

}
