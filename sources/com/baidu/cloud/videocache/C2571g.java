package com.baidu.cloud.videocache;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.videocache.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2571g {
    /* renamed from: a */
    public static Object m19807a(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException();
    }

    /* renamed from: a */
    public static Object m19806a(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m19805a(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m19804a(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    /* renamed from: a */
    public static void m19803a(Object... objArr) {
        for (Object obj : objArr) {
            if (obj == null) {
                throw new NullPointerException();
            }
        }
    }
}
