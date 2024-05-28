package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.tbruyelle.rxpermissions2.RxPermissions;
import org.json.JSONException;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/choosePoi")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ChoosePoiJSPlugin extends BaseJSPlugin {
    private Intent intent;
    private boolean isNeedDetilInfo;

    /* renamed from: ob */
    private JSONObject f18573ob;
    private RxPermissions rxPermissions;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            this.f18573ob = new JSONObject();
            this.intent = new Intent(this.activityContext, BaiDuSearchPoiActivity.class);
            String optString = this.parameterJO.optString("latitude");
            String optString2 = this.parameterJO.optString("longitude");
            if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
                boolean optBoolean = this.parameterJO.optBoolean("cityLimit", false);
                this.isNeedDetilInfo = this.parameterJO.optBoolean("isNeedDetilInfo", false);
                this.intent.putExtra("latitude", optString);
                this.intent.putExtra("longitude", optString2);
                this.intent.putExtra("cityLimit", optBoolean);
                startIntent();
            } else {
                this.f18573ob.put("code", "12");
                this.f18573ob.put("msg", "经纬度参数为空或入参格式不对");
                callbackSuccess(this.f18573ob);
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序异常" + e.getMessage());
        }
    }

    private void startIntent() {
        new AvoidOnResult(this.activityContext).startForResult(this.intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.ChoosePoiJSPlugin.1
            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
            public void onActivityResult(int i, Intent intent) {
                try {
                    if (i != -1 || intent == null) {
                        ChoosePoiJSPlugin.this.f18573ob.put("code", "11");
                        ChoosePoiJSPlugin.this.f18573ob.put("msg", "获取POI列表接口报错");
                        ChoosePoiJSPlugin.this.callbackSuccess(ChoosePoiJSPlugin.this.f18573ob);
                    } else if (intent.getBooleanExtra("isNoSelect", false)) {
                        ChoosePoiJSPlugin.this.f18573ob.put("code", "13");
                        ChoosePoiJSPlugin.this.f18573ob.put("msg", "用户没有选择直接返回");
                        ChoosePoiJSPlugin.this.callbackSuccess(ChoosePoiJSPlugin.this.f18573ob);
                    } else {
                        JSONObject jSONObject = new JSONObject();
                        ChoosePoiJSPlugin.this.f18573ob.put("code", "0000");
                        ChoosePoiJSPlugin.this.f18573ob.put("msg", "获取poi列表信息成功");
                        String stringExtra = intent.getStringExtra("name");
                        String stringExtra2 = intent.getStringExtra("address");
                        String stringExtra3 = intent.getStringExtra("longitude");
                        String stringExtra4 = intent.getStringExtra("latitude");
                        jSONObject.put("name", stringExtra);
                        jSONObject.put("address", stringExtra2);
                        jSONObject.put("latitude", stringExtra4);
                        jSONObject.put("longitude", stringExtra3);
                        if (ChoosePoiJSPlugin.this.isNeedDetilInfo) {
                            jSONObject.put("city", intent.getStringExtra("city"));
                            jSONObject.put("province", intent.getStringExtra("province"));
                            jSONObject.put("district", intent.getStringExtra("district"));
                            jSONObject.put("town", intent.getStringExtra("town"));
                            jSONObject.put("streetName", intent.getStringExtra("streetName"));
                            jSONObject.put("streetNumber", intent.getStringExtra("streetNumber"));
                            jSONObject.put("adCode", intent.getStringExtra("adCode"));
                        }
                        ChoosePoiJSPlugin.this.f18573ob.put("locationInfo", jSONObject);
                        ChoosePoiJSPlugin.this.callbackSuccess(ChoosePoiJSPlugin.this.f18573ob);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
