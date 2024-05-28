package com.unionpay.tsmservice.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\11617560_dexfile_execute.dex */
public class AppStatus implements Parcelable {
    public static final String APPLY = "06";
    public static final String APPROVED = "00";
    public static final String APPROVING = "02";
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.data.AppStatus.1
        @Override // android.os.Parcelable.Creator
        public final AppStatus createFromParcel(Parcel parcel) {
            return new AppStatus(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final AppStatus[] newArray(int i) {
            return new AppStatus[i];
        }
    };
    public static final String NOT_APPROVED = "01";
    public static final String OPEN = "05";
    public static final String VIEW = "07";
    private String mStatus;

    public AppStatus() {
        this.mStatus = "";
    }

    public AppStatus(Parcel parcel) {
        this.mStatus = "";
        this.mStatus = parcel.readString();
    }

    public AppStatus(String str) {
        this.mStatus = "";
        this.mStatus = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getStatus() {
        return this.mStatus;
    }

    public void setStatus(String str) {
        this.mStatus = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mStatus);
    }
}
