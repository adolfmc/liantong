package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class StartCardApplyRequestParams extends RequestParams {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.request.StartCardApplyRequestParams.1
        @Override // android.os.Parcelable.Creator
        public final StartCardApplyRequestParams createFromParcel(Parcel parcel) {
            return new StartCardApplyRequestParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final StartCardApplyRequestParams[] newArray(int i) {
            return new StartCardApplyRequestParams[i];
        }
    };

    public StartCardApplyRequestParams() {
    }

    public StartCardApplyRequestParams(Parcel parcel) {
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
