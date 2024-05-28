package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.aes;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.aes.utils.AesEncryptUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/AesCbcCommon")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AesCommonJSPlugin extends BaseJSPlugin {
    private String data;
    private Handler handler;

    /* renamed from: iv */
    private String f18569iv;
    private String key;
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
                this.f18569iv = this.parameterJO.optString("iv", "");
                this.key = this.parameterJO.optString("key", "");
            }
            this.handler = new Handler();
            if (TextUtils.isEmpty(this.data)) {
                callbackSucces("11", "加密文本不能为空", "");
                return;
            }
            if (!TextUtils.isEmpty(this.type) && ("1".equals(this.type) || "2".equals(this.type))) {
                if (TextUtils.isEmpty(this.key)) {
                    callbackSucces("11", "密钥key不能为空", "");
                    return;
                } else if (this.key.length() != 16 && this.key.length() != 24 && this.key.length() != 32) {
                    callbackSucces("11", "密钥key必须为16位或者24位或者32位", "");
                    return;
                } else if (TextUtils.isEmpty(this.f18569iv)) {
                    callbackSucces("11", "向量iv不能为空", "");
                    return;
                } else if (this.f18569iv.length() != 16) {
                    callbackSucces("11", "向量iv必须为16位", "");
                    return;
                } else if (this.type.equals("1")) {
                    String encrypt = AesEncryptUtil.encrypt(this.data, this.key, this.f18569iv);
                    if (!TextUtils.isEmpty(encrypt)) {
                        callbackSucces("0000", "AES加密成功", encrypt);
                        return;
                    } else {
                        callbackSucces("10", "AES操作失败", "");
                        return;
                    }
                } else if (this.type.equals("2")) {
                    String decrypt = AesEncryptUtil.decrypt(this.data, this.key, this.f18569iv);
                    if (decrypt != null) {
                        callbackSucces("0000", "AES解密成功", decrypt);
                        return;
                    } else {
                        callbackSucces("10", "AES操作失败", "");
                        return;
                    }
                } else {
                    return;
                }
            }
            callbackSucces("11", "type参数异常", "");
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
            callbackSucces("10", "AES操作失败，" + e.getMessage(), "");
        }
    }

    private void callbackSucces(String str, String str2, String str3) {
        try {
            final JSONObject jSONObject = new JSONObject();
            jSONObject.put("status", str);
            jSONObject.put("msg", str2);
            jSONObject.put("result", str3);
            this.handler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.aes.AesCommonJSPlugin.1
                @Override // java.lang.Runnable
                public void run() {
                    AesCommonJSPlugin.this.callbackSuccess(jSONObject);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
