package com.sinovatech.unicom.separatemodule.gamecenter.function;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.GamesEntity;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GameCenterBannerFunction implements Function<String, GamesEntity> {
    @Override // io.reactivex.functions.Function
    public GamesEntity apply(@NonNull String str) throws Exception {
        UIUtils.logD("游戏中心下拉Banner报文----->", str);
        GamesEntity gamesEntity = new GamesEntity();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("code");
            gamesEntity.setCode(optString);
            gamesEntity.setMsg(jSONObject.optString("msg"));
            if ("0000".equals(optString)) {
                CacheDataCenter.getInstance().setGameBanner(str);
            } else {
                String gameBanner = CacheDataCenter.getInstance().getGameBanner();
                if (!TextUtils.isEmpty(gameBanner)) {
                    jSONObject = new JSONObject(gameBanner);
                    optString = jSONObject.optString("code");
                    gamesEntity.setCode(optString);
                    gamesEntity.setMsg(jSONObject.optString("msg"));
                }
            }
            if ("0000".equals(optString)) {
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                UIUtils.logD("游戏中心banner", !(optJSONObject instanceof JSONObject) ? optJSONObject.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject));
                String optString2 = optJSONObject.optString("carousel");
                if (optString2.startsWith("[{")) {
                    JSONArray jSONArray = new JSONArray(optString2);
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject optJSONObject2 = jSONArray.optJSONObject(i);
                        GamesEntity.GamesDataEntity gamesDataEntity = new GamesEntity.GamesDataEntity();
                        gamesDataEntity.setHotImgUrl(optJSONObject2.optString("deploy_game"));
                        gamesDataEntity.setUrl(optJSONObject2.optString("gameUrl"));
                        gamesDataEntity.setQqMark(optJSONObject2.optString("qq_mark"));
                        gamesDataEntity.setName(optJSONObject2.optString("game_name"));
                        gamesDataEntity.setTwoGameType("");
                        gamesDataEntity.setCompany(optJSONObject2.optString("company"));
                        gamesDataEntity.setId(optJSONObject2.optString("game_id"));
                        gamesDataEntity.setGameType("");
                        arrayList.add(gamesDataEntity);
                    }
                    gamesEntity.setBanner(arrayList);
                }
                String optString3 = optJSONObject.optString("icon");
                if (optString3.startsWith("[{")) {
                    JSONArray jSONArray2 = new JSONArray(optString3);
                    if (jSONArray2.length() > 0) {
                        JSONObject optJSONObject3 = jSONArray2.optJSONObject(0);
                        GamesEntity.GamesDataEntity gamesDataEntity2 = new GamesEntity.GamesDataEntity();
                        gamesDataEntity2.setSmallImage(optJSONObject3.optString("bubble"));
                        gamesDataEntity2.setUrl(optJSONObject3.optString("gameUrl"));
                        gamesDataEntity2.setName(optJSONObject3.optString("main_title"));
                        gamesDataEntity2.setGameType("center");
                        gamesEntity.setFristItem(gamesDataEntity2);
                        if (jSONArray2.length() > 1) {
                            JSONObject optJSONObject4 = jSONArray2.optJSONObject(1);
                            gamesEntity.setRightImg(optJSONObject4.optString("bubble"));
                            gamesEntity.setRightUrl(optJSONObject4.optString("gameUrl"));
                        }
                    }
                }
                String optString4 = optJSONObject.optString("advertisement");
                if (optString4.startsWith("[{")) {
                    JSONArray jSONArray3 = new JSONArray(optString4);
                    if (jSONArray3.length() > 0) {
                        gamesEntity.setHotUrl(jSONArray3.optJSONObject(0).optString("deploy_game"));
                        if (jSONArray3.length() > 1) {
                            gamesEntity.setSignInImg(jSONArray3.optJSONObject(1).optString("deploy_game"));
                            gamesEntity.setTitle(jSONArray3.optJSONObject(1).optString("gameUrl"));
                        }
                    }
                }
            }
            return gamesEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return gamesEntity;
        }
    }
}
