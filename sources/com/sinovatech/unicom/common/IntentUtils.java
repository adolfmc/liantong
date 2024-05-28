package com.sinovatech.unicom.common;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.p318ui.App;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class IntentUtils {
    public static boolean isShowCustomCityActivity() {
        try {
            if (App.hasLogined()) {
                String loginType = UserManager.getInstance().getLoginType();
                if ((TextUtils.equals("01", loginType) || TextUtils.equals("06", loginType) || TextUtils.equals("999", loginType) || TextUtils.equals("29", loginType)) && TextUtils.equals("01", ConfigManager.getJumpActSwitch()) && !TextUtils.isEmpty(ConfigManager.getJumpActUrl())) {
                    String currentCityCode = UserManager.getInstance().getCurrentCityCode();
                    if (!TextUtils.isEmpty(ConfigManager.getJumpActCityCode())) {
                        if (ConfigManager.getJumpActCityCode().contains(currentCityCode)) {
                            return true;
                        }
                    }
                    return false;
                }
                return false;
            }
            return false;
        } catch (Exception e) {
            UIUtils.logE(e.getMessage());
            return false;
        }
    }
}
