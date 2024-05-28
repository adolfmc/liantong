package com.unionpay.tsmservice.result;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class OnlinePaymentVerifyResult implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.result.OnlinePaymentVerifyResult.1
        @Override // android.os.Parcelable.Creator
        public final OnlinePaymentVerifyResult createFromParcel(Parcel parcel) {
            return new OnlinePaymentVerifyResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final OnlinePaymentVerifyResult[] newArray(int i) {
            return new OnlinePaymentVerifyResult[i];
        }
    };
    private Bundle mResultData;

    public OnlinePaymentVerifyResult() {
    }

    public OnlinePaymentVerifyResult(Parcel parcel) {
        this.mResultData = parcel.readBundle();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle getResultData() {
        return this.mResultData;
    }

    public void setResultData(Bundle bundle) {
        this.mResultData = bundle;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.mResultData);
    }
}
