package com.taotao.life.okHttp;

import okhttp3.*;

import java.io.IOException;

public class Okhttp {

//    public String get() throws IOException {
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            return response.body().string();
//        }
//    }
//
//    public String post() throws IOException {
//        MediaType JSON = MediaType.get("application/json; charset=utf-8");
//
//        OkHttpClient client = new OkHttpClient();
//
//        RequestBody body = RequestBody.create(json, JSON);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        try (Response response = client.newCall(request).execute()) {
//            return response.body().string();
//        }
//    }
}
