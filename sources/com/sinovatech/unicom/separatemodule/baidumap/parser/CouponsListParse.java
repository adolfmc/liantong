package com.sinovatech.unicom.separatemodule.baidumap.parser;

import com.sinovatech.unicom.separatemodule.baidumap.entity.CouponsListEntity;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CouponsListParse {
    public static CouponsListEntity parseSingleBusiness(JSONObject jSONObject) throws Exception {
        CouponsListEntity couponsListEntity = new CouponsListEntity();
        couponsListEntity.setDefaultImg(jSONObject.optString("defaultImg"));
        couponsListEntity.setBackgroundColor(jSONObject.optString("backgroundColor"));
        couponsListEntity.setBackgroundNoColor(jSONObject.optString("backgroundNoColor"));
        couponsListEntity.setDetailUrl(jSONObject.optString("detailUrl"));
        couponsListEntity.setCode(jSONObject.optString("code"));
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            CouponsListEntity.DataBean dataBean = new CouponsListEntity.DataBean();
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            dataBean.setCouponId(optJSONObject.optString("couponId"));
            dataBean.setTitle(optJSONObject.optString("title"));
            dataBean.setCost(optJSONObject.optString("cost"));
            dataBean.setSubTitle(optJSONObject.optString("subTitle"));
            dataBean.setState(optJSONObject.optString("state"));
            dataBean.setStateDesc(optJSONObject.optString("stateDesc"));
            dataBean.setPaymentType(optJSONObject.optString("paymentType"));
            dataBean.setMore(false);
            arrayList.add(dataBean);
        }
        if (optJSONArray.length() == 1) {
            CouponsListEntity.DataBean dataBean2 = new CouponsListEntity.DataBean();
            dataBean2.setMore(true);
            arrayList.add(dataBean2);
        }
        couponsListEntity.setData(arrayList);
        return couponsListEntity;
    }
}
