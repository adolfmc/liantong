package com.sinovatech.unicom.separatemodule.audience.function;

import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.audience.entity.LianMZBEntity;
import io.reactivex.functions.Function;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class InteractionAnchorsInfoFunction implements Function<String, LianMZBEntity> {
    @Override // io.reactivex.functions.Function
    public LianMZBEntity apply(String str) throws Exception {
        JSONObject optJSONObject;
        UIUtils.logD("audienceActivity", "连麦主播信息---->" + str);
        LianMZBEntity lianMZBEntity = new LianMZBEntity();
        JSONObject jSONObject = new JSONObject(str);
        lianMZBEntity.setStatusCode(jSONObject.optString("statusCode"));
        lianMZBEntity.setMessage(jSONObject.optString("message"));
        if ("0000".equals(lianMZBEntity.getStatusCode()) && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
            Gson gson = new Gson();
            String jSONObject2 = !(optJSONObject instanceof JSONObject) ? optJSONObject.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject);
            lianMZBEntity.setData((LianMZBEntity.AnchorsInfoEntity) (!(gson instanceof Gson) ? gson.fromJson(jSONObject2, (Class<Object>) LianMZBEntity.AnchorsInfoEntity.class) : NBSGsonInstrumentation.fromJson(gson, jSONObject2, (Class<Object>) LianMZBEntity.AnchorsInfoEntity.class)));
        }
        return lianMZBEntity;
    }
}
