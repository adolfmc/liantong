package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class OpenChannelRequestParams extends RequestParams {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.request.OpenChannelRequestParams.1
        @Override // android.os.Parcelable.Creator
        public final OpenChannelRequestParams createFromParcel(Parcel parcel) {
            return new OpenChannelRequestParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final OpenChannelRequestParams[] newArray(int i) {
            return new OpenChannelRequestParams[i];
        }
    };
    private String mAppAID;

    public OpenChannelRequestParams() {
    }

    public OpenChannelRequestParams(Parcel parcel) {
        super(parcel);
        this.mAppAID = parcel.readString();
    }

    public OpenChannelRequestParams(String str) {
        this.mAppAID = str;
    }

    public String getAppAID() {
        return this.mAppAID;
    }

    public void setAppAID(String str) {
        this.mAppAID = str;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mAppAID);
    }
}
