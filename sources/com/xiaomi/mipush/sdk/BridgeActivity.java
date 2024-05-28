package com.xiaomi.mipush.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import com.bytedance.applog.tracker.Tracker;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class BridgeActivity extends Activity {
    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = 1;
        attributes.width = 1;
        attributes.gravity = 51;
        window.setAttributes(attributes);
    }

    @Override // android.app.Activity
    protected void onResume() {
        Intent intent;
        super.onResume();
        try {
            try {
                Intent intent2 = getIntent();
                if (intent2 != null && (intent = (Intent) intent2.getParcelableExtra("mipush_serviceIntent")) != null) {
                    PushMessageHandler.m5175a(getApplicationContext(), intent);
                }
            } catch (Exception e) {
                AbstractC11049b.m5276a(e);
            }
        } finally {
            finish();
        }
    }
}
