package com.eidlink.idocr.sdk.bean;

import android.content.Context;
import java.io.Serializable;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class EidlinkInitParams implements Serializable {
    public String appid;
    public Context context;
    public int envIdCode;

    /* renamed from: ip */
    public String f9735ip;
    public int port;

    public EidlinkInitParams(Context context, String str, String str2, int i, int i2) {
        this.context = context.getApplicationContext();
        this.appid = str;
        this.f9735ip = str2;
        this.port = i;
        this.envIdCode = i2;
    }

    public String getAppid() {
        return this.appid;
    }

    public Context getContext() {
        return this.context;
    }

    public int getEnvIdCode() {
        return this.envIdCode;
    }

    public String getIp() {
        return this.f9735ip;
    }

    public int getPort() {
        return this.port;
    }

    public void setAppid(String str) {
        this.appid = str;
    }

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }

    public void setEnvIdCode(int i) {
        this.envIdCode = i;
    }

    public void setIp(String str) {
        this.f9735ip = str;
    }

    public void setPort(int i) {
        this.port = i;
    }

    public String toString() {
        return "EidlinkInitParams{context=" + this.context + ", appid='" + this.appid + "', ip='" + this.f9735ip + "', port='" + this.port + "', envIdCode='" + this.envIdCode + "'}";
    }
}
