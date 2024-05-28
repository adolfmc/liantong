package com.unicom.pay.common.bean;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class ConfigInfo {
    private String appId;
    private String appKey;
    private String appVersion;
    private String channelCode;
    private EnvConfig envConfig;
    private boolean isOpenLog;

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getChannelCode() {
        return this.channelCode;
    }

    public EnvConfig getEnvConfig() {
        return this.envConfig;
    }

    public boolean isOpenLog() {
        return this.isOpenLog;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public void setChannelCode(String str) {
        this.channelCode = str;
    }

    public void setEnvConfig(EnvConfig envConfig) {
        this.envConfig = envConfig;
    }

    public void setOpenLog(boolean z) {
        this.isOpenLog = z;
    }
}
