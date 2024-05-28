package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class CheckSupportCardApplyRequestParams extends RequestParams {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.request.CheckSupportCardApplyRequestParams.1
        @Override // android.os.Parcelable.Creator
        public final CheckSupportCardApplyRequestParams createFromParcel(Parcel parcel) {
            return new CheckSupportCardApplyRequestParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final CheckSupportCardApplyRequestParams[] newArray(int i) {
            return new CheckSupportCardApplyRequestParams[i];
        }
    };

    public CheckSupportCardApplyRequestParams() {
    }

    public CheckSupportCardApplyRequestParams(Parcel parcel) {
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
