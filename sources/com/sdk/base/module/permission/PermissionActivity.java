package com.sdk.base.module.permission;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import com.bytedance.applog.tracker.Tracker;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PermissionActivity extends Activity {
    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    @SuppressLint({"NewApi"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent().getStringArrayExtra("KEY_INPUT_PERMISSIONS") == null) {
            finish();
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        finish();
    }
}
