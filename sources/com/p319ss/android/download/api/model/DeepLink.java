package com.p319ss.android.download.api.model;

import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.download.api.model.DeepLink */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class DeepLink {

    /* renamed from: id */
    private long f18770id;
    private JSONObject json;
    private String mCloudGameUrl;
    private String mOpenUrl;
    private String mWebTitle;
    private String mWebUrl;
    private String packageName;

    public DeepLink(String str, String str2, String str3) {
        this.mOpenUrl = str;
        this.mWebUrl = str2;
        this.mWebTitle = str3;
    }

    public DeepLink() {
    }

    public String getWebUrl() {
        return this.mWebUrl;
    }

    public void setWebUrl(String str) {
        this.mWebUrl = str;
    }

    public String getOpenUrl() {
        return this.mOpenUrl;
    }

    public void setOpenUrl(String str) {
        this.mOpenUrl = str;
    }

    public void setCloudGameUrl(String str) {
        this.mCloudGameUrl = str;
    }

    public String getCloudGameUrl() {
        return this.mCloudGameUrl;
    }

    public String getWebTitle() {
        return this.mWebTitle;
    }

    public void setWebTitle(String str) {
        this.mWebTitle = str;
    }

    public long getId() {
        return this.f18770id;
    }

    public void setId(long j) {
        this.f18770id = j;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setJson(JSONObject jSONObject) {
        this.json = jSONObject;
    }

    public JSONObject getJson() {
        return this.json;
    }
}
