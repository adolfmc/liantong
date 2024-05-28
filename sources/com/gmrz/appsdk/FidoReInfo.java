package com.gmrz.appsdk;

import com.gmrz.appsdk.commlib.FidoMode;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import java.io.Serializable;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FidoReInfo implements Serializable {
    private HashMap<String, FidoStatus[]> checkNetSupportResults;
    public JSONObject deviceInfo;
    public String discoveryData;
    public String errorMsg;
    public FidoStatus faceStatus;
    public FidoStatus fidoFaceStatus;
    public FidoStatus fidoGestureStatus;
    public FidoMode fidoMode;
    public FidoStatus fpStatus;
    public FidoStatus irisStatus;
    private String mfacResponse;
    private boolean netStatus;
    public FidoStatus realNameFaceStatus;
    public FidoStatus realNameStatus;
    public FidoStatus scanQRCodeStatus;
    public FidoStatus status;
    public String systemDeviceId;
    public String uniqueId;
    private boolean userOpeningStatus;
    public FidoStatus voiceStatus;

    public HashMap<String, FidoStatus[]> getCheckNetSupportResults() {
        return this.checkNetSupportResults;
    }

    public JSONObject getDeviceInfo() {
        return this.deviceInfo;
    }

    public String getDiscoveryData() {
        return this.discoveryData;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public FidoStatus getFaceStatus() {
        return this.faceStatus;
    }

    public FidoStatus getFidoFaceStatus() {
        return this.fidoFaceStatus;
    }

    public FidoStatus getFidoGestureStatus() {
        return this.fidoGestureStatus;
    }

    public FidoMode getFidoMode() {
        return this.fidoMode;
    }

    public FidoStatus getFpStatus() {
        return this.fpStatus;
    }

    public FidoStatus getIrisStatus() {
        return this.irisStatus;
    }

    public String getMfacResponse() {
        return this.mfacResponse;
    }

    public FidoStatus getRealNameFaceStatus() {
        return this.realNameFaceStatus;
    }

    public FidoStatus getRealNameStatus() {
        return this.realNameStatus;
    }

    public FidoStatus getScanQRCodeStatus() {
        return this.scanQRCodeStatus;
    }

    public FidoStatus getStatus() {
        return this.status;
    }

    public String getSystemDeviceId() {
        return this.systemDeviceId;
    }

    public String getUniqueID() {
        return this.uniqueId;
    }

    public FidoStatus getVoiceStatus() {
        return this.voiceStatus;
    }

    public boolean isNetStatus() {
        return this.netStatus;
    }

    public boolean isUserOpeningStatus() {
        return this.userOpeningStatus;
    }

    public void setCheckNetSupportResults(HashMap<String, FidoStatus[]> hashMap) {
        this.checkNetSupportResults = hashMap;
    }

    public void setDeviceInfo(JSONObject jSONObject) {
        this.deviceInfo = jSONObject;
    }

    public void setDiscoveryData(String str) {
        this.discoveryData = str;
    }

    public void setErrorMsg(String str) {
        this.errorMsg = str;
    }

    public void setFaceStatus(FidoStatus fidoStatus) {
        this.faceStatus = fidoStatus;
    }

    public void setFidoFaceStatus(FidoStatus fidoStatus) {
        this.fidoFaceStatus = fidoStatus;
    }

    public void setFidoGestureStatus(FidoStatus fidoStatus) {
        this.fidoGestureStatus = fidoStatus;
    }

    public void setFidoMode(FidoMode fidoMode) {
        this.fidoMode = fidoMode;
    }

    public void setFpStatus(FidoStatus fidoStatus) {
        this.fpStatus = fidoStatus;
    }

    public void setIrisStatus(FidoStatus fidoStatus) {
        this.irisStatus = fidoStatus;
    }

    public void setMfacResponse(String str) {
        this.mfacResponse = str;
    }

    public void setNetStatus(boolean z) {
        this.netStatus = z;
    }

    public void setReInfo(FidoStatus fidoStatus, String str) {
        this.status = fidoStatus;
        this.mfacResponse = str;
    }

    public void setRealNameFaceStatus(FidoStatus fidoStatus) {
        this.realNameFaceStatus = fidoStatus;
    }

    public void setRealNameStatus(FidoStatus fidoStatus) {
        this.realNameStatus = fidoStatus;
    }

    public void setScanQRCodeStatus(FidoStatus fidoStatus) {
        this.scanQRCodeStatus = fidoStatus;
    }

    public FidoReInfo setStatus(FidoStatus fidoStatus) {
        this.status = fidoStatus;
        return this;
    }

    public void setSystemDeviceId(String str) {
        this.systemDeviceId = str;
    }

    public void setUniqueID(String str) {
        this.uniqueId = str;
    }

    public void setUserOpeningStatus(boolean z) {
        this.userOpeningStatus = z;
    }

    public void setVoiceStatus(FidoStatus fidoStatus) {
        this.voiceStatus = fidoStatus;
    }
}
