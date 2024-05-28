package com.sinovatech.unicom.separatemodule.baidumap.parser;

import com.sinovatech.unicom.separatemodule.baidumap.entity.SearchEntity;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SearchJsonDataParser {
    public static SearchEntity parseSingleSearch(JSONObject jSONObject) throws Exception {
        SearchEntity searchEntity = new SearchEntity();
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("ehall");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                SearchEntity.EhallBean ehallBean = new SearchEntity.EhallBean();
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                ehallBean.setEpWeiDu(jSONObject2.optDouble("epWeiDu"));
                ehallBean.setEpJingDu(jSONObject2.optDouble("epJingDu"));
                ehallBean.setEpName(jSONObject2.optString("epName"));
                ehallBean.setEpBusinessTime(jSONObject2.optString("epBusinessTime"));
                ehallBean.setEpAddress(jSONObject2.optString("epAddress"));
                ehallBean.setId(jSONObject2.optString("id"));
                ehallBean.setEpBusinessImg(jSONObject2.optString("epBusinessImg"));
                ehallBean.setBusinessType(jSONObject2.optString("dataTypeFlag"));
                ehallBean.setEhall_frontAddress(jSONObject2.optString("ehall_frontAddress"));
                ehallBean.setDistance(jSONObject2.optString("distance"));
                ehallBean.setDistance1(jSONObject2.optInt("distance"));
                ehallBean.setStarScore(jSONObject2.optString("starScore"));
                ehallBean.setStarScoreImg(jSONObject2.optString("starScoreImg"));
                ehallBean.setTypeIdentifier(jSONObject2.optString("typeIdentifier"));
                ehallBean.setIsLive(jSONObject2.optString("isLive"));
                ehallBean.setWhetherLive(jSONObject2.optString("whetherLive"));
                ehallBean.setShowStarFlag(jSONObject2.optString("showStarFlag"));
                ehallBean.setEpXianname(jSONObject2.optString("epXianname"));
                ehallBean.setTypeName(jSONObject2.optString("typeName"));
                ArrayList arrayList2 = new ArrayList();
                JSONArray optJSONArray2 = jSONObject2.optJSONArray("ehallLabel");
                if (optJSONArray2 != null) {
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        arrayList2.add(optJSONArray2.optString(i2));
                    }
                }
                ehallBean.setEhallLabel(arrayList2);
                ArrayList arrayList3 = new ArrayList();
                JSONArray optJSONArray3 = jSONObject2.optJSONArray("actList");
                if (optJSONArray3 != null) {
                    for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                        SearchEntity.EhallBean.ActListBean actListBean = new SearchEntity.EhallBean.ActListBean();
                        JSONObject jSONObject3 = optJSONArray3.getJSONObject(i3);
                        actListBean.setActivityImg(jSONObject3.optString("activityImg"));
                        actListBean.setTitle(jSONObject3.optString("title"));
                        actListBean.setNewActImg(jSONObject3.optString("newActImg"));
                        actListBean.setPitches(jSONObject3.optString("pitches"));
                        actListBean.setPitches2(jSONObject3.optString("pitches2"));
                        actListBean.setPitches3(jSONObject3.optString("pitches3"));
                        actListBean.setActState(jSONObject3.optString("actState"));
                        ArrayList arrayList4 = new ArrayList();
                        JSONArray optJSONArray4 = jSONObject3.optJSONArray("newActImgs");
                        if (optJSONArray4 != null) {
                            for (int i4 = 0; i4 < optJSONArray4.length(); i4++) {
                                arrayList4.add(optJSONArray4.optString(i4));
                            }
                        }
                        actListBean.setNewActImgs(arrayList4);
                        arrayList3.add(actListBean);
                    }
                }
                ehallBean.setActList(arrayList3);
                ArrayList arrayList5 = new ArrayList();
                JSONArray optJSONArray5 = jSONObject2.optJSONArray("couponList");
                if (optJSONArray5 != null) {
                    for (int i5 = 0; i5 < optJSONArray5.length(); i5++) {
                        SearchEntity.EhallBean.CouponListBean couponListBean = new SearchEntity.EhallBean.CouponListBean();
                        couponListBean.setCouponListImg(optJSONArray5.getJSONObject(i5).optString("couponListImg"));
                        arrayList5.add(couponListBean);
                    }
                }
                ehallBean.setCouponList(arrayList5);
                ArrayList arrayList6 = new ArrayList();
                JSONArray optJSONArray6 = jSONObject2.optJSONArray("ehallLabelMsgs");
                if (optJSONArray6 != null) {
                    for (int i6 = 0; i6 < optJSONArray6.length(); i6++) {
                        arrayList6.add(optJSONArray6.optString(i6));
                    }
                }
                ehallBean.setEhallLabelMsgs(arrayList6);
                ArrayList arrayList7 = new ArrayList();
                JSONArray optJSONArray7 = jSONObject2.optJSONArray("titleList");
                if (optJSONArray7 != null) {
                    for (int i7 = 0; i7 < optJSONArray7.length(); i7++) {
                        SearchEntity.EhallBean.TitleListBean titleListBean = new SearchEntity.EhallBean.TitleListBean();
                        JSONObject jSONObject4 = optJSONArray7.getJSONObject(i7);
                        titleListBean.setIcon(jSONObject4.optString("icon"));
                        titleListBean.setTitle(jSONObject4.optString("title"));
                        arrayList7.add(titleListBean);
                    }
                }
                ehallBean.setTitleList(arrayList7);
                arrayList.add(ehallBean);
            }
        }
        searchEntity.setEhall(arrayList);
        return searchEntity;
    }
}
