package com.sinovatech.unicom.separatemodule.audience.function;

import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.separatemodule.audience.entity.GoodListEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ShopListFunction implements Function<String, List<GoodListEntity>> {
    @Override // io.reactivex.functions.Function
    public List<GoodListEntity> apply(String str) throws Exception {
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = new JSONObject(str).optJSONArray("data");
        for (int i = 0; i < optJSONArray.length(); i++) {
            Gson gson = new Gson();
            String optString = optJSONArray.optString(i);
            arrayList.add((GoodListEntity) (!(gson instanceof Gson) ? gson.fromJson(optString, (Class<Object>) GoodListEntity.class) : NBSGsonInstrumentation.fromJson(gson, optString, (Class<Object>) GoodListEntity.class)));
        }
        return arrayList;
    }
}
