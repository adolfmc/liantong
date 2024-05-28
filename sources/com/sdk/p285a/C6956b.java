package com.sdk.p285a;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.sdk.p286b.C6966c;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.sdk.a.b */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C6956b {

    /* renamed from: c */
    public static long f17987c = 60000;

    /* renamed from: d */
    public static final ConcurrentHashMap<String, Boolean> f17988d;

    /* renamed from: a */
    public final C6966c<String, String> f17989a;

    /* renamed from: b */
    public int f17990b;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sdk.a.b$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class C6957a extends C6966c<String, String> {
        public C6957a(C6956b c6956b, int i) {
            super(i);
        }
    }

    static {
        ConcurrentHashMap<String, Boolean> concurrentHashMap = new ConcurrentHashMap<>(10);
        f17988d = concurrentHashMap;
        concurrentHashMap.put("GET", Boolean.TRUE);
        new ConcurrentHashMap(10);
    }

    public C6956b(int i, long j) {
        this.f17990b = 102400;
        this.f17990b = i;
        f17987c = j;
        this.f17989a = new C6957a(this, i);
    }

    /* renamed from: a */
    public static long m8218a() {
        return f17987c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public String m8217a(String str) {
        String str2 = null;
        if (str != null) {
            C6966c<String, String> c6966c = this.f17989a;
            synchronized (c6966c) {
                if (!c6966c.f18057d.containsKey(str)) {
                    synchronized (c6966c) {
                        Object remove = c6966c.f18054a.remove(str);
                        c6966c.f18057d.m8191a(str);
                        if (remove != null) {
                            c6966c.f18055b -= c6966c.m8188a(str, remove);
                        }
                    }
                } else {
                    Object obj = c6966c.f18054a.get(str);
                    if (obj != null) {
                        str2 = obj;
                    }
                }
            }
            return str2;
        }
        return null;
    }

    @SuppressLint({"DefaultLocale"})
    /* renamed from: b */
    public boolean m8215b(String str) {
        Boolean bool;
        if (TextUtils.isEmpty(str) || (bool = f17988d.get(str.toUpperCase())) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public void m8216a(String str, String str2, long j) {
        if (str == null || str2 == null || j < 1) {
            return;
        }
        C6966c<String, String> c6966c = this.f17989a;
        long currentTimeMillis = System.currentTimeMillis() + j;
        synchronized (c6966c) {
            c6966c.f18055b += c6966c.m8188a(str, str2);
            Object put = c6966c.f18054a.put(str, str2);
            c6966c.f18057d.put(str, Long.valueOf(currentTimeMillis));
            if (put != null) {
                c6966c.f18055b -= c6966c.m8188a(str, put);
            }
        }
        c6966c.m8189a(c6966c.f18056c);
    }
}
