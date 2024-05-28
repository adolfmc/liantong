package com.sinovatech.unicom.basic.p315ui.home.function;

import android.graphics.Color;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeCardBgEntity;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeMergeEntity;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeMergeFuChuangEntity;
import com.sinovatech.unicom.basic.p315ui.manager.HomeSearchNewUserManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.separatemodule.live.capture.util.TimeUtil;
import io.reactivex.functions.Function;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.home.function.HomeMergeFunction */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeMergeFunction implements Function<String, HomeMergeEntity> {
    @Override // io.reactivex.functions.Function
    public HomeMergeEntity apply(String str) throws Exception {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        if (TextUtils.equals("0000", jSONObject.optString("code"))) {
            HomeMergeEntity homeMergeEntity = new HomeMergeEntity();
            JSONObject optJSONObject = jSONObject.optJSONObject("HomeFusion.backGroundQuery");
            if (optJSONObject != null) {
                homeMergeEntity.setCardBgEntity(parseBg(optJSONObject));
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject("HomeFusion.fuchuangAdv_A8");
            if (optJSONObject2 != null) {
                homeMergeEntity.setAdvertiseEntity(parseXuanFuChuang(optJSONObject2));
            }
            return homeMergeEntity;
        }
        return null;
    }

    public HomeCardBgEntity parseBg(JSONObject jSONObject) {
        JSONObject optJSONObject;
        String str;
        JSONObject optJSONObject2 = jSONObject.optJSONObject("isNew");
        if (optJSONObject2 != null) {
            HomeSearchNewUserManager.setNewUserData(optJSONObject2);
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("result");
        if (optJSONArray == null || optJSONArray.length() <= 0 || (optJSONObject = optJSONArray.optJSONObject(0)) == null) {
            return null;
        }
        HomeCardBgEntity homeCardBgEntity = new HomeCardBgEntity();
        String optString = optJSONObject.optString("icons");
        String optString2 = optJSONObject.optString("productRedirecturl");
        String optString3 = optJSONObject.optString("productUrl");
        String optString4 = optJSONObject.optString("isLogin");
        String optString5 = optJSONObject.optString("cornerDesc");
        String optString6 = optJSONObject.optString("productCode");
        JSONObject optJSONObject3 = optJSONObject.optJSONObject("elementMap");
        String optString7 = optJSONObject.optString("textPictureColor");
        String str2 = "";
        String optString8 = optJSONObject3 != null ? optJSONObject3.optString("nine_fgsyhfzs") : "";
        JSONObject optJSONObject4 = optJSONObject.optJSONObject("wxHomePage");
        if (optJSONObject4 != null) {
            String optString9 = optJSONObject4.optString("goodsId");
            String optString10 = optJSONObject4.optString("actType");
            String optString11 = optJSONObject4.optString("actId");
            String optString12 = optJSONObject4.optString("imgDynamic");
            str = optJSONObject4.optString("wealthBoolean");
            homeCardBgEntity.setGoodsId(optString9);
            homeCardBgEntity.setActId(optString11);
            homeCardBgEntity.setActType(optString10);
            str2 = optString12;
        } else {
            str = "";
        }
        homeCardBgEntity.setProductName(str);
        homeCardBgEntity.setIsLogin(optString4);
        homeCardBgEntity.setProductRedirecturl(optString2);
        homeCardBgEntity.setProductUrl(optString3);
        homeCardBgEntity.setProductDesc(optString);
        homeCardBgEntity.setCornerDesc(optString5);
        homeCardBgEntity.setProductCode(optString6);
        homeCardBgEntity.setMobile(UserManager.getInstance().getCurrentPhoneNumber());
        homeCardBgEntity.setCacheTime(TimeUtil.getCurrentDate());
        homeCardBgEntity.setBackgroundPriority(optString8);
        homeCardBgEntity.setImgDynamic(str2);
        if (TextUtils.isEmpty(optString3)) {
            homeCardBgEntity.setCornerDesc("");
        }
        if (TextUtils.equals("1", str) && TextUtils.isEmpty(optString3)) {
            homeCardBgEntity.setProductName("0");
        }
        try {
            Color.parseColor(optString7);
        } catch (Exception unused) {
            optString7 = "";
        }
        homeCardBgEntity.setTextPictureColor(optString7);
        return homeCardBgEntity;
    }

    public HomeMergeFuChuangEntity parseXuanFuChuang(JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("advCntList");
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return null;
            }
            try {
                HomeMergeFuChuangEntity homeMergeFuChuangEntity = new HomeMergeFuChuangEntity();
                JSONObject optJSONObject = optJSONArray.optJSONObject(0);
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("images");
                if (optJSONArray2 == null || optJSONArray2.length() <= 0) {
                    return null;
                }
                JSONObject optJSONObject2 = optJSONArray2.optJSONObject(0);
                homeMergeFuChuangEntity.setTitleColor(optJSONObject.optInt("titleColor"));
                homeMergeFuChuangEntity.setAdvertiseTitle(optJSONObject2.optString("title"));
                homeMergeFuChuangEntity.setAdvertiseTargetURL(optJSONObject2.optString("targetUrl"));
                homeMergeFuChuangEntity.setAdvertiseImageURL(notNull(optJSONObject2.optString("imageSrc")));
                homeMergeFuChuangEntity.setAdvertiseTargetType(optJSONObject2.optString("targetType"));
                homeMergeFuChuangEntity.setIdx(optJSONObject2.optString("idx"));
                homeMergeFuChuangEntity.setAdvertiseIndex(optJSONObject2.optString("idx"));
                if ("0".equals(optJSONObject2.optString("isNeedLogin"))) {
                    homeMergeFuChuangEntity.setNeedLogin(true);
                } else {
                    homeMergeFuChuangEntity.setNeedLogin(false);
                }
                homeMergeFuChuangEntity.setAdvertiseBackMode(optJSONObject2.optString("backMode", "1"));
                homeMergeFuChuangEntity.setAdvJson(optJSONObject2.optString("advJson"));
                homeMergeFuChuangEntity.setViceTitle(optJSONObject2.optString("viceTitle"));
                homeMergeFuChuangEntity.setHandleNumber(optJSONObject2.optString("handleNumber"));
                homeMergeFuChuangEntity.setRightImgSrc(optJSONObject2.optString("rightImgSrc"));
                homeMergeFuChuangEntity.setImageSrcVII(notNull(optJSONObject2.optString("imageSrcVII")));
                homeMergeFuChuangEntity.setImageSrcVIIChecked(notNull(optJSONObject2.optString("imageSrcVIIChecked")));
                homeMergeFuChuangEntity.setCityCode(UserManager.getInstance().getCurrentCityCode());
                homeMergeFuChuangEntity.setProvinceCode(UserManager.getInstance().getCurrentProvinceCode());
                JSONObject optJSONObject3 = optJSONObject2.optJSONObject("wxFuchuang");
                if (optJSONObject3 != null) {
                    String optString = optJSONObject3.optString("goodsId");
                    String optString2 = optJSONObject3.optString("actType");
                    String optString3 = optJSONObject3.optString("actId");
                    homeMergeFuChuangEntity.setGoodsId(optString);
                    homeMergeFuChuangEntity.setActId(optString3);
                    homeMergeFuChuangEntity.setActType(optString2);
                }
                if (UserManager.getInstance().isYiwang()) {
                    homeMergeFuChuangEntity.setYwCode("0");
                } else {
                    homeMergeFuChuangEntity.setYwCode("1");
                }
                return homeMergeFuChuangEntity;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private String notNull(String str) {
        return "null".equals(str) ? "" : str;
    }
}
