package com.xiaomi.push;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.x */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11649x {

    /* renamed from: a */
    private static int f23810a;

    /* renamed from: a */
    public static final String f23811a;

    /* renamed from: a */
    public static boolean f23812a;

    static {
        f23811a = C11130aa.f21452a ? "ONEBOX" : "@SHIP.TO.2A2FE0D7@";
        f23812a = false;
        f23810a = 1;
        if (f23811a.equalsIgnoreCase("SANDBOX")) {
            f23810a = 2;
        } else if (f23811a.equalsIgnoreCase("ONEBOX")) {
            f23810a = 3;
        } else {
            f23810a = 1;
        }
    }

    /* renamed from: a */
    public static boolean m2264a() {
        return f23810a == 2;
    }

    /* renamed from: b */
    public static boolean m2262b() {
        return f23810a == 3;
    }

    /* renamed from: a */
    public static int m2265a() {
        return f23810a;
    }

    /* renamed from: a */
    public static void m2263a(int i) {
        f23810a = i;
    }
}
