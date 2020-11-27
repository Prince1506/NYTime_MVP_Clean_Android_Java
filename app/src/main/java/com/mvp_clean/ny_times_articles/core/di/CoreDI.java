package com.mvp_clean.ny_times_articles.core.di;

public class CoreDI {
    private static DstvComponent dStvComponent;

    public static DstvComponent getDStvComponent() {
        if (dStvComponent == null) {
            dStvComponent = DaggerDstvComponent.builder().build();
        }
        return dStvComponent;
    }
}
