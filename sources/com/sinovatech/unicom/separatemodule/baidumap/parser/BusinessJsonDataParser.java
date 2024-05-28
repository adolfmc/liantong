package com.sinovatech.unicom.separatemodule.baidumap.parser;

import android.graphics.Color;
import com.sinovatech.unicom.separatemodule.baidumap.entity.BusinessEntity;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BusinessJsonDataParser {
    public static BusinessEntity parseSingleBusiness(JSONObject jSONObject) throws Exception {
        BusinessEntity businessEntity = new BusinessEntity();
        businessEntity.setLiveFrontAddress(jSONObject.optString("liveFrontAddress"));
        businessEntity.setLiveFlag(jSONObject.optString("liveFlag"));
        businessEntity.setCode(jSONObject.optString("code"));
        businessEntity.setMsg(jSONObject.optString("msg"));
        businessEntity.setNum(jSONObject.optString("num"));
        JSONArray optJSONArray = jSONObject.optJSONArray("dynamicList");
        ArrayList arrayList = new ArrayList();
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                BusinessEntity.DynamicListBean dynamicListBean = new BusinessEntity.DynamicListBean();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                try {
                    dynamicListBean.setCommentsAmount(optJSONObject.getString("commentsAmount"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    dynamicListBean.setCommentsAmount("");
                }
                try {
                    dynamicListBean.setHorizontalPicture(optJSONObject.getString("horizontalPicture"));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    dynamicListBean.setHorizontalPicture("");
                }
                try {
                    dynamicListBean.setLikesNum(optJSONObject.getString("likesNum"));
                } catch (JSONException e3) {
                    e3.printStackTrace();
                    dynamicListBean.setLikesNum("");
                }
                try {
                    dynamicListBean.setViewsNum(optJSONObject.getString("viewsNum"));
                } catch (JSONException e4) {
                    e4.printStackTrace();
                    dynamicListBean.setViewsNum("");
                }
                try {
                    dynamicListBean.setDynamicAddress(optJSONObject.getString("dynamicAddress"));
                } catch (JSONException e5) {
                    e5.printStackTrace();
                    dynamicListBean.setDynamicAddress("");
                }
                dynamicListBean.setTopic(optJSONObject.getString("topic"));
                arrayList.add(dynamicListBean);
            }
        }
        businessEntity.setDynamicList(arrayList);
        JSONArray optJSONArray2 = jSONObject.optJSONArray("livingList");
        ArrayList arrayList2 = new ArrayList();
        if (optJSONArray2 != null) {
            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                BusinessEntity.LivingListBean livingListBean = new BusinessEntity.LivingListBean();
                JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                try {
                    livingListBean.setHallName(optJSONObject2.optString("hallName"));
                } catch (Exception e6) {
                    e6.printStackTrace();
                    livingListBean.setHallName("");
                }
                try {
                    livingListBean.setHeadImg(optJSONObject2.optString("headImg"));
                } catch (Exception e7) {
                    e7.printStackTrace();
                    livingListBean.setHeadImg("");
                }
                try {
                    livingListBean.setLiveUrl(optJSONObject2.optString("liveUrl"));
                } catch (Exception e8) {
                    e8.printStackTrace();
                    livingListBean.setLiveUrl("");
                }
                try {
                    livingListBean.setLiving(optJSONObject2.optString("living"));
                } catch (Exception e9) {
                    e9.printStackTrace();
                    livingListBean.setLiving("");
                }
                arrayList2.add(livingListBean);
            }
        }
        businessEntity.setLivingList(arrayList2);
        JSONArray optJSONArray3 = jSONObject.optJSONArray("ehallList");
        ArrayList arrayList3 = new ArrayList();
        if (optJSONArray3 != null) {
            for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                BusinessEntity.BusinessBean businessBean = new BusinessEntity.BusinessBean();
                JSONObject optJSONObject3 = optJSONArray3.optJSONObject(i3);
                businessBean.setId(optJSONObject3.optString("id"));
                try {
                    businessBean.setEpJingDu(Double.parseDouble(optJSONObject3.optString("epJingDu")));
                    businessBean.setEpWeiDu(Double.parseDouble(optJSONObject3.optString("epWeiDu")));
                } catch (Exception e10) {
                    e10.printStackTrace();
                    businessBean.setEpJingDu(-1.0d);
                    businessBean.setEpWeiDu(-1.0d);
                }
                businessBean.setEpName(optJSONObject3.optString("epName"));
                try {
                    businessBean.setEpBusinessTime(optJSONObject3.optString("epBusinessTime"));
                } catch (Exception e11) {
                    e11.printStackTrace();
                    businessBean.setEpBusinessTime("");
                }
                businessBean.setEpAddress(optJSONObject3.optString("epAddress"));
                businessBean.setDistance(optJSONObject3.optString("distance"));
                businessBean.setDistance1(optJSONObject3.optInt("distance"));
                businessBean.setBusinessType(optJSONObject3.optString("dataTypeFlag"));
                businessBean.setEpBusinessImg(optJSONObject3.optString("epBusinessImg"));
                businessBean.setPinEpActImg(optJSONObject3.optString("PinEpActImg"));
                businessBean.setStarScore(optJSONObject3.optString("starScore"));
                businessBean.setStarScoreImg(optJSONObject3.optString("starScoreImg"));
                businessBean.setEhall_frontAddress(optJSONObject3.optString("ehall_frontAddress"));
                businessBean.setDistanceUnit(optJSONObject3.optString("distanceUnit"));
                businessBean.setIsLive(optJSONObject3.optString("isLive"));
                businessBean.setWhetherLive(optJSONObject3.optString("whetherLive"));
                businessBean.setEhall_radius(optJSONObject3.optString("ehall_radius"));
                businessBean.setCityCode(optJSONObject3.optString("cityCode"));
                businessBean.setTypeIdentifier(optJSONObject3.optString("typeIdentifier"));
                businessBean.setShowStarFlag(optJSONObject3.optString("showStarFlag"));
                businessBean.setEpXianname(optJSONObject3.optString("epXianname"));
                businessBean.setTypeName(optJSONObject3.optString("typeName"));
                businessBean.setDynamicTitle(optJSONObject3.optString("dynamicTitle"));
                businessBean.setDynamicTopicColor(optJSONObject3.optString("dynamicTopicColor"));
                try {
                    businessBean.setEhall_color(Color.parseColor(optJSONObject3.optString("ehall_color")));
                } catch (Exception e12) {
                    e12.printStackTrace();
                    businessBean.setEhall_color(Color.parseColor(optJSONObject3.optString("#33009eff")));
                }
                ArrayList arrayList4 = new ArrayList();
                JSONArray optJSONArray4 = optJSONObject3.optJSONArray("couponList");
                if (optJSONArray4 != null) {
                    for (int i4 = 0; i4 < optJSONArray4.length(); i4++) {
                        BusinessEntity.BusinessBean.CouponListBean couponListBean = new BusinessEntity.BusinessBean.CouponListBean();
                        couponListBean.setCouponListImg(optJSONArray4.getJSONObject(i4).optString("couponListImg"));
                        arrayList4.add(couponListBean);
                    }
                }
                businessBean.setCouponList(arrayList4);
                ArrayList arrayList5 = new ArrayList();
                JSONArray optJSONArray5 = optJSONObject3.optJSONArray("actList");
                if (optJSONArray5 != null) {
                    for (int i5 = 0; i5 < optJSONArray5.length(); i5++) {
                        BusinessEntity.BusinessBean.ActListBean actListBean = new BusinessEntity.BusinessBean.ActListBean();
                        JSONObject jSONObject2 = optJSONArray5.getJSONObject(i5);
                        actListBean.setActivityImg(jSONObject2.optString("activityImg"));
                        actListBean.setTitle(jSONObject2.optString("title"));
                        actListBean.setNewActImg(jSONObject2.optString("newActImg"));
                        actListBean.setPitches(jSONObject2.optString("pitches"));
                        actListBean.setPitches2(jSONObject2.optString("pitches2"));
                        actListBean.setPitches3(jSONObject2.optString("pitches3"));
                        actListBean.setActState(jSONObject2.optString("actState"));
                        ArrayList arrayList6 = new ArrayList();
                        JSONArray optJSONArray6 = jSONObject2.optJSONArray("newActImgs");
                        if (optJSONArray6 != null) {
                            for (int i6 = 0; i6 < optJSONArray6.length(); i6++) {
                                arrayList6.add(optJSONArray6.optString(i6));
                            }
                        }
                        actListBean.setNewActImgs(arrayList6);
                        arrayList5.add(actListBean);
                    }
                }
                businessBean.setActList(arrayList5);
                ArrayList arrayList7 = new ArrayList();
                JSONArray optJSONArray7 = optJSONObject3.optJSONArray("ehallLabel");
                if (optJSONArray7 != null) {
                    for (int i7 = 0; i7 < optJSONArray7.length(); i7++) {
                        arrayList7.add(optJSONArray7.optString(i7));
                    }
                }
                businessBean.setEhallLabel(arrayList7);
                ArrayList arrayList8 = new ArrayList();
                JSONArray optJSONArray8 = optJSONObject3.optJSONArray("ehallLabelMsgs");
                if (optJSONArray8 != null) {
                    for (int i8 = 0; i8 < optJSONArray8.length(); i8++) {
                        arrayList8.add(optJSONArray8.optString(i8));
                    }
                }
                businessBean.setEhallLabelMsgs(arrayList8);
                ArrayList arrayList9 = new ArrayList();
                JSONArray optJSONArray9 = optJSONObject3.optJSONArray("titleList");
                if (optJSONArray9 != null) {
                    for (int i9 = 0; i9 < optJSONArray9.length(); i9++) {
                        BusinessEntity.BusinessBean.TitleListBean titleListBean = new BusinessEntity.BusinessBean.TitleListBean();
                        JSONObject jSONObject3 = optJSONArray9.getJSONObject(i9);
                        titleListBean.setIcon(jSONObject3.optString("icon"));
                        titleListBean.setTitle(jSONObject3.optString("title"));
                        arrayList9.add(titleListBean);
                    }
                }
                businessBean.setTitleList(arrayList9);
                arrayList3.add(businessBean);
            }
        }
        businessEntity.setEhallList(arrayList3);
        return businessEntity;
    }
}
