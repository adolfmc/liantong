package com.vivo.push.p374g;

import java.util.ArrayList;

/* renamed from: com.vivo.push.g.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class TestManager {

    /* renamed from: a */
    private static String[] f21017a = {"com.vivo.pushservice", "com.vivo.pushdemo.test", "com.vivo.sdk.test"};

    /* renamed from: b */
    private ArrayList<String> f21018b;

    /* synthetic */ TestManager(byte b) {
        this();
    }

    /* renamed from: a */
    public static TestManager m5664a() {
        return C10960a.f21019a;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: TestManager.java */
    /* renamed from: com.vivo.push.g.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class C10960a {

        /* renamed from: a */
        private static TestManager f21019a = new TestManager((byte) 0);
    }

    private TestManager() {
        this.f21018b = null;
        this.f21018b = new ArrayList<>();
    }

    /* renamed from: b */
    public final boolean m5663b() {
        ArrayList<String> arrayList = this.f21018b;
        return (arrayList == null || arrayList.size() == 0) ? false : true;
    }
}
