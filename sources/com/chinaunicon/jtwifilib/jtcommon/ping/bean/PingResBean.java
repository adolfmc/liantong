package com.chinaunicon.jtwifilib.jtcommon.ping.bean;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

@SuppressLint({"ParcelCreator"})
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PingResBean implements Parcelable {
    private String avgTime;

    /* renamed from: ip */
    private String f9710ip;
    private boolean isIP;
    private List<PingItemBean> items;
    private String lossPacket;
    private String maxTime;
    private String mdev;
    private String minTime;
    private String pingType;
    private String receivedNum;
    private String ssid;
    private String time;
    private String transmittedNum;
    private String url;
    private String uuid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public String getSsid() {
        return this.ssid;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }

    public String getIp() {
        return this.f9710ip;
    }

    public void setIp(String str) {
        this.f9710ip = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getPingType() {
        return this.pingType;
    }

    public void setPingType(String str) {
        this.pingType = str;
    }

    public boolean isIP() {
        return this.isIP;
    }

    public void setIP(boolean z) {
        this.isIP = z;
    }

    public List<PingItemBean> getItems() {
        return this.items;
    }

    public void setItems(List<PingItemBean> list) {
        this.items = list;
    }

    public String getTransmittedNum() {
        return this.transmittedNum;
    }

    public void setTransmittedNum(String str) {
        this.transmittedNum = str;
    }

    public String getReceivedNum() {
        return this.receivedNum;
    }

    public void setReceivedNum(String str) {
        this.receivedNum = str;
    }

    public String getLossPacket() {
        return this.lossPacket;
    }

    public void setLossPacket(String str) {
        this.lossPacket = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getMinTime() {
        return this.minTime;
    }

    public void setMinTime(String str) {
        this.minTime = str;
    }

    public String getAvgTime() {
        return this.avgTime;
    }

    public void setAvgTime(String str) {
        this.avgTime = str;
    }

    public String getMaxTime() {
        return this.maxTime;
    }

    public void setMaxTime(String str) {
        this.maxTime = str;
    }

    public String getMdev() {
        return this.mdev;
    }

    public void setMdev(String str) {
        this.mdev = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.uuid);
        parcel.writeString(this.ssid);
        parcel.writeString(this.f9710ip);
        parcel.writeString(this.url);
        parcel.writeString(this.pingType);
        parcel.writeByte(this.isIP ? (byte) 1 : (byte) 0);
        parcel.writeTypedList(this.items);
        parcel.writeString(this.transmittedNum);
        parcel.writeString(this.receivedNum);
        parcel.writeString(this.lossPacket);
        parcel.writeString(this.time);
        parcel.writeString(this.minTime);
        parcel.writeString(this.avgTime);
        parcel.writeString(this.maxTime);
        parcel.writeString(this.mdev);
    }

    public PingResBean() {
    }

    protected PingResBean(Parcel parcel) {
        this.uuid = parcel.readString();
        this.ssid = parcel.readString();
        this.f9710ip = parcel.readString();
        this.url = parcel.readString();
        this.pingType = parcel.readString();
        this.isIP = parcel.readByte() != 0;
        this.items = parcel.createTypedArrayList(PingItemBean.CREATOR);
        this.transmittedNum = parcel.readString();
        this.receivedNum = parcel.readString();
        this.lossPacket = parcel.readString();
        this.time = parcel.readString();
        this.minTime = parcel.readString();
        this.avgTime = parcel.readString();
        this.maxTime = parcel.readString();
        this.mdev = parcel.readString();
    }

    public String toString() {
        return "PingResBean{uuid='" + this.uuid + "', ssid='" + this.ssid + "', ip='" + this.f9710ip + "', url='" + this.url + "', pingType='" + this.pingType + "', isIP=" + this.isIP + ", items=" + this.items + ", transmittedNum='" + this.transmittedNum + "', receivedNum='" + this.receivedNum + "', lossPacket='" + this.lossPacket + "', time='" + this.time + "', minTime='" + this.minTime + "', avgTime='" + this.avgTime + "', maxTime='" + this.maxTime + "', mdev='" + this.mdev + "'}";
    }
}
