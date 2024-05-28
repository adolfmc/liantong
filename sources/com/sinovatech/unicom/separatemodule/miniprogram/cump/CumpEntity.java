package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import android.text.TextUtils;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;
import io.objectbox.annotation.Transient;

@Entity
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CumpEntity {
    private String appDesc;
    private String appHighImg;
    private String appId;
    private String appImg;
    private String appName;
    private String appSecret;
    private String appToken;
    private String createTime;
    private String desktopIcon;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18561id;
    private String latestUpdateTime;
    private String officialMd5;
    private String officialPackageUrl;
    private String officialVersion;
    private String plugCodeList;
    private String privacyUrl;
    private String realmUrlList;
    private String serverPublishType;
    @Transient
    private String serviceTelephone;
    private String timestamp;
    private long lastestCollPopupShowTime = 0;
    private long lastestBottomPopupShowTime = 0;
    private String publishMethod = "";
    private String homePageKey = "";
    private boolean isInnerMiniP = false;
    private String oldVersion = "";
    private String trialVersionNum = "";
    private String officialVersionNum = "";

    public String getPublishMethod() {
        return this.publishMethod;
    }

    public void setPublishMethod(String str) {
        this.publishMethod = str;
    }

    public String getHomePageKey() {
        return this.homePageKey;
    }

    public void setHomePageKey(String str) {
        this.homePageKey = str;
    }

    public long getId() {
        return this.f18561id;
    }

    public void setId(long j) {
        this.f18561id = j;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getAppImg() {
        return this.appImg;
    }

    public void setAppImg(String str) {
        this.appImg = str;
    }

    public String getAppHighImg() {
        return this.appHighImg;
    }

    public void setAppHighImg(String str) {
        this.appHighImg = str;
    }

    public String getAppSecret() {
        return this.appSecret;
    }

    public void setAppSecret(String str) {
        this.appSecret = str;
    }

    public String getAppToken() {
        return this.appToken;
    }

    public void setAppToken(String str) {
        this.appToken = str;
    }

    public String getAppDesc() {
        return this.appDesc;
    }

    public void setAppDesc(String str) {
        this.appDesc = str;
    }

    public String getOfficialVersion() {
        return this.officialVersion;
    }

    public void setOfficialVersion(String str) {
        this.officialVersion = str;
    }

    public String getOfficialPackageUrl() {
        return this.officialPackageUrl;
    }

    public void setOfficialPackageUrl(String str) {
        this.officialPackageUrl = str;
    }

    public String getOfficialMd5() {
        return this.officialMd5;
    }

    public void setOfficialMd5(String str) {
        this.officialMd5 = str;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getLatestUpdateTime() {
        return this.latestUpdateTime;
    }

    public void setLatestUpdateTime(String str) {
        this.latestUpdateTime = str;
    }

    public String getRealmUrlList() {
        return this.realmUrlList;
    }

    public void setRealmUrlList(String str) {
        this.realmUrlList = str;
    }

    public String getPlugCodeList() {
        return TextUtils.isEmpty(this.plugCodeList) ? "" : this.plugCodeList;
    }

    public void setPlugCodeList(String str) {
        this.plugCodeList = str;
    }

    public String getServerPublishType() {
        return this.serverPublishType;
    }

    public void setServerPublishType(String str) {
        this.serverPublishType = str;
    }

    public String getDesktopIcon() {
        return this.desktopIcon;
    }

    public void setDesktopIcon(String str) {
        this.desktopIcon = str;
    }

    public String getPrivacyUrl() {
        return this.privacyUrl;
    }

    public void setPrivacyUrl(String str) {
        this.privacyUrl = str;
    }

    public long getLastestCollPopupShowTime() {
        return this.lastestCollPopupShowTime;
    }

    public void setLastestCollPopupShowTime(long j) {
        this.lastestCollPopupShowTime = j;
    }

    public long getLastestBottomPopupShowTime() {
        return this.lastestBottomPopupShowTime;
    }

    public void setLastestBottomPopupShowTime(long j) {
        this.lastestBottomPopupShowTime = j;
    }

    public String getServiceTelephone() {
        return this.serviceTelephone;
    }

    public void setServiceTelephone(String str) {
        this.serviceTelephone = str;
    }

    public boolean isInnerMiniP() {
        return this.isInnerMiniP;
    }

    public void setInnerMiniP(boolean z) {
        this.isInnerMiniP = z;
    }

    public String getOldVersion() {
        return this.oldVersion;
    }

    public void setOldVersion(String str) {
        this.oldVersion = str;
    }

    public String getTrialVersionNum() {
        return this.trialVersionNum;
    }

    public void setTrialVersionNum(String str) {
        this.trialVersionNum = str;
    }

    public String getOfficialVersionNum() {
        return this.officialVersionNum;
    }

    public void setOfficialVersionNum(String str) {
        this.officialVersionNum = str;
    }
}
