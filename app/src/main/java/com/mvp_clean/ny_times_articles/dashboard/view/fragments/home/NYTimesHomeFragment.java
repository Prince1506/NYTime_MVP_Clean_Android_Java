package com.mvp_clean.ny_times_articles.dashboard.view.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mvp_clean.ny_times_articles.R;
import com.mvp_clean.ny_times_articles.core.constants.IKeyConstant;
import com.mvp_clean.ny_times_articles.core.view.fragments.BaseFragment;
import com.mvp_clean.ny_times_articles.dashboard.domain.NyTimesMostViewArticlesViewModel;
import com.mvp_clean.ny_times_articles.dashboard.view.adapters.NYTimeseMostViewedArticlesAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NYTimesHomeFragment extends BaseFragment {

    @BindView(R.id.rv_nytimes_articles_mostviewd_list)
    RecyclerView rv_nytimes_articles_mostviewd_list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, inflate);
        NyTimesMostViewArticlesViewModel parcelable = (NyTimesMostViewArticlesViewModel) getArguments().getParcelable(IKeyConstant.articleMostViewedList);
        showList(parcelable);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void showList(NyTimesMostViewArticlesViewModel mostViewedArticlesList) {
        rv_nytimes_articles_mostviewd_list.setAdapter(new NYTimeseMostViewedArticlesAdapter(
                this,
                mostViewedArticlesList.getResultEntities()
        ));

        rv_nytimes_articles_mostviewd_list.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.VERTICAL, false)
        );
    }
}