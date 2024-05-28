package com.unicom.pay.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPFidoResultBean implements Parcelable {
    public static final Parcelable.Creator<WPFidoResultBean> CREATOR = new Parcelable.Creator<WPFidoResultBean>() { // from class: com.unicom.pay.common.bean.WPFidoResultBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPFidoResultBean createFromParcel(Parcel parcel) {
            return new WPFidoResultBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPFidoResultBean[] newArray(int i) {
            return new WPFidoResultBean[i];
        }
    };
    private String faceRegStatus;
    private String fingerRegStatus;
    private String statusCode;
    private String uafRequest;
    private String userId;

    public WPFidoResultBean() {
    }

    public WPFidoResultBean(Parcel parcel) {
        this.statusCode = parcel.readString();
        this.uafRequest = parcel.readString();
        this.userId = parcel.readString();
        this.faceRegStatus = parcel.readString();
        this.fingerRegStatus = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getFaceRegStatus() {
        return this.faceRegStatus;
    }

    public String getFingerRegStatus() {
        return this.fingerRegStatus;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public String getUafRequest() {
        return this.uafRequest;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setFaceRegStatus(String str) {
        this.faceRegStatus = str;
    }

    public void setFingerRegStatus(String str) {
        this.fingerRegStatus = str;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    public void setUafRequest(String str) {
        this.uafRequest = str;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.statusCode);
        parcel.writeString(this.uafRequest);
        parcel.writeString(this.userId);
        parcel.writeString(this.faceRegStatus);
        parcel.writeString(this.fingerRegStatus);
    }
}
