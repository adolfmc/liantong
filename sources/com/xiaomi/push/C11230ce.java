package com.xiaomi.push;

import java.net.InetSocketAddress;

/* renamed from: com.xiaomi.push.ce */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11230ce {

    /* renamed from: a */
    private int f21693a;

    /* renamed from: a */
    private String f21694a;

    /* renamed from: a */
    public int m4578a() {
        return this.f21693a;
    }

    /* renamed from: a */
    public String m4577a() {
        return this.f21694a;
    }

    public C11230ce(String str, int i) {
        this.f21694a = str;
        this.f21693a = i;
    }

    /* renamed from: a */
    public static C11230ce m4576a(String str, int i) {
        String str2;
        int lastIndexOf = str.lastIndexOf(":");
        if (lastIndexOf != -1) {
            str2 = str.substring(0, lastIndexOf);
            try {
                int parseInt = Integer.parseInt(str.substring(lastIndexOf + 1));
                if (parseInt > 0) {
                    i = parseInt;
                }
            } catch (NumberFormatException unused) {
            }
        } else {
            str2 = str;
        }
        return new C11230ce(str2, i);
    }

    /* renamed from: a */
    public static InetSocketAddress m4575a(String str, int i) {
        C11230ce m4576a = m4576a(str, i);
        return new InetSocketAddress(m4576a.m4577a(), m4576a.m4578a());
    }

    public String toString() {
        if (this.f21693a > 0) {
            return this.f21694a + ":" + this.f21693a;
        }
        return this.f21694a;
    }
}
