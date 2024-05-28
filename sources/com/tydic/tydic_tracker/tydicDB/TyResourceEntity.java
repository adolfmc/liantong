package com.tydic.tydic_tracker.tydicDB;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class TyResourceEntity {
    public String domain;

    /* renamed from: ft */
    public long f20037ft;

    /* renamed from: le */
    public long f20038le;
    public String link_url;
    public String request_id;
    public String resource_time;

    /* renamed from: rs */
    public long f20039rs;
    public String ses_id;
    public long timestamp;
    public String uri;

    public TyResourceEntity(String str, long j, String str2, String str3, String str4, long j2, String str5, long j3, long j4, String str6) {
        this.ses_id = str;
        this.timestamp = j;
        this.request_id = str2;
        this.link_url = str3;
        this.domain = str4;
        this.f20037ft = j2;
        this.uri = str5;
        this.f20039rs = j3;
        this.f20038le = j4;
        this.resource_time = str6;
    }

    public TyResourceEntity() {
    }

    public String getSes_id() {
        return this.ses_id;
    }

    public void setSes_id(String str) {
        this.ses_id = str;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public String getRequest_id() {
        return this.request_id;
    }

    public void setRequest_id(String str) {
        this.request_id = str;
    }

    public String getLink_url() {
        return this.link_url;
    }

    public void setLink_url(String str) {
        this.link_url = str;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public long getFt() {
        return this.f20037ft;
    }

    public void setFt(long j) {
        this.f20037ft = j;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String str) {
        this.uri = str;
    }

    public long getRs() {
        return this.f20039rs;
    }

    public void setRs(long j) {
        this.f20039rs = j;
    }

    public long getLe() {
        return this.f20038le;
    }

    public void setLe(long j) {
        this.f20038le = j;
    }

    public String getResource_time() {
        return this.resource_time;
    }

    public void setResource_time(String str) {
        this.resource_time = str;
    }
}
