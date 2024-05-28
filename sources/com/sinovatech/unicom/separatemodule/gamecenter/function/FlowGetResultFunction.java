package com.sinovatech.unicom.separatemodule.gamecenter.function;

import android.text.TextUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.FlowGetResultEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.HotGamesEntity;
import io.reactivex.functions.Function;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FlowGetResultFunction implements Function<String, FlowGetResultEntity> {
    @Override // io.reactivex.functions.Function
    public FlowGetResultEntity apply(String str) {
        UIUtils.logD("领流量结果报文-->", str);
        FlowGetResultEntity flowGetResultEntity = new FlowGetResultEntity();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("code");
            flowGetResultEntity.setCode(optString);
            flowGetResultEntity.setMsg(jSONObject.optString("respDesc"));
            if ("0012".equals(optString) && TextUtils.isEmpty(flowGetResultEntity.getMsg())) {
                flowGetResultEntity.setMsg("本奖励仅支持联通手机用户");
            }
            if ("0000".equals(optString)) {
                JSONObject jSONObject2 = new JSONObject(jSONObject.optString("data"));
                HotGamesEntity.HotGame hotGame = new HotGamesEntity.HotGame();
                hotGame.setName(jSONObject2.optString("name"));
                hotGame.setUrl(jSONObject2.optString("url"));
                hotGame.setResourceId(jSONObject2.optString("resourceId"));
                hotGame.setResource_id(jSONObject2.optString("resource_id"));
                hotGame.setId(jSONObject2.optString("id"));
                hotGame.setCompany(jSONObject2.optString("company"));
                hotGame.setSmallImage(jSONObject2.optString("smallImage"));
                hotGame.setFreeFlow(jSONObject2.optString("freeFlow"));
                hotGame.setGameIap(jSONObject2.optString("gameIap"));
                hotGame.setQqMark(jSONObject2.optString("qqMark"));
                hotGame.setCurrentMinute(jSONObject2.optInt("currentMinute"));
                hotGame.setGameType(jSONObject2.optString("gameType"));
                hotGame.setMinute(jSONObject2.optString("minute"));
                hotGame.setState(jSONObject2.optString("state"));
                hotGame.setTwoGameType(jSONObject2.optString("twoGameType"));
                hotGame.setCopywriting(jSONObject2.optString("copywriting"));
                hotGame.setBoutiqueFlag(jSONObject2.optString("boutique_flag"));
                hotGame.setLaceImgUrl(jSONObject2.optString("laceImgUrl"));
                hotGame.setGameLabel(jSONObject2.optString("gameLabel"));
                flowGetResultEntity.setData(hotGame);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flowGetResultEntity;
    }
}
