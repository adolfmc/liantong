package com.unionpay.tsmservice.p364mi.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SafetyKeyboardRequestParams extends RequestParams {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams.1
        @Override // android.os.Parcelable.Creator
        public final SafetyKeyboardRequestParams createFromParcel(Parcel parcel) {
            return new SafetyKeyboardRequestParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final SafetyKeyboardRequestParams[] newArray(int i) {
            return new SafetyKeyboardRequestParams[i];
        }
    };
    private Bundle mParams;

    public SafetyKeyboardRequestParams() {
    }

    public SafetyKeyboardRequestParams(Parcel parcel) {
        super(parcel);
        this.mParams = parcel.readBundle();
    }

    public Bundle getParams() {
        return this.mParams;
    }

    public void setParams(Bundle bundle) {
        this.mParams = bundle;
    }

    @Override // com.unionpay.tsmservice.p364mi.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeBundle(this.mParams);
    }
}
