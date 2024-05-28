package com.sinovatech.unicom.separatemodule.gamecenter.function;

import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.FlowGetEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FlowGetDataFunction implements Function<String, FlowGetEntity> {
    @Override // io.reactivex.functions.Function
    public FlowGetEntity apply(String str) {
        String str2;
        UIUtils.logD("流量楼层数据", str);
        FlowGetEntity flowGetEntity = new FlowGetEntity();
        FlowGetEntity.ConfigBean configBean = new FlowGetEntity.ConfigBean();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("code");
            flowGetEntity.setCode(jSONObject.optString("code"));
            flowGetEntity.setMsg(jSONObject.optString("msg"));
            JSONObject jSONObject2 = jSONObject.getJSONObject("config");
            if ("0000".equals(optString)) {
                CacheDataCenter.getInstance().setFlowGet(str);
            } else {
                jSONObject = new JSONObject(CacheDataCenter.getInstance().getFlowGet());
                flowGetEntity.setCode(jSONObject.optString("code"));
                flowGetEntity.setMsg(jSONObject.optString("msg"));
            }
            if ("0000".equals(optString)) {
                JSONArray optJSONArray = jSONObject.optJSONArray("popularList");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    optJSONObject.optString("name");
                    FlowGetEntity.PopularListBean popularListBean = new FlowGetEntity.PopularListBean();
                    popularListBean.setName(optJSONObject.optString("name"));
                    popularListBean.setGame_name(optJSONObject.optString("game_name"));
                    popularListBean.setMinute(optJSONObject.optString("minute"));
                    popularListBean.setPersonNum(Double.valueOf(optJSONObject.optDouble("personNum")));
                    popularListBean.setBackGroundImg(optJSONObject.optString("backGroundImg"));
                    popularListBean.setUrl(optJSONObject.optString("url"));
                    popularListBean.setSmallImage(optJSONObject.optString("smallImage"));
                    popularListBean.setCurrentMinute(optJSONObject.optInt("currentMinute"));
                    popularListBean.setState(optJSONObject.optString("state"));
                    popularListBean.setCopywriting(optJSONObject.optString("copywriting"));
                    popularListBean.setTagName(optJSONObject.optString("tagName"));
                    popularListBean.setFreeFlow(optJSONObject.optString("freeFlow"));
                    popularListBean.setQqMark(optJSONObject.optString("qqMark"));
                    popularListBean.setQq_mark(optJSONObject.optString("qq_mark"));
                    popularListBean.setId(optJSONObject.optString("id"));
                    popularListBean.setGame_id(optJSONObject.optString("game_id"));
                    popularListBean.setResource_id(optJSONObject.optString("resource_id"));
                    popularListBean.setResourceId(optJSONObject.optString("resourceId"));
                    popularListBean.setCompany(optJSONObject.optString("company"));
                    if (optJSONObject.optString("group_code") != null && !optJSONObject.optString("group_code").isEmpty()) {
                        str2 = "group_code";
                        popularListBean.setGroupCode(optJSONObject.optString(str2));
                        arrayList.add(popularListBean);
                    }
                    str2 = "groupCode";
                    popularListBean.setGroupCode(optJSONObject.optString(str2));
                    arrayList.add(popularListBean);
                }
                flowGetEntity.setPopularList(arrayList);
                configBean.setImg_url(jSONObject2.getString("img_url"));
                configBean.setRight_text(jSONObject2.getString("right_text"));
                configBean.setLeft_text(jSONObject2.getString("left_text"));
                flowGetEntity.setConfig(configBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flowGetEntity;
    }
}
