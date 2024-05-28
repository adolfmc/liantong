package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class CheckSSamsungPayResult implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.result.CheckSSamsungPayResult.1
        @Override // android.os.Parcelable.Creator
        public final CheckSSamsungPayResult createFromParcel(Parcel parcel) {
            return new CheckSSamsungPayResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final CheckSSamsungPayResult[] newArray(int i) {
            return new CheckSSamsungPayResult[i];
        }
    };

    public CheckSSamsungPayResult() {
    }

    public CheckSSamsungPayResult(Parcel parcel) {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }
}
