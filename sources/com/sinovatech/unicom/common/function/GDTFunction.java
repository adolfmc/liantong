package com.sinovatech.unicom.common.function;

import android.text.TextUtils;
import com.sinovatech.unicom.common.entity.GDTEntity;
import com.sinovatech.unicom.common.entity.GTDAndroidEntity;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.functions.Function;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GDTFunction implements Function<String, GDTEntity> {
    @Override // io.reactivex.functions.Function
    public GDTEntity apply(String str) throws Exception {
        MsLogUtil.m7980d("广点通响应数据===" + str);
        try {
            App.getSharePreferenceUtil().putString("guangdiantongguiyinxinxi", str);
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optString("code").equals("0000")) {
                GDTEntity gDTEntity = new GDTEntity();
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                if (optJSONObject != null) {
                    String optString = optJSONObject.optString("muid");
                    String optString2 = optJSONObject.optString("gdtMessage");
                    GDTEntity.DataBean dataBean = new GDTEntity.DataBean();
                    dataBean.setMuid(optString);
                    dataBean.setAndroidIdGdt(optString2);
                    gDTEntity.setData(dataBean);
                    if (TextUtils.isEmpty(optString2)) {
                        return null;
                    }
                    JSONObject jSONObject2 = new JSONObject(optString2);
                    String optString3 = jSONObject2.optString("accountId");
                    String optString4 = jSONObject2.optString("callback");
                    String optString5 = jSONObject2.optString("clickId");
                    String optString6 = jSONObject2.optString("clickTime");
                    String optString7 = jSONObject2.optString("deviceOsType");
                    String optString8 = jSONObject2.optString("hashAndroidId");
                    String optString9 = jSONObject2.optString("ip");
                    String optString10 = jSONObject2.optString("muid");
                    String optString11 = jSONObject2.optString("oaId");
                    String optString12 = jSONObject2.optString("promotedObjectId");
                    String optString13 = jSONObject2.optString("userAgent");
                    GTDAndroidEntity gTDAndroidEntity = new GTDAndroidEntity();
                    gTDAndroidEntity.setAccountId(optString3);
                    gTDAndroidEntity.setCallback(optString4);
                    gTDAndroidEntity.setClickId(optString5);
                    gTDAndroidEntity.setClickTime(optString6);
                    gTDAndroidEntity.setDeviceOsType(optString7);
                    gTDAndroidEntity.setHashAndroidId(optString8);
                    gTDAndroidEntity.setIp(optString9);
                    gTDAndroidEntity.setMuid(optString10);
                    gTDAndroidEntity.setOaId(optString11);
                    gTDAndroidEntity.setPromotedObjectId(optString12);
                    gTDAndroidEntity.setUserAgent(optString13);
                    gDTEntity.setEntity(gTDAndroidEntity);
                    gDTEntity.setUrl(optString4);
                    return gDTEntity;
                }
                return null;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
