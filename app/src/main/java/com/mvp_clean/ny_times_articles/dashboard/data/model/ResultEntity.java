package com.mvp_clean.ny_times_articles.dashboard.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.squareup.moshi.Json;
import java.util.List;

public class ResultEntity implements Parcelable {

    @Json(name = "published_date")
    private final String publishedDate;
    @Json(name = "column")
    private Object column;
    @Json(name = "byline")
    private final String byline;
    @Json(name = "title")
    private final String title;


    protected ResultEntity(Parcel in) {
        String uri = in.readString();
        String url = in.readString();
        long id = in.readLong();
        long assetId;
        if (in.readLong() == 0) {
            assetId = 0;
        } else {
            assetId = in.readLong();
        }
        String source = in.readString();
        publishedDate = in.readString();
        String updated = in.readString();
        String section = in.readString();
        String subsection = in.readString();
        String nytdsection = in.readString();
        String adxKeywords = in.readString();
        byline = in.readString();
        String type = in.readString();
        title = in.readString();
        String _abstract = in.readString();
        List<String> desFacet = in.createStringArrayList();
        List<String> orgFacet = in.createStringArrayList();
        List<String> perFacet = in.createStringArrayList();
        List<String> geoFacet = in.createStringArrayList();
        List<MediumEntity> media = in.createTypedArrayList(MediumEntity.CREATOR);
        Integer etaId;
        if (in.readByte() == 0) {
            etaId = null;
        } else {
            etaId = in.readInt();
        }
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getByline() {
        return byline;
    }

    public String getTitle() {
        return title;
    }

    public static final Creator<ResultEntity> CREATOR = new Creator<ResultEntity>() {
        @Override
        public ResultEntity createFromParcel(Parcel in) {
            return new ResultEntity(in);
        }

        @Override
        public ResultEntity[] newArray(int size) {
            return new ResultEntity[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
