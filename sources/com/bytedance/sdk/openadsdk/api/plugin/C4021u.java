package com.bytedance.sdk.openadsdk.api.plugin;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.bykv.p167vk.openvk.api.proto.EventListener;
import com.bykv.p167vk.openvk.api.proto.Result;
import com.bykv.p167vk.openvk.api.proto.ValueSet;
import com.bytedance.pangle.GlobalParam;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.ZeusPluginStateListener;
import com.bytedance.pangle.log.IZeusLogger;
import com.bytedance.pangle.log.IZeusReporter;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.sdk.openadsdk.TTAdEvent;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTPluginListener;
import com.bytedance.sdk.openadsdk.api.C3969b;
import com.bytedance.sdk.openadsdk.api.C3972mb;
import com.bytedance.sdk.openadsdk.p187ox.C4074mb;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.bytedance.sdk.openadsdk.api.plugin.u */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4021u {

    /* renamed from: hj */
    private static volatile BaseDexClassLoader f9606hj;

    /* renamed from: je */
    private static volatile TTPluginListener f9607je;

    /* renamed from: u */
    private static volatile boolean f9610u;

    /* renamed from: x */
    private final Context f9615x;

    /* renamed from: mb */
    private static final String f9608mb = "next" + File.separator;

    /* renamed from: ox */
    private static final HashMap<String, TTPluginListener> f9609ox = new HashMap<>();

    /* renamed from: b */
    private static final HashMap<String, Handler> f9604b = new HashMap<>();

    /* renamed from: h */
    private static volatile C4021u f9605h = null;

    /* renamed from: ko */
    private final CountDownLatch f9612ko = new CountDownLatch(1);

    /* renamed from: ww */
    private volatile boolean f9614ww = false;

    /* renamed from: lz */
    private volatile String f9613lz = "none";

    /* renamed from: jb */
    private JSONObject f9611jb = new JSONObject();

    private C4021u(Context context) {
        this.f9615x = context.getApplicationContext();
        C3978hj.m16526mb(context);
        m16431ox(context.getApplicationContext());
    }

    /* renamed from: mb */
    public static C4021u m16446mb(Context context) {
        if (f9605h == null) {
            synchronized (C4021u.class) {
                if (f9605h == null) {
                    f9605h = new C4021u(context);
                }
            }
        }
        return f9605h;
    }

    /* renamed from: mb */
    public void m16448mb() {
        f9610u = true;
        C3978hj.m16522mb(new ArrayList());
    }

    /* renamed from: ox */
    public JSONObject m16432ox() {
        return this.f9611jb;
    }

    /* renamed from: mb */
    public BaseDexClassLoader m16442mb(C3977h c3977h) throws Exception {
        if (!this.f9614ww) {
            C3972mb.m16552hj("TTPluginManager", "Zeus init failed.");
            throw new C3976b(4, this.f9613lz);
        }
        if (!Zeus.isPluginInstalled("com.byted.pangle")) {
            try {
                this.f9612ko.await(60000L, TimeUnit.MILLISECONDS);
                c3977h.m16533ox("wait_install_cost");
            } catch (Exception unused) {
                C3972mb.m16552hj("TTPluginManager", "Install wait time out");
                throw new C3976b(8, "install wait timeout");
            }
        }
        boolean z = false;
        if (Zeus.isPluginLoaded("com.byted.pangle") || Zeus.loadPlugin("com.byted.pangle")) {
            f9606hj = Zeus.getPlugin("com.byted.pangle").mClassLoader;
            z = true;
        }
        c3977h.m16533ox("get_classloader_cost");
        Zeus.installFromDownloadDir();
        if (f9606hj == null) {
            if (this.f9612ko.getCount() != 0) {
                C3972mb.m16552hj("TTPluginManager", "Install wait time out");
                throw new C3976b(8, "install wait timeout");
            } else if (z) {
                C3972mb.m16552hj("TTPluginManager", "Get null after load");
                throw new C3976b(9, "Get null after load");
            }
        }
        c3977h.m16533ox("get_classloader_done");
        return f9606hj;
    }

    /* renamed from: ox */
    private void m16431ox(Context context) {
        try {
            IZeusReporter iZeusReporter = new IZeusReporter() { // from class: com.bytedance.sdk.openadsdk.api.plugin.u.1
                @Override // com.bytedance.pangle.log.IZeusReporter
                public void report(String str, JSONObject jSONObject) {
                    if (str == "load_finish" && jSONObject != null && "com.byted.pangle".endsWith(jSONObject.optString("plugin_package_name"))) {
                        try {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("duration", jSONObject.opt("duration"));
                            jSONObject2.put("message", jSONObject.opt("message"));
                            C4021u.this.f9611jb.put("zeus", jSONObject2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (C4021u.f9610u) {
                        C3978hj.m16523mb(str, jSONObject);
                    } else {
                        C3978hj.m16532b(str, jSONObject);
                    }
                }
            };
            GlobalParam globalParam = GlobalParam.getInstance();
            globalParam.setReporter(iZeusReporter);
            globalParam.setCheckPermission(false);
            globalParam.setDownloadDir(m16452b(context));
            globalParam.setLogger(new C4028mb());
            globalParam.setSignature("com.byted.pangle", "MIIDfTCCAmWgAwIBAgIEfRwYPjANBgkqhkiG9w0BAQsFADBvMQswCQYDVQQGEwJDTjEQMA4GA1UECBMHQmVpamluZzEQMA4GA1UEBxMHQmVpamluZzESMBAGA1UEChMJQnl0ZURhbmNlMQ8wDQYDVQQLEwZQYW5nbGUxFzAVBgNVBAMTDkNodWFuIFNoYW4gSmlhMB4XDTIxMTEwODA2MjQzOVoXDTQ2MTEwMjA2MjQzOVowbzELMAkGA1UEBhMCQ04xEDAOBgNVBAgTB0JlaWppbmcxEDAOBgNVBAcTB0JlaWppbmcxEjAQBgNVBAoTCUJ5dGVEYW5jZTEPMA0GA1UECxMGUGFuZ2xlMRcwFQYDVQQDEw5DaHVhbiBTaGFuIEppYTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAIBKeRL+4mfCn1SLYv6OemfwwItkjlLPyqOEugkV6lanFTcZgLwEl5LIkL0y28UncPtMX1Mii6DzCdJ/plw7S9+RT/hYDneu339IKWojaU2qai/5FokHlQ0MMnYl5yry00ghVPsl1u+03cQA2ZnjIMiFhrBJpQzHt7IYvq2aEEMBcY8uT7iFoBI848e1mL1joVS2z02C3NliP7ZNARkXH+rTQAlCJulT5IZk+V/PTaKqzgNrkhsKh0/tBmU7m8u79x/xpgGsE19H18AgS4P/9/MDCRe2Z35boZeccaUy2MXCwv3djzUcDk3rRzQPYzdpyyRnrFMuhiKesc5VHgUMs9kCAwEAAaMhMB8wHQYDVR0OBBYEFENENrNWGzc2WhxdvhoMDs57U70zMA0GCSqGSIb3DQEBCwUAA4IBAQAHqDCrmvyBBmIGXwuL1rwS/Qv9ZJIZykBIaNMm+H1IfitCl4yXd9N2n+PjE0UZtxZ21UZOt9wAr+RFiSl5YRXqpt7WLARTy4YW3RiQ+wiL7bshzeSYBoSiC427Bfeq0WjwY0/jHlr8uouppyJOz++6U9hrYX2EW/6UjH5XlWiKQJ6b2ZzPcP8Xpg/TJn4tWvXJP6jw9kRRP2GmMttY78leWQst2QEZILmWJubXRLPj9O+qx2uP9oGTD4sc1vb9hzkOHBIHzGaalqLFbbGaeFpLFHoGTsnOfPTwUVKDZYmxbkcmR1bp7eYOW+nSQNMLn0FjDewZl5l37Sa/gz0WVHon");
            globalParam.setSignature("com.byted.csj.ext", "MIIDezCCAmOgAwIBAgIENkE1KDANBgkqhkiG9w0BAQsFADBtMQswCQYDVQQGEwI4NjEQMA4GA1UECBMHYmVpamluZzEQMA4GA1UEBxMHYmVpamluZzESMBAGA1UEChMJYnl0ZWRhbmNlMRIwEAYDVQQLEwlieXRlZGFuY2UxEjAQBgNVBAMTCWJ5dGVkYW5jZTAgFw0yMjExMDIwODI3MzlaGA8yMDUwMDMyMDA4MjczOVowbTELMAkGA1UEBhMCODYxEDAOBgNVBAgTB2JlaWppbmcxEDAOBgNVBAcTB2JlaWppbmcxEjAQBgNVBAoTCWJ5dGVkYW5jZTESMBAGA1UECxMJYnl0ZWRhbmNlMRIwEAYDVQQDEwlieXRlZGFuY2UwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCc9Z2F3xxOMX1qTXMy2aPmS9OSkqrp8C8bHwS1hkNVR4umKREuqOn73INNo+R706jaCVnlPwxDwWjtX6H74DE4CveivyM9f2wNC3yIyDW+5j7lW/keTQcOlGLDEJQv4O/6FbB/jNU6epjyNaNIZhgZcvTpgaSixbdyHzRTFmvMh+WovdVK/J9LnHOQ+pmPZj7NB6MQRGMUrPEotLHQca3cmnLrnPAaZQaVoaFE9lOt9syyqEuf361SprNIGDtbkJuX3EqV/QOKWFwZX94IS7ZGSvfyCojcD4kaUSbaSoZC7zEuBb7l69g+ZMrJ/v6wkm01wxsNNssUwF7k6Sp0zubbAgMBAAGjITAfMB0GA1UdDgQWBBSxk+gVdDco1dP65hP67qoKNlMEYDANBgkqhkiG9w0BAQsFAAOCAQEAfosExl/AYEbS2xqHBTHa28cvnp/SElUQuzW6aWLqkfk9cxmFSI/euUV3/eB8RN+U2X47Y05u6+XUxTv0tSSEtyXNawm0qWH8jkR4gZY38YqBChKjhea668oT5X3Uocrw7SYXO/BfI8SKPa0uI/U8Cyl3uctbmmq/pPUkd3mKAy+HgyJoThD6K0oyiADlygngUMVTv6Uvid4qPj/bBnxI+LvVeX4l1dxGqWkiafQW9sz+RbFdge3X2XsSH4eo01BsCwOYEv1lHO2FrbAtFNpnIsSqrERdFaAJZ3tlJmg9bA03png8A2AajEjkhaOhduJB8zkSlvHNpoQMIAS9WtkG/w==");
            globalParam.setSignature("com.byted.live.lite", "MIIDSTCCAjGgAwIBAgIEaLy5tzANBgkqhkiG9w0BAQsFADBVMQswCQYDVQQGEwIxMTEMMAoGA1UECBMDMTExMQ4wDAYDVQQHEwUxMTExMTEMMAoGA1UEChMDMTExMQwwCgYDVQQLEwMxMTExDDAKBgNVBAMTAzExMTAeFw0yMDEyMDMxMjQyMTJaFw00NTExMjcxMjQyMTJaMFUxCzAJBgNVBAYTAjExMQwwCgYDVQQIEwMxMTExDjAMBgNVBAcTBTExMTExMQwwCgYDVQQKEwMxMTExDDAKBgNVBAsTAzExMTEMMAoGA1UEAxMDMTExMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA45E52YdkJm4gPCXZq7KDoM1h6pgSswllC/CwDOmh8pDGvX4ROaYP1vr2biRlXMHg7G0iXpxWVdlTtx+4QFd3dC+cGJQk0f6apGo2n2RpMA0zIsSf0VO1a3GjWLei5INo+4RDdciqJ4jfsoqBIjZETRkky+UU4eO/oyrAwOu4KdMln3Bg3u7eHWU4kMFrXxrRruT3Q/9gzlO90yQa0CZPWVDrk6cGJtJwJGhWm+62S3U8D26HE++eGP7ve83QBDGtKqx7HpCAFWUiYBgXGq12H0amQDkKcPcr/EFCaBlombSgkN0t6zBX80m+wcUPC75IBTmMV/DT2dXcgjZ2I1JSCQIDAQABoyEwHzAdBgNVHQ4EFgQUPDyIeKI0KhZFPHyn36gMMIYrpukwDQYJKoZIhvcNAQELBQADggEBAHkl0DoCRwn+XKsDJE+wGMpBBqUDzL6DSOnJx4SNqb7YZZU0ThcDK7jY4If3QRkvMio6ODrVZc2U/m/Tc3VeMk5h2W2UZRUWHNH3k9Xe0720uL20ZeH2Y6IG4L5HG8kIbTbFtX3gJpPG/xAcez+CzyCFLWQAZt1N+csG0syWkXJ0Nryq8VrgSCyCXD1KzFxrOe+65wtu50Vi68Vlbk7BZe/G8Qm0RhKmxq5BPMBJ4uY3be+03Ba5qC//o1XQHOEAjrJKXcN5wqHdFZTkmuxVyIPogZOzx4JlNl0zOrYGDJxp7aZfKF9FkXQyF7x0Ns3mZEtjx/+flXRzAAU9MDhPr/0=");
            Zeus.registerPluginStateListener(new ZeusPluginStateListener() { // from class: com.bytedance.sdk.openadsdk.api.plugin.u.2
                @Override // com.bytedance.pangle.ZeusPluginStateListener
                public void onPluginStateChange(final String str, final int i, Object... objArr) {
                    C3972mb.m16544ox("TTPluginManager", str + " state changed, " + i);
                    if (i == 7) {
                        C4021u.this.m16426ox(str, i);
                    } else if (i == 6) {
                        C4074mb.m16346mb().m16345mb(new Runnable() { // from class: com.bytedance.sdk.openadsdk.api.plugin.u.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                C4021u.this.m16426ox(str, i);
                            }
                        });
                    }
                }
            });
            Zeus.init((Application) context, true);
            this.f9614ww = true;
        } catch (Throwable th) {
            C3972mb.m16547mb("TTPluginManager", "Unexpected error for init zeus.", th);
            this.f9613lz = th.getMessage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ox */
    public void m16426ox(String str, int i) {
        if ("com.byted.pangle".equals(str) && i == 6) {
            this.f9612ko.countDown();
        }
        m16433mb(i == 6, str);
    }

    /* renamed from: b */
    private static File m16452b(Context context) {
        return new File(new File(context.getDir("tt_pangle_bykv_file", 0), "pangle_com.byted.pangle"), f9608mb);
    }

    /* renamed from: mb */
    public Bundle m16436mb(String str, Bundle bundle) {
        String m16438mb = m16438mb(str);
        if (!TextUtils.isEmpty(m16438mb)) {
            bundle.putString("plugin_version", m16438mb);
        }
        C3982ko.m16514mb(str, bundle);
        Bundle bundle2 = new Bundle();
        bundle2.putBundle(str, bundle);
        Bundle bundle3 = new Bundle();
        bundle3.putBundle("_pl_config_info_", bundle2);
        return bundle3;
    }

    /* renamed from: mb */
    public static String m16438mb(String str) {
        Plugin plugin;
        try {
            if (!Zeus.isPluginInstalled(str) || (plugin = Zeus.getPlugin(str)) == null) {
                return null;
            }
            return m16447mb(plugin.getVersion());
        } catch (Throwable unused) {
            C3972mb.m16552hj("TTPluginManager", "Get local version failed");
            return null;
        }
    }

    /* renamed from: mb */
    public void m16444mb(final TTPluginListener tTPluginListener) {
        if (!this.f9614ww) {
            C3972mb.m16552hj("TTPluginManager", "Zeus init failed.");
            if (tTPluginListener != null) {
                tTPluginListener.onPluginListener(1002, null, null, null);
                return;
            }
            return;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() { // from class: com.bytedance.sdk.openadsdk.api.plugin.u.3
            @Override // java.lang.Runnable
            public void run() {
                C3972mb.m16544ox("TTPluginManager", "Load plugin failed, caused by timeout.");
                tTPluginListener.onPluginListener(1001, null, null, null);
            }
        }, 180000L);
        String packageName = tTPluginListener.packageName();
        Plugin plugin = (Zeus.isPluginInstalled(packageName) && (Zeus.isPluginLoaded(packageName) || Zeus.loadPlugin(packageName))) ? Zeus.getPlugin(packageName) : null;
        StringBuilder sb = new StringBuilder();
        sb.append("Find plugin:");
        sb.append(plugin != null);
        C3972mb.m16544ox("TTPluginManager", sb.toString());
        if (plugin != null) {
            m16430ox(plugin);
            handler.removeCallbacksAndMessages(null);
            tTPluginListener.onPluginListener(1000, plugin.mClassLoader, plugin.mResources, null);
            return;
        }
        f9609ox.put(packageName, tTPluginListener);
        f9604b.put(packageName, handler);
    }

    /* renamed from: ox */
    public void m16429ox(final TTPluginListener tTPluginListener) {
        C4074mb.m16346mb().m16345mb(new Runnable() { // from class: com.bytedance.sdk.openadsdk.api.plugin.u.4
            @Override // java.lang.Runnable
            public void run() {
                String packageName = tTPluginListener.packageName();
                Plugin plugin = (Zeus.isPluginInstalled(packageName) && (Zeus.isPluginLoaded(packageName) || Zeus.loadPlugin(packageName))) ? Zeus.getPlugin(packageName) : null;
                StringBuilder sb = new StringBuilder();
                sb.append("Find plugin:");
                sb.append(plugin != null);
                C3972mb.m16544ox("TTPluginManager", sb.toString());
                if (plugin != null) {
                    C4021u.m16430ox(plugin);
                    tTPluginListener.onPluginListener(1000, plugin.mClassLoader, plugin.mResources, null);
                    return;
                }
                TTPluginListener unused = C4021u.f9607je = tTPluginListener;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static C4017ox m16450b(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return m16434mb(new JSONObject(str));
        } catch (JSONException unused) {
            C3972mb.m16553h("TTPluginManager", "Invalid plugin info:" + str);
            return null;
        }
    }

    /* renamed from: mb */
    private static C4017ox m16434mb(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        C4017ox c4017ox = new C4017ox();
        c4017ox.mPackageName = jSONObject.optString("package_name");
        c4017ox.mVersionCode = jSONObject.optInt("version_code");
        c4017ox.mUrl = jSONObject.optString("download_url");
        c4017ox.mMd5 = jSONObject.optString("md5");
        c4017ox.mApiVersionMin = jSONObject.optInt("min_version");
        c4017ox.mApiVersionMax = jSONObject.optInt("max_version");
        c4017ox.f9601mb = jSONObject.optString("sign");
        c4017ox.mFlag = jSONObject.optBoolean("is_revert") ? 3 : 2;
        c4017ox.f9602ox = new File(jSONObject.optString("plugin_file"));
        return c4017ox;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ox */
    public static void m16430ox(Plugin plugin) {
        if (plugin == null) {
            C3972mb.m16552hj("TTPluginManager", "plugin is null.");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("action", 0);
        bundle.putString("plugin_pkg_name", plugin.mPkgName);
        bundle.putString("plugin_version", m16447mb(plugin.getVersion()));
        TTAdManager adManager = TTAdSdk.getAdManager();
        if (adManager != null) {
            adManager.getExtra(Bundle.class, bundle);
        }
    }

    /* renamed from: mb */
    public static String m16447mb(int i) {
        char[] charArray = String.valueOf(i).toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < charArray.length; i2++) {
            sb.append(charArray[i2]);
            if (i2 < charArray.length - 1) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    /* renamed from: mb */
    private static boolean m16443mb(TTPluginListener tTPluginListener, String str) {
        if (tTPluginListener == null || tTPluginListener.packageName() == null) {
            return false;
        }
        return tTPluginListener.packageName().equals(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ox */
    public static boolean m16428ox(C4017ox c4017ox) {
        if (c4017ox == null || c4017ox.f9602ox == null) {
            C3972mb.m16552hj("TTPluginManager", "plugin config is null");
            return false;
        }
        boolean syncInstallPlugin = Zeus.syncInstallPlugin(c4017ox.mPackageName, c4017ox.f9602ox.getAbsolutePath());
        m16433mb(syncInstallPlugin, c4017ox.mPackageName);
        return syncInstallPlugin;
    }

    /* renamed from: mb */
    private static void m16433mb(boolean z, String str) {
        TTPluginListener tTPluginListener = f9609ox.get(str);
        StringBuilder sb = new StringBuilder();
        sb.append("Install dl plugin ");
        sb.append(str);
        sb.append(z ? " success" : " failed");
        sb.append(", need notify: ");
        sb.append(tTPluginListener != null);
        C3972mb.m16544ox("TTPluginManager", sb.toString());
        Handler handler = f9604b.get(str);
        if (z) {
            TTPluginListener tTPluginListener2 = f9607je;
            if (!m16443mb(tTPluginListener2, str) && (tTPluginListener == null || handler == null)) {
                return;
            }
            if (Zeus.loadPlugin(str)) {
                Plugin plugin = Zeus.getPlugin(str);
                m16430ox(plugin);
                if (handler != null) {
                    handler.removeCallbacksAndMessages(null);
                }
                if (tTPluginListener != null) {
                    tTPluginListener.onPluginListener(1000, plugin.mClassLoader, plugin.mResources, null);
                }
                if (m16443mb(tTPluginListener2, str)) {
                    tTPluginListener2.onPluginListener(1000, plugin.mClassLoader, plugin.mResources, null);
                    f9607je = null;
                }
            } else {
                m16449b(str, 1002);
            }
        } else {
            m16449b(str, 1003);
        }
        f9609ox.remove(str);
        f9604b.remove(str);
    }

    /* renamed from: mb */
    public static void m16435mb(Throwable th) {
        if (th instanceof AbstractMethodError) {
            Zeus.unInstallPlugin("com.byted.pangle");
            C3972mb.m16552hj("TTPluginManager", "AbstractMethodError, rollback to builtin version.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m16449b(String str, int i) {
        C3972mb.m16552hj("TTPluginManager", "plugin update failed");
        Bundle bundle = new Bundle();
        bundle.putInt("code", i);
        TTPluginListener tTPluginListener = f9609ox.get(str);
        if (tTPluginListener != null) {
            tTPluginListener.onPluginListener(1001, null, null, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.bytedance.sdk.openadsdk.api.plugin.u$ox */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static final class C4029ox implements TTAdEvent {
        @Override // com.bytedance.sdk.openadsdk.TTAdEvent
        public void onEvent(int i, Bundle bundle) {
            if (i == 1) {
                String string = bundle.getString("config");
                String string2 = bundle.getString("plugin_pkg_name");
                int i2 = bundle.getInt("code");
                if (i2 != 0) {
                    C4021u.m16449b(string2, i2);
                    return;
                }
                C4017ox m16450b = C4021u.m16450b(string);
                if (m16450b == null || TextUtils.isEmpty(m16450b.mPackageName)) {
                    C3972mb.m16552hj("TTPluginManager", "plugin update received with invalid config");
                } else if (!bundle.getBoolean("success")) {
                    C4021u.m16449b(m16450b.mPackageName, 1004);
                } else {
                    C3972mb.m16552hj("TTPluginManager", "plugin update received: " + m16450b.mPackageName);
                    if (!m16450b.isRevert()) {
                        if (C4021u.m16428ox(m16450b)) {
                            bundle.putBoolean("installed", true);
                            return;
                        }
                        return;
                    }
                    Zeus.unInstallPlugin(m16450b.mPackageName);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.sdk.openadsdk.api.plugin.u$mb */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class C4028mb implements IZeusLogger {
        private C4028mb() {
        }

        @Override // com.bytedance.pangle.log.IZeusLogger
        /* renamed from: v */
        public void mo16423v(String str, String str2) {
            C3972mb.m16548mb(str, str2);
        }

        @Override // com.bytedance.pangle.log.IZeusLogger
        /* renamed from: i */
        public void mo16424i(String str, String str2) {
            C3972mb.m16554b(str, str2);
        }

        @Override // com.bytedance.pangle.log.IZeusLogger
        /* renamed from: w */
        public void mo16422w(String str, String str2) {
            C3972mb.m16548mb(str, str2);
        }

        @Override // com.bytedance.pangle.log.IZeusLogger
        /* renamed from: w */
        public void mo16421w(String str, String str2, Throwable th) {
            C3972mb.m16547mb(str, str2, th);
        }

        @Override // com.bytedance.pangle.log.IZeusLogger
        /* renamed from: e */
        public void mo16425e(String str, String str2, Throwable th) {
            C3972mb.m16543ox(str, str2, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.bytedance.sdk.openadsdk.api.plugin.u$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static final class C4027b implements EventListener, Serializable {
        @Override // com.bykv.p167vk.openvk.api.proto.EventListener
        public ValueSet onEvent(int i, Result result) {
            C3969b m16559mb = C3969b.m16559mb();
            if (i == 1) {
                ValueSet values = result.values();
                if (values == null) {
                    return null;
                }
                String stringValue = values.stringValue(3);
                int code = result.code();
                if (!result.isSuccess()) {
                    C4021u.m16449b(stringValue, code);
                    return null;
                }
                C4017ox m16450b = C4021u.m16450b(values.stringValue(2));
                if (m16450b == null || TextUtils.isEmpty(m16450b.mPackageName)) {
                    C3972mb.m16552hj("TTPluginManager", "plugin update received with invalid config");
                    return null;
                }
                C3972mb.m16552hj("TTPluginManager", "plugin update received: " + m16450b.mPackageName);
                if (!m16450b.isRevert()) {
                    if (C4021u.m16428ox(m16450b)) {
                        m16559mb.m16556mb(4, true);
                    }
                } else {
                    Zeus.unInstallPlugin(m16450b.mPackageName);
                }
            }
            return m16559mb.m16555ox();
        }
    }
}
