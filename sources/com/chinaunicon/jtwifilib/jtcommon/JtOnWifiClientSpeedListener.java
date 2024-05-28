package com.chinaunicon.jtwifilib.jtcommon;

import com.chinaunicon.jtwifilib.core.global.JtApp;
import com.chinaunicon.jtwifilib.core.utils.JtDateUtil;
import com.chinaunicon.jtwifilib.core.utils.JtGsonUtil;
import com.chinaunicon.jtwifilib.core.utils.JtL;
import com.chinaunicon.jtwifilib.jtcommon.model.JtSpeedParams;
import com.chinaunicon.jtwifilib.jtcommon.util.JtUploadLog;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JtOnWifiClientSpeedListener implements JtOnWifiSpeedListener {
    private String Location_X;
    private String Location_Y;
    private String cityCode;
    private String cityName;
    private String privinceCode;
    private String privinceName;
    private String userPhone;

    public void onSuccess(String str) {
    }

    public void onWifiSpeed(String str, int i, String str2) {
    }

    public void onWifiSpeed(String str, String str2, String str3) {
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnWifiSpeedListener
    public void filed(String str) {
        JtUploadLog.getInstance(JtApp.getInstance().getContext()).updateData("1", str, "wifi_speed_filed");
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnWifiSpeedListener
    public void onWifiSpeed(float f, int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("speed", f + "");
            jSONObject.put("type", i + "");
        } catch (JSONException unused) {
        }
        boolean z = jSONObject instanceof JSONObject;
        onWifiSpeed(f + "", i, !z ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        onWifiSpeed(f + "", i + "", !z ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnWifiSpeedListener
    public void onWifiSpeed(float f, int i, String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("speed", f + "");
            jSONObject.put("type", i + "");
            jSONObject.put("max", str + "");
            jSONObject.put("min", str2 + "");
            jSONObject.put("avg", str3 + "");
        } catch (JSONException unused) {
        }
        boolean z = jSONObject instanceof JSONObject;
        onWifiSpeed(f + "", i, !z ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        onWifiSpeed(f + "", i + "", !z ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnWifiSpeedListener
    public void onWifiSpeed(float f, int i, String str) {
        onWifiSpeed(f + "", i, str);
        onWifiSpeed(f + "", i + "", str);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("speed", f + "");
            jSONObject.put("type", i + "");
            jSONObject.put("speedInfo", str);
        } catch (JSONException unused) {
        }
        JtUploadLog.getInstance(JtApp.getInstance().getContext()).updateData("1", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), "wifi_speed_speeding");
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnWifiSpeedListener
    public void success(JtSpeedParams jtSpeedParams) {
        JtL.m16342e("时间测试：消息=结束测速  time=" + JtDateUtil.getDateToString(System.currentTimeMillis()));
        onSuccess(JtGsonUtil.getInstance().toJson(jtSpeedParams));
        JtUploadLog.getInstance(JtApp.getInstance().getContext()).updateData("1", JtGsonUtil.getInstance().toJson(jtSpeedParams), "wifi_speed_success");
    }

    public void setCityInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.privinceName = str;
        this.privinceCode = str2;
        this.cityName = str3;
        this.cityCode = str4;
        this.userPhone = str5;
        this.Location_X = str6;
        this.Location_Y = str7;
        JtUploadLog.getInstance(JtApp.getInstance().getContext()).setCityInfo(this.privinceName, this.privinceCode, this.cityName, this.cityCode, this.userPhone, this.Location_X, this.Location_Y);
    }
}
