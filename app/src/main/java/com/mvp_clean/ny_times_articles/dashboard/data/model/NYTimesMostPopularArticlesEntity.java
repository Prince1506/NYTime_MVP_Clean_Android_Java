package com.mvp_clean.ny_times_articles.dashboard.data.model;

import com.squareup.moshi.Json;
import java.util.List;

public class NYTimesMostPopularArticlesEntity {

     @Json(name = "status")
    private String status;

     @Json(name = "copyright")
    private String copyright;

     @Json(name = "num_results")
    private Integer numResults;

     @Json(name = "results")
    private final List<ResultEntity> resultEntities = null;

    public List<ResultEntity> getResultEntities() {
        return resultEntities;
    }

}
