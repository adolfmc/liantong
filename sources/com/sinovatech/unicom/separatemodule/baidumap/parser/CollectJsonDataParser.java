package com.sinovatech.unicom.separatemodule.baidumap.parser;

import android.graphics.Color;
import com.sinovatech.unicom.separatemodule.baidumap.entity.CollectEntity;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CollectJsonDataParser {
    public static CollectEntity parseSingleBusiness(JSONObject jSONObject) throws Exception {
        CollectEntity collectEntity = new CollectEntity();
        collectEntity.setId(jSONObject.optString("id"));
        collectEntity.setEpId(jSONObject.optString("epId"));
        collectEntity.setEpName(jSONObject.optString("epName"));
        collectEntity.setEpBusinessTime(jSONObject.optString("epBusinessTime"));
        collectEntity.setEpAddress(jSONObject.optString("epAddress"));
        collectEntity.setEpLinkTelphone(jSONObject.optString("epLinkTelphone"));
        try {
            collectEntity.setEhall_color(Color.parseColor(jSONObject.optString("ehall_color")));
        } catch (Exception e) {
            e.printStackTrace();
            collectEntity.setEhall_color(Color.parseColor("#33009eff"));
        }
        collectEntity.setEhall_radius(jSONObject.optString("ehall_radius"));
        collectEntity.setStarScore(jSONObject.optString("starScore"));
        collectEntity.setStarScoreImg(jSONObject.optString("starScoreImg"));
        collectEntity.setDistance(jSONObject.optString("distance"));
        collectEntity.setDistance1(jSONObject.optInt("distance"));
        collectEntity.setIsLineUp(jSONObject.optString("isLineUp"));
        collectEntity.setEhall_frontAddress(jSONObject.optString("ehall_frontAddress"));
        collectEntity.setEpActImg(jSONObject.optString("epActImg"));
        collectEntity.setBusinessType(jSONObject.optString("dataTypeFlag"));
        collectEntity.setEpBusinessImg(jSONObject.optString("epBusinessImg"));
        collectEntity.setDistanceUnit(jSONObject.optString("distanceUnit"));
        collectEntity.setCollectionType(jSONObject.optString("collectionType"));
        collectEntity.setCode(jSONObject.optString("code"));
        collectEntity.setMsg(jSONObject.optString("msg"));
        collectEntity.setCityCode(jSONObject.optString("cityCode"));
        collectEntity.setPinEpActImg(jSONObject.optString("PinEpActImg"));
        collectEntity.setIsLive(jSONObject.optString("isLive"));
        collectEntity.setWhetherLive(jSONObject.optString("whetherLive"));
        collectEntity.setTypeIdentifier(jSONObject.optString("typeIdentifier"));
        collectEntity.setShowStarFlag(jSONObject.optString("showStarFlag"));
        collectEntity.setEpXianname(jSONObject.optString("epXianname"));
        collectEntity.setTypeName(jSONObject.optString("typeName"));
        try {
            collectEntity.setEpJingDu(Double.parseDouble(jSONObject.optString("epJingDu")));
            collectEntity.setEpWeiDu(Double.parseDouble(jSONObject.optString("epWeiDu")));
        } catch (Exception e2) {
            e2.printStackTrace();
            collectEntity.setEpJingDu(-1.0d);
            collectEntity.setEpWeiDu(-1.0d);
        }
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("ehallLabel");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(optJSONArray.optString(i));
            }
        }
        collectEntity.setEhallLabel(arrayList);
        ArrayList arrayList2 = new ArrayList();
        JSONArray optJSONArray2 = jSONObject.optJSONArray("ehallLabelMsgs");
        if (optJSONArray2 != null) {
            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                arrayList2.add(optJSONArray2.optString(i2));
            }
        }
        collectEntity.setEhallLabelMsgs(arrayList2);
        ArrayList arrayList3 = new ArrayList();
        JSONArray optJSONArray3 = jSONObject.optJSONArray("huiList");
        if (optJSONArray3 != null) {
            for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                CollectEntity.HuiListBean huiListBean = new CollectEntity.HuiListBean();
                JSONObject jSONObject2 = optJSONArray3.getJSONObject(i3);
                huiListBean.setHuiImg(jSONObject2.getString("huiImg"));
                huiListBean.setHuiText(jSONObject2.getString("huiText"));
                arrayList3.add(huiListBean);
            }
        }
        collectEntity.setHuiList(arrayList3);
        ArrayList arrayList4 = new ArrayList();
        JSONArray optJSONArray4 = jSONObject.optJSONArray("couponList");
        if (optJSONArray4 != null) {
            for (int i4 = 0; i4 < optJSONArray4.length(); i4++) {
                CollectEntity.CouponListBean couponListBean = new CollectEntity.CouponListBean();
                couponListBean.setCouponListImg(optJSONArray4.getJSONObject(i4).optString("couponListImg"));
                arrayList4.add(couponListBean);
            }
        }
        collectEntity.setCouponList(arrayList4);
        ArrayList arrayList5 = new ArrayList();
        JSONArray optJSONArray5 = jSONObject.optJSONArray("actList");
        if (optJSONArray5 != null) {
            for (int i5 = 0; i5 < optJSONArray5.length(); i5++) {
                CollectEntity.ActListBean actListBean = new CollectEntity.ActListBean();
                JSONObject jSONObject3 = optJSONArray5.getJSONObject(i5);
                actListBean.setActivityImg(jSONObject3.optString("activityImg"));
                actListBean.setTitle(jSONObject3.optString("title"));
                actListBean.setNewActImg(jSONObject3.optString("newActImg"));
                actListBean.setPitches(jSONObject3.optString("pitches"));
                actListBean.setPitches2(jSONObject3.optString("pitches2"));
                actListBean.setPitches3(jSONObject3.optString("pitches3"));
                actListBean.setActState(jSONObject3.optString("actState"));
                ArrayList arrayList6 = new ArrayList();
                JSONArray optJSONArray6 = jSONObject3.optJSONArray("newActImgs");
                if (optJSONArray6 != null) {
                    for (int i6 = 0; i6 < optJSONArray6.length(); i6++) {
                        arrayList6.add(optJSONArray6.optString(i6));
                    }
                }
                actListBean.setNewActImgs(arrayList6);
                arrayList5.add(actListBean);
            }
        }
        collectEntity.setActList(arrayList5);
        ArrayList arrayList7 = new ArrayList();
        JSONArray optJSONArray7 = jSONObject.optJSONArray("titleList");
        if (optJSONArray7 != null) {
            for (int i7 = 0; i7 < optJSONArray7.length(); i7++) {
                CollectEntity.TitleListBean titleListBean = new CollectEntity.TitleListBean();
                JSONObject jSONObject4 = optJSONArray7.getJSONObject(i7);
                titleListBean.setIcon(jSONObject4.optString("icon"));
                titleListBean.setTitle(jSONObject4.optString("title"));
                arrayList7.add(titleListBean);
            }
        }
        collectEntity.setTitleList(arrayList7);
        return collectEntity;
    }
}
