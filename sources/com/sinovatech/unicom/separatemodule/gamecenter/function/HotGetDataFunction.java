package com.sinovatech.unicom.separatemodule.gamecenter.function;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.HotGetEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HotGetDataFunction implements Function<String, HotGetEntity> {
    @Override // io.reactivex.functions.Function
    public HotGetEntity apply(@NotNull String str) {
        String str2;
        HotGetEntity hotGetEntity = new HotGetEntity();
        UIUtils.logD("HotGetDataFunction", "----" + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("code");
            hotGetEntity.setDesc(jSONObject.optString("desc"));
            hotGetEntity.setCode(optString);
            if (optString.equals("0000")) {
                CacheDataCenter.getInstance().setHotGet(str);
            } else {
                String hotGet = CacheDataCenter.getInstance().getHotGet();
                if (!TextUtils.isEmpty(hotGet)) {
                    jSONObject = new JSONObject(hotGet);
                    hotGetEntity.setCode(jSONObject.optString("code"));
                    hotGetEntity.setDesc(jSONObject.optString("desc"));
                }
            }
            if (optString.equals("0000")) {
                HotGetEntity.DataBean dataBean = new HotGetEntity.DataBean();
                HotGetEntity.DataBean.CarouselBean carouselBean = new HotGetEntity.DataBean.CarouselBean();
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                dataBean.setMore_url(optJSONObject.optString("more_url"));
                JSONArray optJSONArray = optJSONObject.optJSONArray("carousel");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                    carouselBean.setDeploy_game(optJSONObject2.optString("deploy_game"));
                    carouselBean.setGameUrl(optJSONObject2.optString("gameUrl"));
                    arrayList.add(carouselBean);
                }
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("icon");
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    JSONObject optJSONObject3 = optJSONArray2.optJSONObject(i2);
                    HotGetEntity.DataBean.IconBean iconBean = new HotGetEntity.DataBean.IconBean();
                    iconBean.setGame_name(optJSONObject3.optString("game_name"));
                    iconBean.setGameUrl(optJSONObject3.optString("gameUrl"));
                    iconBean.setDeploy_game(optJSONObject3.optString("deploy_game"));
                    iconBean.setVice_title(optJSONObject3.optString("vice_title"));
                    iconBean.setBubble(optJSONObject3.optString("bubble"));
                    iconBean.setGame_persons_base(optJSONObject3.optString("game_persons_base"));
                    iconBean.setLocation(optJSONObject3.optInt("location"));
                    iconBean.setQq_mark(optJSONObject3.optString("qq_mark"));
                    iconBean.setCompany(optJSONObject3.optString("company"));
                    iconBean.setTwo_game_type(optJSONObject3.optString("two_game_type"));
                    iconBean.setFree_flow(optJSONObject3.optString("free_flow"));
                    iconBean.setId(optJSONObject3.optString("id"));
                    iconBean.setGame_id(optJSONObject3.optString("game_id"));
                    iconBean.setResource_id(optJSONObject3.optString("resource_id"));
                    iconBean.setResourceId(optJSONObject3.optString("resourceId"));
                    if (optJSONObject3.optString("group_code") != null && !optJSONObject3.optString("group_code").isEmpty()) {
                        str2 = "group_code";
                        iconBean.setGroupCode(optJSONObject3.optString(str2));
                        arrayList2.add(iconBean);
                    }
                    str2 = "groupCode";
                    iconBean.setGroupCode(optJSONObject3.optString(str2));
                    arrayList2.add(iconBean);
                }
                dataBean.setIcon(arrayList2);
                dataBean.setCarousel(arrayList);
                hotGetEntity.setData(dataBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotGetEntity;
    }
}
