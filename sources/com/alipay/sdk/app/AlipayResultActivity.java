package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.alipay.sdk.app.statistic.C2000a;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AlipayResultActivity extends Activity {

    /* renamed from: a */
    public static final ConcurrentHashMap<String, InterfaceC1985a> f3528a = new ConcurrentHashMap<>();
    public NBSTraceUnit _nbs_trace;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.alipay.sdk.app.AlipayResultActivity$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC1985a {
        /* renamed from: a */
        void mo20692a(int i, String str, String str2);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
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

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Intent intent;
        String stringExtra;
        Bundle bundleExtra;
        String stringExtra2;
        JSONObject jSONObject;
        Bundle bundle2;
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        try {
            C2000a.m20899a("biz", "BSPReturned", "");
            intent = getIntent();
            stringExtra = intent.getStringExtra("session");
            bundleExtra = intent.getBundleExtra("result");
            stringExtra2 = intent.getStringExtra("scene");
            C2000a.m20899a("biz", "BSPSession", stringExtra);
        } catch (Throwable unused) {
            finish();
        }
        if (TextUtils.equals("mqpSchemePay", stringExtra2)) {
            m20958a(stringExtra, bundleExtra);
            NBSAppInstrumentation.activityCreateEndIns();
            return;
        }
        if ((TextUtils.isEmpty(stringExtra) || bundleExtra == null) && intent.getData() != null) {
            try {
                JSONObject jSONObject2 = new JSONObject(new String(Base64.decode(intent.getData().getQuery(), 2), "UTF-8"));
                jSONObject = jSONObject2.getJSONObject("result");
                stringExtra = jSONObject2.getString("session");
                C2000a.m20899a("biz", "BSPUriSession", stringExtra);
                bundle2 = new Bundle();
            } catch (Throwable th) {
                th = th;
            }
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    bundle2.putString(next, jSONObject.getString(next));
                }
                bundleExtra = bundle2;
            } catch (Throwable th2) {
                bundleExtra = bundle2;
                th = th2;
                C2000a.m20898a("biz", "BSPResEx", th);
                C2000a.m20898a("biz", "ParseSchemeQueryError", th);
                if (!TextUtils.isEmpty(stringExtra)) {
                }
                C2000a.m20895b(this, "");
                finish();
                NBSAppInstrumentation.activityCreateEndIns();
            }
        }
        if (!TextUtils.isEmpty(stringExtra) || bundleExtra == null) {
            C2000a.m20895b(this, "");
            finish();
        } else {
            m20958a(stringExtra, bundleExtra);
            C2000a.m20895b(this, "");
            finish();
        }
        NBSAppInstrumentation.activityCreateEndIns();
    }

    /* renamed from: a */
    private void m20958a(String str, Bundle bundle) {
        InterfaceC1985a remove = f3528a.remove(str);
        if (remove == null) {
            return;
        }
        try {
            remove.mo20692a(bundle.getInt("endCode"), bundle.getString("memo"), bundle.getString("result"));
        } finally {
            finish();
        }
    }
}
