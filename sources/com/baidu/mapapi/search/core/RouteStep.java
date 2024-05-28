package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RouteStep implements Parcelable {
    public static final Parcelable.Creator<RouteStep> CREATOR = new C2805m();

    /* renamed from: a */
    int f6708a;

    /* renamed from: b */
    int f6709b;

    /* renamed from: c */
    String f6710c;
    protected List<LatLng> mWayPoints;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RouteStep() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RouteStep(Parcel parcel) {
        this.f6708a = parcel.readInt();
        this.f6709b = parcel.readInt();
        this.f6710c = parcel.readString();
        this.mWayPoints = new ArrayList();
        parcel.readList(this.mWayPoints, LatLng.class.getClassLoader());
        if (this.mWayPoints.size() == 0) {
            this.mWayPoints = null;
        }
    }

    public int getDistance() {
        return this.f6708a;
    }

    public void setDistance(int i) {
        this.f6708a = i;
    }

    public int getDuration() {
        return this.f6709b;
    }

    public void setDuration(int i) {
        this.f6709b = i;
    }

    public String getName() {
        return this.f6710c;
    }

    public void setName(String str) {
        this.f6710c = str;
    }

    public List<LatLng> getWayPoints() {
        return this.mWayPoints;
    }

    public void setWayPoints(List<LatLng> list) {
        this.mWayPoints = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f6708a);
        parcel.writeInt(this.f6709b);
        parcel.writeString(this.f6710c);
        parcel.writeList(this.mWayPoints);
    }
}
