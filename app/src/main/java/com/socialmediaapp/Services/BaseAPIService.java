package com.socialmediaapp.Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.socialmediaapp.Socket.SocketIO;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseAPIService {
    private static final String BASE_URL = "http://"+ SocketIO.IPv4+":3000";
    static Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();
    private static final Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson));

    private static final Retrofit retrofit = builder.build();

    private static final OkHttpClient.Builder httpClient
            = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
