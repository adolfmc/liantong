package com.sinovatech.unicom.basic.p315ui.manager;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.eventbus.SearchXinRenLiBaoEvent;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import java.util.Calendar;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.manager.HomeSearchNewUserManager */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HomeSearchNewUserManager {
    public static void setNewUserData(JSONObject jSONObject) {
        clearCacheData();
        if (jSONObject != null) {
            boolean z = jSONObject instanceof JSONObject;
            if (TextUtils.isEmpty(!z ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)) || !"0".equals(jSONObject.optString("flag"))) {
                return;
            }
            CacheDataCenter.getInstance().setNewUserGiftBagData(!z ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            String optString = jSONObject.optString("days");
            int parseInt = UIUtils.isNumeric(optString) ? Integer.parseInt(optString) : 0;
            if (checkDay()) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(5, parseInt);
                CacheDataCenter.getInstance().setNewUserEndTime(String.valueOf(calendar.getTimeInMillis()));
            }
            SearchXinRenLiBaoEvent searchXinRenLiBaoEvent = new SearchXinRenLiBaoEvent(0);
            searchXinRenLiBaoEvent.setUrl(jSONObject.optString("url"));
            searchXinRenLiBaoEvent.setTitle(jSONObject.optString("title"));
            EventBusUtils.post(searchXinRenLiBaoEvent);
        }
    }

    private static boolean checkDay() {
        String newUserEndTime = CacheDataCenter.getInstance().getNewUserEndTime();
        return Long.parseLong((newUserEndTime == null || newUserEndTime.isEmpty()) ? "0" : "0") < System.currentTimeMillis();
    }

    public static void clearCacheData() {
        String newUserEndTime = CacheDataCenter.getInstance().getNewUserEndTime();
        if (newUserEndTime == null || TextUtils.isEmpty(newUserEndTime) || Long.parseLong(newUserEndTime) >= System.currentTimeMillis()) {
            return;
        }
        CacheDataCenter.getInstance().setNewUserGiftBagData("");
        CacheDataCenter.getInstance().setNewUserEndTime("");
    }

    public static boolean isNewUser() {
        try {
            String newUserEndTime = CacheDataCenter.getInstance().getNewUserEndTime();
            if (newUserEndTime != null && !newUserEndTime.isEmpty() && Long.parseLong(newUserEndTime) > System.currentTimeMillis()) {
                if (ConfigManager.isNewComerOpen()) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
