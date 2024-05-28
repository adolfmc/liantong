package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class ExchangeKeyResult implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.result.ExchangeKeyResult.1
        @Override // android.os.Parcelable.Creator
        public final ExchangeKeyResult createFromParcel(Parcel parcel) {
            return new ExchangeKeyResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final ExchangeKeyResult[] newArray(int i) {
            return new ExchangeKeyResult[i];
        }
    };
    private String key;

    public ExchangeKeyResult() {
    }

    public ExchangeKeyResult(Parcel parcel) {
        this.key = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.key);
    }
}
