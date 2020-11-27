package com.mvp_clean.ny_times_articles.core.network;


import com.mvp_clean.ny_times_articles.BuildConfig;
import com.mvp_clean.ny_times_articles.core.constants.IKeyConstant;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@SuppressWarnings("deprecation")
public class NetworkHelper {
    final int timeout = 5 * 1000;

    @Inject
    public NetworkHelper() {
    }

    @SuppressWarnings("deprecation")
    public <T> T createRetrofitApi(final String url, final Class<T> service) {
        try {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            if (BuildConfig.IS_DEBUG) {
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                logging.setLevel(HttpLoggingInterceptor.Level.NONE);
            }
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);
            httpClient.sslSocketFactory(new TLSSocketFactory());
            httpClient.readTimeout(timeout, TimeUnit.SECONDS);
            httpClient.connectTimeout(timeout, TimeUnit.SECONDS);
            httpClient.writeTimeout(timeout, TimeUnit.SECONDS);
                httpClient.addInterceptor(chain -> {
                    Request request = null;
                    try {
                        Request original = chain.request();
                        request = original.newBuilder()
                                .header(IKeyConstant.apiKeyKey,
                                        IKeyConstant.apiKeyVal)
                                .build();

                        return chain.proceed(request);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return chain.proceed(request);
                });

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            return retrofit.create(service);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
