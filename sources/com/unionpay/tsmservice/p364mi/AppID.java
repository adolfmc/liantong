package com.unionpay.tsmservice.p364mi;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.unionpay.tsmservice.mi.AppID */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class AppID implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.mi.AppID.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new AppID(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new AppID[i];
        }
    };

    /* renamed from: a */
    String f20778a;

    /* renamed from: b */
    String f20779b;

    public AppID(Parcel parcel) {
        this.f20778a = "";
        this.f20779b = "";
        this.f20778a = parcel.readString();
        this.f20779b = parcel.readString();
    }

    public AppID(String str, String str2) {
        this.f20778a = "";
        this.f20779b = "";
        this.f20778a = str;
        this.f20779b = str2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAppAid() {
        return this.f20778a;
    }

    public String getAppVersion() {
        return this.f20779b;
    }

    public void setAppAid(String str) {
        this.f20778a = str;
    }

    public void setAppVersion(String str) {
        this.f20779b = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f20778a);
        parcel.writeString(this.f20779b);
    }
}
