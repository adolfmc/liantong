package com.sinovatech.unicom.separatemodule.audience.function;

import com.sinovatech.unicom.separatemodule.audience.entity.AttentionAnchorVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.AttentionVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AttentionAnchorsFunction implements Function<String, BaseVideoEntity<AttentionAnchorVideoEntity>> {
    @Override // io.reactivex.functions.Function
    public BaseVideoEntity<AttentionAnchorVideoEntity> apply(String str) throws Exception {
        BaseVideoEntity<AttentionAnchorVideoEntity> baseVideoEntity = new BaseVideoEntity<>();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        baseVideoEntity.setNextPageNum(jSONObject.optString("nextPageNum"));
        baseVideoEntity.setRedDotState(jSONObject.optString("redDotState"));
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        if (optJSONObject != null) {
            AttentionAnchorVideoEntity attentionAnchorVideoEntity = new AttentionAnchorVideoEntity();
            ArrayList arrayList = new ArrayList();
            attentionAnchorVideoEntity.setAnchors(arrayList);
            addItem2List(arrayList, optJSONObject.optJSONArray("dsjLiveList"));
            addItem2List(arrayList, optJSONObject.optJSONArray("zbLiveList"));
            addItem2List(arrayList, optJSONObject.optJSONArray("dsjPlayBackList"));
            addItem2List(arrayList, optJSONObject.optJSONArray("zbPlayBackList"));
            addItem2List(arrayList, optJSONObject.optJSONArray("smallVideoList"));
            attentionAnchorVideoEntity.setNextPageNum(baseVideoEntity.getNextPageNum());
            baseVideoEntity.setData(attentionAnchorVideoEntity);
        }
        return baseVideoEntity;
    }

    private void addItem2List(List<AttentionVideoEntity> list, JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            AttentionVideoEntity attentionVideoEntity = new AttentionVideoEntity();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            attentionVideoEntity.setDataType(optJSONObject.optString("dataType"));
            attentionVideoEntity.setHeadImage(optJSONObject.optString("headImage"));
            attentionVideoEntity.setJobNumber(optJSONObject.optString("jobNumber"));
            attentionVideoEntity.setNikeName(optJSONObject.optString("nikeName"));
            attentionVideoEntity.setType(optJSONObject.optString("type"));
            JSONArray optJSONArray = optJSONObject.optJSONArray("ids");
            String[] strArr = new String[optJSONArray.length()];
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                strArr[i2] = optJSONArray.optString(i2);
            }
            attentionVideoEntity.setIds(strArr);
            list.add(attentionVideoEntity);
        }
    }
}
