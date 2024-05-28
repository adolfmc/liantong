package com.huawei.multisimservice.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MultiSimDeviceInfo implements Parcelable {
    public static final Parcelable.Creator<MultiSimDeviceInfo> CREATOR = new Parcelable.Creator<MultiSimDeviceInfo>() { // from class: com.huawei.multisimservice.model.MultiSimDeviceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MultiSimDeviceInfo createFromParcel(Parcel parcel) {
            return new MultiSimDeviceInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MultiSimDeviceInfo[] newArray(int i) {
            return new MultiSimDeviceInfo[i];
        }
    };
    public static final int DEVICE_TYPE_ESIM = 2;
    public static final int DEVICE_TYPE_INVALID = 0;
    public static final int DEVICE_TYPE_NO_MODEM = 3;
    public static final int DEVICE_TYPE_SIM = 1;
    public static final int RESULT_CODE_NOT_CONNECT = -2;
    public static final int RESULT_CODE_NOT_SUPPORT = -3;
    public static final int RESULT_CODE_SUCCESS = 1;
    public static final int RESULT_CODE_UNKNOW = 0;
    public static final int RESULT_CODE_USER_REJECT = -1;
    private String mDeviceIMEI;
    private String mDeviceSerialNumber;
    private int mDeviceType;
    private String mEID;
    private String mProductName;
    private int mResultCode;
    private List<SimInfo> mSimInfoList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MultiSimDeviceInfo() {
        this.mSimInfoList = null;
    }

    public MultiSimDeviceInfo(Parcel parcel) {
        this.mSimInfoList = null;
        this.mResultCode = parcel.readInt();
        this.mDeviceType = parcel.readInt();
        this.mDeviceIMEI = parcel.readString();
        this.mDeviceSerialNumber = parcel.readString();
        this.mProductName = parcel.readString();
        this.mEID = parcel.readString();
        if (this.mSimInfoList == null) {
            this.mSimInfoList = new ArrayList();
        }
        parcel.readTypedList(this.mSimInfoList, SimInfo.CREATOR);
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public void setResultCode(int i) {
        this.mResultCode = i;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    public void setDeviceType(int i) {
        this.mDeviceType = i;
    }

    public String getDeviceIMEI() {
        return this.mDeviceIMEI;
    }

    public void setDeviceIMEI(String str) {
        this.mDeviceIMEI = str;
    }

    public String getDeviceSerialNumber() {
        return this.mDeviceSerialNumber;
    }

    public void setDeviceSerialNumber(String str) {
        this.mDeviceSerialNumber = str;
    }

    public String getProductName() {
        return this.mProductName;
    }

    public void setProductName(String str) {
        this.mProductName = str;
    }

    public String getEID() {
        return this.mEID;
    }

    public void setEID(String str) {
        this.mEID = str;
    }

    public List<SimInfo> getSimInfoList() {
        return new ArrayList(this.mSimInfoList);
    }

    public void setSimInfoList(List<SimInfo> list) {
        this.mSimInfoList = list;
    }

    public String getDeviceID() {
        int i = this.mDeviceType;
        if (i == 2 || i == 1) {
            return this.mDeviceIMEI;
        }
        if (i == 3) {
            return this.mDeviceSerialNumber;
        }
        return null;
    }

    public SimInfo getActiveSimProfileInfo() {
        List<SimInfo> list = this.mSimInfoList;
        if (list != null) {
            for (SimInfo simInfo : list) {
                if (simInfo.isActive()) {
                    try {
                        return simInfo.m24476clone();
                    } catch (CloneNotSupportedException unused) {
                        return new SimInfo(simInfo.getIMSI(), simInfo.getICCID(), simInfo.isActive());
                    }
                }
            }
        }
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mResultCode);
        parcel.writeInt(this.mDeviceType);
        parcel.writeString(this.mDeviceIMEI);
        parcel.writeString(this.mDeviceSerialNumber);
        parcel.writeString(this.mProductName);
        parcel.writeString(this.mEID);
        parcel.writeTypedList(this.mSimInfoList);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        List<SimInfo> list = this.mSimInfoList;
        if (list != null && !list.isEmpty()) {
            for (SimInfo simInfo : this.mSimInfoList) {
                stringBuffer.append(simInfo.toString());
            }
        }
        return "MultiSimDeviceInfo [mDeviceType=" + this.mDeviceType + ", mDeviceIdentifierm=" + this.mDeviceIMEI + ", mDeviceSerialNumber=" + this.mDeviceSerialNumber + ", mProductName=" + this.mProductName + ", mEID=" + this.mEID + ", mProfileInfoList=[" + stringBuffer.toString() + "] ]";
    }
}
