package com.sinovatech.unicom.separatemodule.gamecenter.function;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.GamesEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class KingKongDistrictFunction implements Function<String, GamesEntity> {
    @Override // io.reactivex.functions.Function
    public GamesEntity apply(String str) {
        String str2;
        GamesEntity gamesEntity = new GamesEntity();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("code");
            gamesEntity.setCode(optString);
            if ("0000".equals(optString)) {
                CacheDataCenter.getInstance().setGameKingKongDistrict(str);
            } else if (!TextUtils.isEmpty(CacheDataCenter.getInstance().getGameKingKongDistrict())) {
                optString = jSONObject.optString("code");
                gamesEntity.setCode(optString);
            }
            if ("0000".equals(optString)) {
                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                UIUtils.logD("金刚区:", !(optJSONArray instanceof JSONArray) ? optJSONArray.toString() : NBSJSONArrayInstrumentation.toString(optJSONArray));
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    GamesEntity.GamesDataEntity gamesDataEntity = new GamesEntity.GamesDataEntity();
                    gamesDataEntity.setTitle(optJSONObject.optString("title"));
                    gamesDataEntity.setAndroid_version(optJSONObject.optString("android_version"));
                    gamesDataEntity.setUrl(optJSONObject.optString("url"));
                    gamesDataEntity.setIcon(optJSONObject.optString("icon"));
                    gamesDataEntity.setId(optJSONObject.optString("id"));
                    if (optJSONObject.optString("group_code") != null && !optJSONObject.optString("group_code").isEmpty()) {
                        str2 = "group_code";
                        gamesDataEntity.setGroupCode(optJSONObject.optString(str2));
                        arrayList.add(gamesDataEntity);
                    }
                    str2 = "groupCode";
                    gamesDataEntity.setGroupCode(optJSONObject.optString(str2));
                    arrayList.add(gamesDataEntity);
                }
                gamesEntity.setData(arrayList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gamesEntity;
    }
}
