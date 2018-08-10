package com.salton123.emuilib.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * User: newSalton@outlook.com
 * Date: 2018/2/26 下午6:02
 * ModifyTime: 下午6:02
 * Description:
 */
public class VideoBean implements Parcelable {
    public int resId;
    public String url;


    public VideoBean(int resId, String url) {
        this.resId = resId;
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.resId);
        dest.writeString(this.url);
    }

    protected VideoBean(Parcel in) {
        this.resId = in.readInt();
        this.url = in.readString();
    }

    public static final Creator<VideoBean> CREATOR = new Creator<VideoBean>() {
        @Override
        public VideoBean createFromParcel(Parcel source) {
            return new VideoBean(source);
        }

        @Override
        public VideoBean[] newArray(int size) {
            return new VideoBean[size];
        }
    };
}
