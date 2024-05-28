package com.sinovatech.unicom.separatemodule.gamecenter.function;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.GamesEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GameCenterFloorIconFunction implements Function<String, GamesEntity> {
    @Override // io.reactivex.functions.Function
    public GamesEntity apply(String str) {
        GamesEntity gamesEntity = new GamesEntity();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("code");
            gamesEntity.setCode(optString);
            gamesEntity.setMsg(jSONObject.optString("msg"));
            if ("0000".equals(optString)) {
                CacheDataCenter.getInstance().setGameCenterFloor2(str);
            } else {
                String gameCenterFloor2 = CacheDataCenter.getInstance().getGameCenterFloor2();
                if (!TextUtils.isEmpty(gameCenterFloor2)) {
                    jSONObject = new JSONObject(gameCenterFloor2);
                    optString = jSONObject.optString("code");
                    gamesEntity.setCode(optString);
                    gamesEntity.setMsg(jSONObject.optString("msg"));
                }
            }
            if ("0000".equals(optString)) {
                String optString2 = jSONObject.optJSONObject("data").optString("icon");
                if (optString2.startsWith("[{")) {
                    JSONArray jSONArray = new JSONArray(optString2);
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject optJSONObject = jSONArray.optJSONObject(i);
                        GamesEntity.GamesDataEntity gamesDataEntity = new GamesEntity.GamesDataEntity();
                        gamesDataEntity.setSmallImage(optJSONObject.optString("bubble"));
                        gamesDataEntity.setUrl(optJSONObject.optString("gameUrl"));
                        gamesDataEntity.setName(optJSONObject.optString("main_title"));
                        gamesDataEntity.setGameType("center");
                        arrayList.add(gamesDataEntity);
                        if (i == 0) {
                            gamesEntity.setFristItem(gamesDataEntity);
                        }
                    }
                    gamesEntity.setRecommendList(arrayList);
                }
            }
            return gamesEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return gamesEntity;
        }
    }
}
