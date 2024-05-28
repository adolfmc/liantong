package com.sinovatech.unicom.separatemodule.miniprogram.log;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

@Entity
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CumpLogEntity {
    private String authFunctionCode;
    private String cityCode;
    private String clientVersion;
    private String deviceBrand;
    private String deviceId;
    private String deviceModel;
    private String edopAppId;
    private String edopAppName;
    private String endTime;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18584id;
    private String interceptStatus;
    private String jsFunction;
    private String mobile;
    private String newEdopAppVersion;
    private String oldEdopAppVersion;
    private String osType;
    private String osVersion;
    private String provinceCode;
    private String response;
    private String sceneNumber;
    private String serialNumber;
    private String startTime;
    private String status;
    private String stepNumber;
    private String subSceneNumber;
    private String takeupTime;
    private String title;
    private String url;

    public long getId() {
        return this.f18584id;
    }

    public void setId(long j) {
        this.f18584id = j;
    }

    public String getSceneNumber() {
        return this.sceneNumber;
    }

    public void setSceneNumber(String str) {
        this.sceneNumber = str;
    }

    public String getSubSceneNumber() {
        return this.subSceneNumber;
    }

    public void setSubSceneNumber(String str) {
        this.subSceneNumber = str;
    }

    public String getStepNumber() {
        return this.stepNumber;
    }

    public void setStepNumber(String str) {
        this.stepNumber = str;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String str) {
        this.serialNumber = str;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String str) {
        this.startTime = str;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    public String getEdopAppId() {
        return this.edopAppId;
    }

    public void setEdopAppId(String str) {
        this.edopAppId = str;
    }

    public String getEdopAppName() {
        return this.edopAppName;
    }

    public void setEdopAppName(String str) {
        this.edopAppName = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String str) {
        this.response = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getOldEdopAppVersion() {
        return this.oldEdopAppVersion;
    }

    public void setOldEdopAppVersion(String str) {
        this.oldEdopAppVersion = str;
    }

    public String getNewEdopAppVersion() {
        return this.newEdopAppVersion;
    }

    public void setNewEdopAppVersion(String str) {
        this.newEdopAppVersion = str;
    }

    public String getAuthFunctionCode() {
        return this.authFunctionCode;
    }

    public void setAuthFunctionCode(String str) {
        this.authFunctionCode = str;
    }

    public String getInterceptStatus() {
        return this.interceptStatus;
    }

    public void setInterceptStatus(String str) {
        this.interceptStatus = str;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public String getProvinceCode() {
        return this.provinceCode;
    }

    public void setProvinceCode(String str) {
        this.provinceCode = str;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String str) {
        this.cityCode = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getOsType() {
        return this.osType;
    }

    public void setOsType(String str) {
        this.osType = str;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public String getClientVersion() {
        return this.clientVersion;
    }

    public void setClientVersion(String str) {
        this.clientVersion = str;
    }

    public String getDeviceBrand() {
        return this.deviceBrand;
    }

    public void setDeviceBrand(String str) {
        this.deviceBrand = str;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public String getTakeupTime() {
        return this.takeupTime;
    }

    public void setTakeupTime(String str) {
        this.takeupTime = str;
    }

    public String getJsFunction() {
        return this.jsFunction;
    }

    public void setJsFunction(String str) {
        this.jsFunction = str;
    }

    public String toString() {
        return "CumpLogEntity{id=" + this.f18584id + ", sceneNumber='" + this.sceneNumber + "', subSceneNumber='" + this.subSceneNumber + "', stepNumber='" + this.stepNumber + "', serialNumber='" + this.serialNumber + "', startTime='" + this.startTime + "', endTime='" + this.endTime + "', takeupTime='" + this.takeupTime + "', edopAppId='" + this.edopAppId + "', edopAppName='" + this.edopAppName + "', url='" + this.url + "', title='" + this.title + "', response='" + this.response + "', status='" + this.status + "', oldEdopAppVersion='" + this.oldEdopAppVersion + "', newEdopAppVersion='" + this.newEdopAppVersion + "', authFunctionCode='" + this.authFunctionCode + "', jsFunction='" + this.jsFunction + "', interceptStatus='" + this.interceptStatus + "', mobile='" + this.mobile + "', provinceCode='" + this.provinceCode + "', cityCode='" + this.cityCode + "', deviceId='" + this.deviceId + "', osType='" + this.osType + "', osVersion='" + this.osVersion + "', clientVersion='" + this.clientVersion + "', deviceBrand='" + this.deviceBrand + "', deviceModel='" + this.deviceModel + "'}";
    }
}
