package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.chooseCity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.p315ui.activity.CitySelectActivity;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/chooseCity")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ChooseCityJsPlugin extends BaseJSPlugin {
    public static final String TAG = "chooseCityJsPlugin";

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            Intent intent = new Intent(this.activityContext, CitySelectActivity.class);
            intent.putExtra("from", "chooseCity");
            new AvoidOnResult(this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.chooseCity.ChooseCityJsPlugin.1
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public void onActivityResult(int i, Intent intent2) {
                    if (i == -1 && intent2 != null) {
                        try {
                            String stringExtra = intent2.getStringExtra("privienceCode");
                            String stringExtra2 = intent2.getStringExtra("provinceName");
                            String stringExtra3 = intent2.getStringExtra("cityName");
                            String stringExtra4 = intent2.getStringExtra("cityCode");
                            String stringExtra5 = intent2.getStringExtra("type");
                            if (!TextUtils.isEmpty(stringExtra5) && "00".equals(stringExtra5)) {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("provinceCode", stringExtra);
                                jSONObject.put("provinceName", stringExtra2);
                                jSONObject.put("cityName", stringExtra3);
                                jSONObject.put("cityCode", stringExtra4);
                                MsLogUtil.m7979d("chooseCityJsPlugin", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                                ChooseCityJsPlugin.this.callbackSuccess(jSONObject);
                                return;
                            }
                            ChooseCityJsPlugin.this.callbackFail("11", "未选择地市");
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            ChooseCityJsPlugin chooseCityJsPlugin = ChooseCityJsPlugin.this;
                            chooseCityJsPlugin.callbackFail("10", "程序异常" + e.getMessage());
                            return;
                        }
                    }
                    ChooseCityJsPlugin.this.callbackFail("10", "程序异常");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序异常" + e.getMessage());
        }
    }
}
