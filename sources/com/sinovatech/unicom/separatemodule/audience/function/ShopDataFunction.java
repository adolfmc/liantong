package com.sinovatech.unicom.separatemodule.audience.function;

import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.separatemodule.audience.entity.ShopEntity;
import io.reactivex.functions.Function;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ShopDataFunction implements Function<String, ShopEntity> {
    @Override // io.reactivex.functions.Function
    public ShopEntity apply(String str) throws Exception {
        JSONObject optJSONObject;
        ShopEntity shopEntity = new ShopEntity();
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("statusCode");
        shopEntity.setStatusCode(optString);
        shopEntity.setMessage(jSONObject.optString("message"));
        if ("0000".equals(optString) && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
            ShopEntity.DataBean dataBean = new ShopEntity.DataBean();
            dataBean.setViewNumNow(optJSONObject.optString("viewNumNow"));
            dataBean.setHaveGoods(optJSONObject.optString("haveGoods"));
            dataBean.setRoundPraise(optJSONObject.optString("roundPraise"));
            ShopEntity.DataBean.GoodsBean goodsBean = new ShopEntity.DataBean.GoodsBean();
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("goods");
            if (optJSONObject2 != null) {
                Gson gson = new Gson();
                String jSONObject2 = !(optJSONObject2 instanceof JSONObject) ? optJSONObject2.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject2);
                goodsBean = (ShopEntity.DataBean.GoodsBean) (!(gson instanceof Gson) ? gson.fromJson(jSONObject2, (Class<Object>) ShopEntity.DataBean.GoodsBean.class) : NBSGsonInstrumentation.fromJson(gson, jSONObject2, (Class<Object>) ShopEntity.DataBean.GoodsBean.class));
            }
            dataBean.setGoods(goodsBean);
            shopEntity.setData(dataBean);
        }
        return shopEntity;
    }
}
