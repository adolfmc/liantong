package com.sinovatech.unicom.separatemodule.audience.function;

import com.cjt2325.cameralibrary.util.LogUtil;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.separatemodule.audience.entity.AngleMoreEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SharpnessEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SharpnessFunction implements Function<String, SharpnessEntity> {
    @Override // io.reactivex.functions.Function
    public SharpnessEntity apply(String str) throws Exception {
        JSONArray optJSONArray;
        SharpnessEntity sharpnessEntity = new SharpnessEntity();
        LogUtil.m16331i("SharpnessFunction=" + str);
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("statusCode");
        sharpnessEntity.setStatusCode(optString);
        sharpnessEntity.setMessage(jSONObject.optString("message"));
        sharpnessEntity.setLogo(jSONObject.optString("logo"));
        sharpnessEntity.setDataChannel(jSONObject.optString("dataChannel"));
        ArrayList arrayList = new ArrayList();
        if ("0000".equals(optString) && (optJSONArray = jSONObject.optJSONArray("data")) != null) {
            Gson gson = new Gson();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                String jSONObject3 = !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2);
                arrayList.add((SharpnessEntity.LiveUrlBean) (!(gson instanceof Gson) ? gson.fromJson(jSONObject3, (Class<Object>) SharpnessEntity.LiveUrlBean.class) : NBSGsonInstrumentation.fromJson(gson, jSONObject3, (Class<Object>) SharpnessEntity.LiveUrlBean.class)));
            }
        }
        sharpnessEntity.setPaidLive(jSONObject.optString("paidLive"));
        sharpnessEntity.setPayingUser(jSONObject.optString("payingUser"));
        sharpnessEntity.setFreeTime(jSONObject.optString("freeTime"));
        sharpnessEntity.setPaidAd(jSONObject.optString("paidAd"));
        sharpnessEntity.setPaidLinks(jSONObject.optString("paidLinks"));
        sharpnessEntity.setPromptText(jSONObject.optString("promptText"));
        sharpnessEntity.setLiveViewAngleMore(jSONObject.optString("liveViewAngleMore"));
        if (sharpnessEntity.getLiveViewAngleMore().equals("Y")) {
            ArrayList arrayList2 = new ArrayList();
            JSONArray optJSONArray2 = jSONObject.optJSONArray("liveViewAngleMoreList");
            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                JSONObject optJSONObject = optJSONArray2.optJSONObject(i2);
                AngleMoreEntity angleMoreEntity = new AngleMoreEntity();
                angleMoreEntity.setName(optJSONObject.optString("name"));
                angleMoreEntity.setCover(optJSONObject.optString("cover"));
                JSONArray optJSONArray3 = optJSONObject.optJSONArray("list");
                if (optJSONArray3 != null) {
                    Gson gson2 = new Gson();
                    ArrayList arrayList3 = new ArrayList();
                    for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                        JSONObject jSONObject4 = optJSONArray3.getJSONObject(i3);
                        String jSONObject5 = !(jSONObject4 instanceof JSONObject) ? jSONObject4.toString() : NBSJSONObjectInstrumentation.toString(jSONObject4);
                        arrayList3.add((SharpnessEntity.LiveUrlBean) (!(gson2 instanceof Gson) ? gson2.fromJson(jSONObject5, (Class<Object>) SharpnessEntity.LiveUrlBean.class) : NBSGsonInstrumentation.fromJson(gson2, jSONObject5, (Class<Object>) SharpnessEntity.LiveUrlBean.class)));
                    }
                    angleMoreEntity.setList(arrayList3);
                }
                arrayList2.add(angleMoreEntity);
            }
            AngleMoreEntity angleMoreEntity2 = new AngleMoreEntity();
            angleMoreEntity2.setName(jSONObject.optString("viewAngleNameMain"));
            ArrayList arrayList4 = new ArrayList();
            arrayList4.addAll(arrayList);
            angleMoreEntity2.setList(arrayList4);
            arrayList2.add(0, angleMoreEntity2);
            sharpnessEntity.setLiveViewAngleMoreList(arrayList2);
        }
        sharpnessEntity.setData(arrayList);
        return sharpnessEntity;
    }
}
