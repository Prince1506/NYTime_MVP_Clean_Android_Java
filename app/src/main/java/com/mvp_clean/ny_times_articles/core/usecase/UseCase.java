
package com.mvp_clean.ny_times_articles.core.usecase;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Action;

public abstract class UseCase<P, R> {

    private final UseCaseComposer useCaseComposer;

    final Map<P, Observable<R>> observablesMap = new HashMap<>();

    protected UseCase(final UseCaseComposer useCaseComposer) {
        this.useCaseComposer = useCaseComposer;
    }

    protected abstract Observable<R> createUseCaseObservable(final P param);


    public Observable<R> execute(final P param) {

        Observable<R> observable = observablesMap.get(param);

        boolean cacheObservable = true;
        if (observable == null || !cacheObservable) {

            try {
                observable = createUseCaseObservable(param);
            } catch (Exception e) {
                observable = Observable.error(e);
            }

            if (useCaseComposer != null) {
                observable = observable.compose(useCaseComposer.apply());
            }
            observable = observable.doOnDispose(new OnTerminateAction(param));
            observablesMap.put(param, observable);
        }

        return observable;
    }

    private class OnTerminateAction implements Action {


        private final P param;

        OnTerminateAction(P param) {
            this.param = param;
        }

        @Override
        public void run() {
            observablesMap.remove(param);
        }
    }

}
