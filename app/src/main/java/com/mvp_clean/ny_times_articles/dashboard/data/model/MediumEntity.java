package com.mvp_clean.ny_times_articles.dashboard.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.squareup.moshi.Json;

public class MediumEntity implements Parcelable {

    @Json(name = "type")
    private final String type;
    @Json(name = "subtype")
    private final String subtype;
    @Json(name = "caption")
    private final String caption;
    @Json(name = "copyright")
    private final String copyright;
    @Json(name = "approved_for_syndication")
    private final Integer approvedForSyndication;
    @Json(name = "media-metadata")
    private List<MediaMetadatumEntity> mediaMetadata = null;

    protected MediumEntity(Parcel in) {
        type = in.readString();
        subtype = in.readString();
        caption = in.readString();
        copyright = in.readString();
        if (in.readByte() == 0) {
            approvedForSyndication = null;
        } else {
            approvedForSyndication = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(subtype);
        dest.writeString(caption);
        dest.writeString(copyright);
        if (approvedForSyndication == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(approvedForSyndication);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MediumEntity> CREATOR = new Creator<MediumEntity>() {
        @Override
        public MediumEntity createFromParcel(Parcel in) {
            return new MediumEntity(in);
        }

        @Override
        public MediumEntity[] newArray(int size) {
            return new MediumEntity[size];
        }
    };


}
