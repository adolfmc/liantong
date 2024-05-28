package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.List;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPFqInfoBean implements Parcelable {
    public static final Parcelable.Creator<WPFqInfoBean> CREATOR = new Parcelable.Creator<WPFqInfoBean>() { // from class: com.unicom.pay.normal.order.bean.WPFqInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPFqInfoBean createFromParcel(Parcel parcel) {
            return new WPFqInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPFqInfoBean[] newArray(int i) {
            return new WPFqInfoBean[i];
        }
    };
    public static final String JUMPURL = "JUMPURL";
    public static final String WFQ_HB = "WFQ_HB";
    public static final String WFQ_XYK = "WFQ_XYK";
    public static final String WFQ_XYK_PWD = "WFQ_XYK_PWD";
    public static final String WFQ_ZL = "WFQ_ZL";
    private boolean defaultChecked;
    private String expand;
    private List<WPFqNumInfoBean> fqNumInfos;
    private WPFqTitleBean fqTitle;
    private String iconUrl;
    private String jumpUrl;
    private String methodDesc;
    private String methodDiscount;
    private String methodName;
    private boolean showNext;
    private String type;

    public WPFqInfoBean() {
    }

    public WPFqInfoBean(Parcel parcel) {
        this.iconUrl = parcel.readString();
        this.methodName = parcel.readString();
        this.methodDesc = parcel.readString();
        this.methodDiscount = parcel.readString();
        this.defaultChecked = parcel.readByte() != 0;
        this.jumpUrl = parcel.readString();
        this.expand = parcel.readString();
        this.showNext = parcel.readByte() != 0;
        this.type = parcel.readString();
        this.fqTitle = (WPFqTitleBean) parcel.readParcelable(WPFqTitleBean.class.getClassLoader());
        this.fqNumInfos = parcel.createTypedArrayList(WPFqNumInfoBean.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getExpand() {
        return this.expand;
    }

    public List<WPFqNumInfoBean> getFqNumInfos() {
        return this.fqNumInfos;
    }

    public WPFqTitleBean getFqTitle() {
        return this.fqTitle;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public String getJumpUrl() {
        return this.jumpUrl;
    }

    public String getMethodDesc() {
        return this.methodDesc;
    }

    public String getMethodDiscount() {
        return this.methodDiscount;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public String getType() {
        return this.type;
    }

    public boolean isDefaultChecked() {
        return this.defaultChecked;
    }

    public boolean isJumpWeb() {
        return !TextUtils.isEmpty(this.type) && this.type.contains(JUMPURL);
    }

    public boolean isShowNext() {
        return this.showNext;
    }

    public void setDefaultChecked(boolean z) {
        this.defaultChecked = z;
    }

    public void setExpand(String str) {
        this.expand = str;
    }

    public void setFqNumInfos(List<WPFqNumInfoBean> list) {
        this.fqNumInfos = list;
    }

    public void setFqTitle(WPFqTitleBean wPFqTitleBean) {
        this.fqTitle = wPFqTitleBean;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }

    public void setJumpUrl(String str) {
        this.jumpUrl = str;
    }

    public void setMethodDesc(String str) {
        this.methodDesc = str;
    }

    public void setMethodDiscount(String str) {
        this.methodDiscount = str;
    }

    public void setMethodName(String str) {
        this.methodName = str;
    }

    public void setShowNext(boolean z) {
        this.showNext = z;
    }

    public void setType(String str) {
        this.type = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.iconUrl);
        parcel.writeString(this.methodName);
        parcel.writeString(this.methodDesc);
        parcel.writeString(this.methodDiscount);
        parcel.writeByte(this.defaultChecked ? (byte) 1 : (byte) 0);
        parcel.writeString(this.jumpUrl);
        parcel.writeString(this.expand);
        parcel.writeByte(this.showNext ? (byte) 1 : (byte) 0);
        parcel.writeString(this.type);
        parcel.writeParcelable(this.fqTitle, i);
        parcel.writeTypedList(this.fqNumInfos);
    }
}
