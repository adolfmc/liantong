package com.baidu.mapapi.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ParcelItem implements Parcelable {
    public static final Parcelable.Creator<ParcelItem> CREATOR = new C2789c();

    /* renamed from: a */
    private Bundle f6609a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setBundle(Bundle bundle) {
        this.f6609a = bundle;
    }

    public Bundle getBundle() {
        return this.f6609a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.f6609a);
    }
}
