package com.chinaunicon.jtwifilib.jtcommon.ping.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PingItemBean implements Parcelable {
    public static final Parcelable.Creator<PingItemBean> CREATOR = new Parcelable.Creator<PingItemBean>() { // from class: com.chinaunicon.jtwifilib.jtcommon.ping.bean.PingItemBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PingItemBean createFromParcel(Parcel parcel) {
            return new PingItemBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PingItemBean[] newArray(int i) {
            return new PingItemBean[i];
        }
    };
    private String bytes;

    /* renamed from: ip */
    private String f9709ip;
    private String seq;
    private String time;
    private String ttl;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getIp() {
        return this.f9709ip;
    }

    public void setIp(String str) {
        this.f9709ip = str;
    }

    public String getSeq() {
        return this.seq;
    }

    public void setSeq(String str) {
        this.seq = str;
    }

    public String getBytes() {
        return this.bytes;
    }

    public void setBytes(String str) {
        this.bytes = str;
    }

    public String getTtl() {
        return this.ttl;
    }

    public void setTtl(String str) {
        this.ttl = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f9709ip);
        parcel.writeString(this.seq);
        parcel.writeString(this.bytes);
        parcel.writeString(this.ttl);
        parcel.writeString(this.time);
    }

    public PingItemBean() {
    }

    protected PingItemBean(Parcel parcel) {
        this.f9709ip = parcel.readString();
        this.seq = parcel.readString();
        this.bytes = parcel.readString();
        this.ttl = parcel.readString();
        this.time = parcel.readString();
    }

    public String toString() {
        return "PingItemBean{ip='" + this.f9709ip + "', seq='" + this.seq + "', bytes='" + this.bytes + "', ttl='" + this.ttl + "', time='" + this.time + "'}";
    }
}
