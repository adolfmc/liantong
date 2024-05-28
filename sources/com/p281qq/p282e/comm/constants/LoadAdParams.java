package com.p281qq.p282e.comm.constants;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.Map;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.qq.e.comm.constants.LoadAdParams */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LoadAdParams {

    /* renamed from: a */
    private LoginType f17905a;

    /* renamed from: b */
    private String f17906b;

    /* renamed from: c */
    private String f17907c;

    /* renamed from: d */
    private String f17908d;

    /* renamed from: e */
    private Map<String, String> f17909e;

    /* renamed from: f */
    private JSONObject f17910f;

    /* renamed from: g */
    private final JSONObject f17911g = new JSONObject();

    public Map getDevExtra() {
        return this.f17909e;
    }

    public String getDevExtraJsonString() {
        try {
            if (this.f17909e == null || this.f17909e.size() <= 0) {
                return "";
            }
            JSONObject jSONObject = new JSONObject(this.f17909e);
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public JSONObject getExtraInfo() {
        return this.f17910f;
    }

    public String getLoginAppId() {
        return this.f17906b;
    }

    public String getLoginOpenid() {
        return this.f17907c;
    }

    public LoginType getLoginType() {
        return this.f17905a;
    }

    public JSONObject getParams() {
        return this.f17911g;
    }

    public String getUin() {
        return this.f17908d;
    }

    public void setDevExtra(Map<String, String> map) {
        this.f17909e = map;
    }

    public void setExtraInfo(JSONObject jSONObject) {
        this.f17910f = jSONObject;
    }

    public void setLoginAppId(String str) {
        this.f17906b = str;
    }

    public void setLoginOpenid(String str) {
        this.f17907c = str;
    }

    public void setLoginType(LoginType loginType) {
        this.f17905a = loginType;
    }

    public void setUin(String str) {
        this.f17908d = str;
    }

    public String toString() {
        return "LoadAdParams{, loginType=" + this.f17905a + ", loginAppId=" + this.f17906b + ", loginOpenid=" + this.f17907c + ", uin=" + this.f17908d + ", passThroughInfo=" + this.f17909e + ", extraInfo=" + this.f17910f + '}';
    }
}
