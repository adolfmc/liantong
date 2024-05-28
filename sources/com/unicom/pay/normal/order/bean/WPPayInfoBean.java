package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPPayInfoBean implements Parcelable {
    public static final String ACTIVITY = "ACTIVITY";

    /* renamed from: BK */
    public static final String f20221BK = "BK";
    public static final String BKYH = "BKYH";
    public static final Parcelable.Creator<WPPayInfoBean> CREATOR = new Parcelable.Creator<WPPayInfoBean>() { // from class: com.unicom.pay.normal.order.bean.WPPayInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPPayInfoBean createFromParcel(Parcel parcel) {
            return new WPPayInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPPayInfoBean[] newArray(int i) {
            return new WPPayInfoBean[i];
        }
    };
    public static final String DZQ = "DZQ";
    public static final String EVENT_TYPE_CHECK = "check";
    public static final String EVENT_TYPE_H5 = "h5";
    public static final String EVENT_TYPE_OPEN = "open";

    /* renamed from: KJ */
    public static final String f20222KJ = "KJ";
    public static final String MORE = "MORE";
    public static final String QPAY = "QPAY";

    /* renamed from: SB */
    public static final String f20223SB = "SB";

    /* renamed from: TY */
    public static final String f20224TY = "TY";
    public static final String WAPPAY = "WAPPAY";
    public static final String WAP_PAY = "WAP_PAY";
    public static final String WFQ = "WFQ";

    /* renamed from: WX */
    public static final String f20225WX = "WX";

    /* renamed from: YB */
    public static final String f20226YB = "YB";

    /* renamed from: YE */
    public static final String f20227YE = "YE";

    /* renamed from: YL */
    public static final String f20228YL = "YL";
    public static final String ZFB = "ZFB";

    /* renamed from: ZL */
    public static final String f20229ZL = "ZL";
    private String amount;
    private String checked;
    private String eventIconType;
    private String eventType;
    private WPToolFqInfoBean fqInfo;
    private String h5Url;
    private String iconUrl;
    private String openType;
    private String recommendMsg;
    private String secondIconUrl;
    private String toolCode;
    private String toolDesc;
    private String toolDiscountIconState;
    private String toolDiscountIconType;
    private String toolDiscountMsg;
    private String toolDiscountNext;
    private String toolExpand;
    private String toolGroup;
    private String toolId;
    private String toolName;

    public WPPayInfoBean() {
    }

    public WPPayInfoBean(Parcel parcel) {
        this.iconUrl = parcel.readString();
        this.toolCode = parcel.readString();
        this.toolId = parcel.readString();
        this.toolGroup = parcel.readString();
        this.toolName = parcel.readString();
        this.toolDesc = parcel.readString();
        this.secondIconUrl = parcel.readString();
        this.recommendMsg = parcel.readString();
        this.toolDiscountMsg = parcel.readString();
        this.toolDiscountIconType = parcel.readString();
        this.toolDiscountNext = parcel.readString();
        this.toolDiscountIconState = parcel.readString();
        this.eventIconType = parcel.readString();
        this.eventType = parcel.readString();
        this.openType = parcel.readString();
        this.h5Url = parcel.readString();
        this.checked = parcel.readString();
        this.amount = parcel.readString();
        this.toolExpand = parcel.readString();
        this.fqInfo = (WPToolFqInfoBean) parcel.readParcelable(WPToolFqInfoBean.class.getClassLoader());
    }

    public void copy(WPPayInfoBean wPPayInfoBean) {
        setIconUrl(wPPayInfoBean.getIconUrl());
        setToolCode(wPPayInfoBean.getToolCode());
        setToolId(wPPayInfoBean.getToolId());
        setToolGroup(wPPayInfoBean.getToolGroup());
        setToolName(wPPayInfoBean.getToolName());
        setToolDesc(wPPayInfoBean.getToolDesc());
        setSecondIconUrl(wPPayInfoBean.getSecondIconUrl());
        setRecommendMsg(wPPayInfoBean.getRecommendMsg());
        setToolDiscountMsg(wPPayInfoBean.getToolDiscountMsg());
        setToolDiscountIconType(wPPayInfoBean.getToolDiscountIconType());
        setToolDiscountIconState(wPPayInfoBean.getToolDiscountIconState());
        setToolDiscountNext(wPPayInfoBean.getToolDiscountNext());
        setEventIconType(wPPayInfoBean.getEventIconType());
        setEventType(wPPayInfoBean.getEventType());
        setOpenType(wPPayInfoBean.getOpenType());
        setH5Url(wPPayInfoBean.getH5Url());
        setAmount(wPPayInfoBean.getAmount());
        setToolExpand(wPPayInfoBean.getToolExpand());
        setFqInfo(wPPayInfoBean.getFqInfo());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getChecked() {
        return this.checked;
    }

    public String getEventIconType() {
        return this.eventIconType;
    }

    public String getEventType() {
        return this.eventType;
    }

    public WPToolFqInfoBean getFqInfo() {
        return this.fqInfo;
    }

    public String getH5Url() {
        return this.h5Url;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public String getOpenType() {
        return this.openType;
    }

    public String getRecommendMsg() {
        return this.recommendMsg;
    }

    public String getSecondIconUrl() {
        return this.secondIconUrl;
    }

    public String getToolCode() {
        return this.toolCode;
    }

    public String getToolDesc() {
        return this.toolDesc;
    }

    public String getToolDiscountIconState() {
        return this.toolDiscountIconState;
    }

    public String getToolDiscountIconType() {
        return this.toolDiscountIconType;
    }

    public String getToolDiscountMsg() {
        return this.toolDiscountMsg;
    }

    public String getToolDiscountNext() {
        return this.toolDiscountNext;
    }

    public String getToolExpand() {
        return this.toolExpand;
    }

    public String getToolGroup() {
        return this.toolGroup;
    }

    public String getToolId() {
        return this.toolId;
    }

    public String getToolName() {
        return this.toolName;
    }

    public boolean isChecked() {
        return "1".equals(this.checked);
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public void setChecked(String str) {
        this.checked = str;
    }

    public void setEventIconType(String str) {
        this.eventIconType = str;
    }

    public void setEventType(String str) {
        this.eventType = str;
    }

    public void setFqInfo(WPToolFqInfoBean wPToolFqInfoBean) {
        this.fqInfo = wPToolFqInfoBean;
    }

    public void setH5Url(String str) {
        this.h5Url = str;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }

    public void setOpenType(String str) {
        this.openType = str;
    }

    public void setRecommendMsg(String str) {
        this.recommendMsg = str;
    }

    public void setSecondIconUrl(String str) {
        this.secondIconUrl = str;
    }

    public void setToolCode(String str) {
        this.toolCode = str;
    }

    public void setToolDesc(String str) {
        this.toolDesc = str;
    }

    public void setToolDiscountIconState(String str) {
        this.toolDiscountIconState = str;
    }

    public void setToolDiscountIconType(String str) {
        this.toolDiscountIconType = str;
    }

    public void setToolDiscountMsg(String str) {
        this.toolDiscountMsg = str;
    }

    public void setToolDiscountNext(String str) {
        this.toolDiscountNext = str;
    }

    public void setToolExpand(String str) {
        this.toolExpand = str;
    }

    public void setToolGroup(String str) {
        this.toolGroup = str;
    }

    public void setToolId(String str) {
        this.toolId = str;
    }

    public void setToolName(String str) {
        this.toolName = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.iconUrl);
        parcel.writeString(this.toolCode);
        parcel.writeString(this.toolId);
        parcel.writeString(this.toolGroup);
        parcel.writeString(this.toolName);
        parcel.writeString(this.toolDesc);
        parcel.writeString(this.secondIconUrl);
        parcel.writeString(this.recommendMsg);
        parcel.writeString(this.toolDiscountMsg);
        parcel.writeString(this.toolDiscountIconType);
        parcel.writeString(this.toolDiscountNext);
        parcel.writeString(this.toolDiscountIconState);
        parcel.writeString(this.eventIconType);
        parcel.writeString(this.eventType);
        parcel.writeString(this.openType);
        parcel.writeString(this.h5Url);
        parcel.writeString(this.checked);
        parcel.writeString(this.amount);
        parcel.writeString(this.toolExpand);
        parcel.writeParcelable(this.fqInfo, i);
    }
}
