package com.sinovatech.unicom.separatemodule.audience.function;

import android.text.TextUtils;
import com.sinovatech.unicom.separatemodule.audience.entity.LuckyDrawResultEntity;
import io.reactivex.functions.Function;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LuckyDrawResultFunction implements Function<String, LuckyDrawResultEntity> {
    @Override // io.reactivex.functions.Function
    public LuckyDrawResultEntity apply(String str) throws Exception {
        LuckyDrawResultEntity luckyDrawResultEntity = new LuckyDrawResultEntity();
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("respCode");
            String optString2 = jSONObject.optString("prizeType");
            String optString3 = jSONObject.optString("prizeName");
            String optString4 = jSONObject.optString("prizeImg");
            String optString5 = jSONObject.optString("receivePrompt");
            String optString6 = jSONObject.optString("receiveHref");
            luckyDrawResultEntity.setRespCode(optString);
            luckyDrawResultEntity.setPrizeType(optString2);
            luckyDrawResultEntity.setPrizeName(optString3);
            luckyDrawResultEntity.setPrizeImg(optString4);
            luckyDrawResultEntity.setReceivePrompt(optString5);
            luckyDrawResultEntity.setReceiveHref(optString6);
        }
        return luckyDrawResultEntity;
    }
}
