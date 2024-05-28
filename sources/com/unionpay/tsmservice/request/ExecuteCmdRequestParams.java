package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class ExecuteCmdRequestParams extends RequestParams {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.request.ExecuteCmdRequestParams.1
        @Override // android.os.Parcelable.Creator
        public final ExecuteCmdRequestParams createFromParcel(Parcel parcel) {
            return new ExecuteCmdRequestParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final ExecuteCmdRequestParams[] newArray(int i) {
            return new ExecuteCmdRequestParams[i];
        }
    };
    private String mSign;
    private String mSsid;

    public ExecuteCmdRequestParams() {
    }

    public ExecuteCmdRequestParams(Parcel parcel) {
        super(parcel);
        this.mSsid = parcel.readString();
        this.mSign = parcel.readString();
    }

    public String getSign() {
        return this.mSign;
    }

    public String getSsid() {
        return this.mSsid;
    }

    public void setSign(String str) {
        this.mSign = str;
    }

    public void setSsid(String str) {
        this.mSsid = str;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mSsid);
        parcel.writeString(this.mSign);
    }
}
