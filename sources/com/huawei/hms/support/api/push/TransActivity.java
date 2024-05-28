package com.huawei.hms.support.api.push;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import com.bytedance.applog.tracker.Tracker;
import com.huawei.android.hms.push.C4804R;
import com.huawei.hms.push.SelfShow;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class TransActivity extends Activity {
    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C4804R.C4807layout.hwpush_trans_activity);
        getWindow().addFlags(67108864);
        SelfShow.m14194a(this, getIntent());
        finish();
    }
}
