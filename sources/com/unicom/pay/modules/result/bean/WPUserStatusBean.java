package com.unicom.pay.modules.result.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPUserStatusBean implements Parcelable {
    public static final Parcelable.Creator<WPUserStatusBean> CREATOR = new Parcelable.Creator<WPUserStatusBean>() { // from class: com.unicom.pay.modules.result.bean.WPUserStatusBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPUserStatusBean createFromParcel(Parcel parcel) {
            return new WPUserStatusBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPUserStatusBean[] newArray(int i) {
            return new WPUserStatusBean[i];
        }
    };
    public static final String STAT_BANK_CARD = "1";
    public static final String STAT_OPEN = "0";
    public static final String STAT_PWD = "2";
    private String leftNextStepDesc;
    private String nextStepUrl;
    private String phoneNo;
    private String promptMsg;
    private String promptTitle;
    private String rightNextStepDesc;
    private String userState;
    private String userStateMsg;

    public WPUserStatusBean(Parcel parcel) {
        this.userState = parcel.readString();
        this.userStateMsg = parcel.readString();
        this.promptTitle = parcel.readString();
        this.promptMsg = parcel.readString();
        this.nextStepUrl = parcel.readString();
        this.leftNextStepDesc = parcel.readString();
        this.rightNextStepDesc = parcel.readString();
        this.phoneNo = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getLeftNextStepDesc() {
        return this.leftNextStepDesc;
    }

    public String getNextStepUrl() {
        return this.nextStepUrl;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public String getPromptMsg() {
        return this.promptMsg;
    }

    public String getPromptTitle() {
        return this.promptTitle;
    }

    public String getRightNextStepDesc() {
        return this.rightNextStepDesc;
    }

    public String getUserState() {
        return this.userState;
    }

    public String getUserStateMsg() {
        return this.userStateMsg;
    }

    public void setLeftNextStepDesc(String str) {
        this.leftNextStepDesc = str;
    }

    public void setNextStepUrl(String str) {
        this.nextStepUrl = str;
    }

    public void setPhoneNo(String str) {
        this.phoneNo = str;
    }

    public void setPromptMsg(String str) {
        this.promptMsg = str;
    }

    public void setPromptTitle(String str) {
        this.promptTitle = str;
    }

    public void setRightNextStepDesc(String str) {
        this.rightNextStepDesc = str;
    }

    public void setUserState(String str) {
        this.userState = str;
    }

    public void setUserStateMsg(String str) {
        this.userStateMsg = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.userState);
        parcel.writeString(this.userStateMsg);
        parcel.writeString(this.promptTitle);
        parcel.writeString(this.promptMsg);
        parcel.writeString(this.nextStepUrl);
        parcel.writeString(this.leftNextStepDesc);
        parcel.writeString(this.rightNextStepDesc);
        parcel.writeString(this.phoneNo);
    }
}
