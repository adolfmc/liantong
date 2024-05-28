package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.rsa;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.rsa.utils.RSAEncryptUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/RSA")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RsaJSPlugin extends BaseJSPlugin {
    private String code0000 = "0000";
    private String code10 = "10";
    private String code11 = "11";
    private String data;
    private Handler handler;
    private String key;
    private String sign;
    private String type;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            if (this.parameterJO != null) {
                this.data = this.parameterJO.optString("data", "");
                this.type = this.parameterJO.optString("type", "");
                this.key = this.parameterJO.optString("key", "");
                this.sign = this.parameterJO.optString("sign", "");
            }
            this.handler = new Handler();
            if (!TextUtils.isEmpty(this.type) && ("1".equals(this.type) || "3".equals(this.type))) {
                if (TextUtils.isEmpty(this.data)) {
                    callbackSucces(this.code11, "文本不能为空", "");
                    return;
                } else if (TextUtils.isEmpty(this.key)) {
                    callbackSucces(this.code11, "密钥不能为空", "");
                    return;
                } else if (this.type.equals("3") && TextUtils.isEmpty(this.sign)) {
                    callbackSucces(this.code11, "加签文本不能为空", "");
                    return;
                } else if (this.type.equals("1")) {
                    String encodeToString = Base64.getEncoder().encodeToString(RSAEncryptUtil.encryptByPublicKey(this.data.getBytes(StandardCharsets.UTF_8), this.key));
                    if (!TextUtils.isEmpty(encodeToString)) {
                        callbackSucces(this.code0000, "RSA加密成功", encodeToString);
                        return;
                    } else {
                        callbackSucces(this.code10, "RSA操作失败", "");
                        return;
                    }
                } else if (this.type.equals("3")) {
                    if (RSAEncryptUtil.verifySign(this.key, this.data, this.sign)) {
                        callbackSucces(this.code0000, "RSA验签成功", "");
                        return;
                    } else {
                        callbackSucces(this.code10, "RSA操作失败", "");
                        return;
                    }
                } else {
                    return;
                }
            }
            callbackSucces(this.code11, "type参数异常", "");
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
            String str = this.code10;
            callbackSucces(str, "RSA操作失败：" + e.getMessage(), "");
        }
    }

    private void callbackSucces(String str, String str2, String str3) {
        try {
            final JSONObject jSONObject = new JSONObject();
            jSONObject.put("status", str);
            jSONObject.put("msg", str2);
            jSONObject.put("result", str3);
            this.handler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.rsa.RsaJSPlugin.1
                @Override // java.lang.Runnable
                public void run() {
                    RsaJSPlugin.this.callbackSuccess(jSONObject);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
