package com.euicc.server.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class EUICCDeviceInfo implements Parcelable {
    public static final Parcelable.Creator<EUICCDeviceInfo> CREATOR = new Parcelable.Creator<EUICCDeviceInfo>() { // from class: com.euicc.server.model.EUICCDeviceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EUICCDeviceInfo createFromParcel(Parcel parcel) {
            return new EUICCDeviceInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EUICCDeviceInfo[] newArray(int i) {
            return new EUICCDeviceInfo[i];
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
    private List<EUICCInfo> mEUICCInfoList;
    private String mProductName;
    private int mResultCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public EUICCDeviceInfo() {
        this.mEUICCInfoList = null;
    }

    public EUICCDeviceInfo(Parcel parcel) {
        this.mEUICCInfoList = null;
        this.mResultCode = parcel.readInt();
        this.mDeviceType = parcel.readInt();
        this.mDeviceIMEI = parcel.readString();
        this.mDeviceSerialNumber = parcel.readString();
        this.mProductName = parcel.readString();
        this.mEID = parcel.readString();
        if (this.mEUICCInfoList == null) {
            this.mEUICCInfoList = new ArrayList();
        }
        parcel.readTypedList(this.mEUICCInfoList, EUICCInfo.CREATOR);
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

    public List<EUICCInfo> getSimInfoList() {
        return new ArrayList(this.mEUICCInfoList);
    }

    public void setSimInfoList(List<EUICCInfo> list) {
        this.mEUICCInfoList = list;
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

    public EUICCInfo getActiveSimProfileInfo() {
        List<EUICCInfo> list = this.mEUICCInfoList;
        if (list != null) {
            for (EUICCInfo eUICCInfo : list) {
                if (eUICCInfo.isActive()) {
                    try {
                        return eUICCInfo.m24468clone();
                    } catch (CloneNotSupportedException unused) {
                        return new EUICCInfo(eUICCInfo.getIMSI(), eUICCInfo.getICCID(), eUICCInfo.isActive());
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
        parcel.writeTypedList(this.mEUICCInfoList);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        List<EUICCInfo> list = this.mEUICCInfoList;
        if (list != null && !list.isEmpty()) {
            for (EUICCInfo eUICCInfo : this.mEUICCInfoList) {
                stringBuffer.append(eUICCInfo.toString());
            }
        }
        return "MultiSimDeviceInfo [mDeviceType=" + this.mDeviceType + ", mDeviceIdentifierm=" + this.mDeviceIMEI + ", mDeviceSerialNumber=" + this.mDeviceSerialNumber + ", mProductName=" + this.mProductName + ", mEID=" + this.mEID + ", mProfileInfoList=[" + stringBuffer.toString() + "] ]";
    }
}
