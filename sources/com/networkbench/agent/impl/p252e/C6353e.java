package com.networkbench.agent.impl.p252e;

import android.app.Activity;
import android.app.Application;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAppAgent;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.p252e.p253a.C6341c;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6353e implements Application.ActivityLifecycleCallbacks {

    /* renamed from: d */
    private static final String f15994d = "tingyun.";

    /* renamed from: g */
    private static final int f15997g = 123456;

    /* renamed from: h */
    private static C6353e f15998h;

    /* renamed from: i */
    private C6367o f15999i;

    /* renamed from: j */
    private WeakReference<Activity> f16000j;

    /* renamed from: c */
    private static final InterfaceC6393e f15993c = C6394f.m10150a();

    /* renamed from: e */
    private static boolean f15995e = false;

    /* renamed from: f */
    private static boolean f15996f = false;

    /* renamed from: a */
    public static String f15991a = "";

    /* renamed from: b */
    public static String f15992b = "";

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    /* renamed from: a */
    public static C6353e m10315a() {
        if (f15998h == null) {
            f15998h = new C6353e();
        }
        return f15998h;
    }

    private C6353e() {
    }

    /* renamed from: b */
    public C6367o m10313b() {
        return this.f15999i;
    }

    /* renamed from: c */
    public WeakReference<Activity> m10311c() {
        return this.f16000j;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        m10307e(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        try {
            f15993c.mo10122a("onActivityResumed");
            m10307e(activity);
            if (!f15995e || this.f15999i == null) {
                return;
            }
            this.f16000j = new WeakReference<>(activity);
            this.f15999i.mo10277a(new WeakReference<>(activity));
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = f15993c;
            interfaceC6393e.mo10116d("onActivityResumed e:" + e.getMessage());
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        try {
            f15993c.mo10122a("onActivityPaused");
            if (!f15995e || this.f15999i == null) {
                return;
            }
            this.f15999i.mo10278a();
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = f15993c;
            interfaceC6393e.mo10116d("onActivityResumed e:" + e.getMessage());
        }
    }

    /* renamed from: a */
    private String m10314a(Activity activity) {
        Uri m10310c = m10310c(activity);
        String scheme = m10310c != null ? m10310c.getScheme() : "";
        InterfaceC6393e interfaceC6393e = f15993c;
        interfaceC6393e.mo10122a("intent data value:" + scheme);
        return scheme;
    }

    /* renamed from: b */
    private String m10312b(Activity activity) {
        Uri m10310c = m10310c(activity);
        String host = m10310c != null ? m10310c.getHost() : "";
        InterfaceC6393e interfaceC6393e = f15993c;
        interfaceC6393e.mo10122a("intent data value:" + host);
        return host;
    }

    /* renamed from: c */
    private Uri m10310c(Activity activity) {
        if (activity == null) {
            return null;
        }
        try {
            return activity.getIntent().getData();
        } catch (Exception e) {
            f15993c.mo10121a("error get intent data:", e);
            return null;
        }
    }

    /* renamed from: d */
    private boolean m10308d(Activity activity) {
        String m10314a = m10314a(activity);
        if (TextUtils.isEmpty(m10314a) || !m10314a.toLowerCase().startsWith("tingyun.")) {
            return false;
        }
        return TextUtils.isEmpty(NBSAppAgent.schemeIgnore) || !m10314a.toLowerCase().contains(NBSAppAgent.schemeIgnore.toLowerCase());
    }

    /* renamed from: e */
    private void m10307e(Activity activity) {
        if (f15996f) {
            return;
        }
        f15995e = m10308d(activity);
        if (f15995e) {
            f15992b = m10314a(activity);
            f15991a = m10312b(activity);
            m10305g(activity);
            C6369q.m10273a().m10272a(new Runnable() { // from class: com.networkbench.agent.impl.e.e.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Harvest.getInstance().getHarvestConnection().getResourceBmp();
                    } catch (Throwable th) {
                        InterfaceC6393e interfaceC6393e = C6353e.f15993c;
                        interfaceC6393e.mo10122a("get resource error:" + th.getMessage());
                    }
                }
            });
        }
        this.f15999i = new C6367o();
        f15996f = true;
        InterfaceC6393e interfaceC6393e = f15993c;
        interfaceC6393e.mo10122a("runOnlyOnce isStartFromNBSBrowser:" + f15995e);
    }

    /* renamed from: f */
    private boolean m10306f(Activity activity) {
        return activity.checkSelfPermission("android.permission.SYSTEM_ALERT_WINDOW") == 0;
    }

    /* renamed from: g */
    private void m10305g(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23 && !m10306f(activity) && !Settings.canDrawOverlays(activity)) {
            activity.requestPermissions(new String[]{"android.permission.SYSTEM_ALERT_WINDOW"}, 123456);
        }
        boolean m10338a = new C6341c(activity).m10338a(activity);
        InterfaceC6393e interfaceC6393e = f15993c;
        interfaceC6393e.mo10122a("permission result:" + m10338a);
    }
}
