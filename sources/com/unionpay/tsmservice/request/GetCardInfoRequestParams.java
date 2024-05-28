package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class GetCardInfoRequestParams extends RequestParams implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.request.GetCardInfoRequestParams.1
        @Override // android.os.Parcelable.Creator
        public final GetCardInfoRequestParams createFromParcel(Parcel parcel) {
            return new GetCardInfoRequestParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final GetCardInfoRequestParams[] newArray(int i) {
            return new GetCardInfoRequestParams[i];
        }
    };
    private String[] mAppAID;

    public GetCardInfoRequestParams() {
    }

    public GetCardInfoRequestParams(Parcel parcel) {
        super(parcel);
        this.mAppAID = parcel.createStringArray();
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String[] getAppAID() {
        return this.mAppAID;
    }

    public void setAppAID(String[] strArr) {
        this.mAppAID = strArr;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeStringArray(this.mAppAID);
    }
}
