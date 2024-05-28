package com.networkbench.agent.impl.p254f;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.f.l */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6400l {

    /* renamed from: a */
    private static final int f16164a = 4000;

    /* renamed from: b */
    private static final int f16165b = 5;

    /* renamed from: c */
    private static final char f16166c = 9484;

    /* renamed from: d */
    private static final char f16167d = 9492;

    /* renamed from: e */
    private static final char f16168e = 9500;

    /* renamed from: f */
    private static final char f16169f = 9474;

    /* renamed from: g */
    private static final String f16170g = "────────────────────────────────────────────────────────";

    /* renamed from: h */
    private static final String f16171h = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";

    /* renamed from: i */
    private static final String f16172i = "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────";

    /* renamed from: j */
    private static final String f16173j = "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────";

    /* renamed from: k */
    private static final String f16174k = "├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";

    /* renamed from: l */
    private final int f16175l;

    /* renamed from: m */
    private final int f16176m;

    /* renamed from: n */
    private final boolean f16177n;
    @NonNull

    /* renamed from: o */
    private final C6390b f16178o;
    @Nullable

    /* renamed from: p */
    private final String f16179p;

    private C6400l(@NonNull C6402a c6402a) {
        C6391c.m10168b(c6402a);
        this.f16175l = c6402a.f16180a;
        this.f16176m = c6402a.f16181b;
        this.f16177n = c6402a.f16182c;
        this.f16178o = c6402a.f16183d;
        this.f16179p = c6402a.f16184e;
    }

    @NonNull
    /* renamed from: a */
    public static C6402a m10090a() {
        return new C6402a();
    }

    /* renamed from: a */
    public void m10087a(int i, @Nullable String str, @NonNull String str2) {
        C6391c.m10168b(str2);
        String m10082b = m10082b(str);
        m10089a(i, m10082b);
        m10088a(i, m10082b, this.f16175l);
        byte[] bytes = str2.getBytes();
        int length = bytes.length;
        if (length <= 4000) {
            if (this.f16175l > 0) {
                m10081c(i, m10082b);
            }
            m10083b(i, m10082b, str2);
            m10084b(i, m10082b);
            return;
        }
        if (this.f16175l > 0) {
            m10081c(i, m10082b);
        }
        for (int i2 = 0; i2 < length; i2 += 4000) {
            m10083b(i, m10082b, new String(bytes, i2, Math.min(length - i2, 4000)));
        }
        m10084b(i, m10082b);
    }

    /* renamed from: a */
    private void m10089a(int i, @Nullable String str) {
        m10080c(i, str, "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
    }

    /* renamed from: a */
    private void m10088a(int i, @Nullable String str, int i2) {
        int i3;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (this.f16177n) {
            m10080c(i, str, "│ Thread: " + Thread.currentThread().getName());
            m10081c(i, str);
        }
        String str2 = "";
        int m10085a = m10085a(stackTrace) + this.f16176m;
        if (i2 + m10085a > stackTrace.length) {
            i2 = (stackTrace.length - m10085a) - 1;
        }
        while (i2 > 0) {
            if (i2 + m10085a < stackTrace.length) {
                str2 = str2 + "   ";
                m10080c(i, str, "│ " + str2 + m10086a(stackTrace[i3].getClassName()) + "." + stackTrace[i3].getMethodName() + "  (" + stackTrace[i3].getFileName() + ":" + stackTrace[i3].getLineNumber() + ")");
            }
            i2--;
        }
    }

    /* renamed from: b */
    private void m10084b(int i, @Nullable String str) {
        m10080c(i, str, "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
    }

    /* renamed from: c */
    private void m10081c(int i, @Nullable String str) {
        m10080c(i, str, "├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄");
    }

    /* renamed from: b */
    private void m10083b(int i, @Nullable String str, @NonNull String str2) {
        String[] split;
        C6391c.m10168b(str2);
        for (String str3 : str2.split(System.getProperty("line.separator"))) {
            m10080c(i, str, "│ " + str3);
        }
    }

    /* renamed from: c */
    private void m10080c(int i, @Nullable String str, @NonNull String str2) {
        C6391c.m10168b(str2);
        this.f16178o.m10174a(i, str, str2);
    }

    /* renamed from: a */
    private String m10086a(@NonNull String str) {
        C6391c.m10168b(str);
        return str.substring(str.lastIndexOf(".") + 1);
    }

    /* renamed from: a */
    private int m10085a(@NonNull StackTraceElement[] stackTraceElementArr) {
        C6391c.m10168b(stackTraceElementArr);
        for (int i = 5; i < stackTraceElementArr.length; i++) {
            String className = stackTraceElementArr[i].getClassName();
            if (!className.equals(C6399k.class.getName()) && !className.equals(C6398j.class.getName())) {
                return i - 1;
            }
        }
        return -1;
    }

    @Nullable
    /* renamed from: b */
    private String m10082b(@Nullable String str) {
        if (!C6391c.m10172a((CharSequence) str) && !C6391c.m10171a(this.f16179p, str)) {
            return this.f16179p + "-" + str;
        }
        return this.f16179p;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.f.l$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6402a {

        /* renamed from: a */
        int f16180a;

        /* renamed from: b */
        int f16181b;

        /* renamed from: c */
        boolean f16182c;
        @Nullable

        /* renamed from: d */
        C6390b f16183d;
        @Nullable

        /* renamed from: e */
        String f16184e;

        private C6402a() {
            this.f16180a = 2;
            this.f16181b = 0;
            this.f16182c = false;
            this.f16184e = "NBSAgent";
        }

        @NonNull
        /* renamed from: a */
        public C6402a m10078a(int i) {
            this.f16180a = i;
            return this;
        }

        @NonNull
        /* renamed from: b */
        public C6402a m10074b(int i) {
            this.f16181b = i;
            return this;
        }

        @NonNull
        /* renamed from: a */
        public C6402a m10075a(boolean z) {
            this.f16182c = z;
            return this;
        }

        @NonNull
        /* renamed from: a */
        public C6402a m10077a(@Nullable C6390b c6390b) {
            this.f16183d = c6390b;
            return this;
        }

        @NonNull
        /* renamed from: a */
        public C6402a m10076a(@Nullable String str) {
            this.f16184e = str;
            return this;
        }

        @NonNull
        /* renamed from: a */
        public C6400l m10079a() {
            if (this.f16183d == null) {
                this.f16183d = new C6390b();
            }
            return new C6400l(this);
        }
    }
}
