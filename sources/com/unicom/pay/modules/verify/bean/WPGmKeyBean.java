package com.unicom.pay.modules.verify.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPGmKeyBean implements Parcelable {
    public static final Parcelable.Creator<WPGmKeyBean> CREATOR = new Parcelable.Creator<WPGmKeyBean>() { // from class: com.unicom.pay.modules.verify.bean.WPGmKeyBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPGmKeyBean createFromParcel(Parcel parcel) {
            return new WPGmKeyBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPGmKeyBean[] newArray(int i) {
            return new WPGmKeyBean[i];
        }
    };
    private String cipherKey;
    private String keyboardToken;
    private String sm2key;

    public WPGmKeyBean() {
    }

    public WPGmKeyBean(Parcel parcel) {
        this.cipherKey = parcel.readString();
        this.keyboardToken = parcel.readString();
        this.sm2key = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCipherKey() {
        return this.cipherKey;
    }

    public String getKeyboardToken() {
        return this.keyboardToken;
    }

    public String getSm2RealKey() {
        if (TextUtils.isEmpty(this.sm2key)) {
            return "";
        }
        String substring = this.sm2key.substring(2);
        return substring.substring(0, 64) + "|" + substring.substring(64);
    }

    public String getSm2key() {
        return this.sm2key;
    }

    public void setCipherKey(String str) {
        this.cipherKey = str;
    }

    public void setKeyboardToken(String str) {
        this.keyboardToken = str;
    }

    public void setSm2key(String str) {
        this.sm2key = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.cipherKey);
        parcel.writeString(this.keyboardToken);
        parcel.writeString(this.sm2key);
    }
}
