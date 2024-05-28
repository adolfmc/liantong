package com.tydic.softphone.tydic_tracker.tydicDB;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class TyWebHttpEntity {
    public String domain;
    public int is_err;
    public String link_url;
    public String req_method;
    public long req_time;
    public String req_url;
    public String request_id;
    public long res_time;
    public String sender_name;
    public String ses_id;
    public String target_name;
    public long timestamp;
    public String uri;

    public TyWebHttpEntity(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, long j2, int i, long j3, String str9) {
        this.sender_name = "WebView";
        this.req_method = "POST";
        this.ses_id = str;
        this.request_id = str2;
        this.timestamp = j;
        this.target_name = str3;
        this.sender_name = str4;
        this.link_url = str5;
        this.domain = str6;
        this.uri = str7;
        this.req_method = str8;
        this.req_time = j2;
        this.is_err = i;
        this.res_time = j3;
        this.req_url = str9;
    }

    public TyWebHttpEntity() {
        this.sender_name = "WebView";
        this.req_method = "POST";
    }

    public String getSes_id() {
        return this.ses_id;
    }

    public void setSes_id(String str) {
        this.ses_id = str;
    }

    public String getRequest_id() {
        return this.request_id;
    }

    public void setRequest_id(String str) {
        this.request_id = str;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public String getTarget_name() {
        return this.target_name;
    }

    public void setTarget_name(String str) {
        this.target_name = str;
    }

    public String getSender_name() {
        return this.sender_name;
    }

    public void setSender_name(String str) {
        this.sender_name = str;
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

    public String getUri() {
        return this.uri;
    }

    public void setUri(String str) {
        this.uri = str;
    }

    public String getReq_method() {
        return this.req_method;
    }

    public void setReq_method(String str) {
        this.req_method = str;
    }

    public long getReq_time() {
        return this.req_time;
    }

    public void setReq_time(long j) {
        this.req_time = j;
    }

    public int getIs_err() {
        return this.is_err;
    }

    public void setIs_err(int i) {
        this.is_err = i;
    }

    public long getRes_time() {
        return this.res_time;
    }

    public void setRes_time(long j) {
        this.res_time = j;
    }

    public String getReq_url() {
        return this.req_url;
    }

    public void setReq_url(String str) {
        this.req_url = str;
    }
}
