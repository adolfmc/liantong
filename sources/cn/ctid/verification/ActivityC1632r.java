package cn.ctid.verification;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MotionEvent;
import com.bytedance.applog.tracker.Tracker;
import com.tfd.sdk.LF8bOvWP4;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.ctid.verification.r */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ActivityC1632r extends Activity {
    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(139);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    public native void onCreate(Bundle bundle, PersistableBundle persistableBundle);
}
