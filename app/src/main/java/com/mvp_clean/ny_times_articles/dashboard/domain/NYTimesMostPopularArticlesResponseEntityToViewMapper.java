package com.mvp_clean.ny_times_articles.dashboard.domain;


import com.mvp_clean.ny_times_articles.dashboard.data.model.NYTimesMostPopularArticlesEntity;

import javax.inject.Inject;

public class NYTimesMostPopularArticlesResponseEntityToViewMapper {

    @Inject
    public NYTimesMostPopularArticlesResponseEntityToViewMapper(){

    }

    public NyTimesMostViewArticlesViewModel mapEntityToView(
            NYTimesMostPopularArticlesEntity nyTimesMostPopularArticlesEntity
    ) {
        NyTimesMostViewArticlesViewModel nyTimesMostViewArticlesViewModel = new NyTimesMostViewArticlesViewModel();
        nyTimesMostViewArticlesViewModel.setResultEntities(nyTimesMostPopularArticlesEntity.getResultEntities());
        return nyTimesMostViewArticlesViewModel;
    }
}

