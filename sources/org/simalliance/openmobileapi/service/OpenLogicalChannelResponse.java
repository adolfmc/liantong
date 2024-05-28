package org.simalliance.openmobileapi.service;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class OpenLogicalChannelResponse implements Parcelable {
    public static final Parcelable.Creator<OpenLogicalChannelResponse> CREATOR = new Parcelable.Creator<OpenLogicalChannelResponse>() { // from class: org.simalliance.openmobileapi.service.OpenLogicalChannelResponse.1
        @Override // android.os.Parcelable.Creator
        public OpenLogicalChannelResponse createFromParcel(Parcel parcel) {
            return new OpenLogicalChannelResponse(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public OpenLogicalChannelResponse[] newArray(int i) {
            return new OpenLogicalChannelResponse[i];
        }
    };
    public int mChannelNumber;
    public byte[] mSelectResponse;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getChannel() {
        return this.mChannelNumber;
    }

    public byte[] getSelectResponse() {
        return this.mSelectResponse;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mChannelNumber);
        parcel.writeByteArray(this.mSelectResponse);
    }

    public OpenLogicalChannelResponse(int i, byte[] bArr) {
        this.mChannelNumber = i;
        this.mSelectResponse = bArr;
    }

    public OpenLogicalChannelResponse(Parcel parcel) {
        this.mChannelNumber = parcel.readInt();
        this.mSelectResponse = parcel.createByteArray();
    }
}
