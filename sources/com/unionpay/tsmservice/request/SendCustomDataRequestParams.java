package com.unionpay.tsmservice.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SendCustomDataRequestParams extends RequestParams {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.request.SendCustomDataRequestParams.1
        @Override // android.os.Parcelable.Creator
        public final SendCustomDataRequestParams createFromParcel(Parcel parcel) {
            return new SendCustomDataRequestParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final SendCustomDataRequestParams[] newArray(int i) {
            return new SendCustomDataRequestParams[i];
        }
    };
    private Bundle mParams;

    public SendCustomDataRequestParams() {
    }

    public SendCustomDataRequestParams(Parcel parcel) {
        super(parcel);
        this.mParams = parcel.readBundle();
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle getParams() {
        return this.mParams;
    }

    public void setParams(Bundle bundle) {
        this.mParams = bundle;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeBundle(this.mParams);
    }
}
