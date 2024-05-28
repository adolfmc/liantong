package com.sinovatech.unicom.separatemodule.user.function;

import android.text.TextUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.user.entity.UserFootPrintEntity;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUserFootPrint;
import io.reactivex.functions.Function;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserFootPrintFuction implements Function<String, UserFootPrintEntity> {
    private static final String SUCCESS_CODE = "0000";

    @Override // io.reactivex.functions.Function
    public UserFootPrintEntity apply(String str) throws Exception {
        JSONObject optJSONObject;
        try {
            MsLogUtil.m7979d("UserFootPrintFuction", "我的足迹数据\n" + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(str) && str.startsWith("{")) {
            JSONObject jSONObject = new JSONObject(str);
            if ("0000".equals(jSONObject.optString("status")) && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
                UserFootPrintEntity userFootPrintEntity = new UserFootPrintEntity();
                String optString = optJSONObject.optString("footMarkFloorTitle");
                String optString2 = optJSONObject.optString("footMarkSwitch");
                String optString3 = optJSONObject.optString("shoppingCartImgSrc");
                String optString4 = optJSONObject.optString("shoppingCartNum");
                String optString5 = optJSONObject.optString("shoppingCartTitle");
                String optString6 = optJSONObject.optString("shoppingCartUrls");
                userFootPrintEntity.setFootMarkFloorTitle(optString);
                userFootPrintEntity.setFootMarkSwitch(optString2);
                userFootPrintEntity.setShoppingCartImgSrc(optString3);
                userFootPrintEntity.setShoppingCartNum(optString4);
                userFootPrintEntity.setShoppingCartTitle(optString5);
                userFootPrintEntity.setShoppingCartUrls(optString6);
                if (!TextUtils.isEmpty(optString3) && !TextUtils.isEmpty(optString6) && !TextUtils.isEmpty(optString5)) {
                    userFootPrintEntity.setShow(true);
                } else {
                    userFootPrintEntity.setShow(false);
                }
                ManagerUserFootPrint.getInstance().saveCache(str);
                return userFootPrintEntity;
            }
            return null;
        }
        return null;
    }
}
