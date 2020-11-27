package com.mvp_clean.ny_times_articles.dashboard.domain;


import android.os.Parcel;
import android.os.Parcelable;

import com.mvp_clean.ny_times_articles.dashboard.data.model.ResultEntity;

import java.util.List;

public class NyTimesMostViewArticlesViewModel implements Parcelable {
    private String status;
    private String copyright;
    private Integer numResults;
    private List<ResultEntity> resultEntities = null;

    protected NyTimesMostViewArticlesViewModel(Parcel in) {
        status = in.readString();
        copyright = in.readString();
        if (in.readByte() == 0) {
            numResults = null;
        } else {
            numResults = in.readInt();
        }
        resultEntities = in.createTypedArrayList(ResultEntity.CREATOR);
    }

    public NyTimesMostViewArticlesViewModel() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(copyright);
        if (numResults == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(numResults);
        }
        dest.writeTypedList(resultEntities);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NYTimesMostPopularArticlesDataModel> CREATOR = new Creator<NYTimesMostPopularArticlesDataModel>() {
        @Override
        public NYTimesMostPopularArticlesDataModel createFromParcel(Parcel in) {
            return new NYTimesMostPopularArticlesDataModel(in);
        }

        @Override
        public NYTimesMostPopularArticlesDataModel[] newArray(int size) {
            return new NYTimesMostPopularArticlesDataModel[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<ResultEntity> getResultEntities() {
        return resultEntities;
    }

    public void setResultEntities(List<ResultEntity> resultEntities) {
        this.resultEntities = resultEntities;
    }
}
