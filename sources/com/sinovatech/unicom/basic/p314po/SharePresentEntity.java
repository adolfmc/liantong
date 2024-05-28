package com.sinovatech.unicom.basic.p314po;

import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.SharePresentEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SharePresentEntity {
    private String activityCode;
    private String activityEndTime;
    private String activityInfo;
    private String activityName;
    private String activityRemark;
    private String activityStartTime;
    private String respCode;
    private String respMsg;
    private String returnUrl;
    private String tipsIco;
    private String tipsIcoHidden;
    private String transId;
    private String urlShareTemplate;

    public String getRespCode() {
        return this.respCode;
    }

    public void setRespCode(String str) {
        this.respCode = str;
    }

    public String getRespMsg() {
        return this.respMsg;
    }

    public void setRespMsg(String str) {
        this.respMsg = str;
    }

    public String getTransId() {
        return this.transId;
    }

    public void setTransId(String str) {
        this.transId = str;
    }

    public String getActivityInfo() {
        return this.activityInfo;
    }

    public void setActivityInfo(String str) {
        this.activityInfo = str;
    }

    public String getActivityName() {
        return this.activityName;
    }

    public void setActivityName(String str) {
        this.activityName = str;
    }

    public String getActivityCode() {
        return this.activityCode;
    }

    public void setActivityCode(String str) {
        this.activityCode = str;
    }

    public String getActivityRemark() {
        return this.activityRemark;
    }

    public void setActivityRemark(String str) {
        this.activityRemark = str;
    }

    public String getActivityStartTime() {
        return this.activityStartTime;
    }

    public void setActivityStartTime(String str) {
        this.activityStartTime = str;
    }

    public String getActivityEndTime() {
        return this.activityEndTime;
    }

    public void setActivityEndTime(String str) {
        this.activityEndTime = str;
    }

    public String getReturnUrl() {
        return this.returnUrl;
    }

    public void setReturnUrl(String str) {
        this.returnUrl = str;
    }

    public String getUrlShareTemplate() {
        return this.urlShareTemplate;
    }

    public void setUrlShareTemplate(String str) {
        this.urlShareTemplate = str;
    }

    public String getTipsIco() {
        return this.tipsIco;
    }

    public void setTipsIco(String str) {
        this.tipsIco = str;
    }

    public String getTipsIcoHidden() {
        return this.tipsIcoHidden;
    }

    public void setTipsIcoHidden(String str) {
        this.tipsIcoHidden = str;
    }

    public static SharePresentEntity getSharePresentEntity(String str) {
        SharePresentEntity sharePresentEntity = new SharePresentEntity();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if ("0000".equals(jSONObject.optString("respCode"))) {
                JSONObject optJSONObject = jSONObject.optJSONObject("activityInfo");
                sharePresentEntity.setActivityCode(optJSONObject.optString("activeCode"));
                sharePresentEntity.setActivityName(optJSONObject.optString("activityName"));
                sharePresentEntity.setReturnUrl(optJSONObject.optString("returnUrl"));
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("urlShareTemlate");
                if (optJSONObject2 != null) {
                    sharePresentEntity.setActivityRemark(optJSONObject2.optString("activityRulePicture"));
                    sharePresentEntity.setTipsIco(optJSONObject2.optString("tipsIco"));
                    sharePresentEntity.setTipsIcoHidden(optJSONObject2.optString("tipsIcoHidden"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sharePresentEntity;
    }
}
