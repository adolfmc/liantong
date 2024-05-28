package org.fidoalliance.uaf.client;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class UAFMessage implements Parcelable {
    public static final Parcelable.Creator<UAFMessage> CREATOR = new Parcelable.Creator<UAFMessage>() { // from class: org.fidoalliance.uaf.client.UAFMessage.1
        @Override // android.os.Parcelable.Creator
        public UAFMessage[] newArray(int i) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public UAFMessage createFromParcel(Parcel parcel) {
            return new UAFMessage(parcel);
        }
    };
    public String additionalData;
    public String uafProtocolMessage;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UAFMessage() {
        this.uafProtocolMessage = null;
        this.additionalData = null;
    }

    private UAFMessage(Parcel parcel) {
        this.uafProtocolMessage = null;
        this.additionalData = null;
        this.uafProtocolMessage = parcel.readString();
        this.additionalData = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.uafProtocolMessage);
        parcel.writeString(this.additionalData);
    }
}
