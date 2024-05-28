package com.sinovatech.unicom.separatemodule.tongyicaiji.entity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NetCollectTempEntitiy {
    long byteCount;
    String code;
    long dnsEnd;
    long dnsStart;
    long execute_time;
    long reveive;
    long sslEnd;
    long sslStart;
    long tcpEnd;
    long tcpStart;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public long getTcpStart() {
        return this.tcpStart;
    }

    public void setTcpStart(long j) {
        this.tcpStart = j;
    }

    public long getTcpEnd() {
        return this.tcpEnd;
    }

    public void setTcpEnd(long j) {
        this.tcpEnd = j;
    }

    public long getSslStart() {
        return this.sslStart;
    }

    public void setSslStart(long j) {
        this.sslStart = j;
    }

    public long getSslEnd() {
        return this.sslEnd;
    }

    public void setSslEnd(long j) {
        this.sslEnd = j;
    }

    public long getReveive() {
        return this.reveive;
    }

    public void setReveive(long j) {
        this.reveive = j;
    }

    public long getExecute_time() {
        return this.execute_time;
    }

    public void setExecute_time(long j) {
        this.execute_time = j;
    }

    public long getDnsStart() {
        return this.dnsStart;
    }

    public void setDnsStart(long j) {
        this.dnsStart = j;
    }

    public long getDnsEnd() {
        return this.dnsEnd;
    }

    public void setDnsEnd(long j) {
        this.dnsEnd = j;
    }

    public void setSend(long j) {
        this.byteCount = j;
    }

    public long getByteCount() {
        return this.byteCount;
    }
}
