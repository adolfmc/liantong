package com.sinovatech.unicom.basic.eventbus;

import com.sinovatech.unicom.common.EventMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class EdopXunJianEvent extends EventMessage<String> {
    private String batchId;
    private String decrypt;
    private String edopAppid;
    private long timeMillis;

    public EdopXunJianEvent(int i) {
        super(i);
    }

    public String getEdopAppid() {
        return this.edopAppid;
    }

    public void setEdopAppid(String str) {
        this.edopAppid = str;
    }

    public String getDecrypt() {
        return this.decrypt;
    }

    public void setDecrypt(String str) {
        this.decrypt = str;
    }

    public String getBatchId() {
        return this.batchId;
    }

    public void setBatchId(String str) {
        this.batchId = str;
    }

    public long getTimeMillis() {
        return this.timeMillis;
    }

    public void setTimeMillis(long j) {
        this.timeMillis = j;
    }
}
