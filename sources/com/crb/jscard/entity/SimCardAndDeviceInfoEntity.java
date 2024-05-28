package com.crb.jscard.entity;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SimCardAndDeviceInfoEntity {
    private String device_model;
    private String device_vendor;
    private boolean hasNfc;
    private String iccid;
    private boolean isEnableNfc;
    private String phoneNumber;

    public String getDevice_model() {
        return this.device_model;
    }

    public String getDevice_vendor() {
        return this.device_vendor;
    }

    public String getIccid() {
        return this.iccid;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public boolean isEnableNfc() {
        return this.isEnableNfc;
    }

    public boolean isHasNfc() {
        return this.hasNfc;
    }

    public void setDevice_model(String str) {
        this.device_model = str;
    }

    public void setDevice_vendor(String str) {
        this.device_vendor = str;
    }

    public void setEnableNfc(boolean z) {
        this.isEnableNfc = z;
    }

    public void setHasNfc(boolean z) {
        this.hasNfc = z;
    }

    public void setIccid(String str) {
        this.iccid = str;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }
}
