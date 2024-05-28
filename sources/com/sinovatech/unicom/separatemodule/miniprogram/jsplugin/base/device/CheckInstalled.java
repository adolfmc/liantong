package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.AppUtils;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/checkInstalled")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CheckInstalled extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        char c;
        String optString = this.parameterJO.optString("type");
        String optString2 = this.parameterJO.optString("packageName");
        int hashCode = optString.hashCode();
        if (hashCode != 686218487) {
            if (hashCode == 1868995410 && optString.equals("checkInstalled")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (optString.equals("checkPermission")) {
                c = 0;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                if (TextUtils.equals(checkPermission(), "true")) {
                    callbackSuccess("0", "已申请读取应用列表权限");
                    return;
                } else {
                    callbackFail("10", "无读取应用列表权限");
                    return;
                }
            case 1:
                checkInstalled(optString2);
                return;
            default:
                return;
        }
    }

    private void checkInstalled(String str) {
        if (TextUtils.equals(checkPermission(), "true")) {
            if (AppUtils.isAppInstalled(str)) {
                callbackFail("0", "已安装应用程序");
                return;
            } else {
                callbackFail("1", "未安装应用程序");
                return;
            }
        }
        callbackFail("10", "无读取应用列表权限");
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void callbackFail(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", str);
            jSONObject.put("msg", str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        callbackFail(jSONObject);
    }

    public void callbackSuccess(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", str);
            jSONObject.put("msg", str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        callbackFail(jSONObject);
    }

    private String checkPermission() {
        return (Build.VERSION.SDK_INT < 30 || SoulPermission.getInstance().checkSinglePermission("android.permission.QUERY_ALL_PACKAGES").isGranted()) ? "true" : "false";
    }
}
