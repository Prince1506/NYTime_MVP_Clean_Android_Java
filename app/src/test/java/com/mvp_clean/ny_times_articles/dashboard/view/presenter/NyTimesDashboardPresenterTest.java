package com.mvp_clean.ny_times_articles.dashboard.view.presenter;

import com.mvp_clean.ny_times_articles.core.callback.IRetryCallBack;
import com.mvp_clean.ny_times_articles.core.constants.IKeyConstant;
import com.mvp_clean.ny_times_articles.dashboard.domain.NyTimesMostViewArticlesDataModel;
import com.mvp_clean.ny_times_articles.dashboard.domain.NyTimesMostViewArticlesViewModel;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class NyTimesDashboardPresenterTest {

    // region constants  ----------------------------------------------
    final int previousDay = 1;

    // Region helper fields  ----------------------------------------------


    // End helper fields ----------------------------------------------
    @Mock
    NyTimesMostViewArticlesViewModel nyTimesMostViewArticlesViewModel;
    @Mock
    NyTimesMostViewArticlesDataModel nyTimesMostViewArticlesDataModel;
    @InjectMocks
    private NyTimesDashboardPresenter SUT;
    @Mock
    NyTimesDashboardActivity dashboardActivity;

    // End region constants fields ----------------------------------------------



    @Before
    public void setup() {
        SUT.bind(dashboardActivity);
    }

    // Region helper methods ----------------------------------------------

    /** If all articles are obtained correctly then verify following tests
     *  1. Progress dialog to show and hide is called only once
     *  2. The data obtained from api is correctly passed to end point (UI here )
     *  3. showArticles is called once we obtain success from API.
     */
    @Test
    public void getArticles_success_showArticles() {
        // Arrange

        when(dashboardActivity.isNetworkAvailable(dashboardActivity) ).thenReturn(true);

        when(SUT.getNYTimeArticlesDashboardUseCase().execute(anyInt()))
                .thenReturn(Observable.just(nyTimesMostViewArticlesDataModel));

        when(
                SUT.getNyTimesMostPopularArticlesResponseDataToViewMapper()
                .mapDataToViewMapper(nyTimesMostViewArticlesDataModel))
                .thenReturn(nyTimesMostViewArticlesViewModel);

        // Act
        SUT.getArticles(previousDay);

        // Assert
        verify(dashboardActivity, times(1)).showProgress();
        verify(dashboardActivity, times(1)).hideProgress();
        verify(dashboardActivity, times(1)).showArticles(nyTimesMostViewArticlesViewModel);
    }


    /**
     *  When API calls fails
     *  1. Show popup of api call is failed.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void getArticles_serverError_serverErrorPopupShown() {
        // Arrange
        ArgumentCaptor<String> noInternetArgumentCapture = ArgumentCaptor.forClass(String.class);

        when(dashboardActivity.isNetworkAvailable(dashboardActivity) ).thenReturn(true);

        when(SUT.getNYTimeArticlesDashboardUseCase().execute(anyInt())).thenReturn(Observable.error(new Throwable()));

        // Act
        SUT.getArticles(previousDay);

        // Assert
        verify(dashboardActivity, times(1)).showProgress();
        verify(dashboardActivity, times(1)).hideProgress();
        verify(dashboardActivity, times(1)).startServerApiErrorScreen(noInternetArgumentCapture.capture());
        Assert.assertThat(
                noInternetArgumentCapture.getValue(),
                CoreMatchers.is(IKeyConstant.Companion.getServerError())
        );
    }

    /**
     *  When API calls timeouts
     *  1. Show popup of api call timed out
     */
    @Test
    public void getArticles_timeout_serverErrorPopupShown() {
        // Arrange
        ArgumentCaptor<String> noInternetArgumentCapture = ArgumentCaptor.forClass(String.class);

        when(dashboardActivity.isNetworkAvailable(dashboardActivity) ).thenReturn(true);

        when(SUT.getNYTimeArticlesDashboardUseCase().execute(anyInt())).thenReturn(Observable.error(new SocketTimeoutException()));

        // Act
        SUT.getArticles(previousDay);

        // Assert
        verify(dashboardActivity, times(1)).showProgress();
        verify(dashboardActivity, times(1)).hideProgress();
        verify(dashboardActivity, times(1)).startServerApiErrorScreen(noInternetArgumentCapture.capture());
        Assert.assertThat(noInternetArgumentCapture.getValue(), CoreMatchers.is(IKeyConstant.Companion.getTimeOut())  );
    }

    /**
     *  When no internet is available, no internet popup should be shown
     */
    @Test
    public void getArticles_noInternet_noInternetPopupShown() {

        // Arrange
        ArgumentCaptor<String> noInternetArgumentCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<IRetryCallBack> iRetryCallBackArgumentCapture = ArgumentCaptor.forClass(IRetryCallBack.class);

        when(dashboardActivity.isNetworkAvailable(dashboardActivity) ).thenReturn(false);


        // Act
        SUT.getArticles(previousDay);

        // Assert
        verify(dashboardActivity, times(1)).startServerApiRetryScreen(
                noInternetArgumentCapture.capture(),
                iRetryCallBackArgumentCapture.capture()
        );
        Assert.assertThat(
                noInternetArgumentCapture.getValue(),
                CoreMatchers.is(IKeyConstant.Companion.getNoInternet())
        );
    }



    // Region helper classes ----------------------------------------------


    // End region classes ----------------------------------------------


    // End region helper methods ----------------------------------------------
}