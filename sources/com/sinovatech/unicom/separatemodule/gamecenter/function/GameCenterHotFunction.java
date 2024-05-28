package com.sinovatech.unicom.separatemodule.gamecenter.function;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.HotGamesEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GameCenterHotFunction implements Function<String, HotGamesEntity> {
    @Override // io.reactivex.functions.Function
    public HotGamesEntity apply(String str) {
        HotGamesEntity hotGamesEntity = new HotGamesEntity();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("code");
            hotGamesEntity.setCode(optString);
            hotGamesEntity.setReceiveFlag(jSONObject.optString("receiveFlag"));
            String optString2 = jSONObject.optString("popularList");
            if ("0000".equals(optString) && optString2.startsWith("[")) {
                CacheDataCenter.getInstance().setGameCenterHot(str);
            } else {
                String gameCenterHot = CacheDataCenter.getInstance().getGameCenterHot();
                if (!TextUtils.isEmpty(gameCenterHot)) {
                    jSONObject = new JSONObject(gameCenterHot);
                    optString = jSONObject.optString("code");
                    hotGamesEntity.setCode(optString);
                    hotGamesEntity.setReceiveFlag(jSONObject.optString("receiveFlag"));
                    optString2 = jSONObject.optString("popularList");
                }
            }
            if ("0000".equals(optString) && optString2.startsWith("[")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("popularList");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    HotGamesEntity.HotGame hotGame = new HotGamesEntity.HotGame();
                    hotGame.setName(optJSONObject.optString("name"));
                    hotGame.setUrl(optJSONObject.optString("url"));
                    hotGame.setResourceId(optJSONObject.optString("resourceId"));
                    hotGame.setResource_id(optJSONObject.optString("resource_id"));
                    hotGame.setId(optJSONObject.optString("id"));
                    hotGame.setCompany(optJSONObject.optString("company"));
                    hotGame.setSmallImage(optJSONObject.optString("smallImage"));
                    hotGame.setFreeFlow(optJSONObject.optString("freeFlow"));
                    hotGame.setGameIap(optJSONObject.optString("gameIap"));
                    hotGame.setBackGroundImg(optJSONObject.optString("backGroundImg"));
                    hotGame.setPersonNum(optJSONObject.optString("personNum"));
                    hotGame.setCurrentMinute(optJSONObject.optInt("currentMinute"));
                    hotGame.setQqMark(optJSONObject.optString("qqMark"));
                    hotGame.setGameType(optJSONObject.optString("gameType"));
                    hotGame.setMinute(optJSONObject.optString("minute"));
                    hotGame.setState(optJSONObject.optString("state"));
                    hotGame.setTwoGameType(optJSONObject.optString("twoGameType"));
                    hotGame.setCopywriting(optJSONObject.optString("copywriting"));
                    hotGame.setBoutiqueFlag(optJSONObject.optString("boutique_flag"));
                    hotGame.setLaceImgUrl(optJSONObject.optString("laceImgUrl"));
                    hotGame.setGameLabel(optJSONObject.optString("gameLabel"));
                    arrayList.add(hotGame);
                }
                hotGamesEntity.setPopularList(arrayList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotGamesEntity;
    }
}
