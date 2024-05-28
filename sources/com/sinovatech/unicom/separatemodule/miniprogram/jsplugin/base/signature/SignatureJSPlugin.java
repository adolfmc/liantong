package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.signature;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.separatemodule.dianziqianming.SignatureActivity;
import com.sinovatech.unicom.separatemodule.dianziqianming.utils.SignatureUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils.LogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/Signature")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SignatureJSPlugin extends BaseJSPlugin {
    private static final String TAG = "com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.signature.SignatureJSPlugin";
    private String height;
    private String msg;
    private String needYaSuo;
    private String width;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            if (this.parameterJO != null) {
                this.msg = this.parameterJO.optString("msg", "");
                this.width = this.parameterJO.optString("width", "1800");
                this.height = this.parameterJO.optString("height", "1200");
                this.needYaSuo = this.parameterJO.optString("needYaSuo", "false");
            }
            try {
                SignatureUtils.qWidth = Integer.parseInt(this.width);
                SignatureUtils.qHeight = Integer.parseInt(this.height);
            } catch (Exception unused) {
                SignatureUtils.qWidth = 1800;
                SignatureUtils.qHeight = 1200;
            }
            Intent intent = new Intent(this.activityContext, SignatureActivity.class);
            intent.putExtra("msg", this.msg);
            intent.putExtra("needYaSuo", this.needYaSuo);
            new AvoidOnResult(this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.signature.SignatureJSPlugin.1
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public void onActivityResult(int i, Intent intent2) {
                    Activity activity = SignatureJSPlugin.this.activityContext;
                    if (i == -1 && intent2 != null) {
                        boolean equals = TextUtils.equals("true", SignatureJSPlugin.this.needYaSuo);
                        String stringExtra = intent2.getStringExtra("signPic");
                        try {
                            JSONObject jSONObject = new JSONObject();
                            if (!TextUtils.isEmpty(stringExtra)) {
                                jSONObject.put("code", "0");
                                jSONObject.put("data", stringExtra);
                                if (equals) {
                                    String stringExtra2 = intent2.getStringExtra("x");
                                    String stringExtra3 = intent2.getStringExtra("y");
                                    String stringExtra4 = intent2.getStringExtra("dataArray");
                                    jSONObject.put("RESOLUTION_X", stringExtra2);
                                    jSONObject.put("RESOLUTION_Y", stringExtra3);
                                    jSONObject.put("SIGN_DATA", stringExtra4);
                                }
                            } else {
                                jSONObject.put("code", "1");
                                jSONObject.put("data", "");
                                jSONObject.put("msg", "签名数据为空");
                            }
                            SignatureJSPlugin.this.callbackSuccess(jSONObject);
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            SignatureJSPlugin.this.callbackFail(e.getMessage());
                            return;
                        }
                    }
                    try {
                        String stringExtra5 = intent2.getStringExtra("msg");
                        if (TextUtils.isEmpty(stringExtra5)) {
                            stringExtra5 = "";
                        }
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("code", "1");
                        jSONObject2.put("data", "");
                        jSONObject2.put("msg", stringExtra5);
                        SignatureJSPlugin.this.callbackSuccess(jSONObject2);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        SignatureJSPlugin.this.callbackFail(e2.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            String str = TAG;
            LogUtil.m7987e(str, "异常：" + e.getMessage());
            callbackFail(e.getMessage());
        }
    }
}
