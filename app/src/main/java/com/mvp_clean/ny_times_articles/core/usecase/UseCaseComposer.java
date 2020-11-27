
package com.mvp_clean.ny_times_articles.core.usecase;


import io.reactivex.ObservableTransformer;

public  interface UseCaseComposer {

    <T> ObservableTransformer<T, T> apply();

}
