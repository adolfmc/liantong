package com.sinovatech.unicom.separatemodule.skin.function;

import android.text.TextUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundSkinBean;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import szcom.coremedia.iso.boxes.FreeBox;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BackgroundMoreFunction implements Function<String, List<BackgroundSkinBean>> {
    private static final String TAG = "BackgroundMoreFunction";
    private String groupName;
    private String showType;
    private BackgroundSkinBean skinBean;

    public BackgroundMoreFunction(String str, BackgroundSkinBean backgroundSkinBean, String str2) {
        this.showType = str;
        this.skinBean = backgroundSkinBean;
        this.groupName = str2;
    }

    @Override // io.reactivex.functions.Function
    public List<BackgroundSkinBean> apply(String str) throws Exception {
        JSONObject optJSONObject;
        List<BackgroundSkinBean> parseTequan;
        List<BackgroundSkinBean> parseMianfei;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            if (!TextUtils.equals("0000", jSONObject.optString("code")) || (optJSONObject = jSONObject.optJSONObject("data")) == null) {
                return null;
            }
            if (!TextUtils.equals(FreeBox.TYPE, this.showType) || (parseMianfei = parseMianfei(optJSONObject.optJSONArray("list"))) == null || parseMianfei.size() <= 0) {
                if (!TextUtils.equals("privilege", this.showType) || (parseTequan = parseTequan(optJSONObject.optJSONArray("list"))) == null) {
                    return null;
                }
                if (parseTequan.size() > 0) {
                    return parseTequan;
                }
                return null;
            }
            return parseMianfei;
        } catch (Exception e) {
            String str2 = TAG;
            MsLogUtil.m7977e(str2, "解析背景数据异常:" + e.getMessage());
            return null;
        }
    }

    public List<BackgroundSkinBean> parseMianfei(JSONArray jSONArray) {
        if (jSONArray != null) {
            try {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < jSONArray.length(); i++) {
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
                                    backgroundSkinBean.setGroupName(this.groupName);
                                    backgroundSkinBean.setShowType(FreeBox.TYPE);
                                    arrayList.add(backgroundSkinBean);
                                }
                            }
                        }
                    }
                }
                return arrayList;
            } catch (Exception e) {
                String str = TAG;
                MsLogUtil.m7977e(str, "解析免费皮肤异常:" + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public List<BackgroundSkinBean> parseTequan(JSONArray jSONArray) {
        if (jSONArray != null) {
            try {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        String optString = optJSONObject.optString("productName");
                        if (!TextUtils.isEmpty(optString)) {
                            String optString2 = optJSONObject.optString("productDesc");
                            String optString3 = optJSONObject.optString("productUrl");
                            if (!TextUtils.isEmpty(optString3)) {
                                String optString4 = optJSONObject.optString("productRedirecturl");
                                String optString5 = optJSONObject.optString("productId");
                                String optString6 = optJSONObject.optJSONObject("elementMap") != null ? optJSONObject.optString("nineSkinSubtile") : "";
                                if (!TextUtils.isEmpty(optString6)) {
                                    BackgroundSkinBean backgroundSkinBean = new BackgroundSkinBean();
                                    backgroundSkinBean.setProductId(optString5);
                                    backgroundSkinBean.setProductName(optString);
                                    backgroundSkinBean.setProductImgUrl(optString3);
                                    backgroundSkinBean.setProductLinkUrl(optString4);
                                    backgroundSkinBean.setProductSubtitle(optString6);
                                    backgroundSkinBean.setProductDesc(optString2);
                                    backgroundSkinBean.setGroupName(this.groupName);
                                    backgroundSkinBean.setShowType("privilege");
                                    if (this.skinBean != null && TextUtils.equals(optString5, this.skinBean.getProductId())) {
                                        arrayList.add(0, backgroundSkinBean);
                                    } else {
                                        arrayList.add(backgroundSkinBean);
                                    }
                                }
                            }
                        }
                    }
                }
                return arrayList;
            } catch (Exception e) {
                MsLogUtil.m7977e(TAG, "解析特权皮肤异常:" + e.getMessage());
                return null;
            }
        }
        return null;
    }
}
