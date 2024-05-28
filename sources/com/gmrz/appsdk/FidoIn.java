package com.gmrz.appsdk;

import android.content.Context;
import android.text.TextUtils;
import com.gmrz.appsdk.commlib.api.FidoParam;
import com.gmrz.appsdk.commlib.api.FidoType;
import com.gmrz.appsdk.commlib.api.IGmrzAdapter;
import com.gmrz.appsdk.commlib.api.UACPlugin;
import com.gmrz.appsdk.commlib.api.UACType;
import com.gmrz.appsdk.entity.OperationHeader;
import com.gmrz.appsdk.util.CryptoSuit;
import com.gmrz.appsdk.util.Logger;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FidoIn implements Cloneable {
    private static final String TAG = "FidoIn";
    private Map<UACPlugin, IGmrzAdapter> authAbilty;
    private String[] authType;
    public Context callerActivity;
    private String cardEndDate;
    private String cardName;
    private String cardNo;
    private String cardStartDate;
    private Map<String, String> channelBinding;
    private String checkDeviceAbilityServerResp;
    private String cryptoKey;
    private FidoParam fidoParam;
    public FidoType fidoType;
    private String fidoin;
    private String gestureUVT;
    private boolean isCheckpolicy;
    private boolean isExactMatch = false;
    private boolean isOfflineEnable = false;
    public boolean isTee;
    private String offlineAuthTransText;
    private OperationHeader.OperationType operationType;
    private String requestVoiceData;
    private String transType;
    public UACType uacType;
    public String uafIntent;
    private String username;

    public static FidoIn Builder() {
        return new FidoIn();
    }

    public Object clone() {
        return super.clone();
    }

    public Map<UACPlugin, IGmrzAdapter> getAuthAbilty() {
        return this.authAbilty;
    }

    public String[] getAuthType() {
        return this.authType;
    }

    public String getCacheFileName() {
        if (TextUtils.isEmpty(this.transType)) {
            Logger.m15757e(TAG, "get cache file name need transType can not set null");
            return null;
        }
        String[] strArr = this.authType;
        if (strArr != null && !TextUtils.isEmpty(strArr[0])) {
            if (TextUtils.isEmpty(this.username)) {
                Logger.m15757e(TAG, "get cache file name need username can not set null");
                return null;
            }
            return this.transType + "_" + this.authType[0] + "_" + this.username;
        }
        Logger.m15757e(TAG, "get cache file name need authType can not set null");
        return null;
    }

    public Context getCallerActivity() {
        return this.callerActivity;
    }

    public String getCardEndDate() {
        return this.cardEndDate;
    }

    public String getCardName() {
        return this.cardName;
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public String getCardStartDate() {
        return this.cardStartDate;
    }

    public Map<String, String> getChannelBinding() {
        return this.channelBinding;
    }

    public String getCheckDeviceAbilityServerResp() {
        return this.checkDeviceAbilityServerResp;
    }

    public String getCryptoKey(String str) {
        return CryptoSuit.decrypt(this.cryptoKey, str);
    }

    public String getFidoIn() {
        return this.fidoin;
    }

    public FidoParam getFidoParam() {
        return this.fidoParam;
    }

    public String getGestureUVT() {
        return this.gestureUVT;
    }

    public String getOfflineAuthTransText() {
        return this.offlineAuthTransText;
    }

    public OperationHeader.OperationType getOperationType() {
        return this.operationType;
    }

    public String getRequestVoiceData() {
        return this.requestVoiceData;
    }

    public String getTransType() {
        return this.transType;
    }

    public String getUafIntent() {
        return this.uafIntent;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isCheckpolicy() {
        return this.isCheckpolicy;
    }

    public boolean isExactMatch() {
        return this.isExactMatch;
    }

    public boolean isOfflineEnable() {
        return this.isOfflineEnable;
    }

    public boolean isTee() {
        return this.isTee;
    }

    public void setAuthAbilty(Map<UACPlugin, IGmrzAdapter> map) {
        this.authAbilty = map;
    }

    public void setAuthType(String[] strArr) {
        this.authType = strArr;
    }

    public FidoIn setCallerActivity(Context context) {
        this.callerActivity = context;
        return this;
    }

    public void setCardEndDate(String str) {
        this.cardEndDate = str;
    }

    public void setCardName(String str) {
        this.cardName = str;
    }

    public void setCardNo(String str) {
        this.cardNo = str;
    }

    public void setCardStartDate(String str) {
        this.cardStartDate = str;
    }

    public FidoIn setChannelBinding(Map<String, String> map) {
        this.channelBinding = map;
        return this;
    }

    public void setCheckDeviceAbilityServerResp(String str) {
        this.checkDeviceAbilityServerResp = str;
    }

    public FidoIn setCheckpolicy(boolean z) {
        this.isCheckpolicy = z;
        return this;
    }

    public void setCryptoKey(String str, String str2) {
        this.cryptoKey = CryptoSuit.encrypt(str, str2);
    }

    public void setExactMatch(boolean z) {
        this.isExactMatch = z;
    }

    public FidoIn setFidoIn(String str) {
        this.fidoin = str;
        return this;
    }

    public FidoIn setFidoParam(FidoParam fidoParam) {
        try {
            this.fidoParam = (FidoParam) fidoParam.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void setGestureUVT(String str) {
        this.gestureUVT = str;
    }

    public void setOfflineAuthTransText(String str) {
        this.offlineAuthTransText = str;
    }

    public void setOfflineEnable(boolean z) {
        this.isOfflineEnable = z;
    }

    public void setOperationType(OperationHeader.OperationType operationType) {
        this.operationType = operationType;
    }

    public void setRequestVoiceData(String str) {
        this.requestVoiceData = str;
    }

    public void setTee(boolean z) {
        this.isTee = z;
    }

    public void setTransType(String str) {
        this.transType = str;
    }

    public FidoIn setUacType(FidoType fidoType) {
        this.fidoType = fidoType;
        return this;
    }

    public FidoIn setUafIntent(String str) {
        this.uafIntent = str;
        return this;
    }

    public void setUsername(String str) {
        this.username = str;
    }
}
