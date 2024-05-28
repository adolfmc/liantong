package com.sinovatech.unicom.separatemodule.huotijiance;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HuoTiEntity implements Parcelable {
    public static final Parcelable.Creator<HuoTiEntity> CREATOR = new Parcelable.Creator<HuoTiEntity>() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.HuoTiEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HuoTiEntity createFromParcel(Parcel parcel) {
            return new HuoTiEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HuoTiEntity[] newArray(int i) {
            return new HuoTiEntity[i];
        }
    };
    private String formData;
    private String frame;
    private String hintText;
    private boolean isPortrait;
    private boolean isUpLoad;
    private String maxDuration;
    private String minDuration;
    private String name;

    /* renamed from: no */
    private String f18539no;
    private String province;
    private String readNumber;
    private String token;
    private String url;
    private String water;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getWater() {
        return this.water;
    }

    public void setWater(String str) {
        this.water = str;
    }

    public String getReadNumber() {
        return this.readNumber;
    }

    public void setReadNumber(String str) {
        this.readNumber = str;
    }

    public String getMinDuration() {
        return this.minDuration;
    }

    public void setMinDuration(String str) {
        this.minDuration = str;
    }

    public String getMaxDuration() {
        return this.maxDuration;
    }

    public void setMaxDuration(String str) {
        this.maxDuration = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getNo() {
        return this.f18539no;
    }

    public void setNo(String str) {
        this.f18539no = str;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String getFormData() {
        return this.formData;
    }

    public void setFormData(String str) {
        this.formData = str;
    }

    public String getFrame() {
        return this.frame;
    }

    public void setFrame(String str) {
        this.frame = str;
    }

    public boolean isUpLoad() {
        return this.isUpLoad;
    }

    public void setUpLoad(boolean z) {
        this.isUpLoad = z;
    }

    public boolean isPortrait() {
        return this.isPortrait;
    }

    public void setPortrait(boolean z) {
        this.isPortrait = z;
    }

    public String getHintText() {
        return this.hintText;
    }

    public void setHintText(String str) {
        this.hintText = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.readNumber);
        parcel.writeString(this.minDuration);
        parcel.writeString(this.maxDuration);
        parcel.writeString(this.name);
        parcel.writeString(this.url);
        parcel.writeString(this.token);
        parcel.writeString(this.f18539no);
        parcel.writeString(this.province);
        parcel.writeString(this.formData);
        parcel.writeString(this.frame);
        parcel.writeString(this.water);
        parcel.writeByte(this.isUpLoad ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isPortrait ? (byte) 1 : (byte) 0);
        parcel.writeString(this.hintText);
    }

    public void readFromParcel(Parcel parcel) {
        this.readNumber = parcel.readString();
        this.minDuration = parcel.readString();
        this.maxDuration = parcel.readString();
        this.name = parcel.readString();
        this.url = parcel.readString();
        this.token = parcel.readString();
        this.f18539no = parcel.readString();
        this.province = parcel.readString();
        this.formData = parcel.readString();
        this.frame = parcel.readString();
        this.water = parcel.readString();
        this.isUpLoad = parcel.readByte() != 0;
        this.isPortrait = parcel.readByte() != 0;
        this.hintText = parcel.readString();
    }

    public HuoTiEntity() {
    }

    protected HuoTiEntity(Parcel parcel) {
        this.readNumber = parcel.readString();
        this.minDuration = parcel.readString();
        this.maxDuration = parcel.readString();
        this.name = parcel.readString();
        this.url = parcel.readString();
        this.token = parcel.readString();
        this.f18539no = parcel.readString();
        this.province = parcel.readString();
        this.formData = parcel.readString();
        this.frame = parcel.readString();
        this.water = parcel.readString();
        this.isUpLoad = parcel.readByte() != 0;
        this.isPortrait = parcel.readByte() != 0;
        this.hintText = parcel.readString();
    }
}
