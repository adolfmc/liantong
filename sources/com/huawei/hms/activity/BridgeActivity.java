package com.huawei.hms.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.huawei.hms.p224ui.SafeIntent;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.ResolutionFlagUtil;
import com.huawei.hms.utils.UIUtil;
import java.lang.reflect.InvocationTargetException;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BridgeActivity extends Activity {
    public static final String EXTRA_DELEGATE_CLASS_NAME = "intent.extra.DELEGATE_CLASS_OBJECT";
    public static final String EXTRA_DELEGATE_UPDATE_INFO = "intent.extra.update.info";
    public static final String EXTRA_INTENT = "intent.extra.intent";
    public static final String EXTRA_IS_FULLSCREEN = "intent.extra.isfullscreen";
    public static final String EXTRA_RESULT = "intent.extra.RESULT";

    /* renamed from: b */
    private static final int f10890b = m15336a("ro.build.hw_emui_api_level", 0);

    /* renamed from: a */
    private IBridgeActivityDelegate f10891a;

    /* renamed from: com.huawei.hms.activity.BridgeActivity$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class View$OnApplyWindowInsetsListenerC4837a implements View.OnApplyWindowInsetsListener {

        /* renamed from: a */
        final /* synthetic */ ViewGroup f10892a;

        View$OnApplyWindowInsetsListenerC4837a(ViewGroup viewGroup) {
            this.f10892a = viewGroup;
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            try {
                Object invoke = Class.forName("com.huawei.android.view.WindowManagerEx$LayoutParamsEx").getMethod("getDisplaySideRegion", WindowInsets.class).invoke(null, windowInsets);
                if (invoke == null) {
                    HMSLog.m14110i("BridgeActivity", "sideRegion is null");
                } else {
                    Rect rect = (Rect) Class.forName("com.huawei.android.view.DisplaySideRegionEx").getMethod("getSafeInsets", new Class[0]).invoke(invoke, new Object[0]);
                    ViewGroup viewGroup = this.f10892a;
                    if (viewGroup != null) {
                        viewGroup.setPadding(rect.left, 0, rect.right, 0);
                    }
                }
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                HMSLog.m14112e("BridgeActivity", "An exception occurred while reading: onApplyWindowInsets" + e.getMessage());
            }
            return view.onApplyWindowInsets(windowInsets);
        }
    }

    /* renamed from: a */
    private static void m15337a(Window window, boolean z) {
        try {
            window.getClass().getMethod("setHwFloating", Boolean.TYPE).invoke(window, Boolean.valueOf(z));
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e) {
            HMSLog.m14112e("BridgeActivity", "In setHwFloating, Failed to call Window.setHwFloating()." + e.getMessage());
        }
    }

    /* renamed from: b */
    private boolean m15335b() {
        Intent intent = getIntent();
        if (intent == null) {
            HMSLog.m14112e("BridgeActivity", "In initialize, Must not pass in a null intent.");
            return false;
        }
        if (intent.getBooleanExtra(EXTRA_IS_FULLSCREEN, false)) {
            getWindow().setFlags(1024, 1024);
        }
        try {
            String stringExtra = intent.getStringExtra(EXTRA_DELEGATE_CLASS_NAME);
            if (stringExtra != null) {
                IBridgeActivityDelegate iBridgeActivityDelegate = (IBridgeActivityDelegate) Class.forName(stringExtra).asSubclass(IBridgeActivityDelegate.class).newInstance();
                this.f10891a = iBridgeActivityDelegate;
                try {
                    iBridgeActivityDelegate.onBridgeActivityCreate(this);
                    return true;
                } catch (RuntimeException e) {
                    HMSLog.m14112e("BridgeActivity", "Run time Exception." + e.getMessage());
                    return false;
                }
            }
            HMSLog.m14112e("BridgeActivity", "In initialize, Must not pass in a null or non class object.");
            return false;
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalStateException | InstantiationException e2) {
            HMSLog.m14112e("BridgeActivity", "In initialize, Failed to create 'IUpdateWizard' instance." + e2.getMessage());
            return false;
        }
    }

    /* renamed from: c */
    private void m15334c() {
        try {
            requestWindowFeature(1);
        } catch (Exception e) {
            HMSLog.m14109w("BridgeActivity", "requestWindowFeature " + e.getMessage());
        }
        Window window = getWindow();
        if (f10890b >= 9) {
            window.addFlags(67108864);
            m15337a(window, true);
        }
        window.getDecorView().setSystemUiVisibility(0);
    }

    public static Intent getIntentStartBridgeActivity(Activity activity, String str) {
        Intent intent = new Intent(activity, BridgeActivity.class);
        intent.putExtra(EXTRA_DELEGATE_CLASS_NAME, str);
        intent.putExtra(EXTRA_IS_FULLSCREEN, UIUtil.isActivityFullscreen(activity));
        return intent;
    }

    public static void setFullScreenWindowLayoutInDisplayCutout(Window window) {
        if (window == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            window.setAttributes(attributes);
            window.getDecorView().setSystemUiVisibility(1280);
            return;
        }
        WindowManager.LayoutParams attributes2 = window.getAttributes();
        try {
            Class<?> cls = Class.forName("com.huawei.android.view.LayoutParamsEx");
            cls.getMethod("addHwFlags", Integer.TYPE).invoke(cls.getConstructor(WindowManager.LayoutParams.class).newInstance(attributes2), 65536);
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
            HMSLog.m14112e("BridgeActivity", "com.huawei.android.view.LayoutParamsEx fail");
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    public void finish() {
        HMSLog.m14110i("BridgeActivity", "Enter finish.");
        super.finish();
    }

    @Override // android.app.Activity
    public Intent getIntent() {
        Intent modifyIntentBehaviorsSafe = UIUtil.modifyIntentBehaviorsSafe(super.getIntent());
        if (modifyIntentBehaviorsSafe != null) {
            return new SafeIntent(modifyIntentBehaviorsSafe);
        }
        return null;
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        SafeIntent safeIntent = new SafeIntent(intent);
        super.onActivityResult(i, i2, safeIntent);
        IBridgeActivityDelegate iBridgeActivityDelegate = this.f10891a;
        if (iBridgeActivityDelegate == null || iBridgeActivityDelegate.onBridgeActivityResult(i, i2, safeIntent) || isFinishing()) {
            return;
        }
        setResult(i2, UIUtil.modifyIntentBehaviorsSafe(safeIntent));
        finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        IBridgeActivityDelegate iBridgeActivityDelegate = this.f10891a;
        if (iBridgeActivityDelegate != null) {
            iBridgeActivityDelegate.onBridgeConfigurationChanged();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 14);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        try {
            ResolutionFlagUtil.getInstance().removeResolutionFlag(new SafeIntent(getIntent()).getStringExtra("transaction_id"));
        } catch (Throwable th) {
            HMSLog.m14109w("BridgeActivity", "get transaction_id from intent fail: " + th.getClass().getSimpleName());
        }
        IBridgeActivityDelegate iBridgeActivityDelegate = this.f10891a;
        if (iBridgeActivityDelegate != null) {
            iBridgeActivityDelegate.onBridgeActivityDestroy();
        }
        HMSLog.m14110i("BridgeActivity", "BridgeActivity onDestroy");
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        IBridgeActivityDelegate iBridgeActivityDelegate = this.f10891a;
        if (iBridgeActivityDelegate != null) {
            iBridgeActivityDelegate.onKeyUp(i, keyEvent);
        }
        return super.onKeyUp(i, keyEvent);
    }

    public static Intent getIntentStartBridgeActivity(Context context, String str) {
        Intent intent = new Intent(context, BridgeActivity.class);
        intent.putExtra(EXTRA_DELEGATE_CLASS_NAME, str);
        intent.putExtra(EXTRA_IS_FULLSCREEN, false);
        return intent;
    }

    /* renamed from: a */
    private void m15338a() {
        View findViewById = getWindow().findViewById(16908290);
        if (findViewById != null && (findViewById instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) findViewById;
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            try {
                Class<?> cls = Class.forName("com.huawei.android.view.WindowManagerEx$LayoutParamsEx");
                cls.getMethod("setDisplaySideMode", Integer.TYPE).invoke(cls.getDeclaredConstructor(WindowManager.LayoutParams.class).newInstance(attributes), 1);
            } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                HMSLog.m14112e("BridgeActivity", "An exception occurred while reading: setDisplaySideMode" + e.getMessage());
            }
            if (Build.VERSION.SDK_INT >= 20) {
                getWindow().getDecorView().setOnApplyWindowInsetsListener(new View$OnApplyWindowInsetsListenerC4837a(viewGroup));
                return;
            }
            return;
        }
        HMSLog.m14112e("BridgeActivity", "rootView is null or not ViewGroup");
    }

    /* renamed from: a */
    private static int m15336a(String str, int i) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return ((Integer) cls.getDeclaredMethod("getInt", String.class, Integer.TYPE).invoke(cls, str, Integer.valueOf(i))).intValue();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            HMSLog.m14112e("BridgeActivity", "An exception occurred while reading: EMUI_SDK_INT");
            return i;
        }
    }
}
