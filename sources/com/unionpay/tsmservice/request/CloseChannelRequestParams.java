package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class CloseChannelRequestParams extends RequestParams {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.request.CloseChannelRequestParams.1
        @Override // android.os.Parcelable.Creator
        public final CloseChannelRequestParams createFromParcel(Parcel parcel) {
            return new CloseChannelRequestParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final CloseChannelRequestParams[] newArray(int i) {
            return new CloseChannelRequestParams[i];
        }
    };
    private String mChannel;

    public CloseChannelRequestParams() {
    }

    public CloseChannelRequestParams(Parcel parcel) {
        super(parcel);
        this.mChannel = parcel.readString();
    }

    public CloseChannelRequestParams(String str) {
        this.mChannel = str;
    }

    public String getChannel() {
        return this.mChannel;
    }

    public void setChannel(String str) {
        this.mChannel = str;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mChannel);
    }
}
