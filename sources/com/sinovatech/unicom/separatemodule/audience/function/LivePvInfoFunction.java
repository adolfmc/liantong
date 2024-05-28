package com.sinovatech.unicom.separatemodule.audience.function;

import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.separatemodule.audience.entity.LivePvInfoEntity;
import io.reactivex.functions.Function;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LivePvInfoFunction implements Function<String, LivePvInfoEntity> {
    @Override // io.reactivex.functions.Function
    public LivePvInfoEntity apply(String str) throws Exception {
        JSONObject optJSONObject;
        LivePvInfoEntity livePvInfoEntity = new LivePvInfoEntity();
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("statusCode");
        livePvInfoEntity.setStatusCode(optString);
        livePvInfoEntity.setMessage(jSONObject.optString("message"));
        LivePvInfoEntity.InfoEntity infoEntity = new LivePvInfoEntity.InfoEntity();
        if ("0000".equals(optString) && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
            Gson gson = new Gson();
            String jSONObject2 = !(optJSONObject instanceof JSONObject) ? optJSONObject.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject);
            infoEntity = (LivePvInfoEntity.InfoEntity) (!(gson instanceof Gson) ? gson.fromJson(jSONObject2, (Class<Object>) LivePvInfoEntity.InfoEntity.class) : NBSGsonInstrumentation.fromJson(gson, jSONObject2, (Class<Object>) LivePvInfoEntity.InfoEntity.class));
        }
        livePvInfoEntity.setData(infoEntity);
        return livePvInfoEntity;
    }
}
