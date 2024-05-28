package com.networkbench.agent.impl.p254f;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.f.k */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6399k {
    @NonNull

    /* renamed from: a */
    private static C6399k f16160a = new C6399k();

    /* renamed from: b */
    private static final int f16161b = 2;

    /* renamed from: c */
    private final ThreadLocal<String> f16162c = new ThreadLocal<>();

    /* renamed from: d */
    private C6400l f16163d;

    /* renamed from: a */
    public C6399k m10099a(String str) {
        if (str != null) {
            this.f16162c.set(str);
        }
        return this;
    }

    /* renamed from: a */
    public void m10098a(@NonNull String str, @Nullable Object... objArr) {
        m10102a(3, (Throwable) null, str, objArr);
    }

    /* renamed from: a */
    public void m10100a(@Nullable Object obj) {
        m10102a(3, (Throwable) null, C6391c.m10170a(obj), new Object[0]);
    }

    /* renamed from: b */
    public void m10095b(@NonNull String str, @Nullable Object... objArr) {
        m10097a(null, str, objArr);
    }

    /* renamed from: a */
    public void m10097a(@Nullable Throwable th, @NonNull String str, @Nullable Object... objArr) {
        m10102a(6, th, str, objArr);
    }

    /* renamed from: c */
    public void m10094c(@NonNull String str, @Nullable Object... objArr) {
        m10102a(4, (Throwable) null, str, objArr);
    }

    /* renamed from: d */
    public void m10093d(@NonNull String str, @Nullable Object... objArr) {
        m10102a(2, (Throwable) null, str, objArr);
    }

    /* renamed from: e */
    public void m10092e(@NonNull String str, @Nullable Object... objArr) {
        m10102a(5, (Throwable) null, str, objArr);
    }

    /* renamed from: b */
    public void m10096b(String str) {
        if (C6391c.m10172a((CharSequence) str)) {
            m10100a("Empty/Null json content");
            return;
        }
        try {
            String trim = str.trim();
            if (trim.startsWith("{")) {
                m10100a((Object) new JSONObject(trim).toString(2));
            } else if (trim.startsWith("[")) {
                m10100a((Object) new JSONArray(trim).toString(2));
            } else {
                m10095b("Invalid Json", new Object[0]);
            }
        } catch (JSONException unused) {
            m10095b("Invalid Json", new Object[0]);
        }
    }

    /* renamed from: a */
    public synchronized void m10103a(int i, String str, String str2, Throwable th) {
        if (th != null && str2 != null) {
            str2 = str2 + "\n " + C6391c.m10169a(th);
        }
        if (th != null && str2 == null) {
            str2 = C6391c.m10169a(th);
        }
        if (C6391c.m10172a((CharSequence) str2)) {
            str2 = "Empty/NULL log message";
        }
        this.f16163d.m10087a(i, str, str2);
    }

    /* renamed from: a */
    public void m10101a(@NonNull C6400l c6400l) {
        this.f16163d = c6400l;
    }

    /* renamed from: a */
    private synchronized void m10102a(int i, Throwable th, @NonNull String str, Object... objArr) {
        m10103a(i, m10104a(), m10091f(str, objArr), th);
    }

    /* renamed from: a */
    private String m10104a() {
        String str = this.f16162c.get();
        if (str != null) {
            this.f16162c.remove();
            return str;
        }
        return null;
    }

    /* renamed from: f */
    private String m10091f(@NonNull String str, Object... objArr) {
        return (objArr == null || objArr.length == 0) ? str : String.format(str, objArr);
    }
}
