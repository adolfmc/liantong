package com.sinovatech.unicom.common;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AuthResult {
    public static String convertJSON(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            for (String str : map.keySet()) {
                if (TextUtils.equals(str, "resultStatus")) {
                    jSONObject.put("resultStatus", map.get(str));
                } else if (TextUtils.equals(str, "result")) {
                    jSONObject.put("result", map.get(str));
                } else if (TextUtils.equals(str, "memo")) {
                    jSONObject.put("memo", map.get(str));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
    }
}
