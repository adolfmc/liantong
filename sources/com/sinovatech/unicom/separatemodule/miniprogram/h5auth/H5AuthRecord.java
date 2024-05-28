package com.sinovatech.unicom.separatemodule.miniprogram.h5auth;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class H5AuthRecord {
    private String appId;
    private String appName;
    private String authReson;
    private String authScopeCode;
    private String authScopeDesc;
    private String authScopeHint;
    private boolean authStatus;

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

    public String getAuthReson() {
        return this.authReson;
    }

    public void setAuthReson(String str) {
        this.authReson = str;
    }

    public String getAuthScopeCode() {
        return this.authScopeCode;
    }

    public void setAuthScopeCode(String str) {
        this.authScopeCode = str;
    }

    public String getAuthScopeDesc() {
        return this.authScopeDesc;
    }

    public void setAuthScopeDesc(String str) {
        this.authScopeDesc = str;
    }

    public String getAuthScopeHint() {
        return this.authScopeHint;
    }

    public void setAuthScopeHint(String str) {
        this.authScopeHint = str;
    }

    public boolean isAuthStatus() {
        return this.authStatus;
    }

    public void setAuthStatus(boolean z) {
        this.authStatus = z;
    }

    public String toString() {
        return "H5AuthRecord{appId='" + this.appId + "', appName='" + this.appName + "', authReson='" + this.authReson + "', authScopeCode='" + this.authScopeCode + "', authScopeDesc='" + this.authScopeDesc + "', authScopeHint='" + this.authScopeHint + "', authStatus=" + this.authStatus + '}';
    }
}
