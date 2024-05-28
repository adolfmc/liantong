package com.sinovatech.unicom.separatemodule.audience.function;

import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.separatemodule.audience.entity.AudienceDataEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ListDataEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ListDataFunction implements Function<String, AudienceDataEntity> {
    @Override // io.reactivex.functions.Function
    public AudienceDataEntity apply(String str) throws Exception {
        JSONArray optJSONArray;
        AudienceDataEntity audienceDataEntity = new AudienceDataEntity();
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("statusCode");
        audienceDataEntity.setStatusCode(optString);
        audienceDataEntity.setMessage(jSONObject.optString("message"));
        audienceDataEntity.setNextPageNum(jSONObject.optString("nextPageNum"));
        if ("0000".equals(optString) && (optJSONArray = jSONObject.optJSONArray("data")) != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                Gson gson = new Gson();
                String jSONObject2 = !(optJSONObject instanceof JSONObject) ? optJSONObject.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject);
                arrayList.add((ListDataEntity) (!(gson instanceof Gson) ? gson.fromJson(jSONObject2, (Class<Object>) ListDataEntity.class) : NBSGsonInstrumentation.fromJson(gson, jSONObject2, (Class<Object>) ListDataEntity.class)));
            }
        }
        audienceDataEntity.setList(arrayList);
        return audienceDataEntity;
    }
}
