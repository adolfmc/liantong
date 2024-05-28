package com.huawei.hms.framework.network.grs.p215e;

import android.content.Context;
import android.content.pm.PackageManager;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.PLSharedPreferences;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.huawei.hms.framework.network.grs.e.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4925c {

    /* renamed from: b */
    private static final String f11231b = "c";

    /* renamed from: c */
    private static final Map<String, PLSharedPreferences> f11232c = new ConcurrentHashMap(16);

    /* renamed from: a */
    private final PLSharedPreferences f11233a;

    public C4925c(Context context, String str) {
        String packageName = context.getPackageName();
        Logger.m15053d(f11231b, "get pkgname from context is{%s}", packageName);
        Map<String, PLSharedPreferences> map = f11232c;
        if (map.containsKey(str + packageName)) {
            Map<String, PLSharedPreferences> map2 = f11232c;
            this.f11233a = map2.get(str + packageName);
        } else {
            this.f11233a = new PLSharedPreferences(context, str + packageName);
            Map<String, PLSharedPreferences> map3 = f11232c;
            map3.put(str + packageName, this.f11233a);
        }
        m14992a(context);
    }

    /* renamed from: a */
    private void m14992a(Context context) {
        String str = f11231b;
        Logger.m15049i(str, "ContextHolder.getAppContext() from GRS is:" + ContextHolder.getAppContext());
        if (ContextHolder.getAppContext() != null) {
            context = ContextHolder.getAppContext();
        }
        try {
            String l = Long.toString(context.getPackageManager().getPackageInfo(context.getPackageName(), 16384).versionCode);
            String m14990a = m14990a("version", "");
            if (l.equals(m14990a)) {
                return;
            }
            Logger.m15048i(f11231b, "app version changed! old version{%s} and new version{%s}", m14990a, l);
            m14989b();
            m14988b("version", l);
        } catch (PackageManager.NameNotFoundException unused) {
            Logger.m15045w(f11231b, "get app version failed and catch NameNotFoundException");
        }
    }

    /* renamed from: a */
    public String m14990a(String str, String str2) {
        String string;
        PLSharedPreferences pLSharedPreferences = this.f11233a;
        if (pLSharedPreferences == null) {
            return str2;
        }
        synchronized (pLSharedPreferences) {
            string = this.f11233a.getString(str, str2);
        }
        return string;
    }

    /* renamed from: a */
    public Map<String, ?> m14993a() {
        Map<String, ?> all;
        PLSharedPreferences pLSharedPreferences = this.f11233a;
        if (pLSharedPreferences == null) {
            return new HashMap();
        }
        synchronized (pLSharedPreferences) {
            all = this.f11233a.getAll();
        }
        return all;
    }

    /* renamed from: a */
    public void m14991a(String str) {
        PLSharedPreferences pLSharedPreferences = this.f11233a;
        if (pLSharedPreferences == null) {
            return;
        }
        synchronized (pLSharedPreferences) {
            this.f11233a.remove(str);
        }
    }

    /* renamed from: b */
    public void m14989b() {
        PLSharedPreferences pLSharedPreferences = this.f11233a;
        if (pLSharedPreferences == null) {
            return;
        }
        synchronized (pLSharedPreferences) {
            this.f11233a.clear();
        }
    }

    /* renamed from: b */
    public void m14988b(String str, String str2) {
        PLSharedPreferences pLSharedPreferences = this.f11233a;
        if (pLSharedPreferences == null) {
            return;
        }
        synchronized (pLSharedPreferences) {
            this.f11233a.putString(str, str2);
        }
    }
}
