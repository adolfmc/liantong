package com.sinovatech.unicom.separatemodule.notice.utils;

import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ReqBody {
    private String beginPushTime;
    private String clientType;
    private String clientVersion;
    private String deviceId;
    private String endPushTime;
    private Object extNode;
    private List<Message> messages;
    private String phoneModel;
    private String platformToken;
    private String pushPlatform;
    private String pushStatus;
    private UserInfo userInfo;

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public Object getExtNode() {
        return this.extNode;
    }

    public void setExtNode(Object obj) {
        this.extNode = obj;
    }

    public String toString() {
        return this.extNode + "#" + this.deviceId;
    }

    public String getClientType() {
        return this.clientType;
    }

    public void setClientType(String str) {
        this.clientType = str;
    }

    public String getClientVersion() {
        return this.clientVersion;
    }

    public void setClientVersion(String str) {
        this.clientVersion = str;
    }

    public UserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(List<Message> list) {
        this.messages = list;
    }

    public String getPushStatus() {
        return this.pushStatus;
    }

    public void setPushStatus(String str) {
        this.pushStatus = str;
    }

    public String getBeginPushTime() {
        return this.beginPushTime;
    }

    public void setBeginPushTime(String str) {
        this.beginPushTime = str;
    }

    public String getEndPushTime() {
        return this.endPushTime;
    }

    public void setEndPushTime(String str) {
        this.endPushTime = str;
    }

    public String getPhoneModel() {
        return this.phoneModel;
    }

    public void setPhoneModel(String str) {
        this.phoneModel = str;
    }

    public String getPushPlatform() {
        return this.pushPlatform;
    }

    public void setPushPlatform(String str) {
        this.pushPlatform = str;
    }

    public String getPlatformToken() {
        return this.platformToken;
    }

    public void setPlatformToken(String str) {
        this.platformToken = str;
    }
}
