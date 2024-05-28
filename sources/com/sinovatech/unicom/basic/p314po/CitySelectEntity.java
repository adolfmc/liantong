package com.sinovatech.unicom.basic.p314po;

import android.support.annotation.NonNull;

/* renamed from: com.sinovatech.unicom.basic.po.CitySelectEntity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CitySelectEntity implements Comparable<CitySelectEntity> {
    private String cityCode;
    private String cityName;
    private String cityRank;
    private double latitude;
    private double longitude;
    private String mapCode;
    private String pingyin;
    private String privienceCode;
    private String provienceName;
    private String sortLetters;

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String str) {
        this.cityCode = str;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public String getCityRank() {
        return this.cityRank;
    }

    public void setCityRank(String str) {
        this.cityRank = str;
    }

    public String getPingyin() {
        return this.pingyin;
    }

    public void setPingyin(String str) {
        this.pingyin = str;
    }

    public String getPrivienceCode() {
        return this.privienceCode;
    }

    public void setPrivienceCode(String str) {
        this.privienceCode = str;
    }

    public String getProvienceName() {
        return this.provienceName;
    }

    public void setProvienceName(String str) {
        this.provienceName = str;
    }

    public String getSortLetters() {
        return this.sortLetters;
    }

    public void setSortLetters(String str) {
        this.sortLetters = str;
    }

    public String getMapCode() {
        return this.mapCode;
    }

    public void setMapCode(String str) {
        this.mapCode = str;
    }

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

    @Override // java.lang.Comparable
    public int compareTo(@NonNull CitySelectEntity citySelectEntity) {
        return this.pingyin.compareTo(citySelectEntity.getPingyin());
    }
}
