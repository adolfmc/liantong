package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SendApduRequestParams extends RequestParams {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.request.SendApduRequestParams.1
        @Override // android.os.Parcelable.Creator
        public final SendApduRequestParams createFromParcel(Parcel parcel) {
            return new SendApduRequestParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final SendApduRequestParams[] newArray(int i) {
            return new SendApduRequestParams[i];
        }
    };
    private String channel;
    private String hexApdu;

    public SendApduRequestParams() {
    }

    public SendApduRequestParams(Parcel parcel) {
        super(parcel);
        this.channel = parcel.readString();
        this.hexApdu = parcel.readString();
    }

    public String getChannel() {
        return this.channel;
    }

    public String getHexApdu() {
        return this.hexApdu;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public void setHexApdu(String str) {
        this.hexApdu = str;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.channel);
        parcel.writeString(this.hexApdu);
    }
}
