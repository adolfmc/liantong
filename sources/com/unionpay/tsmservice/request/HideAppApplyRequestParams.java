package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class HideAppApplyRequestParams extends RequestParams {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.request.HideAppApplyRequestParams.1
        @Override // android.os.Parcelable.Creator
        public final HideAppApplyRequestParams createFromParcel(Parcel parcel) {
            return new HideAppApplyRequestParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final HideAppApplyRequestParams[] newArray(int i) {
            return new HideAppApplyRequestParams[i];
        }
    };
    private String mApplyId;

    public HideAppApplyRequestParams() {
    }

    public HideAppApplyRequestParams(Parcel parcel) {
        super(parcel);
        this.mApplyId = parcel.readString();
    }

    public HideAppApplyRequestParams(String str) {
        this.mApplyId = str;
    }

    public String getApplyId() {
        return this.mApplyId;
    }

    public void setApplyId(String str) {
        this.mApplyId = str;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mApplyId);
    }
}
