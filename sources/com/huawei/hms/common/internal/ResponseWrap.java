package com.huawei.hms.common.internal;

import android.text.TextUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ResponseWrap {

    /* renamed from: a */
    private String f11167a;

    /* renamed from: b */
    private ResponseHeader f11168b;

    public ResponseWrap(ResponseHeader responseHeader) {
        this.f11168b = responseHeader;
    }

    public boolean fromJson(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f11168b.setStatusCode(JsonUtil.getIntValue(jSONObject, "status_code"));
            this.f11168b.setErrorCode(JsonUtil.getIntValue(jSONObject, "error_code"));
            this.f11168b.setErrorReason(JsonUtil.getStringValue(jSONObject, "error_reason"));
            this.f11168b.setSrvName(JsonUtil.getStringValue(jSONObject, "srv_name"));
            this.f11168b.setApiName(JsonUtil.getStringValue(jSONObject, "api_name"));
            this.f11168b.setAppID(JsonUtil.getStringValue(jSONObject, "app_id"));
            this.f11168b.setPkgName(JsonUtil.getStringValue(jSONObject, "pkg_name"));
            this.f11168b.setSessionId(JsonUtil.getStringValue(jSONObject, "session_id"));
            this.f11168b.setTransactionId(JsonUtil.getStringValue(jSONObject, "transaction_id"));
            this.f11168b.setResolution(JsonUtil.getStringValue(jSONObject, "resolution"));
            this.f11167a = JsonUtil.getStringValue(jSONObject, "body");
            return true;
        } catch (JSONException e) {
            HMSLog.m14112e("ResponseWrap", "fromJson failed: " + e.getMessage());
            return false;
        }
    }

    public String getBody() {
        if (TextUtils.isEmpty(this.f11167a)) {
            this.f11167a = new JSONObject().toString();
        }
        return this.f11167a;
    }

    public ResponseHeader getResponseHeader() {
        return this.f11168b;
    }

    public void setBody(String str) {
        this.f11167a = str;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.f11168b = responseHeader;
    }

    public String toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status_code", this.f11168b.getStatusCode());
            jSONObject.put("error_code", this.f11168b.getErrorCode());
            jSONObject.put("error_reason", this.f11168b.getErrorReason());
            jSONObject.put("srv_name", this.f11168b.getSrvName());
            jSONObject.put("api_name", this.f11168b.getApiName());
            jSONObject.put("app_id", this.f11168b.getAppID());
            jSONObject.put("pkg_name", this.f11168b.getPkgName());
            jSONObject.put("transaction_id", this.f11168b.getTransactionId());
            jSONObject.put("resolution", this.f11168b.getResolution());
            String sessionId = this.f11168b.getSessionId();
            if (!TextUtils.isEmpty(sessionId)) {
                jSONObject.put("session_id", sessionId);
            }
            if (!TextUtils.isEmpty(this.f11167a)) {
                jSONObject.put("body", this.f11167a);
            }
        } catch (JSONException e) {
            HMSLog.m14112e("ResponseWrap", "toJson failed: " + e.getMessage());
        }
        return jSONObject.toString();
    }

    public String toString() {
        return "ResponseWrap{body='" + this.f11167a + "', responseHeader=" + this.f11168b + '}';
    }
}
