package com.sinovatech.unicom.separatemodule.baidumap.entity;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BaiDuLoacationEntity implements Serializable {
    private int adCode;
    private String address;
    private String city;
    private String distance;
    private String district;
    private boolean isCheck;
    private double latitude;
    private double longitude;
    private String name;
    private String province;
    private String streetName;
    private String streetNumber;
    private String town;

    public String getDistance() {
        return this.distance;
    }

    public void setDistance(String str) {
        this.distance = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
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

    public boolean isCheck() {
        return this.isCheck;
    }

    public void setCheck(boolean z) {
        this.isCheck = z;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String str) {
        this.district = str;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String str) {
        this.town = str;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String str) {
        this.streetName = str;
    }

    public String getStreetNumber() {
        return this.streetNumber;
    }

    public void setStreetNumber(String str) {
        this.streetNumber = str;
    }

    public int getAdCode() {
        return this.adCode;
    }

    public void setAdCode(int i) {
        this.adCode = i;
    }
}
