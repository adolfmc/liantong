package com.unionpay.tsmservice.p364mi.result;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.unionpay.tsmservice.mi.result.GetPubKeyResult */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class GetPubKeyResult implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.mi.result.GetPubKeyResult.1
        @Override // android.os.Parcelable.Creator
        public final GetPubKeyResult createFromParcel(Parcel parcel) {
            return new GetPubKeyResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final GetPubKeyResult[] newArray(int i) {
            return new GetPubKeyResult[i];
        }
    };
    private String key;

    public GetPubKeyResult() {
    }

    public GetPubKeyResult(Parcel parcel) {
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
