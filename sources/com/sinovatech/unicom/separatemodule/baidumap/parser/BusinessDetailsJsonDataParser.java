package com.sinovatech.unicom.separatemodule.baidumap.parser;

import android.util.Base64;
import com.sinovatech.unicom.common.RSACryptos;
import com.sinovatech.unicom.separatemodule.baidumap.entity.BusinessDetailsEntity;
import java.net.URLDecoder;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BusinessDetailsJsonDataParser {
    public static BusinessDetailsEntity parseSingleBusinessDetails(JSONObject jSONObject) throws Exception {
        BusinessDetailsEntity businessDetailsEntity = new BusinessDetailsEntity();
        businessDetailsEntity.setId(jSONObject.optString("id"));
        businessDetailsEntity.setEpName(jSONObject.optString("epName"));
        businessDetailsEntity.setEpBusinessTime(jSONObject.optString("epBusinessTime"));
        businessDetailsEntity.setEpAddress(jSONObject.optString("epAddress"));
        businessDetailsEntity.setEpBusinessImg(jSONObject.optString("epBusinessImg"));
        businessDetailsEntity.setStarScore(jSONObject.optString("starScore"));
        businessDetailsEntity.setStarScoreImg(jSONObject.optString("starScoreImg"));
        businessDetailsEntity.setEhall_frontAddress(jSONObject.optString("ehall_frontAddress"));
        businessDetailsEntity.setIsLive(jSONObject.optString("isLive"));
        businessDetailsEntity.setWhetherLive(jSONObject.optString("whetherLive"));
        businessDetailsEntity.setComplaintUrl(jSONObject.optString("complaintUrl"));
        businessDetailsEntity.setTypeIdentifier(jSONObject.optString("typeIdentifier"));
        businessDetailsEntity.setShowStarFlag(jSONObject.optString("showStarFlag"));
        businessDetailsEntity.setEpXianname(jSONObject.optString("epXianname"));
        businessDetailsEntity.setTypeName(jSONObject.optString("typeName"));
        String optString = jSONObject.optString("epLinkTelphone");
        try {
            Long.parseLong(optString);
        } catch (Exception e) {
            try {
                optString = new String(RSACryptos.decryptByPrivateKeyForSpilt(Base64.decode(URLDecoder.decode(optString, "UTF-8"), 2), Base64.decode("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJCxhm90liVd3oS5uikBTIvKTQXGxL2E/dVPKLYMgsFV8R/GOv0gxzUJK7IcNr2QRIiHyuWDIHwO+dZb3TNz7ErE+cDZ509Eak8N0scTLvw8PoVRcUEzrGEtSVpmFRmIvJH6+2SC02hI+4OHxDdSTq49QGOyWq/PklXu5WS27PSTAgMBAAECgYA9FoYwhiNh8Uih9pvO9hpwJl/wYWPss7pLFZmViqClqXgctx9ugSk5jf6huuGtlLwqfFJAJeVQGy6FvtEcCQQoQ2kb089XOVijf04XltEqFCUVxkUnE+ENtcKCnOSwNXFNQbP25caAyT/0Sf6FqsXAUkSX0EaUMa9e0k76YWsM2QJBAOpIKlVRPET03Q+nnqhMnwZz0Jp7+ytOVTrXCxZcMy1dzRK08y7S97OCbp+pCUYGrKo+NMjk57HLmqF6Vch+NZUCQQCeG01AEAlsupkQx1PIl9adc8qL8fVCqhqTmZBG39UQM0lVoBBBkUv5nxzTuaobwaKXNbepQVXw+yQPJK3yISeHAkEAlvtY5NDMcXgIOr2APt/aIDNk/RnnXRpHTPsm9wsGJDduIJ8ilUt6PGJTXmt2QX2tqq0aIVl7g5Y+GdCYFfRYHQI/HkMbhieLpkQRCCUe5EYrzfdbzW2ChEAK1jWOaAJvxaoLX1hDxEkLQbwyyFPBO47UkBy4Cq12xalMPZnHsZCnAkEAwIt5qL9RTy9LYDq4NI00zKB+/8iuKtery+V+sdE7xrCfI6tDH1Y/qSz5IcdTq2Da0XokZtZkx2sjPYFaF7T49A==", 2)));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            e.printStackTrace();
        }
        businessDetailsEntity.setEpLinkTelphone(optString);
        businessDetailsEntity.setDistance(jSONObject.optString("distance"));
        businessDetailsEntity.setDistance1(jSONObject.optInt("distance"));
        businessDetailsEntity.setBusinessType(jSONObject.optString("dataTypeFlag"));
        businessDetailsEntity.setCollectionStatus(jSONObject.optString("collectionStatus"));
        businessDetailsEntity.setCode(jSONObject.optString("code"));
        businessDetailsEntity.setMsg(jSONObject.optString("msg"));
        businessDetailsEntity.setCityCode(jSONObject.optString("cityCode"));
        try {
            businessDetailsEntity.setEpJingDu(Double.parseDouble(jSONObject.optString("epJingDu")));
            businessDetailsEntity.setEpWeiDu(Double.parseDouble(jSONObject.optString("epWeiDu")));
        } catch (Exception e3) {
            e3.printStackTrace();
            businessDetailsEntity.setEpJingDu(0.0d);
            businessDetailsEntity.setEpWeiDu(0.0d);
        }
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("ehallLabel");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(optJSONArray.optString(i));
            }
        }
        businessDetailsEntity.setBusinsessPic(arrayList);
        ArrayList arrayList2 = new ArrayList();
        JSONArray optJSONArray2 = jSONObject.optJSONArray("huiList");
        if (optJSONArray2 != null) {
            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                BusinessDetailsEntity.HuiListBean huiListBean = new BusinessDetailsEntity.HuiListBean();
                JSONObject jSONObject2 = optJSONArray2.getJSONObject(i2);
                huiListBean.setHuiImg(jSONObject2.optString("huiImg"));
                huiListBean.setHuiText(jSONObject2.optString("huiText"));
                arrayList2.add(huiListBean);
            }
        }
        businessDetailsEntity.setHuiList(arrayList2);
        ArrayList arrayList3 = new ArrayList();
        JSONArray optJSONArray3 = jSONObject.optJSONArray("actList");
        if (optJSONArray3 != null) {
            for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                BusinessDetailsEntity.ActListBean actListBean = new BusinessDetailsEntity.ActListBean();
                JSONObject jSONObject3 = optJSONArray3.getJSONObject(i3);
                actListBean.setTitle(jSONObject3.optString("title"));
                actListBean.setNewActImg(jSONObject3.optString("newActImg"));
                actListBean.setDescribe(jSONObject3.optString("describe"));
                actListBean.setActUrl(jSONObject3.optString("actUrl"));
                actListBean.setDetailImg(jSONObject3.optString("detailImg"));
                actListBean.setActivityImg(jSONObject3.optString("activityImg"));
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
        businessDetailsEntity.setActList(arrayList3);
        ArrayList arrayList5 = new ArrayList();
        JSONArray optJSONArray5 = jSONObject.optJSONArray("dynamicList");
        if (optJSONArray5 != null) {
            for (int i5 = 0; i5 < optJSONArray5.length(); i5++) {
                BusinessDetailsEntity.DynamicListBean dynamicListBean = new BusinessDetailsEntity.DynamicListBean();
                dynamicListBean.setMessage(optJSONArray5.getJSONObject(i5).optString("message"));
                arrayList5.add(dynamicListBean);
            }
        }
        businessDetailsEntity.setDynamicList(arrayList5);
        ArrayList arrayList6 = new ArrayList();
        JSONArray optJSONArray6 = jSONObject.optJSONArray("couponList");
        if (optJSONArray6 != null) {
            for (int i6 = 0; i6 < optJSONArray6.length(); i6++) {
                BusinessDetailsEntity.CouponListBean couponListBean = new BusinessDetailsEntity.CouponListBean();
                JSONObject jSONObject4 = optJSONArray6.getJSONObject(i6);
                couponListBean.setCouponImg(jSONObject4.optString("couponImg"));
                couponListBean.setCouponName(jSONObject4.optString("couponName"));
                couponListBean.setCompany(jSONObject4.optString("company"));
                couponListBean.setEffectiveTime(jSONObject4.optString("effectiveTime"));
                couponListBean.setLostEffectiveTime(jSONObject4.optString("lostEffectiveTime"));
                couponListBean.setLootingImg(jSONObject4.optString("lootingImg"));
                couponListBean.setCouponGetImg(jSONObject4.optString("couponGetImg"));
                couponListBean.setCouponUseImg(jSONObject4.optString("couponUseImg"));
                couponListBean.setCouponListImg(jSONObject4.optString("couponListImg"));
                couponListBean.setCouponUseURL(jSONObject4.optString("couponUseURL"));
                couponListBean.setIsDraw(jSONObject4.optString("isDraw"));
                couponListBean.setHaveReceivedImg(jSONObject4.optString("haveReceivedImg"));
                couponListBean.setId(jSONObject4.optString("id"));
                arrayList6.add(couponListBean);
            }
        }
        businessDetailsEntity.setCouponList(arrayList6);
        ArrayList arrayList7 = new ArrayList();
        JSONArray optJSONArray7 = jSONObject.optJSONArray("ehallLabelMsgs");
        if (optJSONArray7 != null) {
            for (int i7 = 0; i7 < optJSONArray7.length(); i7++) {
                arrayList7.add(optJSONArray7.optString(i7));
            }
        }
        businessDetailsEntity.setEhallLabelMsgs(arrayList7);
        ArrayList arrayList8 = new ArrayList();
        JSONArray optJSONArray8 = jSONObject.optJSONArray("titleList");
        if (optJSONArray8 != null) {
            for (int i8 = 0; i8 < optJSONArray8.length(); i8++) {
                BusinessDetailsEntity.TitleListBean titleListBean = new BusinessDetailsEntity.TitleListBean();
                JSONObject jSONObject5 = optJSONArray8.getJSONObject(i8);
                titleListBean.setIcon(jSONObject5.optString("icon"));
                titleListBean.setTitle(jSONObject5.optString("title"));
                arrayList8.add(titleListBean);
            }
        }
        businessDetailsEntity.setTitleList(arrayList8);
        return businessDetailsEntity;
    }
}
