package com.sinovatech.unicom.separatemodule.miniprogram.h5auth;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;

@Entity
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class H5RegisterEntity {
    private String appId;
    private String appName;
    private String h5Urls;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18565id;
    private String plugCodes;

    public H5RegisterEntity() {
    }

    public H5RegisterEntity(String str, String str2, String str3, String str4) {
        this.appId = str;
        this.appName = str2;
        this.h5Urls = str3;
        this.plugCodes = str4;
    }

    public long getId() {
        return this.f18565id;
    }

    public void setId(long j) {
        this.f18565id = j;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getH5Urls() {
        return this.h5Urls;
    }

    public void setH5Urls(String str) {
        this.h5Urls = str;
    }

    public String getPlugCodes() {
        return this.plugCodes;
    }

    public void setPlugCodes(String str) {
        this.plugCodes = str;
    }

    public Map<String, String> getGrantConfig() {
        HashMap hashMap = new HashMap();
        try {
            JSONArray jSONArray = new JSONArray(this.plugCodes);
            for (int i = 0; i < jSONArray.length(); i++) {
                hashMap.put(jSONArray.getJSONObject(i).getString("plugCode"), jSONArray.getJSONObject(i).getString("grantRemark"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    public String getGrantRemark(String str) {
        return getGrantConfig().get(str);
    }
}
