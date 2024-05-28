package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class OpenChannelResult implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.result.OpenChannelResult.1
        @Override // android.os.Parcelable.Creator
        public final OpenChannelResult createFromParcel(Parcel parcel) {
            return new OpenChannelResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final OpenChannelResult[] newArray(int i) {
            return new OpenChannelResult[i];
        }
    };
    private String channel;
    private String outHexApdu;

    public OpenChannelResult() {
    }

    public OpenChannelResult(Parcel parcel) {
        this.outHexApdu = parcel.readString();
        this.channel = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getChannel() {
        return this.channel;
    }

    public String getOutHexApdu() {
        return this.outHexApdu;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public void setOutHexApdu(String str) {
        this.outHexApdu = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.outHexApdu);
        parcel.writeString(this.channel);
    }
}
