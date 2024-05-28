package com.networkbench.agent.impl.plugin.p275f.p276a.p277a;

import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.f.a.a.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6576c {

    /* renamed from: a */
    public static final int f16848a = 1;

    /* renamed from: b */
    public static final int f16849b = 5;

    /* renamed from: c */
    public final String f16850c;

    /* renamed from: d */
    public final int f16851d;

    /* renamed from: e */
    public final int f16852e;

    /* renamed from: f */
    public final long f16853f;

    public C6576c(String str, int i, int i2, long j) {
        this.f16850c = str;
        this.f16851d = i;
        this.f16852e = i2;
        this.f16853f = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C6576c)) {
            return false;
        }
        C6576c c6576c = (C6576c) obj;
        return this.f16850c.equals(c6576c.f16850c) && this.f16851d == c6576c.f16851d && this.f16852e == c6576c.f16852e && this.f16853f == c6576c.f16853f;
    }

    /* renamed from: a */
    public boolean m9331a() {
        return this.f16851d == 1;
    }

    public String toString() {
        String str;
        int i = this.f16851d;
        if (i == 1) {
            str = "A";
        } else if (i == 5) {
            str = "CNAME";
        } else {
            str = "type-" + this.f16851d;
        }
        return String.format(Locale.ENGLISH, "%s\t%s\t%d", str, this.f16850c, Integer.valueOf(this.f16852e));
    }
}
