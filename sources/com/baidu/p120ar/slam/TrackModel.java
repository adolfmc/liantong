package com.baidu.p120ar.slam;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.slam.TrackModel */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TrackModel implements Parcelable {
    public static final Parcelable.Creator<TrackModel> CREATOR = new Parcelable.Creator() { // from class: com.baidu.ar.slam.TrackModel.1
        @Override // android.os.Parcelable.Creator
        public Object createFromParcel(Parcel parcel) {
            TrackModel trackModel = new TrackModel();
            trackModel.setId(parcel.readString());
            trackModel.setPose(parcel.createFloatArray());
            return trackModel;
        }

        @Override // android.os.Parcelable.Creator
        public TrackModel[] newArray(int i) {
            return new TrackModel[i];
        }
    };

    /* renamed from: id */
    public String f4094id;
    public float[] pose;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.f4094id;
    }

    public void setId(String str) {
        this.f4094id = str;
    }

    public float[] getPose() {
        return this.pose;
    }

    public void setPose(float[] fArr) {
        this.pose = fArr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4094id);
        parcel.writeFloatArray(this.pose);
    }
}
