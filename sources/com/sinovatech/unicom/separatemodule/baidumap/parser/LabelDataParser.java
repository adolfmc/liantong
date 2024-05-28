package com.sinovatech.unicom.separatemodule.baidumap.parser;

import com.sinovatech.unicom.separatemodule.baidumap.entity.LabelDataEntity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LabelDataParser {
    public static List<LabelDataEntity> parseSingleBusiness(JSONArray jSONArray) throws Exception {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            LabelDataEntity labelDataEntity = new LabelDataEntity();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            labelDataEntity.setTitleDetial(optJSONObject.optString("titleDetial"));
            labelDataEntity.setTitleFlag(optJSONObject.optString("titleFlag"));
            labelDataEntity.setChargeFlag(optJSONObject.optString("chargeFlag"));
            JSONArray optJSONArray = optJSONObject.optJSONArray("unifiedLabel");
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                LabelDataEntity.UnifiedLabelBean unifiedLabelBean = new LabelDataEntity.UnifiedLabelBean();
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                unifiedLabelBean.setServiceLabel(optJSONObject2.optString("serviceLabel"));
                unifiedLabelBean.setLabelFlag(optJSONObject2.optString("labelFlag"));
                unifiedLabelBean.setId(optJSONObject2.optString("id"));
                arrayList2.add(unifiedLabelBean);
            }
            labelDataEntity.setUnifiedLabel(arrayList2);
            arrayList.add(labelDataEntity);
        }
        return arrayList;
    }
}
