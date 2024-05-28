package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class GetDefaultCardRequestParams extends RequestParams {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.request.GetDefaultCardRequestParams.1
        @Override // android.os.Parcelable.Creator
        public final GetDefaultCardRequestParams createFromParcel(Parcel parcel) {
            return new GetDefaultCardRequestParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final GetDefaultCardRequestParams[] newArray(int i) {
            return new GetDefaultCardRequestParams[i];
        }
    };

    public GetDefaultCardRequestParams() {
    }

    public GetDefaultCardRequestParams(Parcel parcel) {
        super(parcel);
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
