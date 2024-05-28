package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BusInfo extends TransitBaseInfo {
    public static final Parcelable.Creator<BusInfo> CREATOR = new C2794b();

    /* renamed from: a */
    private int f6659a;

    /* renamed from: b */
    private int f6660b;

    @Override // com.baidu.mapapi.search.core.TransitBaseInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BusInfo() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BusInfo(Parcel parcel) {
        super(parcel);
        this.f6659a = parcel.readInt();
        this.f6660b = parcel.readInt();
    }

    public int getType() {
        return this.f6659a;
    }

    public void setType(int i) {
        this.f6659a = i;
    }

    public int getStopNum() {
        return this.f6660b;
    }

    public void setStopNum(int i) {
        this.f6660b = i;
    }

    @Override // com.baidu.mapapi.search.core.TransitBaseInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f6659a);
        parcel.writeInt(this.f6660b);
    }
}
