package com.sinovatech.unicom.separatemodule.miniprogram.jspermission;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class JSPermissionEntity {
    private String callAction;
    private String innerAction;
    private boolean onlyEdop;
    private String plugCode;
    private String remark;
    private boolean reqBgGrant_H5;
    private boolean reqBgGrant_edop;
    private boolean reqUserGrant_H5;
    private boolean reqUserGrant_edop;
    private String scope;

    public JSPermissionEntity(String str, String str2, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, String str5) {
        this.innerAction = "";
        this.callAction = "";
        this.plugCode = "";
        this.scope = "";
        this.reqBgGrant_edop = false;
        this.reqBgGrant_H5 = false;
        this.reqUserGrant_edop = false;
        this.reqUserGrant_H5 = false;
        this.onlyEdop = false;
        this.remark = "";
        this.innerAction = str;
        this.callAction = str2;
        this.plugCode = str3;
        this.scope = str4;
        this.reqBgGrant_edop = z;
        this.reqBgGrant_H5 = z2;
        this.reqUserGrant_edop = z3;
        this.reqUserGrant_H5 = z4;
        this.onlyEdop = z5;
        this.remark = str5;
    }

    public String getInnerAction() {
        return this.innerAction;
    }

    public void setInnerAction(String str) {
        this.innerAction = str;
    }

    public String getCallAction() {
        return this.callAction;
    }

    public void setCallAction(String str) {
        this.callAction = str;
    }

    public String getPlugCode() {
        return this.plugCode;
    }

    public void setPlugCode(String str) {
        this.plugCode = str;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String str) {
        this.scope = str;
    }

    public boolean isReqBgGrant_edop() {
        return this.reqBgGrant_edop;
    }

    public void setReqBgGrant_edop(boolean z) {
        this.reqBgGrant_edop = z;
    }

    public boolean isReqBgGrant_H5() {
        return this.reqBgGrant_H5;
    }

    public void setReqBgGrant_H5(boolean z) {
        this.reqBgGrant_H5 = z;
    }

    public boolean isReqUserGrant_edop() {
        return this.reqUserGrant_edop;
    }

    public void setReqUserGrant_edop(boolean z) {
        this.reqUserGrant_edop = z;
    }

    public boolean isReqUserGrant_H5() {
        return this.reqUserGrant_H5;
    }

    public void setReqUserGrant_H5(boolean z) {
        this.reqUserGrant_H5 = z;
    }

    public boolean isOnlyEdop() {
        return this.onlyEdop;
    }

    public void setOnlyEdop(boolean z) {
        this.onlyEdop = z;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public String toString() {
        return "JSPermissionEntity{innerAction='" + this.innerAction + "', callAction='" + this.callAction + "', plugCode='" + this.plugCode + "', scope='" + this.scope + "', reqBgGrant_edop=" + this.reqBgGrant_edop + ", reqBgGrant_H5=" + this.reqBgGrant_H5 + ", reqUserGrant_edop=" + this.reqUserGrant_edop + ", reqUserGrant_H5=" + this.reqUserGrant_H5 + ", onlyEdop=" + this.onlyEdop + ", remark='" + this.remark + "'}";
    }
}
