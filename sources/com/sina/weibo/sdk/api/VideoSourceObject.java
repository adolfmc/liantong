package com.sina.weibo.sdk.api;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class VideoSourceObject extends MediaObject {
    public static final Parcelable.Creator<VideoSourceObject> CREATOR = new Parcelable.Creator<VideoSourceObject>() { // from class: com.sina.weibo.sdk.api.VideoSourceObject.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final VideoSourceObject createFromParcel(Parcel parcel) {
            return new VideoSourceObject(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final VideoSourceObject[] newArray(int i) {
            return new VideoSourceObject[i];
        }
    };
    private static final long serialVersionUID = 2745594466460840583L;
    public Uri coverPath;
    public long during;
    public Uri videoPath;

    @Override // com.sina.weibo.sdk.api.MediaObject, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.sina.weibo.sdk.api.MediaObject, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.coverPath, i);
        parcel.writeParcelable(this.videoPath, i);
        parcel.writeLong(this.during);
    }

    public VideoSourceObject() {
    }

    protected VideoSourceObject(Parcel parcel) {
        super(parcel);
        this.coverPath = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.videoPath = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.during = parcel.readLong();
    }
}
