package com.unicom.pay.normal.discount.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.unicom.pay.normal.order.bean.WPDzqPageBean;
import java.util.ArrayList;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPDiscountInfoBean implements Parcelable {
    public static final Parcelable.Creator<WPDiscountInfoBean> CREATOR = new Parcelable.Creator<WPDiscountInfoBean>() { // from class: com.unicom.pay.normal.discount.bean.WPDiscountInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPDiscountInfoBean createFromParcel(Parcel parcel) {
            return new WPDiscountInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPDiscountInfoBean[] newArray(int i) {
            return new WPDiscountInfoBean[i];
        }
    };
    public static final String DZQ = "DZQ";
    public static final String PAYTOOLSYX = "PAYTOOLSYX";
    public static final String TRENDS_EVENT_CHOOSE = "去选择";
    public static final String TYYX = "TYYX";
    private String checked;
    private boolean discountAmount;
    private String discountButtonInfo;
    private String discountDesc;
    private ArrayList<WPDiscountDetailBean> discountDetails;
    private String discountName;
    private String discountShowInfo;
    private String discountType;
    private String dzqDiscountExpand;
    private WPDzqPageBean dzqPage;
    private String eventIconType;
    private String iconUrl;
    private String isEnable;

    public WPDiscountInfoBean() {
    }

    public WPDiscountInfoBean(Parcel parcel) {
        this.iconUrl = parcel.readString();
        this.discountName = parcel.readString();
        this.discountDesc = parcel.readString();
        this.checked = parcel.readString();
        this.discountType = parcel.readString();
        this.discountAmount = parcel.readByte() != 0;
        this.eventIconType = parcel.readString();
        this.discountDetails = parcel.createTypedArrayList(WPDiscountDetailBean.CREATOR);
        this.isEnable = parcel.readString();
        this.discountShowInfo = parcel.readString();
        this.discountButtonInfo = parcel.readString();
        this.dzqDiscountExpand = parcel.readString();
        this.dzqPage = (WPDzqPageBean) parcel.readParcelable(WPDzqPageBean.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getChecked() {
        return this.checked;
    }

    public String getDiscountButtonInfo() {
        return this.discountButtonInfo;
    }

    public String getDiscountDesc() {
        return this.discountDesc;
    }

    public ArrayList<WPDiscountDetailBean> getDiscountDetails() {
        return this.discountDetails;
    }

    public String getDiscountName() {
        return this.discountName;
    }

    public String getDiscountShowInfo() {
        return this.discountShowInfo;
    }

    public String getDiscountType() {
        return this.discountType;
    }

    public String getDzqDiscountExpand() {
        return this.dzqDiscountExpand;
    }

    public WPDzqPageBean getDzqPage() {
        return this.dzqPage;
    }

    public String getEventIconType() {
        return this.eventIconType;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public String getIsEnable() {
        return this.isEnable;
    }

    public boolean isChecked() {
        return "1".equals(this.checked);
    }

    public boolean isDiscountAmount() {
        return this.discountAmount;
    }

    public void setChecked(String str) {
        this.checked = str;
    }

    public void setDiscountAmount(boolean z) {
        this.discountAmount = z;
    }

    public void setDiscountButtonInfo(String str) {
        this.discountButtonInfo = str;
    }

    public void setDiscountDesc(String str) {
        this.discountDesc = str;
    }

    public void setDiscountDetails(ArrayList<WPDiscountDetailBean> arrayList) {
        this.discountDetails = arrayList;
    }

    public void setDiscountName(String str) {
        this.discountName = str;
    }

    public void setDiscountShowInfo(String str) {
        this.discountShowInfo = str;
    }

    public void setDiscountType(String str) {
        this.discountType = str;
    }

    public void setDzqDiscountExpand(String str) {
        this.dzqDiscountExpand = str;
    }

    public void setDzqPage(WPDzqPageBean wPDzqPageBean) {
        this.dzqPage = wPDzqPageBean;
    }

    public void setEventIconType(String str) {
        this.eventIconType = str;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }

    public void setIsEnable(String str) {
        this.isEnable = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.iconUrl);
        parcel.writeString(this.discountName);
        parcel.writeString(this.discountDesc);
        parcel.writeString(this.checked);
        parcel.writeString(this.discountType);
        parcel.writeByte(this.discountAmount ? (byte) 1 : (byte) 0);
        parcel.writeString(this.eventIconType);
        parcel.writeTypedList(this.discountDetails);
        parcel.writeString(this.isEnable);
        parcel.writeString(this.discountShowInfo);
        parcel.writeString(this.discountButtonInfo);
        parcel.writeString(this.dzqDiscountExpand);
        parcel.writeParcelable(this.dzqPage, i);
    }
}
