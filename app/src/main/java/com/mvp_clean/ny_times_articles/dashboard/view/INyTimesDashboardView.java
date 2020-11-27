package com.mvp_clean.ny_times_articles.dashboard.view;


import com.mvp_clean.ny_times_articles.core.view.IBaseView;
import com.mvp_clean.ny_times_articles.dashboard.domain.NyTimesMostViewArticlesViewModel;

public interface INyTimesDashboardView extends IBaseView {
    void showArticles(NyTimesMostViewArticlesViewModel nyTimesMostViewArticlesViewModel);
}
