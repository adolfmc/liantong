package com.sinovatech.unicom.separatemodule.skin.function;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundBannerEntity;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundEntity;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundSkinBean;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundSkinEntity;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundTongYongBean;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import szcom.coremedia.iso.boxes.FreeBox;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BackgroundFunction implements Function<String, BackgroundEntity> {
    private static final String TAG = "BackgroundFunction";
    private BackgroundSkinBean skinBean;

    public BackgroundFunction(BackgroundSkinBean backgroundSkinBean) {
        this.skinBean = backgroundSkinBean;
    }

    @Override // io.reactivex.functions.Function
    public BackgroundEntity apply(String str) throws Exception {
        JSONObject optJSONObject;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            if (!TextUtils.equals("0000", jSONObject.optString("code")) || (optJSONObject = jSONObject.optJSONObject("data")) == null) {
                return null;
            }
            CacheDataCenter.getInstance().setSkinCache(str);
            BackgroundEntity backgroundEntity = new BackgroundEntity();
            List<BackgroundBannerEntity> parseBanner = parseBanner(optJSONObject.optJSONArray("rotationInfo"));
            if (parseBanner != null && parseBanner.size() > 0) {
                backgroundEntity.setBannerEntities(parseBanner);
            }
            ArrayList arrayList = new ArrayList();
            BackgroundSkinEntity backgroundSkinEntity = new BackgroundSkinEntity();
            String optString = optJSONObject.optString("freeSkinName");
            if (TextUtils.isEmpty(optString)) {
                optString = "免费皮肤";
            }
            List<BackgroundSkinBean> parseMianfei = parseMianfei(optString, optJSONObject.optJSONArray("freeSkinInfo"));
            if (parseMianfei != null && parseMianfei.size() > 0) {
                backgroundSkinEntity.setGroupName(optString);
                backgroundSkinEntity.setShowType(FreeBox.TYPE);
                backgroundSkinEntity.setGroupName(optString);
                backgroundSkinEntity.setSkinList(parseMianfei);
                arrayList.add(backgroundSkinEntity);
            }
            BackgroundSkinEntity backgroundSkinEntity2 = new BackgroundSkinEntity();
            String optString2 = optJSONObject.optString("privilegeSkinName");
            if (TextUtils.isEmpty(optString2)) {
                optString2 = "特权皮肤";
            }
            List<BackgroundSkinBean> parseTequan = parseTequan(optString2, optJSONObject.optJSONArray("privilegeSkinInfo"));
            if (parseTequan != null && parseTequan.size() > 0) {
                backgroundSkinEntity2.setGroupName(optString2);
                backgroundSkinEntity2.setShowType("privilege");
                backgroundSkinEntity2.setSkinList(parseTequan);
                arrayList.add(backgroundSkinEntity2);
            }
            backgroundEntity.setSkinEntities(arrayList);
            BackgroundTongYongBean parseTongYong = parseTongYong(optJSONObject.optJSONArray("currencySkinInfo"));
            if (parseTongYong != null) {
                backgroundEntity.setTongYongBean(parseTongYong);
            }
            return backgroundEntity;
        } catch (Exception e) {
            String str2 = TAG;
            MsLogUtil.m7977e(str2, "解析背景数据异常:" + e.getMessage());
            return null;
        }
    }

    private BackgroundTongYongBean parseTongYong(JSONArray jSONArray) {
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 0) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(0);
                    String optString = optJSONObject.optString("defaultPicLink");
                    String optString2 = optJSONObject.optString("previewPicLink");
                    BackgroundTongYongBean backgroundTongYongBean = new BackgroundTongYongBean();
                    backgroundTongYongBean.setDefaultPicLink(optString);
                    backgroundTongYongBean.setPreviewPicLink(optString2);
                    return backgroundTongYongBean;
                }
                return null;
            } catch (Exception e) {
                String str = TAG;
                MsLogUtil.m7977e(str, "解析通用皮肤数据异常:" + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public List<BackgroundSkinBean> parseMianfei(String str, JSONArray jSONArray) {
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 0) {
                    ArrayList arrayList = new ArrayList();
                    int length = jSONArray.length() > 4 ? 4 : jSONArray.length();
                    boolean z = false;
                    for (int i = 0; i < length; i++) {
                        JSONObject optJSONObject = jSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            String optString = optJSONObject.optString("skinName");
                            if (!TextUtils.isEmpty(optString)) {
                                String optString2 = optJSONObject.optString("skinDescription");
                                String optString3 = optJSONObject.optString("backPic");
                                if (!TextUtils.isEmpty(optString3)) {
                                    String optString4 = optJSONObject.optString("productRedirecturl");
                                    String optString5 = optJSONObject.optString("id");
                                    String optString6 = optJSONObject.optString("navigationBar");
                                    String optString7 = optJSONObject.optString("bottomLabel");
                                    String optString8 = optJSONObject.optString("skinSubtitle");
                                    if (!TextUtils.isEmpty(optString8)) {
                                        BackgroundSkinBean backgroundSkinBean = new BackgroundSkinBean();
                                        backgroundSkinBean.setProductId(optString5);
                                        backgroundSkinBean.setProductName(optString);
                                        backgroundSkinBean.setProductImgUrl(optString3);
                                        backgroundSkinBean.setProductLinkUrl(optString4);
                                        backgroundSkinBean.setProductSubtitle(optString8);
                                        backgroundSkinBean.setProductDesc(optString2);
                                        backgroundSkinBean.setTitleColor(optString6);
                                        backgroundSkinBean.setBottomIcon(optString7);
                                        backgroundSkinBean.setShowType(FreeBox.TYPE);
                                        backgroundSkinBean.setGroupName(str);
                                        if (this.skinBean != null && TextUtils.equals(FreeBox.TYPE, this.skinBean.getShowType()) && TextUtils.equals(optString5, this.skinBean.getProductId())) {
                                            arrayList.add(0, backgroundSkinBean);
                                            z = true;
                                        } else {
                                            arrayList.add(backgroundSkinBean);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!z && this.skinBean != null && TextUtils.equals(FreeBox.TYPE, this.skinBean.getShowType())) {
                        arrayList.add(0, this.skinBean);
                    }
                    return arrayList.size() > 4 ? arrayList.subList(0, 4) : arrayList;
                }
            } catch (Exception e) {
                MsLogUtil.m7977e(TAG, "解析免费皮肤异常:" + e.getMessage());
                return null;
            }
        }
        if (this.skinBean == null || !TextUtils.equals(this.skinBean.getShowType(), FreeBox.TYPE)) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(this.skinBean);
        return arrayList2;
    }

    public List<BackgroundSkinBean> parseTequan(String str, JSONArray jSONArray) {
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 0) {
                    ArrayList arrayList = new ArrayList();
                    boolean z = false;
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject optJSONObject = jSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            String optString = optJSONObject.optString("productName");
                            if (!TextUtils.isEmpty(optString) && !TextUtils.equals("1", optString)) {
                                String optString2 = optJSONObject.optString("productDesc");
                                String optString3 = optJSONObject.optString("productUrl");
                                if (!TextUtils.isEmpty(optString3)) {
                                    String optString4 = optJSONObject.optString("productRedirecturl");
                                    String optString5 = optJSONObject.optString("productId");
                                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("elementMap");
                                    String optString6 = optJSONObject2 != null ? optJSONObject2.optString("nineSkinSubtile") : "";
                                    if (!TextUtils.isEmpty(optString6)) {
                                        BackgroundSkinBean backgroundSkinBean = new BackgroundSkinBean();
                                        backgroundSkinBean.setProductId(optString5);
                                        backgroundSkinBean.setProductName(optString);
                                        backgroundSkinBean.setProductImgUrl(optString3);
                                        backgroundSkinBean.setProductLinkUrl(optString4);
                                        backgroundSkinBean.setProductSubtitle(optString6);
                                        backgroundSkinBean.setProductDesc(optString2);
                                        backgroundSkinBean.setShowType("privilege");
                                        backgroundSkinBean.setGroupName(str);
                                        if (this.skinBean != null && TextUtils.equals("privilege", this.skinBean.getShowType()) && TextUtils.equals(optString5, this.skinBean.getProductId())) {
                                            arrayList.add(0, backgroundSkinBean);
                                            z = true;
                                        } else {
                                            arrayList.add(backgroundSkinBean);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!z && this.skinBean != null && TextUtils.equals("privilege", this.skinBean.getShowType())) {
                        arrayList.add(0, this.skinBean);
                    }
                    return arrayList.size() > 4 ? arrayList.subList(0, 4) : arrayList;
                }
            } catch (Exception e) {
                MsLogUtil.m7977e(TAG, "解析特权皮肤异常:" + e.getMessage());
                return null;
            }
        }
        if (this.skinBean == null || !TextUtils.equals(this.skinBean.getShowType(), "privilege")) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(this.skinBean);
        return arrayList2;
    }

    public List<BackgroundBannerEntity> parseBanner(JSONArray jSONArray) {
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject optJSONObject = jSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            String optString = optJSONObject.optString("businessLinkUrl");
                            if (!TextUtils.isEmpty(optString) && !TextUtils.equals("null", optString)) {
                                String optString2 = optJSONObject.optString("backPic");
                                if (!TextUtils.isEmpty(optString2) && !TextUtils.equals("null", optString2)) {
                                    String optString3 = optJSONObject.optString("content");
                                    String optString4 = optJSONObject.optString("productBusinessName");
                                    BackgroundBannerEntity backgroundBannerEntity = new BackgroundBannerEntity();
                                    backgroundBannerEntity.setUrl(optString);
                                    backgroundBannerEntity.setImg(optString2);
                                    backgroundBannerEntity.setContent(optString3);
                                    backgroundBannerEntity.setProductBusinessName(optString4);
                                    arrayList.add(backgroundBannerEntity);
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                return null;
            } catch (Exception unused) {
                MsLogUtil.m7977e(TAG, "解析banner数据异常");
                return null;
            }
        }
        return null;
    }
}
