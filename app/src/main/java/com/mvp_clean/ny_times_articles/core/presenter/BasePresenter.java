package com.mvp_clean.ny_times_articles.core.presenter;


import com.mvp_clean.ny_times_articles.core.view.IBaseView;

public class BasePresenter<V extends IBaseView> {
    public V view;

    public final void bind(V viewToBind) {
        view = viewToBind;
    }

    public final void unbind() {
        view = null;
    }
}

