package com.sinovatech.unicom.separatemodule.gamecenter.function;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.GamesEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RecentlyGamesFunction implements Function<String, GamesEntity> {
    @Override // io.reactivex.functions.Function
    public GamesEntity apply(String str) {
        String str2;
        UIUtils.logD("最近在玩报文", str);
        GamesEntity gamesEntity = new GamesEntity();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("code");
            gamesEntity.setCode(optString);
            gamesEntity.setMsg(jSONObject.optString("msg"));
            String optString2 = jSONObject.optString("recentlyList");
            if ("0000".equals(optString) && optString2.startsWith("[")) {
                CacheDataCenter.getInstance().setGameCenterPlay(str);
            } else {
                String gameCenterPlay = CacheDataCenter.getInstance().getGameCenterPlay();
                if (!TextUtils.isEmpty(gameCenterPlay)) {
                    jSONObject = new JSONObject(gameCenterPlay);
                    optString = jSONObject.optString("code");
                    gamesEntity.setCode(optString);
                    gamesEntity.setMsg(jSONObject.optString("msg"));
                    optString2 = jSONObject.optString("popularList");
                }
            }
            if ("0000".equals(optString) && optString2.startsWith("[{")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("recentlyList");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    GamesEntity.GamesDataEntity gamesDataEntity = new GamesEntity.GamesDataEntity();
                    gamesDataEntity.setName(optJSONObject.optString("name"));
                    gamesDataEntity.setGame_name(optJSONObject.optString("game_name"));
                    gamesDataEntity.setUrl(optJSONObject.optString("url"));
                    gamesDataEntity.setResourceId(optJSONObject.optString("resourceId"));
                    gamesDataEntity.setResource_id(optJSONObject.optString("resource_id"));
                    gamesDataEntity.setId(optJSONObject.optString("id"));
                    gamesDataEntity.setGame_id(optJSONObject.optString("game_id"));
                    gamesDataEntity.setCompany(optJSONObject.optString("company"));
                    gamesDataEntity.setSmallImage(optJSONObject.optString("smallImage"));
                    gamesDataEntity.setFreeFlow(optJSONObject.optString("freeFlow"));
                    gamesDataEntity.setGameIap(optJSONObject.optString("gameIap"));
                    gamesDataEntity.setQqMark(optJSONObject.optString("qqMark"));
                    gamesDataEntity.setQq_mark(optJSONObject.optString("qq_mark"));
                    gamesDataEntity.setBoutiqueFlag(optJSONObject.optString("boutique_flag"));
                    gamesDataEntity.setLaceImgUrl(optJSONObject.optString("laceImgUrl"));
                    gamesDataEntity.setGameLabel(optJSONObject.optString("gameLabel"));
                    gamesDataEntity.setSlogan(optJSONObject.optString("slogan"));
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
