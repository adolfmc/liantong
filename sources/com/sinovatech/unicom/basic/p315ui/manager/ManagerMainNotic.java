package com.sinovatech.unicom.basic.p315ui.manager;

import android.app.Activity;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.notice.PushAlarmManager;
import com.sinovatech.unicom.separatemodule.push.PushManager;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerMainNotic */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerMainNotic {
    private Activity activityContext;
    private SharePreferenceUtil preference = App.getSharePreferenceUtil();

    public ManagerMainNotic(Activity activity) {
        this.activityContext = activity;
    }

    public void chedkPush() {
        try {
            this.preference.putBoolean("isAllowNotification", true);
            PushManager.getInstance().pushSwitch(this.activityContext, true);
            getToken();
            PushAlarmManager.startAlarmManagerPush(5000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getToken() {
        PushManager.getInstance().getToken(this.activityContext);
    }
}
