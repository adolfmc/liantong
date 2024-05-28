package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.navigate;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/navigateGoBack")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NavigateGoBackJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        int i = -1;
        try {
            int parseInt = Integer.parseInt(this.parameterJO.optString("num"));
            if (parseInt < 0) {
                i = parseInt;
            }
        } catch (Exception unused) {
        }
        JSONObject optJSONObject = this.parameterJO.optJSONObject("params");
        if (optJSONObject != null) {
            App.navigateParamsCacheMap.put("chinaunicom", transformJSONObjectToMap(optJSONObject));
        } else {
            App.navigateParamsCacheMap.remove("chinaunicom");
        }
        if (this.f18589wv.canGoBackOrForward(i)) {
            this.f18589wv.goBackOrForward(i);
        } else {
            this.activityContext.finish();
        }
    }

    private Map<String, Object> transformJSONObjectToMap(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        if (jSONObject != null) {
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, jSONObject.get(next));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hashMap;
    }
}
