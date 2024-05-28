package com.sinovatech.unicom.separatemodule.audience.function;

import com.sinovatech.unicom.separatemodule.audience.entity.GiftEntity;
import io.reactivex.functions.Function;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GiftDataFunction implements Function<String, GiftEntity> {
    @Override // io.reactivex.functions.Function
    public GiftEntity apply(String str) throws Exception {
        "0000".equals(new JSONObject(str).optString("statusCode"));
        return new GiftEntity();
    }
}
