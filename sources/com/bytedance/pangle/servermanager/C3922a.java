package com.bytedance.pangle.servermanager;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.servermanager.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3922a implements Parcelable {
    public static final Parcelable.Creator<C3922a> CREATOR = new Parcelable.Creator<C3922a>() { // from class: com.bytedance.pangle.servermanager.a.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ C3922a[] newArray(int i) {
            return new C3922a[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ C3922a createFromParcel(Parcel parcel) {
            return new C3922a(parcel);
        }
    };

    /* renamed from: a */
    final IBinder f9330a;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    C3922a(Parcel parcel) {
        this.f9330a = parcel.readStrongBinder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C3922a(IBinder iBinder) {
        this.f9330a = iBinder;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.f9330a);
    }
}
