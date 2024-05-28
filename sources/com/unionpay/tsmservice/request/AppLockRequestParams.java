package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.AppID;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class AppLockRequestParams extends RequestParams {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.request.AppLockRequestParams.1
        @Override // android.os.Parcelable.Creator
        public final AppLockRequestParams createFromParcel(Parcel parcel) {
            return new AppLockRequestParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final AppLockRequestParams[] newArray(int i) {
            return new AppLockRequestParams[i];
        }
    };
    private AppID mAppID;

    public AppLockRequestParams() {
    }

    public AppLockRequestParams(Parcel parcel) {
        super(parcel);
        this.mAppID = (AppID) parcel.readParcelable(AppID.class.getClassLoader());
    }

    public AppID getAppID() {
        return this.mAppID;
    }

    public void setAppID(AppID appID) {
        this.mAppID = appID;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.mAppID, i);
    }
}
