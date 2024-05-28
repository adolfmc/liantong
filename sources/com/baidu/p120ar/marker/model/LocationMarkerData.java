package com.baidu.p120ar.marker.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.marker.model.LocationMarkerData */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LocationMarkerData implements Parcelable {
    public static final Parcelable.Creator<LocationMarkerData> CREATOR = new Parcelable.Creator<LocationMarkerData>() { // from class: com.baidu.ar.marker.model.LocationMarkerData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LocationMarkerData createFromParcel(Parcel parcel) {
            return new LocationMarkerData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LocationMarkerData[] newArray(int i) {
            return new LocationMarkerData[i];
        }
    };
    public float accuracy;
    public String buildingId;
    public float confidence;
    public String floorId;
    public int frameId;
    public double[] locationPoints;
    public String mapTag;
    public int position;
    public String session;
    public String timeStamp;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LocationMarkerData(String str, String str2, String str3, String str4, double d, double d2, float f, float f2, int i, int i2, String str5) {
        this.mapTag = str2;
        this.buildingId = str3;
        this.floorId = str4;
        this.locationPoints = new double[]{d, d2};
        this.timeStamp = str;
        this.accuracy = f;
        this.confidence = f2;
        this.frameId = i;
        this.position = i2;
        this.session = str5;
    }

    protected LocationMarkerData(Parcel parcel) {
        this.mapTag = parcel.readString();
        this.buildingId = parcel.readString();
        this.floorId = parcel.readString();
        this.locationPoints = parcel.createDoubleArray();
        this.timeStamp = parcel.readString();
        this.accuracy = parcel.readFloat();
        this.confidence = parcel.readFloat();
        this.frameId = parcel.readInt();
        this.position = parcel.readInt();
        this.session = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mapTag);
        parcel.writeString(this.buildingId);
        parcel.writeString(this.floorId);
        parcel.writeDoubleArray(this.locationPoints);
        parcel.writeString(this.timeStamp);
        parcel.writeFloat(this.accuracy);
        parcel.writeFloat(this.confidence);
        parcel.writeInt(this.frameId);
        parcel.writeInt(this.position);
        parcel.writeString(this.session);
    }
}
