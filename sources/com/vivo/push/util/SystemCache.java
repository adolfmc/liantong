package com.vivo.push.util;

import android.content.Context;
import java.util.HashMap;

/* renamed from: com.vivo.push.util.af */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class SystemCache implements Cache {

    /* renamed from: a */
    private static final HashMap<String, Integer> f21185a = new HashMap<>();

    /* renamed from: b */
    private static final HashMap<String, Long> f21186b = new HashMap<>();

    /* renamed from: c */
    private static final HashMap<String, String> f21187c = new HashMap<>();

    /* renamed from: d */
    private static SystemCache f21188d;

    /* renamed from: e */
    private Context f21189e;

    /* renamed from: f */
    private Cache f21190f;

    /* renamed from: g */
    private boolean f21191g;

    private SystemCache(Context context) {
        this.f21191g = false;
        this.f21189e = context;
        this.f21191g = mo5409a(context);
        LogUtil.m5341d("SystemCache", "init status is " + this.f21191g + ";  curCache is " + this.f21190f);
    }

    /* renamed from: b */
    public static synchronized SystemCache m5449b(Context context) {
        SystemCache systemCache;
        synchronized (SystemCache.class) {
            if (f21188d == null) {
                f21188d = new SystemCache(context.getApplicationContext());
            }
            systemCache = f21188d;
        }
        return systemCache;
    }

    /* renamed from: a */
    public final void m5450a() {
        SpCache spCache = new SpCache();
        if (spCache.mo5409a(this.f21189e)) {
            spCache.m5451a();
            LogUtil.m5341d("SystemCache", "sp cache is cleared");
        }
    }

    @Override // com.vivo.push.util.Cache
    /* renamed from: a */
    public final boolean mo5409a(Context context) {
        this.f21190f = new SettingsCache();
        boolean mo5409a = this.f21190f.mo5409a(context);
        if (!mo5409a) {
            this.f21190f = new SpCache();
            mo5409a = this.f21190f.mo5409a(context);
        }
        if (!mo5409a) {
            this.f21190f = null;
        }
        return mo5409a;
    }

    @Override // com.vivo.push.util.Cache
    /* renamed from: a */
    public final String mo5408a(String str, String str2) {
        Cache cache;
        String str3 = f21187c.get(str);
        return (str3 != null || (cache = this.f21190f) == null) ? str3 : cache.mo5408a(str, str2);
    }

    @Override // com.vivo.push.util.Cache
    /* renamed from: b */
    public final void mo5407b(String str, String str2) {
        Cache cache;
        f21187c.put(str, str2);
        if (!this.f21191g || (cache = this.f21190f) == null) {
            return;
        }
        cache.mo5407b(str, str2);
    }
}
