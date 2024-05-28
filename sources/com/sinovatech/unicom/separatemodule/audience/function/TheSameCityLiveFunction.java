package com.sinovatech.unicom.separatemodule.audience.function;

import com.sinovatech.unicom.separatemodule.audience.entity.AudienceDataEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ListDataEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class TheSameCityLiveFunction implements Function<String, AudienceDataEntity> {
    @Override // io.reactivex.functions.Function
    public AudienceDataEntity apply(String str) throws Exception {
        AudienceDataEntity audienceDataEntity = new AudienceDataEntity();
        JSONObject jSONObject = new JSONObject(str);
        audienceDataEntity.setStatusCode(jSONObject.optString("statusCode"));
        audienceDataEntity.setMessage(jSONObject.optString("message"));
        audienceDataEntity.setNextPageNum(jSONObject.optString("nextPageNum"));
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            ListDataEntity listDataEntity = new ListDataEntity();
            listDataEntity.setCoverImg(optJSONObject.optString("coverImg"));
            listDataEntity.setJobNumber(optJSONObject.optString("jobNumber"));
            listDataEntity.setDataType(optJSONObject.optString("dataType"));
            listDataEntity.setLivePvUrl(optJSONObject.optString("livePvUrl"));
            listDataEntity.setTypeCode(optJSONObject.optString("typeCode"));
            arrayList.add(listDataEntity);
        }
        audienceDataEntity.setList(arrayList);
        return audienceDataEntity;
    }
}
