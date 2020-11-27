package com.mvp_clean.ny_times_articles.dashboard.data.repo;


import com.mvp_clean.ny_times_articles.dashboard.data.model.NYTimesMostPopularArticlesEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface INyTimesMostViewedArticlesRepo {

    @GET("svc/mostpopular/v2/viewed/{day}.json")
    Observable<NYTimesMostPopularArticlesEntity> getNyTimesMostViewedArticles(
             @Path("day")Integer day,
             @Query("api-key")String apiKey
     );
}