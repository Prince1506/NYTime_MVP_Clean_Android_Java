package com.mvp_clean.ny_times_articles.core.di;

import android.content.Context;
import com.mvp_clean.ny_times_articles.core.MyApplication;
import com.mvp_clean.ny_times_articles.core.network.NetworkHelper;
import com.mvp_clean.ny_times_articles.core.usecase.AndroidUseCaseComposer;
import com.mvp_clean.ny_times_articles.core.usecase.UseCaseComposer;
import dagger.Module;
import dagger.Provides;

@Module
public class DstvModule {

    @Provides
    public Context provideDStvApplicationContext() {
        return MyApplication.getMyApplicationContext();
    }

    @Provides
    public NetworkHelper provideDStvNetworkHelper() {
        return new NetworkHelper();
    }

    @Provides
    UseCaseComposer provideUseCaseComposer() {
        return new AndroidUseCaseComposer();
    }

}
