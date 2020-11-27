package com.mvp_clean.ny_times_articles.core.view;

import android.content.Context;
import com.mvp_clean.ny_times_articles.core.callback.IRetryCallBack;

public interface IBaseView {
    void startServerApiErrorScreen(String error);
    Boolean isNetworkAvailable(Context context);
    void startServerApiRetryScreen(String error, IRetryCallBack context);
    void showProgress();
    void hideProgress();
}
