package com.inception.paycrypt.interceptor;


import androidx.annotation.NonNull;

import com.inception.paycrypt.storage.Storage;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


@RequiredArgsConstructor
public class AuthInterceptor implements Interceptor {

    private final Storage storage;

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder request = chain.request().newBuilder();
        String token = storage.getToken();
        if (!token.isEmpty()) {
            request.addHeader("Authorization", "Bearer " + token);
        }
        return chain.proceed(request.build());
    }
}
