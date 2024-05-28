package com.sinovatech.unicom.separatemodule.audience.function;

import android.text.TextUtils;
import com.sinovatech.unicom.separatemodule.audience.entity.AudienceDataEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ListDataEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AnchorVideoWorksFunction implements Function<String, AudienceDataEntity> {
    @Override // io.reactivex.functions.Function
    public AudienceDataEntity apply(String str) throws Exception {
        JSONObject optJSONObject;
        JSONArray optJSONArray;
        AudienceDataEntity audienceDataEntity = new AudienceDataEntity();
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("statusCode");
        audienceDataEntity.setStatusCode(optString);
        audienceDataEntity.setMessage(jSONObject.optString("message"));
        if ("0000".equals(optString) && (optJSONObject = jSONObject.optJSONObject("data")) != null && (optJSONArray = optJSONObject.optJSONArray("result")) != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                String optString2 = optJSONObject2.optJSONObject("bloggerInfoVO").optString("userId");
                JSONArray optJSONArray2 = optJSONObject2.optJSONArray("multiViewList");
                if (optJSONArray2 != null) {
                    getLiveData(optJSONArray2, arrayList);
                }
                JSONArray optJSONArray3 = optJSONObject2.optJSONArray("liveList");
                if (optJSONArray3 != null) {
                    getLiveData(optJSONArray3, arrayList);
                }
                JSONArray optJSONArray4 = optJSONObject2.optJSONArray("multiViewLiveBroadcastPlayback");
                if (optJSONArray4 != null) {
                    getArrayData(optJSONArray4, arrayList, "3", optString2);
                }
                JSONArray optJSONArray5 = optJSONObject2.optJSONArray("livePlayback");
                if (optJSONArray5 != null) {
                    getArrayData(optJSONArray5, arrayList, "3", optString2);
                }
                JSONArray optJSONArray6 = optJSONObject2.optJSONArray("smallVideo");
                if (optJSONArray6 != null) {
                    getArrayData(optJSONArray6, arrayList, "4", optString2);
                }
            }
        }
        audienceDataEntity.setList(arrayList);
        return audienceDataEntity;
    }

    private void getLiveData(JSONArray jSONArray, List<ListDataEntity> list) {
        for (int i = 0; i < jSONArray.length(); i++) {
            ListDataEntity listDataEntity = new ListDataEntity();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            listDataEntity.setVideoId(optJSONObject.optString("videoId"));
            listDataEntity.setCoverImg(optJSONObject.optString("coverImg"));
            listDataEntity.setDataType("1");
            listDataEntity.setJobNumber(optJSONObject.optString("jobNumber"));
            listDataEntity.setLivePvUrl(optJSONObject.optString("livePvUrl"));
            list.add(listDataEntity);
        }
    }

    private void getArrayData(JSONArray jSONArray, List<ListDataEntity> list, String str, String str2) {
        for (int i = 0; i < jSONArray.length(); i++) {
            ListDataEntity listDataEntity = new ListDataEntity();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            listDataEntity.setVideoId(optJSONObject.optString("videoId"));
            listDataEntity.setCoverImg(optJSONObject.optString("videoImg"));
            if (TextUtils.isEmpty(listDataEntity.getCoverImg())) {
                listDataEntity.setCoverImg(optJSONObject.optString("transcodeImg"));
            }
            listDataEntity.setDataType(str);
            listDataEntity.setJobNumber(str2);
            listDataEntity.setLivePvUrl(optJSONObject.optString("videoLink"));
            list.add(listDataEntity);
        }
    }
}
