package com.networkbench.agent.impl.p266l;

import com.networkbench.agent.impl.harvest.ConfigurationName;
import com.networkbench.agent.impl.util.C6638h;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.l.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6492b {

    /* renamed from: c */
    private static volatile C6492b f16430c;

    /* renamed from: b */
    public volatile Boolean f16432b = new Boolean(false);

    /* renamed from: a */
    C6491a f16431a = new C6491a(C6638h.m8963w().m9076K(), ConfigurationName.BROWSER_DATA_STORE_PATH);

    /* renamed from: a */
    public static C6492b m9780a() {
        if (f16430c == null) {
            synchronized (C6492b.class) {
                if (f16430c == null) {
                    f16430c = new C6492b();
                }
            }
        }
        return f16430c;
    }

    /* renamed from: a */
    public synchronized void m9778a(String str, long j) {
        this.f16431a.m9785a(str, j);
    }

    /* renamed from: b */
    public synchronized Map<String, ?> m9777b() {
        return this.f16431a.m9788a();
    }

    /* renamed from: a */
    public synchronized void m9779a(String str) {
        this.f16431a.m9786a(str);
    }
}
