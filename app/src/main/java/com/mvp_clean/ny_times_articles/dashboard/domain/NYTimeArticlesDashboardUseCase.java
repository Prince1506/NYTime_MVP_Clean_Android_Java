package com.mvp_clean.ny_times_articles.dashboard.domain;

import com.mvp_clean.ny_times_articles.core.constants.IKeyConstant;
import com.mvp_clean.ny_times_articles.core.usecase.UseCase;
import com.mvp_clean.ny_times_articles.core.usecase.UseCaseComposer;
import com.mvp_clean.ny_times_articles.dashboard.data.repo.NYTimesMostViewedArticlesRepo;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.Observable;


public class NYTimeArticlesDashboardUseCase extends UseCase<Integer, NyTimesMostViewArticlesViewModel> {

    @Inject
    public com.mvp_clean.ny_times_articles.dashboard.data.repo.NYTimesMostViewedArticlesRepo NYTimesMostViewedArticlesRepo;
    @Inject
    public NYTimesMostPopularArticlesResponseEntityToViewMapper nyTimesMostPopularArticlesResponseEntityToViewMapper;

    @Inject
    public  NYTimeArticlesDashboardUseCase(UseCaseComposer useCaseComposer,
                                           NYTimesMostViewedArticlesRepo NYTimesMostViewedArticlesRepo,
                                           NYTimesMostPopularArticlesResponseEntityToViewMapper nyTimesMostPopularArticlesResponseEntityToViewMapper) {
        super(useCaseComposer);
        this.NYTimesMostViewedArticlesRepo = NYTimesMostViewedArticlesRepo;
        this.nyTimesMostPopularArticlesResponseEntityToViewMapper = nyTimesMostPopularArticlesResponseEntityToViewMapper;
    }

    @NotNull
    @Override
    protected Observable<NyTimesMostViewArticlesViewModel> createUseCaseObservable(Integer days) {
        return NYTimesMostViewedArticlesRepo.getNyTimesMostViewedArticles(
                days,
                IKeyConstant.apiKeyVal
        ).map(nyTimesMostPopularArticlesResponseEntityToViewMapper::mapEntityToView);
    }

}
