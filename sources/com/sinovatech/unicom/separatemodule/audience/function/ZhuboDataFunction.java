package com.sinovatech.unicom.separatemodule.audience.function;

import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.common.PreferenceConstUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity;
import io.reactivex.functions.Function;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ZhuboDataFunction implements Function<String, ZhuboDataEntity> {
    @Override // io.reactivex.functions.Function
    public ZhuboDataEntity apply(String str) throws Exception {
        JSONObject optJSONObject;
        ZhuboDataEntity zhuboDataEntity = new ZhuboDataEntity();
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("statusCode");
        zhuboDataEntity.setStatusCode(optString);
        zhuboDataEntity.setMessage(jSONObject.optString("message"));
        ZhuboDataEntity.AnchorInfoBean anchorInfoBean = new ZhuboDataEntity.AnchorInfoBean();
        if ("0000".equals(optString) && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
            String optString2 = optJSONObject.optString("liveRoomNotice");
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("anchorInfo");
            zhuboDataEntity.setLiveRoomNotice(optString2);
            Gson gson = new Gson();
            String jSONObject2 = !(optJSONObject2 instanceof JSONObject) ? optJSONObject2.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject2);
            anchorInfoBean = (ZhuboDataEntity.AnchorInfoBean) (!(gson instanceof Gson) ? gson.fromJson(jSONObject2, (Class<Object>) ZhuboDataEntity.AnchorInfoBean.class) : NBSGsonInstrumentation.fromJson(gson, jSONObject2, (Class<Object>) ZhuboDataEntity.AnchorInfoBean.class));
            App.getSharePreferenceUtil().putString(PreferenceConstUtils.shareUserNumSc(), optJSONObject.optString("shareUserNumSc"));
        }
        zhuboDataEntity.setAnchorInfoBean(anchorInfoBean);
        return zhuboDataEntity;
    }
}
