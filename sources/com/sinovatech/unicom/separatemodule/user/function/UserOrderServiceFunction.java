package com.sinovatech.unicom.separatemodule.user.function;

import android.text.TextUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.user.entity.UserServiceOrderEntity;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUserServiceOrder;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserOrderServiceFunction implements Function<String, UserServiceOrderEntity> {
    @Override // io.reactivex.functions.Function
    public UserServiceOrderEntity apply(String str) throws Exception {
        try {
            if (!TextUtils.isEmpty(str) && str.startsWith("{")) {
                MsLogUtil.m7979d("我的服务订单", str);
                UserServiceOrderEntity userServiceOrderEntity = new UserServiceOrderEntity();
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("myOrderTip");
                if (!TextUtils.isEmpty(optString)) {
                    userServiceOrderEntity.setMyOrderTip(optString);
                }
                String optString2 = jSONObject.optString("myOrderSwitch");
                if (!TextUtils.isEmpty(optString2)) {
                    userServiceOrderEntity.setMyOrderSwitch(optString2);
                }
                String optString3 = jSONObject.optString("message");
                if (!TextUtils.isEmpty(optString3)) {
                    userServiceOrderEntity.setMessage(optString3);
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("datas");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    UserServiceOrderEntity.Data data = new UserServiceOrderEntity.Data();
                    data.setIcon(optJSONObject.optString("icon"));
                    data.setStatus(optJSONObject.optString("status"));
                    data.setNum(optJSONObject.optString("num"));
                    data.setUrl(optJSONObject.optString("url"));
                    arrayList.add(data);
                }
                userServiceOrderEntity.setDataList(arrayList);
                ManagerUserServiceOrder.getInstance().saveCache(str);
                return userServiceOrderEntity;
            }
            MsLogUtil.m7980d("数据返回为空");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
