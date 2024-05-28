package com.sinovatech.unicom.separatemodule.advtise.utils;

import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AdConfigParser {
    public static AdConfigEntity parser(JSONObject jSONObject) {
        AdConfigEntity adConfigEntity = new AdConfigEntity();
        try {
            String upperCase = jSONObject.optString("adType").toUpperCase();
            String optString = jSONObject.optString("adAction");
            String optString2 = jSONObject.optString("androidCodeId");
            String optString3 = jSONObject.optString("channel");
            boolean optBoolean = jSONObject.optBoolean("rewards");
            int optInt = jSONObject.optInt("timeOut");
            JSONObject optJSONObject = jSONObject.optJSONObject("style");
            if (optJSONObject != null) {
                adConfigEntity.setHeight(optJSONObject.optInt("top"));
                Double valueOf = Double.valueOf(optJSONObject.optDouble("scale"));
                adConfigEntity.setScale(Double.isNaN(valueOf.doubleValue()) ? 1.0d : valueOf.doubleValue());
                int optInt2 = optJSONObject.optInt("height");
                int optInt3 = optJSONObject.optInt("width");
                if (optInt2 == 0 || optInt3 == 0) {
                    adConfigEntity.setBannerHeight(90);
                    adConfigEntity.setBannerWidth(600);
                }
            } else {
                adConfigEntity.setBannerHeight(90);
                adConfigEntity.setBannerWidth(600);
            }
            adConfigEntity.setAdType(upperCase);
            adConfigEntity.setAdAction(optString);
            adConfigEntity.setCodeId(optString2);
            adConfigEntity.setChannel(optString3);
            adConfigEntity.setRewards(optBoolean);
            adConfigEntity.setTimeOutSecond(optInt);
            adConfigEntity.setDirection(jSONObject.optInt("direction"));
            adConfigEntity.setAcId(jSONObject.optString("acId"));
            adConfigEntity.setTaskId(jSONObject.optString("taskId"));
            adConfigEntity.setRemark(jSONObject.optString("remark"));
            adConfigEntity.setAdIntervals(jSONObject.optInt("adIntervals"));
            adConfigEntity.setChannelName(jSONObject.optString("channelName"));
            adConfigEntity.setUnWantedToast(jSONObject.optBoolean("unWantedToast"));
            adConfigEntity.setUnWantedToast2(jSONObject.optBoolean("unWantedToast2"));
            adConfigEntity.setAccountChannel(jSONObject.optString("accountChannel"));
            adConfigEntity.setAccountUserName(jSONObject.optString("accountUserName"));
            adConfigEntity.setAccountPassword(jSONObject.optString("accountPassword"));
            adConfigEntity.setAccountToken(jSONObject.optString("accountToken"));
            if ("PANGLE".equals(upperCase)) {
                adConfigEntity.setWaitTime(1);
            } else {
                adConfigEntity.setWaitTime(jSONObject.optInt("waitTime"));
            }
            adConfigEntity.setPrepareLoad("YES".equalsIgnoreCase(jSONObject.optString("isPrepareLoad")));
            adConfigEntity.setSubFlag(jSONObject.optString("subFlag"));
            adConfigEntity.setSubCodeId(jSONObject.optString("subCodeId"));
            adConfigEntity.setSubAdType(jSONObject.optString("subAdType"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adConfigEntity;
    }
}
