package com.example.utente5academy.eserciziolog.classi;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by utente5.academy on 12/12/2017.
 */

public class RegistrationToken extends FirebaseInstanceIdService {

    public RegistrationToken() {
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        try {
            registrationToken(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registrationToken(String token) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(6, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS).build();
        RequestBody body = new FormBody.Builder()
                .add("token", token)
                .build();
        final Request request = new Request.Builder()
                .post(body)
                .url("http://192.168.43.126/php/registrationToken.php")
                .build();
        client.newCall(request).execute();

    }
}
