package com.unionpay.tsmservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class AppID implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.AppID.1
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
    String f20700a;

    /* renamed from: b */
    String f20701b;

    public AppID(Parcel parcel) {
        this.f20700a = "";
        this.f20701b = "";
        this.f20700a = parcel.readString();
        this.f20701b = parcel.readString();
    }

    public AppID(String str, String str2) {
        this.f20700a = "";
        this.f20701b = "";
        this.f20700a = str;
        this.f20701b = str2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAppAid() {
        return this.f20700a;
    }

    public String getAppVersion() {
        return this.f20701b;
    }

    public void setAppAid(String str) {
        this.f20700a = str;
    }

    public void setAppVersion(String str) {
        this.f20701b = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f20700a);
        parcel.writeString(this.f20701b);
    }
}
