package com.vivo.push.sdk.service;

import android.app.Activity;
import android.view.MotionEvent;
import com.bytedance.applog.tracker.Tracker;
import com.vivo.push.util.LogUtil;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class LinkProxyActivity extends Activity {
    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x00df, code lost:
        startService(r7);
     */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onCreate(android.os.Bundle r7) {
        /*
            Method dump skipped, instructions count: 257
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.sdk.service.LinkProxyActivity.onCreate(android.os.Bundle):void");
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.m5341d("LinkProxyActivity", hashCode() + " onDestory " + getPackageName());
    }
}
