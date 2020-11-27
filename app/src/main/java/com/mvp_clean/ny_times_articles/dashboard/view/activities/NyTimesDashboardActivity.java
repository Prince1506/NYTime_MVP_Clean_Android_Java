package com.mvp_clean.ny_times_articles.dashboard.view.activities;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mvp_clean.ny_times_articles.R;
import com.mvp_clean.ny_times_articles.core.callback.IRetryCallBack;
import com.mvp_clean.ny_times_articles.core.constants.IKeyConstant;
import com.mvp_clean.ny_times_articles.core.di.CoreDI;
import com.mvp_clean.ny_times_articles.core.view.activities.BaseActivity;
import com.mvp_clean.ny_times_articles.dashboard.domain.NyTimesMostViewArticlesViewModel;
import com.mvp_clean.ny_times_articles.dashboard.view.INyTimesDashboardView;
import com.mvp_clean.ny_times_articles.dashboard.view.fragments.home.NYTimesHomeFragment;
import com.mvp_clean.ny_times_articles.dashboard.view.presenter.NyTimesDashboardPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NyTimesDashboardActivity
        extends BaseActivity
        implements INyTimesDashboardView, IRetryCallBack {

    private AppBarConfiguration appBarConfiguration;
    private int previousDate = 1;

    @Inject
    NyTimesDashboardPresenter nyTimesDashboardPresenter= null;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        CoreDI.getDStvComponent().inject(this);
        nyTimesDashboardPresenter.bind(this);
        setupNavigationDrawer();
        nyTimesDashboardPresenter.getArticles(previousDate);

    }

    private void setupNavigationDrawer() {
        setSupportActionBar(toolbar);
//
//        fab.setOnClickListener(v -> Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show());

/*
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawer_layout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//     public boolean onSupportNavigateUp() {
//        NavController navController = findNavController(R.id.nav_host_fragment);
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }

    @Override
     public void showRetry() {
    }

    @Override
     public void showArticles(NyTimesMostViewArticlesViewModel nyTimesMostViewArticlesViewModels) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(IKeyConstant.articleMostViewedList, nyTimesMostViewArticlesViewModels);
        changeFragment(new NYTimesHomeFragment(), bundle, false );
    }

     public void changeFragment(Fragment fragment ,
                                Bundle bundle,
                                Boolean addToBackStack) {

        try {
            if (isNetworkAvailable(this)) {
                if (!isFinishing() && !isDestroyed()) {
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    if (bundle != null) {
                        fragment.setArguments(bundle);
                    }
                    Log.d("nameclass", fragment.getClass().getSimpleName());
                    ft.replace(R.id.nav_host_fragment, fragment, fragment.getClass().getSimpleName());
                    if (addToBackStack) {
                        ft.addToBackStack(null);
                    }
                    ft.commitAllowingStateLoss();
                }
            } else {
                startServerApiErrorScreen(IKeyConstant.noInternet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}