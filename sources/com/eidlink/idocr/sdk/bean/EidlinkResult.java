package com.eidlink.idocr.sdk.bean;

import java.io.Serializable;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class EidlinkResult implements Serializable {
    public String data;
    public IdentityBean identity;
    public String reqId;
    public String signpacket;

    public EidlinkResult() {
    }

    public EidlinkResult(String str) {
        this.reqId = str;
        this.signpacket = "";
    }

    public EidlinkResult(String str, String str2) {
        this.reqId = str;
        this.signpacket = str2;
    }

    public String getData() {
        return this.data;
    }

    public IdentityBean getIdentity() {
        return this.identity;
    }

    public String getReqId() {
        return this.reqId;
    }

    public String getSignpacket() {
        return this.signpacket;
    }

    public void setData(String str) {
        this.data = str;
    }

    public void setIdentity(IdentityBean identityBean) {
        this.identity = identityBean;
    }

    public void setReqId(String str) {
        this.reqId = str;
    }

    public void setSignpacket(String str) {
        this.signpacket = str;
    }

    public String toString() {
        return "EidlinkResult{reqId='" + this.reqId + "', signpacket='" + this.signpacket + "', identity=" + this.identity + '}';
    }
}
