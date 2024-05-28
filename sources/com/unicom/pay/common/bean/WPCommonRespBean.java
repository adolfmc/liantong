package com.unicom.pay.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPCommonRespBean implements Parcelable {
    public static final Parcelable.Creator<WPCommonRespBean> CREATOR = new Parcelable.Creator<WPCommonRespBean>() { // from class: com.unicom.pay.common.bean.WPCommonRespBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPCommonRespBean createFromParcel(Parcel parcel) {
            return new WPCommonRespBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPCommonRespBean[] newArray(int i) {
            return new WPCommonRespBean[i];
        }
    };
    private String authId;
    private String pid;
    private String tgtid;
    private String userTokenId;

    public WPCommonRespBean(Parcel parcel) {
        this.pid = parcel.readString();
        this.userTokenId = parcel.readString();
        this.tgtid = parcel.readString();
        this.authId = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAuthId() {
        return this.authId;
    }

    public String getPid() {
        return this.pid;
    }

    public String getTgtid() {
        return this.tgtid;
    }

    public String getUserTokenId() {
        return this.userTokenId;
    }

    public void setAuthId(String str) {
        this.authId = str;
    }

    public void setPid(String str) {
        this.pid = str;
    }

    public void setTgtid(String str) {
        this.tgtid = str;
    }

    public void setUserTokenId(String str) {
        this.userTokenId = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.pid);
        parcel.writeString(this.userTokenId);
        parcel.writeString(this.tgtid);
        parcel.writeString(this.authId);
    }
}
