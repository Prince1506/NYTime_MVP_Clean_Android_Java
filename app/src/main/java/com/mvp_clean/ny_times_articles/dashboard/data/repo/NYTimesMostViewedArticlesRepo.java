package com.mvp_clean.ny_times_articles.dashboard.data.repo;

import com.mvp_clean.ny_times_articles.core.network.NetworkHelper;
import com.mvp_clean.ny_times_articles.dashboard.data.model.NYTimesMostPopularArticlesEntity;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.mvp_clean.ny_times_articles.BuildConfig.BASE_URL;

public class NYTimesMostViewedArticlesRepo  implements INyTimesMostViewedArticlesRepo{
    public NetworkHelper networkHelper ;

    @Inject
    public NYTimesMostViewedArticlesRepo(
            NetworkHelper networkHelper
    ){
        this.networkHelper = networkHelper;
    }




    @Override
    public Observable<NYTimesMostPopularArticlesEntity> getNyTimesMostViewedArticles(Integer day, String apiKey) {
        return networkHelper.createRetrofitApi(
            BASE_URL,
            INyTimesMostViewedArticlesRepo.class
        ).getNyTimesMostViewedArticles(day, apiKey);
    }

}