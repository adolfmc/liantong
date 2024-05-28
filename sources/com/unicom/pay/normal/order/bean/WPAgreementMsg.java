package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPAgreementMsg implements Parcelable {
    public static final Parcelable.Creator<WPAgreementMsg> CREATOR = new Parcelable.Creator<WPAgreementMsg>() { // from class: com.unicom.pay.normal.order.bean.WPAgreementMsg.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPAgreementMsg createFromParcel(Parcel parcel) {
            return new WPAgreementMsg(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPAgreementMsg[] newArray(int i) {
            return new WPAgreementMsg[i];
        }
    };
    private String name;
    private String url;

    public WPAgreementMsg(Parcel parcel) {
        this.name = parcel.readString();
        this.url = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.url);
    }
}
