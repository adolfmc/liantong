package com.sinovatech.unicom.separatemodule.audience.function;

import android.text.TextUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.audience.entity.ActivityTimeEntity;
import io.reactivex.functions.Function;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ActivityTimeFunction implements Function<String, ActivityTimeEntity> {
    @Override // io.reactivex.functions.Function
    public ActivityTimeEntity apply(String str) throws Exception {
        ActivityTimeEntity activityTimeEntity = new ActivityTimeEntity();
        UIUtils.logD("chatroom_push_all", "活动消息---->" + str);
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("respCode");
            String optString2 = jSONObject.optString("acId");
            String optString3 = jSONObject.optString("acName");
            String optString4 = jSONObject.optString("acType");
            Long valueOf = Long.valueOf(jSONObject.optLong("preheatTime"));
            String optString5 = jSONObject.optString("preheatImg");
            String optString6 = jSONObject.optString("durationTime");
            Long valueOf2 = Long.valueOf(jSONObject.optLong("beginTime"));
            Long valueOf3 = Long.valueOf(jSONObject.optLong("endTime"));
            String optString7 = jSONObject.optString("ifPartakeActivity");
            activityTimeEntity.setRespCode(optString);
            activityTimeEntity.setAcId(optString2);
            activityTimeEntity.setAcName(optString3);
            activityTimeEntity.setAcType(optString4);
            activityTimeEntity.setPreheatTime(valueOf);
            activityTimeEntity.setPreheatImg(optString5);
            activityTimeEntity.setDurationTime(optString6);
            activityTimeEntity.setBeginTime(valueOf2);
            activityTimeEntity.setEndTime(valueOf3);
            activityTimeEntity.setIfPartakeActivity(optString7);
            activityTimeEntity.setPopupActivityHref(jSONObject.optString("popupActivityHref"));
            activityTimeEntity.setPopupActivityImg(jSONObject.optString("popupActivityImg"));
        }
        return activityTimeEntity;
    }
}
