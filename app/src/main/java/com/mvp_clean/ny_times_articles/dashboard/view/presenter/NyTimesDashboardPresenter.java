package com.mvp_clean.ny_times_articles.dashboard.view.presenter;


import com.mvp_clean.ny_times_articles.core.constants.IKeyConstant;
import com.mvp_clean.ny_times_articles.core.presenter.BasePresenter;
import com.mvp_clean.ny_times_articles.dashboard.domain.NYTimeArticlesDashboardUseCase;
import com.mvp_clean.ny_times_articles.dashboard.domain.NYTimesMostPopularArticlesResponseEntityToViewMapper;
import com.mvp_clean.ny_times_articles.dashboard.domain.NyTimesMostViewArticlesViewModel;
import com.mvp_clean.ny_times_articles.dashboard.view.INyTimesDashboardView;
import com.mvp_clean.ny_times_articles.dashboard.view.activities.NyTimesDashboardActivity;

import java.net.SocketTimeoutException;

import javax.inject.Inject;

public class NyTimesDashboardPresenter
        extends
        BasePresenter<INyTimesDashboardView> implements INyTimesDashboardPresenter {

    private final NYTimeArticlesDashboardUseCase nYTimeArticlesDashboardUseCase;
    private final NYTimesMostPopularArticlesResponseEntityToViewMapper nyTimesMostPopularArticlesResponseEntityToViewMapper;

    @Inject
    public NyTimesDashboardPresenter(
            NYTimeArticlesDashboardUseCase nYTimeArticlesDashboardUseCase,
            NYTimesMostPopularArticlesResponseEntityToViewMapper nyTimesMostPopularArticlesResponseEntityToViewMapper) {

        this.nYTimeArticlesDashboardUseCase = nYTimeArticlesDashboardUseCase;
        this.nyTimesMostPopularArticlesResponseEntityToViewMapper = nyTimesMostPopularArticlesResponseEntityToViewMapper;
    }

    @Override
    public void getArticles(Integer previousDate) {
        if (!view.isNetworkAvailable(((NyTimesDashboardActivity) view))) {
            view.startServerApiRetryScreen(
                    IKeyConstant.noInternet,
                    (NyTimesDashboardActivity) view
            );
        } else {
            view.showProgress();
            nYTimeArticlesDashboardUseCase.execute(previousDate)
                    .subscribe((NyTimesMostViewArticlesViewModel nyTimesMostViewArticlesViewModel) -> {
                        if (view != null) {
                            view.showArticles(
                                    nyTimesMostViewArticlesViewModel
                            );
                            view.hideProgress();
                        }
                    }, (Throwable throwable) -> {

                        if (view == null) return;
                        view.hideProgress();
                        if (throwable instanceof SocketTimeoutException) {
                            view.startServerApiErrorScreen(IKeyConstant.timeOut);
                        } else {
                            view.startServerApiErrorScreen(IKeyConstant.ServerError);
                        }
                    });
        }
    }


//    @Override
//    void getArticles(Integer previousDate) {
//            if (!(view?.isNetworkAvailable(view as NyTimesDashboardActivity?))!!) {
//                view?.startServerApiRetryScreen(
//                    IKeyConstant.noInternet,
//                    view as NyTimesDashboardActivity?
//                )
//            } else {
//                view?.showProgress()
//                nYTimeArticlesDashboardUseCase.execute(previousDate)
//                    ?.subscribe({ videoResponseDataModel ->
//                        if (view != null) {
//                            view?.showArticles(
//                                nyTimesMostPopularArticlesResponseDataToViewMapper.mapDataToViewMapper(
//                                    videoResponseDataModel
//                                )
//                            )
//                            view?.hideProgress()
//                        }
//                }) { throwable: Throwable? ->
//                    if (view == null) return@subscribe
//                    view?.hideProgress()
//                    if (throwable is SocketTimeoutException) {
//                        view?.startServerApiErrorScreen(IKeyConstant.timeOut)
//                    } else {
//                        view?.startServerApiErrorScreen(IKeyConstant.ServerError)
//                    }
//                }
//            }
//    }
}