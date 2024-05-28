package com.huawei.hms.update.note;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import com.huawei.hms.activity.IBridgeActivityDelegate;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DoNothingResolution implements IBridgeActivityDelegate {
    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        HMSLog.m14110i("DoNothingResolution", "<DoNothingResolution getRequestCode>");
        return 0;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        HMSLog.m14110i("DoNothingResolution", "<DoNothingResolution onBridgeActivityCreate>");
        if (activity != null && !activity.isFinishing()) {
            activity.setResult(30);
            activity.finish();
            return;
        }
        HMSLog.m14112e("DoNothingResolution", "<Resolution onBridgeActivityCreate> activity is null or finishing");
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        HMSLog.m14110i("DoNothingResolution", "<DoNothingResolution onBridgeActivityDestroy>");
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) {
        HMSLog.m14110i("DoNothingResolution", "<DoNothingResolution onBridgeActivityResult>");
        return false;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeConfigurationChanged() {
        HMSLog.m14110i("DoNothingResolution", "<DoNothingResolution onBridgeConfigurationChanged>");
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onKeyUp(int i, KeyEvent keyEvent) {
        HMSLog.m14110i("DoNothingResolution", "<DoNothingResolution onKeyUp>");
    }
}
