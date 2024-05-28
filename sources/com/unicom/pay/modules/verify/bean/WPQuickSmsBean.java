package com.unicom.pay.modules.verify.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPQuickSmsBean implements Parcelable {
    public static final Parcelable.Creator<WPQuickSmsBean> CREATOR = new Parcelable.Creator<WPQuickSmsBean>() { // from class: com.unicom.pay.modules.verify.bean.WPQuickSmsBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPQuickSmsBean createFromParcel(Parcel parcel) {
            return new WPQuickSmsBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPQuickSmsBean[] newArray(int i) {
            return new WPQuickSmsBean[i];
        }
    };
    private String cdKey;

    public WPQuickSmsBean() {
    }

    public WPQuickSmsBean(Parcel parcel) {
        this.cdKey = parcel.readString();
    }

    public WPQuickSmsBean(String str) {
        this.cdKey = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCdKey() {
        return this.cdKey;
    }

    public void setCdKey(String str) {
        this.cdKey = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.cdKey);
    }
}
