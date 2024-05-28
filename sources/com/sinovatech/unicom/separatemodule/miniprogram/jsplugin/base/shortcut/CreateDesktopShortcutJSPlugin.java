package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.shortcut;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.JsonObject;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/createDesktopShortcut")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CreateDesktopShortcutJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            final String optString = this.parameterJO.optString("id");
            if (!TextUtils.isEmpty(optString)) {
                HashMap hashMap = new HashMap();
                hashMap.put("appIds", optString);
                App.getAsyncHttpClient().rxPost(URLSet.getH5AddShortcutDestopConfig(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.shortcut.CreateDesktopShortcutJSPlugin.1
                    @Override // io.reactivex.functions.Consumer
                    public void accept(String str) throws Exception {
                        JSONObject jSONObject = new JSONObject(str);
                        String string = jSONObject.getString("code");
                        String optString2 = jSONObject.optString("errorMessage", "");
                        if ("0000".equals(string)) {
                            JSONArray jSONArray = jSONObject.getJSONArray("data");
                            if (jSONArray.length() > 0) {
                                JSONObject jSONObject2 = jSONArray.getJSONObject(0);
                                jSONObject2.getString("appId");
                                String string2 = jSONObject2.getString("appName");
                                String string3 = jSONObject2.getString("androidAppIcon");
                                String string4 = jSONObject2.getString("openUrl");
                                ShortcutUtil.addPinedShortcutToDesktop(CreateDesktopShortcutJSPlugin.this.activityContext, optString, string2, string3, "chinaunicom://app?desktopshortcut=desktopshortcut&openUrl=" + string4);
                                CreateDesktopShortcutJSPlugin.this.callbackSuccess(new JsonObject());
                                return;
                            }
                            CreateDesktopShortcutJSPlugin.this.callbackFail("11", "程序错误：快捷方式授权验证错误 没有授权");
                            return;
                        }
                        CreateDesktopShortcutJSPlugin createDesktopShortcutJSPlugin = CreateDesktopShortcutJSPlugin.this;
                        createDesktopShortcutJSPlugin.callbackFail("11", "程序错误：快捷方式授权验证错误 没有授权 " + optString2);
                    }
                }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.shortcut.CreateDesktopShortcutJSPlugin.2
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) throws Exception {
                        CreateDesktopShortcutJSPlugin createDesktopShortcutJSPlugin = CreateDesktopShortcutJSPlugin.this;
                        createDesktopShortcutJSPlugin.callbackFail("11", "程序错误：快捷方式授权验证错误 " + th.getMessage());
                    }
                });
            } else {
                callbackFail("10", "需要传递业务ID");
            }
        } catch (Exception e) {
            callbackFail("10", "程序错误：" + e.getMessage());
        }
    }
}
