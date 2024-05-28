package com.unionpay.tsmservice.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class PreDownloadRequestParams extends RequestParams {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.request.PreDownloadRequestParams.1
        @Override // android.os.Parcelable.Creator
        public final PreDownloadRequestParams createFromParcel(Parcel parcel) {
            return new PreDownloadRequestParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final PreDownloadRequestParams[] newArray(int i) {
            return new PreDownloadRequestParams[i];
        }
    };
    private Bundle mParams;

    public PreDownloadRequestParams() {
    }

    public PreDownloadRequestParams(Parcel parcel) {
        super(parcel);
        this.mParams = parcel.readBundle();
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
