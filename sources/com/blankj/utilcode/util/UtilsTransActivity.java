package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.blankj.utilcode.util.Utils;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UtilsTransActivity extends AppCompatActivity {
    private static final Map<UtilsTransActivity, TransActivityDelegate> CALLBACK_MAP = new HashMap();
    protected static final String EXTRA_DELEGATE = "extra_delegate";
    public NBSTraceUnit _nbs_trace;

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        JniLib.m15918cV(this, bundle, 4);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    public static void start(TransActivityDelegate transActivityDelegate) {
        start(null, null, transActivityDelegate, UtilsTransActivity.class);
    }

    public static void start(Utils.Consumer<Intent> consumer, TransActivityDelegate transActivityDelegate) {
        start(null, consumer, transActivityDelegate, UtilsTransActivity.class);
    }

    public static void start(Activity activity, TransActivityDelegate transActivityDelegate) {
        start(activity, null, transActivityDelegate, UtilsTransActivity.class);
    }

    public static void start(Activity activity, Utils.Consumer<Intent> consumer, TransActivityDelegate transActivityDelegate) {
        start(activity, consumer, transActivityDelegate, UtilsTransActivity.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void start(Activity activity, Utils.Consumer<Intent> consumer, TransActivityDelegate transActivityDelegate, Class<?> cls) {
        if (transActivityDelegate == null) {
            return;
        }
        Intent intent = new Intent(Utils.getApp(), cls);
        intent.putExtra(EXTRA_DELEGATE, transActivityDelegate);
        if (consumer != null) {
            consumer.accept(intent);
        }
        if (activity == null) {
            intent.addFlags(268435456);
            Utils.getApp().startActivity(intent);
            return;
        }
        activity.startActivity(intent);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        TransActivityDelegate transActivityDelegate = CALLBACK_MAP.get(this);
        if (transActivityDelegate == null) {
            NBSAppInstrumentation.activityStartEndIns();
            return;
        }
        transActivityDelegate.onStarted(this);
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        TransActivityDelegate transActivityDelegate = CALLBACK_MAP.get(this);
        if (transActivityDelegate == null) {
            NBSAppInstrumentation.activityResumeEndIns();
            return;
        }
        transActivityDelegate.onResumed(this);
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        overridePendingTransition(0, 0);
        super.onPause();
        TransActivityDelegate transActivityDelegate = CALLBACK_MAP.get(this);
        if (transActivityDelegate == null) {
            return;
        }
        transActivityDelegate.onPaused(this);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
        TransActivityDelegate transActivityDelegate = CALLBACK_MAP.get(this);
        if (transActivityDelegate == null) {
            return;
        }
        transActivityDelegate.onStopped(this);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        TransActivityDelegate transActivityDelegate = CALLBACK_MAP.get(this);
        if (transActivityDelegate == null) {
            return;
        }
        transActivityDelegate.onSaveInstanceState(this, bundle);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        TransActivityDelegate transActivityDelegate = CALLBACK_MAP.get(this);
        if (transActivityDelegate == null) {
            return;
        }
        transActivityDelegate.onDestroy(this);
        CALLBACK_MAP.remove(this);
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity, android.support.p083v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (strArr == null) {
            throw new NullPointerException("Argument 'permissions' of type String[] (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (iArr == null) {
            throw new NullPointerException("Argument 'grantResults' of type int[] (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
        TransActivityDelegate transActivityDelegate = CALLBACK_MAP.get(this);
        if (transActivityDelegate == null) {
            return;
        }
        transActivityDelegate.onRequestPermissionsResult(this, i, strArr, iArr);
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        TransActivityDelegate transActivityDelegate = CALLBACK_MAP.get(this);
        if (transActivityDelegate == null) {
            return;
        }
        transActivityDelegate.onActivityResult(this, i, i2, intent);
    }

    @Override // android.support.p083v4.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        TransActivityDelegate transActivityDelegate = CALLBACK_MAP.get(this);
        if (transActivityDelegate == null) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (transActivityDelegate.dispatchTouchEvent(this, motionEvent)) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static abstract class TransActivityDelegate implements Serializable {
        public void onCreateBefore(@NonNull UtilsTransActivity utilsTransActivity, @Nullable Bundle bundle) {
            if (utilsTransActivity == null) {
                throw new NullPointerException("Argument 'activity' of type UtilsTransActivity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        public void onCreated(@NonNull UtilsTransActivity utilsTransActivity, @Nullable Bundle bundle) {
            if (utilsTransActivity == null) {
                throw new NullPointerException("Argument 'activity' of type UtilsTransActivity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        public void onStarted(@NonNull UtilsTransActivity utilsTransActivity) {
            if (utilsTransActivity == null) {
                throw new NullPointerException("Argument 'activity' of type UtilsTransActivity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        public void onDestroy(@NonNull UtilsTransActivity utilsTransActivity) {
            if (utilsTransActivity == null) {
                throw new NullPointerException("Argument 'activity' of type UtilsTransActivity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        public void onResumed(@NonNull UtilsTransActivity utilsTransActivity) {
            if (utilsTransActivity == null) {
                throw new NullPointerException("Argument 'activity' of type UtilsTransActivity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        public void onPaused(@NonNull UtilsTransActivity utilsTransActivity) {
            if (utilsTransActivity == null) {
                throw new NullPointerException("Argument 'activity' of type UtilsTransActivity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        public void onStopped(@NonNull UtilsTransActivity utilsTransActivity) {
            if (utilsTransActivity == null) {
                throw new NullPointerException("Argument 'activity' of type UtilsTransActivity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        public void onSaveInstanceState(@NonNull UtilsTransActivity utilsTransActivity, Bundle bundle) {
            if (utilsTransActivity == null) {
                throw new NullPointerException("Argument 'activity' of type UtilsTransActivity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        public void onRequestPermissionsResult(@NonNull UtilsTransActivity utilsTransActivity, int i, @NonNull String[] strArr, @NonNull int[] iArr) {
            if (utilsTransActivity == null) {
                throw new NullPointerException("Argument 'activity' of type UtilsTransActivity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            if (strArr == null) {
                throw new NullPointerException("Argument 'permissions' of type String[] (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            if (iArr == null) {
                throw new NullPointerException("Argument 'grantResults' of type int[] (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        public void onActivityResult(@NonNull UtilsTransActivity utilsTransActivity, int i, int i2, Intent intent) {
            if (utilsTransActivity == null) {
                throw new NullPointerException("Argument 'activity' of type UtilsTransActivity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        public boolean dispatchTouchEvent(@NonNull UtilsTransActivity utilsTransActivity, MotionEvent motionEvent) {
            if (utilsTransActivity != null) {
                return false;
            }
            throw new NullPointerException("Argument 'activity' of type UtilsTransActivity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }
}
