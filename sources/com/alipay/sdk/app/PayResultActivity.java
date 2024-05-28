package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.alipay.sdk.util.C2040c;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class PayResultActivity extends Activity {

    /* renamed from: a */
    public static final String f3539a = "{\"isLogin\":\"false\"}";

    /* renamed from: b */
    public static final HashMap<String, Object> f3540b = new HashMap<>();

    /* renamed from: c */
    public static final String f3541c = "hk.alipay.wallet";

    /* renamed from: d */
    public static final String f3542d = "phonecashier.pay.hash";

    /* renamed from: e */
    public static final String f3543e = "orderSuffix";

    /* renamed from: f */
    public static final String f3544f = "externalPkgName";

    /* renamed from: g */
    public static final String f3545g = "phonecashier.pay.result";

    /* renamed from: h */
    public static final String f3546h = "phonecashier.pay.resultOrderHash";

    /* renamed from: i */
    private static String f3547i;
    public NBSTraceUnit _nbs_trace;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.alipay.sdk.app.PayResultActivity$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class C1986a {

        /* renamed from: a */
        public static volatile String f3548a;

        /* renamed from: b */
        public static volatile String f3549b;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 3);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    /* renamed from: a */
    private static void m20947a(Activity activity, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        Intent intent = new Intent();
        try {
            intent.setPackage("hk.alipay.wallet");
            intent.setData(Uri.parse("alipayhk://platformapi/startApp?appId=20000125&schemePaySession=" + URLEncoder.encode(str, "UTF-8") + "&orderSuffix=" + URLEncoder.encode(str2, "UTF-8") + "&packageName=" + URLEncoder.encode(str3, "UTF-8") + "&externalPkgName=" + URLEncoder.encode(str3, "UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            C2040c.m20714b("msp", "PayTask:payReuslt: UnsupportedEncodingException:" + e);
        }
        if (activity != null) {
            try {
                activity.startActivity(intent);
            } catch (Throwable unused) {
                activity.finish();
            }
        }
    }

    /* renamed from: a */
    private static void m20946a(String str) {
        C1986a.f3549b = C1998j.m20910c();
        m20944a(f3540b, str);
    }

    /* renamed from: a */
    private static void m20945a(String str, String str2) {
        C1986a.f3549b = str;
        m20944a(f3540b, str2);
    }

    /* renamed from: a */
    private static void m20948a(Activity activity, int i) {
        new Handler().postDelayed(new RunnableC1994f(activity), i);
    }

    /* renamed from: a */
    private static boolean m20944a(HashMap<String, Object> hashMap, String str) {
        Object obj;
        if (hashMap == null || str == null || (obj = hashMap.get(str)) == null) {
            return false;
        }
        synchronized (obj) {
            obj.notifyAll();
        }
        return true;
    }
}
