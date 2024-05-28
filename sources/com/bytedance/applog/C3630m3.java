package com.bytedance.applog;

import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.Nullable;
import com.bytedance.applog.C3597h3;
import com.bytedance.applog.IOaidObserver;
import com.bytedance.applog.InterfaceC3645n3;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.m3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3630m3 {

    /* renamed from: i */
    public static final String f8585i = C3630m3.class.getSimpleName() + "#";

    /* renamed from: j */
    public static final String f8586j = f8585i;
    @Nullable

    /* renamed from: k */
    public static IOaidObserver f8587k;

    /* renamed from: l */
    public static String f8588l;

    /* renamed from: b */
    public InterfaceC3645n3 f8590b;

    /* renamed from: c */
    public boolean f8591c;

    /* renamed from: d */
    public C3663p3 f8592d;

    /* renamed from: e */
    public final Context f8593e;

    /* renamed from: g */
    public Map<String, String> f8595g;

    /* renamed from: h */
    public Long f8596h;

    /* renamed from: a */
    public final ReentrantLock f8589a = new ReentrantLock();

    /* renamed from: f */
    public AtomicBoolean f8594f = new AtomicBoolean(false);

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.m3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class RunnableC3631a implements Runnable {
        public RunnableC3631a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C3630m3.this.m17221b();
        }
    }

    public C3630m3(Context context) {
        boolean z;
        this.f8593e = context;
        InterfaceC3645n3 interfaceC3645n3 = null;
        if (C3680r2.m17131c()) {
            interfaceC3645n3 = new C3681r3(new C3725w3());
        } else if (C3725w3.m17058a()) {
            interfaceC3645n3 = new C3725w3();
        } else if (C3675q3.m17136a()) {
            interfaceC3645n3 = new C3675q3(context);
        } else if (C3680r2.m17133b().toUpperCase().contains("HUAWEI")) {
            interfaceC3645n3 = new C3597h3();
        } else if ("OnePlus".equalsIgnoreCase(Build.MANUFACTURER)) {
            interfaceC3645n3 = new C3681r3(null);
        } else {
            String str = Build.BRAND;
            if (str == null ? false : str.toLowerCase(Locale.ENGLISH).contains("meizu")) {
                interfaceC3645n3 = new C3616k3();
            } else if (Build.VERSION.SDK_INT > 28) {
                if ("samsung".equalsIgnoreCase(Build.BRAND) || "samsung".equalsIgnoreCase(Build.MANUFACTURER)) {
                    interfaceC3645n3 = new C3705u3();
                } else if (C3680r2.m17133b().toUpperCase().contains("NUBIA")) {
                    interfaceC3645n3 = new C3623l3();
                } else {
                    String str2 = Build.FINGERPRINT;
                    if (TextUtils.isEmpty(str2)) {
                        String m17134a = C3680r2.m17134a("ro.build.version.incremental");
                        z = !TextUtils.isEmpty(m17134a) && m17134a.contains("VIBEUI_V2");
                    } else {
                        z = str2.contains("VIBEUI_V2");
                    }
                    if (z) {
                        interfaceC3645n3 = new C3610j3();
                    } else {
                        interfaceC3645n3 = C3680r2.m17133b().toUpperCase().contains("ASUS") ? new C3738y2() : new C3561c3();
                    }
                }
            } else if (!C3680r2.m17129e() && C3597h3.m17284c(context)) {
                interfaceC3645n3 = new C3597h3();
            }
        }
        this.f8590b = interfaceC3645n3;
        InterfaceC3645n3 interfaceC3645n32 = this.f8590b;
        if (interfaceC3645n32 != null) {
            this.f8591c = interfaceC3645n32.mo17056b(context);
        } else {
            this.f8591c = false;
        }
        this.f8592d = new C3663p3(context);
    }

    /* renamed from: a */
    public static void m17225a(@Nullable IOaidObserver.Oaid oaid) {
        IOaidObserver iOaidObserver;
        if (oaid == null || (iOaidObserver = f8587k) == null) {
            return;
        }
        iOaidObserver.onOaidLoaded(oaid);
    }

    /* renamed from: a */
    public static <K, V> void m17223a(Map<K, V> map, K k, V v) {
        if (k == null || v == null) {
            return;
        }
        map.put(k, v);
    }

    /* renamed from: a */
    public static void m17222a(JSONObject jSONObject, String str, Object obj) {
        if (TextUtils.isEmpty(str) || obj == null) {
            return;
        }
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m17226a() {
        if (this.f8594f.compareAndSet(false, true)) {
            RunnableC3631a runnableC3631a = new RunnableC3631a();
            String m17348a = C3535a.m17348a(new StringBuilder(), f8586j, "-query");
            if (TextUtils.isEmpty(m17348a)) {
                m17348a = "TrackerDr";
            }
            new Thread(new RunnableC3590g3(runnableC3631a, m17348a), m17348a).start();
        }
    }

    /* renamed from: b */
    public final void m17221b() {
        String str;
        Boolean bool;
        C3656o3 c3656o3;
        int i;
        String str2;
        InterfaceC3645n3.C3646a mo17057a;
        C3578e3.m17305a(f8586j, "Oaid#initOaid", null);
        try {
            this.f8589a.lock();
            C3578e3.m17305a(f8586j, "Oaid#initOaid exec", null);
            C3656o3 m17164a = this.f8592d.m17164a();
            C3578e3.m17305a(f8586j, "Oaid#initOaid fetch=" + m17164a, null);
            if (m17164a != null) {
                f8588l = m17164a.f8677a;
                this.f8595g = m17164a.m17184a();
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Context context = this.f8593e;
            InterfaceC3645n3 interfaceC3645n3 = this.f8590b;
            if (interfaceC3645n3 == null || (mo17057a = interfaceC3645n3.mo17057a(context)) == null) {
                str = null;
                bool = null;
            } else {
                str = mo17057a.f8617a;
                bool = Boolean.valueOf(mo17057a.f8618b);
                if (mo17057a instanceof C3597h3.C3599b) {
                    this.f8596h = Long.valueOf(((C3597h3.C3599b) mo17057a).f8493c);
                }
            }
            Pair pair = new Pair(str, bool);
            long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
            if (pair.first != null) {
                if (m17164a != null) {
                    str2 = m17164a.f8678b;
                    i = m17164a.f8682f.intValue() + 1;
                } else {
                    i = -1;
                    str2 = null;
                }
                if (TextUtils.isEmpty(str2)) {
                    str2 = UUID.randomUUID().toString();
                }
                String str3 = str2;
                if (i <= 0) {
                    i = 1;
                }
                c3656o3 = new C3656o3((String) pair.first, str3, (Boolean) pair.second, Long.valueOf(elapsedRealtime2), Long.valueOf(System.currentTimeMillis()), Integer.valueOf(i), this.f8596h);
                this.f8592d.m17163a(c3656o3);
            } else {
                c3656o3 = null;
            }
            if (c3656o3 != null) {
                f8588l = c3656o3.f8677a;
                this.f8595g = c3656o3.m17184a();
            }
            C3578e3.m17305a(f8586j, "Oaid#initOaid oaidModel=" + c3656o3, null);
        } finally {
            this.f8589a.unlock();
            m17225a(new IOaidObserver.Oaid(f8588l));
        }
    }
}
