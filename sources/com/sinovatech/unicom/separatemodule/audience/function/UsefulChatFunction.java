package com.sinovatech.unicom.separatemodule.audience.function;

import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.audience.entity.UsefulChatEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UsefulChatFunction implements Function<String, UsefulChatEntity> {
    @Override // io.reactivex.functions.Function
    public UsefulChatEntity apply(String str) throws Exception {
        UIUtils.logD("audienceActivity", "直播间常用语列表---->" + str);
        UsefulChatEntity usefulChatEntity = new UsefulChatEntity();
        JSONObject jSONObject = new JSONObject(str);
        usefulChatEntity.setStatusCode(jSONObject.optString("statusCode"));
        usefulChatEntity.setMessage(jSONObject.optString("message"));
        if ("0000".equals(usefulChatEntity.getStatusCode())) {
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(optJSONArray.optString(i));
                }
            }
            usefulChatEntity.setData(arrayList);
        }
        return usefulChatEntity;
    }
}
