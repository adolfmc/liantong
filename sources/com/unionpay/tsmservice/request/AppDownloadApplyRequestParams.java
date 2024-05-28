package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.AppID;
import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class AppDownloadApplyRequestParams extends RequestParams {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.request.AppDownloadApplyRequestParams.1
        @Override // android.os.Parcelable.Creator
        public final AppDownloadApplyRequestParams createFromParcel(Parcel parcel) {
            return new AppDownloadApplyRequestParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final AppDownloadApplyRequestParams[] newArray(int i) {
            return new AppDownloadApplyRequestParams[i];
        }
    };
    private AppID mAppID;
    private HashMap mParams;

    public AppDownloadApplyRequestParams() {
    }

    public AppDownloadApplyRequestParams(Parcel parcel) {
        super(parcel);
        this.mAppID = (AppID) parcel.readParcelable(AppID.class.getClassLoader());
        this.mParams = parcel.readHashMap(HashMap.class.getClassLoader());
    }

    public AppID getAppID() {
        return this.mAppID;
    }

    public Map getParams() {
        return this.mParams;
    }

    public void setAppID(AppID appID) {
        this.mAppID = appID;
    }

    public void setParams(HashMap hashMap) {
        this.mParams = hashMap;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.mAppID, i);
        parcel.writeMap(this.mParams);
    }
}
