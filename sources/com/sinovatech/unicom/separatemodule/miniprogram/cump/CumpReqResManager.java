package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import android.content.Context;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.p318ui.App;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CumpReqResManager {
    public static Observable<CumpResponse> createRequest(Context context, String str, String str2, Map<String, String> map) {
        return App.getAsyncHttpClient().rxPost(str, getParams(context, str2, map)).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, CumpResponse>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpReqResManager.1
            @Override // io.reactivex.functions.Function
            public CumpResponse apply(String str3) throws Exception {
                CumpResponse cumpResponse = new CumpResponse();
                JSONObject jSONObject = new JSONObject(str3);
                cumpResponse.setRespCode(jSONObject.getJSONObject("response").getJSONObject("head").optString("respCode", ""));
                cumpResponse.setRespMsg(jSONObject.getJSONObject("response").getJSONObject("head").optString("respMsg", ""));
                cumpResponse.setSeq(jSONObject.getJSONObject("response").getJSONObject("head").optString("seq", ""));
                cumpResponse.setBody(jSONObject.getJSONObject("response").getJSONObject("body"));
                return cumpResponse;
            }
        });
    }

    private static String getParams(Context context, String str, Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        try {
            jSONObject3.put("interNo", str);
            jSONObject3.put("seq", uuid());
            jSONObject3.put("reqTime", String.valueOf(System.currentTimeMillis()));
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    jSONObject4.put(entry.getKey(), entry.getValue());
                }
            }
            jSONObject4.put("appVersion", context.getString(2131886969));
            jSONObject2.put("head", jSONObject3);
            jSONObject2.put("body", jSONObject4);
            jSONObject.put("request", jSONObject2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
    }

    private static String uuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }
}
