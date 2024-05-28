package com.baidu.platform.comjni.tools;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ParcelItem implements Parcelable {
    public static final Parcelable.Creator<ParcelItem> CREATOR = new C3114a();

    /* renamed from: a */
    private Bundle f8128a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setBundle(Bundle bundle) {
        this.f8128a = bundle;
    }

    public Bundle getBundle() {
        return this.f8128a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.f8128a);
    }
}
