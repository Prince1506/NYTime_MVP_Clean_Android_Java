package com.mvp_clean.ny_times_articles.core.di;



import com.mvp_clean.ny_times_articles.dashboard.view.activities.NyTimesDashboardActivity;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = DstvModule.class)
public interface DstvComponent {
	void inject(NyTimesDashboardActivity nyTimesDashboardActivity);
}
