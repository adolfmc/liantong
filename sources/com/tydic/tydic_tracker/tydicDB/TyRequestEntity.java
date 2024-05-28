package com.tydic.tydic_tracker.tydicDB;

import com.tydic.tydic_tracker.app.TYUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class TyRequestEntity {
    public String domain;
    public String error_code;
    public String error_type;
    public String event_tag;
    public long execute_time;
    public String http_method;
    public int is_error;
    public String request_id = TYUtil.getRandom();
    public int response_time;
    public int reveive;
    public int send;
    public String ses_id;
    public String target_name;
    public String url;

    public String getSes_id() {
        return this.ses_id;
    }

    public void setSes_id(String str) {
        this.ses_id = str;
    }

    public long getExecute_time() {
        return this.execute_time;
    }

    public void setExecute_time(long j) {
        this.execute_time = j;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getTarget_name() {
        return this.target_name;
    }

    public void setTarget_name(String str) {
        this.target_name = str;
    }

    public String getEvent_tag() {
        return this.event_tag;
    }

    public void setEvent_tag(String str) {
        this.event_tag = str;
    }

    public int getResponse_time() {
        return this.response_time;
    }

    public void setResponse_time(int i) {
        this.response_time = i;
    }

    public int getIs_error() {
        return this.is_error;
    }

    public void setIs_error(int i) {
        this.is_error = i;
    }

    public String getError_type() {
        return this.error_type;
    }

    public void setError_type(String str) {
        this.error_type = str;
    }

    public String getError_code() {
        return this.error_code;
    }

    public void setError_code(String str) {
        this.error_code = str;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public String getRequest_id() {
        return this.request_id;
    }

    public void setRequest_id(String str) {
        this.request_id = str;
    }

    public String getHttp_method() {
        return this.http_method;
    }

    public void setHttp_method(String str) {
        this.http_method = str;
    }

    public int getSend() {
        return this.send;
    }

    public void setSend(int i) {
        this.send = i;
    }

    public int getReveive() {
        return this.reveive;
    }

    public void setReveive(int i) {
        this.reveive = i;
    }
}
