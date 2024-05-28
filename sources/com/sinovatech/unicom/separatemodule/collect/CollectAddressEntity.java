package com.sinovatech.unicom.separatemodule.collect;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CollectAddressEntity {
    private String cityName;
    private double latitude;
    private String locateCityCode;
    private String locateProvinceCode;
    private double longitude;

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public String getLocateProvinceCode() {
        return this.locateProvinceCode;
    }

    public void setLocateProvinceCode(String str) {
        this.locateProvinceCode = str;
    }

    public String getLocateCityCode() {
        return this.locateCityCode;
    }

    public void setLocateCityCode(String str) {
        this.locateCityCode = str;
    }
}
