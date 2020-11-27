package com.mvp_clean.ny_times_articles.dashboard.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.mvp_clean.ny_times_articles.R;
import com.mvp_clean.ny_times_articles.R.id;
import com.mvp_clean.ny_times_articles.dashboard.data.model.ResultEntity;
import com.mvp_clean.ny_times_articles.dashboard.view.fragments.home.NYTimesHomeFragment;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Text;

public class NYTimeseMostViewedArticlesAdapter extends
        RecyclerView.Adapter<NYTimeseMostViewedArticlesAdapter.MyViewHolder> {
   private final List resultEntities;


   public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
      View listItem  = layoutInflater.inflate(R.layout.dashboard_articles_item, parent, false);
      return new MyViewHolder(listItem);
   }

   public void onBindViewHolder(@NotNull NYTimeseMostViewedArticlesAdapter.MyViewHolder holder, int position) {
      holder.bindArticleUiToData((ResultEntity)this.resultEntities.get(position));
   }


   public int getItemCount() {
      return this.resultEntities.size();
   }

   public NYTimeseMostViewedArticlesAdapter(@NotNull NYTimesHomeFragment NYTimesHomeFragment, @NotNull List resultEntities) {
      this.resultEntities = resultEntities;
   }

    class MyViewHolder extends ViewHolder {
      @BindView(R.id.tv_article_item_body)
       TextView tv_article_item_body;
      @BindView(R.id.tv_article_item_author)
      TextView tv_article_item_author;
      @BindView(id.tv_article_item_date)
      TextView tv_article_item_date;

      public final void bindArticleUiToData(@NotNull ResultEntity resultEntity){
        tv_article_item_body.setText(resultEntity.getTitle());
        tv_article_item_author.setText(resultEntity.getByline());
        tv_article_item_date.setText(resultEntity.getPublishedDate());

      }

      public MyViewHolder(@Nullable View itemView) {
          super(itemView);
          ButterKnife.bind(this, itemView);
      }
   }
}
