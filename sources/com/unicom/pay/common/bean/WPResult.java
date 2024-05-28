package com.unicom.pay.common.bean;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPResult<T> implements Parcelable {
    public static final Parcelable.Creator<WPResult> CREATOR = new Parcelable.Creator<WPResult>() { // from class: com.unicom.pay.common.bean.WPResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPResult createFromParcel(Parcel parcel) {
            return new WPResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPResult[] newArray(int i) {
            return new WPResult[i];
        }
    };
    private static String DATA_KEY = "dataKey";
    private String code;
    private WPCommonRespBean commonResp;
    private T data;
    private String msg;
    private String msgDetail;
    private String systemNo;

    public WPResult() {
    }

    public WPResult(Parcel parcel) {
        this.code = parcel.readString();
        this.commonResp = (WPCommonRespBean) parcel.readParcelable(WPCommonRespBean.class.getClassLoader());
        this.msg = parcel.readString();
        this.msgDetail = parcel.readString();
        this.systemNo = parcel.readString();
        this.data = (T) parcel.readBundle(getClass().getClassLoader()).getParcelable(DATA_KEY);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCode() {
        return this.code;
    }

    public WPCommonRespBean getCommonResp() {
        return this.commonResp;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getMsgDetail() {
        return this.msgDetail;
    }

    public String getSystemNo() {
        return this.systemNo;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setCommonResp(WPCommonRespBean wPCommonRespBean) {
        this.commonResp = wPCommonRespBean;
    }

    public void setData(T t) {
        this.data = t;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setMsgDetail(String str) {
        this.msgDetail = str;
    }

    public void setSystemNo(String str) {
        this.systemNo = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.code);
        parcel.writeParcelable(this.commonResp, i);
        parcel.writeString(this.msg);
        parcel.writeString(this.msgDetail);
        parcel.writeString(this.systemNo);
        if (this.data instanceof Parcelable) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(DATA_KEY, (Parcelable) this.data);
            parcel.writeBundle(bundle);
        }
    }
}
