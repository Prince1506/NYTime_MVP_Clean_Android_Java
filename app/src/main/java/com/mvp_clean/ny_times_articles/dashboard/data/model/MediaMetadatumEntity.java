package com.mvp_clean.ny_times_articles.dashboard.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.squareup.moshi.Json;

public class MediaMetadatumEntity implements Parcelable {

    @Json(name = "url")
    private final String url;
    @Json(name = "format")
    private final String format;
    @Json(name = "height")
    private final Integer height;
    @Json(name = "width")
    private final Integer width;

    protected MediaMetadatumEntity(Parcel in) {
        url = in.readString();
        format = in.readString();
        if (in.readByte() == 0) {
            height = null;
        } else {
            height = in.readInt();
        }
        if (in.readByte() == 0) {
            width = null;
        } else {
            width = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(format);
        if (height == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(height);
        }
        if (width == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(width);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MediaMetadatumEntity> CREATOR = new Creator<MediaMetadatumEntity>() {
        @Override
        public MediaMetadatumEntity createFromParcel(Parcel in) {
            return new MediaMetadatumEntity(in);
        }

        @Override
        public MediaMetadatumEntity[] newArray(int size) {
            return new MediaMetadatumEntity[size];
        }
    };


}
