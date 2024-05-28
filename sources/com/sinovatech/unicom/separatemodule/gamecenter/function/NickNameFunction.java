package com.sinovatech.unicom.separatemodule.gamecenter.function;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.NickNameEntity;
import io.reactivex.functions.Function;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NickNameFunction implements Function<String, NickNameEntity> {
    @Override // io.reactivex.functions.Function
    public NickNameEntity apply(@NotNull String str) {
        NickNameEntity nickNameEntity = new NickNameEntity();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("respcode");
            jSONObject.optString("msg");
            if (optString.equals("0000")) {
                CacheDataCenter.getInstance().setNickName(str);
            } else if (!TextUtils.isEmpty(CacheDataCenter.getInstance().getNickName())) {
                nickNameEntity.setMsg(jSONObject.optString("msg"));
                nickNameEntity.setRespcode(jSONObject.optString("respcode"));
            }
            if (optString.equals("0000")) {
                NickNameEntity.RespDataBean respDataBean = new NickNameEntity.RespDataBean();
                respDataBean.setNickName(jSONObject.optJSONObject("respData").optString("nickName"));
                nickNameEntity.setRespData(respDataBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nickNameEntity;
    }
}
