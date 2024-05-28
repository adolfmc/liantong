package com.unionpay.tsmservice.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SeAppDetail implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.data.SeAppDetail.1
        @Override // android.os.Parcelable.Creator
        public final SeAppDetail createFromParcel(Parcel parcel) {
            return new SeAppDetail(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final SeAppDetail[] newArray(int i) {
            return new SeAppDetail[i];
        }
    };
    private Bundle mDetail;

    public SeAppDetail() {
    }

    public SeAppDetail(Parcel parcel) {
        this.mDetail = parcel.readBundle();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle getDetail() {
        return this.mDetail;
    }

    public void setDetail(Bundle bundle) {
        this.mDetail = bundle;
    }

    public String toString() {
        StringBuilder sb;
        String str;
        if (this.mDetail != null) {
            String str2 = "AppDetail [mDetail=Bundle(";
            for (String str3 : this.mDetail.keySet()) {
                str2 = str2 + str3 + ":" + this.mDetail.get(str3) + ";";
            }
            sb = new StringBuilder();
            sb.append(str2);
            str = ")]";
        } else {
            sb = new StringBuilder();
            sb.append("AppDetail [mDetail=");
            str = "null]";
        }
        sb.append(str);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.mDetail);
    }
}
