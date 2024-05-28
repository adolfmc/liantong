package com.sinovatech.unicom.separatemodule.recentmenu.entity;

import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;
import org.json.JSONObject;

@Entity
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RecentMiniEntity {
    private String appDesc;
    private String appId;
    private String appImg;
    private String appName;
    private String appletUrl;
    private String createTime;
    private String dateString;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18601id;
    private boolean isBianJi;
    private boolean isH5;
    private boolean isSelect;
    private String mobile;
    private String productId;
    private String state;
    private long timeTemp;
    private int type;

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getAppImg() {
        return this.appImg;
    }

    public void setAppImg(String str) {
        this.appImg = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getAppletUrl() {
        return this.appletUrl;
    }

    public void setAppletUrl(String str) {
        try {
            str = str.replace("http://edop_unicom/?", "http://edop_unicom?").replace("https://edop_unicom/?", "https://edop_unicom?");
        } catch (Exception e) {
            MsLogUtil.m7978e("替换小程序地址异常:" + e.getMessage());
        }
        this.appletUrl = str;
    }

    public boolean isH5() {
        return this.isH5;
    }

    public void setH5(boolean z) {
        this.isH5 = z;
    }

    public long getId() {
        return this.f18601id;
    }

    public void setId(long j) {
        this.f18601id = j;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public long getTimeTemp() {
        return this.timeTemp;
    }

    public void setTimeTemp(long j) {
        this.timeTemp = j;
    }

    public String getDateString() {
        return this.dateString;
    }

    public void setDateString(String str) {
        this.dateString = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getAppDesc() {
        return this.appDesc;
    }

    public void setAppDesc(String str) {
        this.appDesc = str;
    }

    public boolean isBianJi() {
        return this.isBianJi;
    }

    public void setBianJi(boolean z) {
        this.isBianJi = z;
    }

    public static RecentMiniEntity getInstance(JSONObject jSONObject) {
        RecentMiniEntity recentMiniEntity = new RecentMiniEntity();
        if (jSONObject != null) {
            recentMiniEntity.setAppId(jSONObject.optString("appId"));
            recentMiniEntity.setAppImg(jSONObject.optString("appImg"));
            recentMiniEntity.setAppName(jSONObject.optString("appName"));
            recentMiniEntity.setAppletUrl(jSONObject.optString("appletUrl"));
            recentMiniEntity.setProductId(jSONObject.optString("productId"));
            recentMiniEntity.setCreateTime(jSONObject.optString("createTime"));
            recentMiniEntity.setState(jSONObject.optString("state"));
            recentMiniEntity.setAppDesc(jSONObject.optString("appDesc"));
        }
        return recentMiniEntity;
    }

    public String toString() {
        return "MiniEntity{id=" + this.f18601id + ", appId='" + this.appId + "', appImg='" + this.appImg + "', appName='" + this.appName + "', appletUrl='" + this.appletUrl + "', isH5=" + this.isH5 + ", mobile='" + this.mobile + "', timeTemp=" + this.timeTemp + ", type=" + this.type + ", dateString='" + this.dateString + "', productId='" + this.productId + "', createTime='" + this.createTime + "', state='" + this.state + "', appDesc='" + this.appDesc + "'}";
    }
}
