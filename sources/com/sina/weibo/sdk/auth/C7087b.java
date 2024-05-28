package com.sina.weibo.sdk.auth;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.auth.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7087b {

    /* renamed from: e */
    private Map<String, WbAuthListener> f18301e;

    /* synthetic */ C7087b(byte b) {
        this();
    }

    private C7087b() {
        this.f18301e = new HashMap();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sina.weibo.sdk.auth.b$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static class C7088a {

        /* renamed from: f */
        private static final C7087b f18302f = new C7087b((byte) 0);
    }

    /* renamed from: b */
    public static synchronized C7087b m8090b() {
        C7087b c7087b;
        synchronized (C7087b.class) {
            c7087b = C7088a.f18302f;
        }
        return c7087b;
    }

    /* renamed from: a */
    public final synchronized void m8091a(String str, WbAuthListener wbAuthListener) {
        if (!TextUtils.isEmpty(str) && wbAuthListener != null) {
            this.f18301e.put(str, wbAuthListener);
        }
    }

    /* renamed from: a */
    public final synchronized WbAuthListener m8092a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.f18301e.get(str);
    }

    /* renamed from: b */
    public final synchronized void m8089b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f18301e.remove(str);
    }
}
