package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TransitResultNode implements Parcelable {
    public static final Parcelable.Creator<TransitResultNode> CREATOR = new C2809r();

    /* renamed from: a */
    private int f6724a;

    /* renamed from: b */
    private String f6725b;

    /* renamed from: c */
    private LatLng f6726c;

    /* renamed from: d */
    private String f6727d;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TransitResultNode(int i, String str, LatLng latLng, String str2) {
        this.f6725b = null;
        this.f6726c = null;
        this.f6727d = null;
        this.f6724a = i;
        this.f6725b = str;
        this.f6726c = latLng;
        this.f6727d = str2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TransitResultNode(Parcel parcel) {
        this.f6725b = null;
        this.f6726c = null;
        this.f6727d = null;
        this.f6724a = parcel.readInt();
        this.f6725b = parcel.readString();
        this.f6726c = (LatLng) parcel.readValue(LatLng.class.getClassLoader());
        this.f6727d = parcel.readString();
    }

    public String getSearchWord() {
        return this.f6727d;
    }

    public LatLng getLocation() {
        return this.f6726c;
    }

    public String getCityName() {
        return this.f6725b;
    }

    public int getCityId() {
        return this.f6724a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f6724a);
        parcel.writeString(this.f6725b);
        parcel.writeValue(this.f6726c);
        parcel.writeString(this.f6727d);
    }
}
