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
public class GameCenterFloorFunction implements Function<String, GamesEntity> {
    @Override // io.reactivex.functions.Function
    public GamesEntity apply(String str) {
        GamesEntity gamesEntity = new GamesEntity();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("code");
            gamesEntity.setCode(optString);
            gamesEntity.setMsg(jSONObject.optString("msg"));
            if ("0000".equals(optString)) {
                CacheDataCenter.getInstance().setGameCenterFloor(str);
            } else {
                String gameCenterFloor = CacheDataCenter.getInstance().getGameCenterFloor();
                if (!TextUtils.isEmpty(gameCenterFloor)) {
                    jSONObject = new JSONObject(gameCenterFloor);
                    optString = jSONObject.optString("code");
                    gamesEntity.setCode(optString);
                    gamesEntity.setMsg(jSONObject.optString("msg"));
                }
            }
            if ("0000".equals(optString)) {
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                JSONArray optJSONArray = optJSONObject.optJSONArray("poster");
                StringBuilder sb = new StringBuilder();
                sb.append("==");
                sb.append(!(optJSONArray instanceof JSONArray) ? optJSONArray.toString() : NBSJSONArrayInstrumentation.toString(optJSONArray));
                UIUtils.logD("海报数据", sb.toString());
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    CacheDataCenter.getInstance().setGameCenterPoster(!(optJSONArray instanceof JSONArray) ? optJSONArray.toString() : NBSJSONArrayInstrumentation.toString(optJSONArray));
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                        if (optJSONObject2 != null) {
                            GamesEntity.PosterData posterData = new GamesEntity.PosterData();
                            String optString2 = optJSONObject2.optString("deploy_game");
                            String optString3 = optJSONObject2.optString("qq_mark");
                            String optString4 = optJSONObject2.optString("gameUrl");
                            String optString5 = optJSONObject2.optString("game_name");
                            String optString6 = optJSONObject2.optString("company");
                            String optString7 = optJSONObject2.optString("game_id");
                            String optString8 = optJSONObject2.optString("is_action");
                            if (TextUtils.equals("null", optString2)) {
                                optString2 = "";
                            }
                            posterData.setDeploy_game(optString2);
                            if (TextUtils.equals("null", optString3)) {
                                optString3 = "";
                            }
                            posterData.setQq_mark(optString3);
                            if (TextUtils.equals("null", optString6)) {
                                optString6 = "";
                            }
                            posterData.setCompany(optString6);
                            if (TextUtils.equals("null", optString7)) {
                                optString7 = "";
                            }
                            posterData.setGame_id(optString7);
                            if (TextUtils.equals("null", optString5)) {
                                optString5 = "";
                            }
                            posterData.setGame_name(optString5);
                            if (TextUtils.equals("null", optString4)) {
                                optString4 = "";
                            }
                            posterData.setGameUrl(optString4);
                            if (TextUtils.equals("null", optString8)) {
                                optString8 = "";
                            }
                            posterData.setIs_action(optString8);
                            arrayList.add(posterData);
                        }
                    }
                    gamesEntity.setPoster(arrayList);
                }
                String optString9 = optJSONObject.optString("icon");
                if (optString9.startsWith("[{")) {
                    JSONArray jSONArray = new JSONArray(optString9);
                    if (jSONArray.length() > 0) {
                        JSONObject optJSONObject3 = jSONArray.optJSONObject(0);
                        GamesEntity.GamesDataEntity gamesDataEntity = new GamesEntity.GamesDataEntity();
                        gamesDataEntity.setSmallImage(optJSONObject3.optString("bubble"));
                        gamesDataEntity.setUrl(optJSONObject3.optString("gameUrl"));
                        gamesDataEntity.setName(optJSONObject3.optString("main_title"));
                        gamesDataEntity.setGameType("center");
                        gamesEntity.setFristItem(gamesDataEntity);
                        if (jSONArray.length() > 1) {
                            JSONObject optJSONObject4 = jSONArray.optJSONObject(1);
                            gamesEntity.setRightImg(optJSONObject4.optString("bubble"));
                            gamesEntity.setRightUrl(optJSONObject4.optString("gameUrl"));
                        }
                    }
                }
                String optString10 = optJSONObject.optString("advertisement");
                if (optString10.startsWith("[{")) {
                    JSONArray jSONArray2 = new JSONArray(optString10);
                    if (jSONArray2.length() > 0) {
                        gamesEntity.setHotUrl(jSONArray2.optJSONObject(0).optString("deploy_game"));
                        if (jSONArray2.length() > 1) {
                            gamesEntity.setSignInImg(jSONArray2.optJSONObject(1).optString("deploy_game"));
                            gamesEntity.setTitle(jSONArray2.optJSONObject(1).optString("gameUrl"));
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
