package com.sinovatech.unicom.separatemodule.audience.function;

import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.separatemodule.audience.entity.ZhuangXiuEntity;
import io.reactivex.functions.Function;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ZhuangxiuDataFunction implements Function<String, ZhuangXiuEntity> {
    @Override // io.reactivex.functions.Function
    public ZhuangXiuEntity apply(String str) throws Exception {
        ZhuangXiuEntity.Renovation renovation;
        JSONObject optJSONObject;
        ZhuangXiuEntity zhuangXiuEntity = new ZhuangXiuEntity();
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("statusCode");
        zhuangXiuEntity.setStatusCode(optString);
        zhuangXiuEntity.setMessage(jSONObject.optString("message"));
        ZhuangXiuEntity.Renovation renovation2 = new ZhuangXiuEntity.Renovation();
        ZhuangXiuEntity.Renovation renovation3 = new ZhuangXiuEntity.Renovation();
        ZhuangXiuEntity.Renovation renovation4 = new ZhuangXiuEntity.Renovation();
        ZhuangXiuEntity.Renovation renovation5 = new ZhuangXiuEntity.Renovation();
        ZhuangXiuEntity.Renovation renovation6 = new ZhuangXiuEntity.Renovation();
        ZhuangXiuEntity.Renovation renovation7 = new ZhuangXiuEntity.Renovation();
        ZhuangXiuEntity.Renovation renovation8 = new ZhuangXiuEntity.Renovation();
        if (!"0000".equals(optString) || (optJSONObject = jSONObject.optJSONObject("data")) == null) {
            renovation = renovation8;
        } else {
            Gson gson = new Gson();
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("ad_renovation_01");
            String jSONObject2 = !(optJSONObject2 instanceof JSONObject) ? optJSONObject2.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject2);
            boolean z = gson instanceof Gson;
            renovation2 = (ZhuangXiuEntity.Renovation) (!z ? gson.fromJson(jSONObject2, (Class<Object>) ZhuangXiuEntity.Renovation.class) : NBSGsonInstrumentation.fromJson(gson, jSONObject2, (Class<Object>) ZhuangXiuEntity.Renovation.class));
            JSONObject optJSONObject3 = optJSONObject.optJSONObject("ad_renovation_02");
            String jSONObject3 = !(optJSONObject3 instanceof JSONObject) ? optJSONObject3.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject3);
            renovation3 = (ZhuangXiuEntity.Renovation) (!z ? gson.fromJson(jSONObject3, (Class<Object>) ZhuangXiuEntity.Renovation.class) : NBSGsonInstrumentation.fromJson(gson, jSONObject3, (Class<Object>) ZhuangXiuEntity.Renovation.class));
            JSONObject optJSONObject4 = optJSONObject.optJSONObject("ad_renovation_03");
            String jSONObject4 = !(optJSONObject4 instanceof JSONObject) ? optJSONObject4.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject4);
            ZhuangXiuEntity.Renovation renovation9 = (ZhuangXiuEntity.Renovation) (!z ? gson.fromJson(jSONObject4, (Class<Object>) ZhuangXiuEntity.Renovation.class) : NBSGsonInstrumentation.fromJson(gson, jSONObject4, (Class<Object>) ZhuangXiuEntity.Renovation.class));
            JSONObject optJSONObject5 = optJSONObject.optJSONObject("paster_renovation_01");
            String jSONObject5 = !(optJSONObject5 instanceof JSONObject) ? optJSONObject5.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject5);
            ZhuangXiuEntity.Renovation renovation10 = (ZhuangXiuEntity.Renovation) (!z ? gson.fromJson(jSONObject5, (Class<Object>) ZhuangXiuEntity.Renovation.class) : NBSGsonInstrumentation.fromJson(gson, jSONObject5, (Class<Object>) ZhuangXiuEntity.Renovation.class));
            JSONObject optJSONObject6 = optJSONObject.optJSONObject("paster_renovation_02");
            String jSONObject6 = !(optJSONObject6 instanceof JSONObject) ? optJSONObject6.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject6);
            ZhuangXiuEntity.Renovation renovation11 = (ZhuangXiuEntity.Renovation) (!z ? gson.fromJson(jSONObject6, (Class<Object>) ZhuangXiuEntity.Renovation.class) : NBSGsonInstrumentation.fromJson(gson, jSONObject6, (Class<Object>) ZhuangXiuEntity.Renovation.class));
            JSONObject optJSONObject7 = optJSONObject.optJSONObject("paster_renovation_03");
            String jSONObject7 = !(optJSONObject7 instanceof JSONObject) ? optJSONObject7.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject7);
            ZhuangXiuEntity.Renovation renovation12 = (ZhuangXiuEntity.Renovation) (!z ? gson.fromJson(jSONObject7, (Class<Object>) ZhuangXiuEntity.Renovation.class) : NBSGsonInstrumentation.fromJson(gson, jSONObject7, (Class<Object>) ZhuangXiuEntity.Renovation.class));
            JSONObject optJSONObject8 = optJSONObject.optJSONObject("float_img_01");
            String jSONObject8 = !(optJSONObject8 instanceof JSONObject) ? optJSONObject8.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject8);
            renovation = (ZhuangXiuEntity.Renovation) (!z ? gson.fromJson(jSONObject8, (Class<Object>) ZhuangXiuEntity.Renovation.class) : NBSGsonInstrumentation.fromJson(gson, jSONObject8, (Class<Object>) ZhuangXiuEntity.Renovation.class));
            renovation4 = renovation9;
            renovation5 = renovation10;
            renovation6 = renovation11;
            renovation7 = renovation12;
        }
        zhuangXiuEntity.setAdRenovation01(renovation2);
        zhuangXiuEntity.setAdRenovation02(renovation3);
        zhuangXiuEntity.setAdRenovation03(renovation4);
        zhuangXiuEntity.setPasterRenovation01(renovation5);
        zhuangXiuEntity.setPasterRenovation02(renovation6);
        zhuangXiuEntity.setPasterRenovation03(renovation7);
        zhuangXiuEntity.setFloatImg01(renovation);
        return zhuangXiuEntity;
    }
}
