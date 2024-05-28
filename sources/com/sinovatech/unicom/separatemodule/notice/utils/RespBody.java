package com.sinovatech.unicom.separatemodule.notice.utils;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RespBody {
    private String deviceId;
    private Object extNode;
    private List<Message> messages;
    private String respCode;
    private String respDesp;
    private String respType;

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getRespType() {
        return this.respType;
    }

    public void setRespType(String str) {
        this.respType = str;
    }

    public String getRespCode() {
        return this.respCode;
    }

    public void setRespCode(String str) {
        this.respCode = str;
    }

    public String getRespDesp() {
        return this.respDesp;
    }

    public void setRespDesp(String str) {
        this.respDesp = str;
    }

    public List<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(List<Message> list) {
        this.messages = list;
    }

    public Object getExtNode() {
        return this.extNode;
    }

    public void setExtNode(Object obj) {
        this.extNode = obj;
    }
}
