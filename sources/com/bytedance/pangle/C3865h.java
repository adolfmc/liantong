package com.bytedance.pangle;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.ProviderInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.bytedance.pangle.apm.ApmUtils;
import com.bytedance.pangle.log.C3871b;
import com.bytedance.pangle.log.IZeusReporter;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.p175c.C3784b;
import com.bytedance.pangle.p175c.InterfaceC3783a;
import com.bytedance.pangle.p176d.C3789a;
import com.bytedance.pangle.p176d.C3793d;
import com.bytedance.pangle.p176d.C3794e;
import com.bytedance.pangle.p177e.C3811f;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.pangle.provider.ContentProviderManager;
import com.bytedance.pangle.receiver.C3897b;
import com.bytedance.pangle.servermanager.MainServerManager;
import com.bytedance.pangle.util.C3950i;
import com.bytedance.pangle.util.C3951j;
import com.bytedance.pangle.util.FieldUtils;
import com.bytedance.pangle.util.MethodUtils;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.bytedance.pangle.h */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3865h {

    /* renamed from: d */
    private static volatile C3865h f9215d;

    /* renamed from: a */
    boolean f9216a;

    /* renamed from: b */
    final List<ZeusPluginStateListener> f9217b = new CopyOnWriteArrayList();

    /* renamed from: c */
    final List<ZeusPluginEventCallback> f9218c = new ArrayList();

    /* renamed from: e */
    private final Handler f9219e = new Handler(Looper.getMainLooper());

    /* renamed from: a */
    public static C3865h m16801a() {
        if (f9215d == null) {
            synchronized (C3865h.class) {
                if (f9215d == null) {
                    f9215d = new C3865h();
                }
            }
        }
        return f9215d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized void m16799a(Application application, boolean z) {
        if (this.f9216a) {
            ZeusLogger.m16788w("Zeus/init_pangle", "ZeusManager zeus has been inited!");
            return;
        }
        m16800a(3000, 0, null, -1, null);
        Zeus.setAppContext(application);
        GlobalParam globalParam = GlobalParam.getInstance();
        globalParam.init();
        if (application == null) {
            throw new IllegalArgumentException("context must be not null !!!");
        }
        ZeusLogger.setDebug(globalParam.isDebug());
        ZeusLogger.m16792i("Zeus/init_pangle", "ZeusManager init, context = " + application + ", hParam = " + globalParam);
        C3794e.m16918a(new Runnable() { // from class: com.bytedance.pangle.h.1
            @Override // java.lang.Runnable
            public final void run() {
                C3951j.m16609b();
            }
        });
        if (GlobalParam.getInstance().isPostBgDexOptByInit()) {
            C3811f.m16894a();
        }
        if (z) {
            Zeus.onPrivacyAgreed();
        }
        C3784b m16961a = C3784b.m16961a();
        InterfaceC3783a interfaceC3783a = new InterfaceC3783a() { // from class: com.bytedance.pangle.h.2
            @Override // com.bytedance.pangle.p175c.InterfaceC3783a
            /* renamed from: a */
            public final void mo16796a(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
                if (jSONObject == null) {
                    jSONObject = new JSONObject();
                }
                if (jSONObject2 == null) {
                    jSONObject2 = new JSONObject();
                }
                if (jSONObject3 == null) {
                    jSONObject3 = new JSONObject();
                }
                String str2 = null;
                if (Zeus.getAppApplication() != null) {
                    Zeus.getAppApplication();
                    str2 = C3793d.m16922a(C3793d.m16924a());
                }
                String packageName = Zeus.getAppApplication() != null ? Zeus.getAppApplication().getPackageName() : "";
                if (str2 == null) {
                    str2 = "unknown";
                }
                try {
                    jSONObject.putOpt(ContentProviderManager.PLUGIN_PROCESS_NAME, C3871b.m16783a(str2));
                    jSONObject.putOpt("host_package_name", packageName);
                    Plugin plugin = Zeus.getPlugin(jSONObject.optString("plugin_package_name", ""), false);
                    jSONObject.putOpt("plugin_api_version", C3871b.m16783a(Integer.valueOf(plugin != null ? plugin.getApiVersionCode() : -1)));
                    jSONObject.putOpt("zeus_sdk_version", C3871b.m16783a("0.0.1-beta.4200.107-pangle"));
                    StringBuilder sb = new StringBuilder("eventName: ");
                    sb.append(str);
                    sb.append("\ncategoryData:");
                    sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString(1) : NBSJSONObjectInstrumentation.toString(jSONObject, 1));
                    sb.append("\nmetricData:");
                    sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString(1) : NBSJSONObjectInstrumentation.toString(jSONObject2, 1));
                    sb.append("\nlogExtrData:");
                    sb.append(!(jSONObject3 instanceof JSONObject) ? jSONObject3.toString(1) : NBSJSONObjectInstrumentation.toString(jSONObject3, 1));
                    ZeusLogger.m16790v("Zeus/reporter_pangle", sb.toString());
                    IZeusReporter reporter = GlobalParam.getInstance().getReporter();
                    if (reporter != null) {
                        JSONObject jSONObject4 = new JSONObject();
                        Iterator<String> keys = jSONObject.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            jSONObject4.putOpt(next, jSONObject.opt(next));
                        }
                        Iterator<String> keys2 = jSONObject2.keys();
                        while (keys2.hasNext()) {
                            String next2 = keys2.next();
                            jSONObject4.putOpt(next2, jSONObject2.opt(next2));
                        }
                        Iterator<String> keys3 = jSONObject3.keys();
                        while (keys3.hasNext()) {
                            String next3 = keys3.next();
                            jSONObject4.putOpt(next3, jSONObject3.opt(next3));
                        }
                        reporter.report(str, jSONObject4);
                    }
                    ApmUtils.getApmInstance().monitorEvent(str, jSONObject, jSONObject2, jSONObject3);
                } catch (JSONException e) {
                    ZeusLogger.m16787w("Zeus/reporter_pangle", e.getMessage(), e);
                }
            }
        };
        synchronized (m16961a.f9044a) {
            m16961a.f9044a.add(interfaceC3783a);
        }
        if (!globalParam.isCloseFlipped()) {
            C3776b.m16974a();
        }
        if (Build.VERSION.SDK_INT == 29) {
            C3794e.m16918a(new Runnable() { // from class: com.bytedance.pangle.h.3
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        MethodUtils.invokeStaticMethod(Class.forName("com.android.server.SystemConfig"), "getInstance", new Object[0]);
                    } catch (Throwable unused) {
                    }
                }
            });
        }
        if (C3950i.m16615i()) {
            try {
                FieldUtils.writeField(C3789a.m16958a(), "mHiddenApiWarningShown", Boolean.TRUE);
                ZeusLogger.m16788w("Zeus/init_pangle", "ZeusManager disableApiWarningShownForAndroidP, true");
            } catch (Exception e) {
                ZeusLogger.errReport("Zeus/init_pangle", "disableApiWarningShownForAndroidP failed", e);
            }
        }
        m16798b();
        ContentProviderManager.getInstance().initSystemContentProviderInfo();
        C3897b.m16740a(application);
        this.f9216a = true;
        m16800a(3100, 0, null, -1, null);
    }

    /* renamed from: b */
    private static void m16798b() {
        ProviderInfo[] providerInfoArr;
        String str;
        try {
            PackageInfo packageInfo = Zeus.getAppApplication().getPackageManager().getPackageInfo(Zeus.getAppApplication().getPackageName(), 8);
            if (packageInfo != null && packageInfo.providers != null) {
                for (ProviderInfo providerInfo : packageInfo.providers) {
                    if (!TextUtils.isEmpty(providerInfo.authority)) {
                        if (providerInfo.authority.contains(Zeus.getAppApplication().getPackageName() + ".pangle.servermanager.")) {
                            if (!TextUtils.isEmpty(providerInfo.processName) && providerInfo.processName.contains(":")) {
                                str = providerInfo.processName.split(":")[1];
                                if (Zeus.getServerManagerHashMap().get(str) != null || !TextUtils.equals(str, "main") || !TextUtils.equals(providerInfo.name, MainServerManager.class.getName())) {
                                    Zeus.getServerManagerHashMap().put(str, providerInfo);
                                }
                            }
                            str = "main";
                            if (Zeus.getServerManagerHashMap().get(str) != null) {
                            }
                            Zeus.getServerManagerHashMap().put(str, providerInfo);
                        }
                    }
                }
                return;
            }
            ZeusLogger.errReport("Zeus/init_pangle", "initServerManager failed. packageInfo:".concat(String.valueOf(packageInfo)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public final void m16800a(final int i, final int i2, @Nullable final String str, final int i3, @Nullable final Throwable th) {
        Object[] m16797c;
        for (final Object obj : m16797c()) {
            this.f9219e.post(new Runnable() { // from class: com.bytedance.pangle.h.4
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        ((ZeusPluginEventCallback) obj).onPluginEvent(i, i2, str, i3, th);
                    } catch (Throwable unused) {
                    }
                }
            });
        }
    }

    /* renamed from: c */
    private Object[] m16797c() {
        Object[] array;
        synchronized (this.f9218c) {
            array = !this.f9218c.isEmpty() ? this.f9218c.toArray() : null;
        }
        return array == null ? new Object[0] : array;
    }
}
