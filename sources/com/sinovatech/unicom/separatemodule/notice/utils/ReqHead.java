package com.sinovatech.unicom.separatemodule.notice.utils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ReqHead {
    private String aesIndex;
    private String aesStr;
    private String bipCode;
    private Object extNode;
    private String procId;
    private String srcCode;
    private String version;

    public String getProcId() {
        return this.procId;
    }

    public void setProcId(String str) {
        this.procId = str;
    }

    public String getSrcCode() {
        return this.srcCode;
    }

    public void setSrcCode(String str) {
        this.srcCode = str;
    }

    public String getAesStr() {
        return this.aesStr;
    }

    public void setAesStr(String str) {
        this.aesStr = str;
    }

    public String getAesIndex() {
        return this.aesIndex;
    }

    public void setAesIndex(String str) {
        this.aesIndex = str;
    }

    public String getBipCode() {
        return this.bipCode;
    }

    public void setBipCode(String str) {
        this.bipCode = str;
    }

    public Object getExtNode() {
        return this.extNode;
    }

    public void setExtNode(Object obj) {
        this.extNode = obj;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
