package com.networkbench.agent.impl.p243c;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.i */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6305i {

    /* renamed from: a */
    public static final int f15806a = 1024;

    /* renamed from: b */
    public static final int f15807b = 1024;

    /* renamed from: c */
    public static final C6305i f15808c = new C6305i();

    /* renamed from: d */
    private final String f15809d;

    /* renamed from: e */
    private final String f15810e;

    /* renamed from: f */
    private final long f15811f;

    /* renamed from: g */
    private final long f15812g;

    /* renamed from: h */
    private final long f15813h;

    /* renamed from: i */
    private final long f15814i;

    /* renamed from: j */
    private final int f15815j;

    /* renamed from: k */
    private final boolean f15816k;

    /* renamed from: l */
    private final int f15817l;

    /* renamed from: m */
    private final int f15818m;

    /* renamed from: n */
    private final int f15819n;

    /* renamed from: o */
    private final ArrayList<C6307b> f15820o;

    /* renamed from: p */
    private final ArrayList<C6306a> f15821p;

    private C6305i() {
        this.f15809d = null;
        this.f15810e = "";
        this.f15811f = 60L;
        this.f15812g = 480L;
        this.f15813h = 600L;
        this.f15814i = 1000L;
        this.f15815j = 50;
        this.f15818m = 1024;
        this.f15816k = true;
        this.f15817l = 10;
        this.f15819n = 0;
        this.f15820o = null;
        this.f15821p = null;
    }

    /* renamed from: a */
    public String m10514a() {
        return this.f15810e;
    }

    /* renamed from: b */
    public long m10513b() {
        return this.f15811f;
    }

    /* renamed from: c */
    public long m10512c() {
        return TimeUnit.MILLISECONDS.convert(this.f15811f, TimeUnit.SECONDS);
    }

    /* renamed from: d */
    public long m10511d() {
        return this.f15812g;
    }

    /* renamed from: e */
    public long m10510e() {
        return TimeUnit.MILLISECONDS.convert(this.f15812g, TimeUnit.SECONDS);
    }

    /* renamed from: f */
    public long m10509f() {
        return this.f15813h;
    }

    public C6305i(String str, String str2, long j, long j2, long j3, long j4, int i, int i2, boolean z, int i3, int i4, ArrayList<C6307b> arrayList, ArrayList<C6306a> arrayList2) {
        this.f15809d = str;
        this.f15810e = str2;
        this.f15811f = j;
        this.f15812g = j2;
        this.f15813h = j3;
        this.f15814i = j4;
        this.f15815j = i;
        this.f15818m = i2;
        this.f15816k = z;
        this.f15817l = i3;
        this.f15819n = i4;
        this.f15820o = arrayList;
        this.f15821p = arrayList2;
    }

    /* renamed from: g */
    public String m10508g() {
        return this.f15809d;
    }

    /* renamed from: h */
    public long m10507h() {
        return this.f15814i;
    }

    /* renamed from: i */
    public int m10506i() {
        return this.f15815j;
    }

    /* renamed from: j */
    public int m10505j() {
        return this.f15818m;
    }

    /* renamed from: k */
    public boolean m10504k() {
        return this.f15816k;
    }

    /* renamed from: l */
    public int m10503l() {
        return this.f15817l;
    }

    public String toString() {
        return this.f15809d;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.c.i$b */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6307b {

        /* renamed from: a */
        public int f15825a;

        /* renamed from: b */
        public String f15826b;

        public String toString() {
            return "matchMode:" + this.f15825a + ", rule:" + this.f15826b;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.c.i$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6306a {

        /* renamed from: a */
        public int f15822a;

        /* renamed from: b */
        public String f15823b;

        /* renamed from: c */
        public String f15824c;

        public String toString() {
            return "matchMode:" + this.f15822a + ", rule:" + this.f15823b + ", errorCode:" + this.f15824c;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.c.i$c */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6308c {

        /* renamed from: a */
        public String[] f15827a;

        /* renamed from: b */
        public String[] f15828b;

        /* renamed from: c */
        public String[] f15829c;

        /* renamed from: d */
        public String[] f15830d;

        public String toString() {
            StringBuilder sb = new StringBuilder();
            String[] strArr = this.f15827a;
            if (strArr != null && strArr.length > 0) {
                sb.append("getMethodParam:");
                for (String str : this.f15827a) {
                    sb.append(str);
                    sb.append(',');
                }
            }
            String[] strArr2 = this.f15828b;
            if (strArr2 != null && strArr2.length > 0) {
                sb.append("postMethodParam:");
                for (String str2 : this.f15828b) {
                    sb.append(str2);
                    sb.append(',');
                }
            }
            String[] strArr3 = this.f15829c;
            if (strArr3 != null && strArr3.length > 0) {
                sb.append("headers:");
                for (String str3 : this.f15829c) {
                    sb.append(str3);
                    sb.append(',');
                }
            }
            String[] strArr4 = this.f15830d;
            if (strArr4 != null && strArr4.length > 0) {
                sb.append("resHeaders:");
                for (String str4 : this.f15830d) {
                    sb.append(str4);
                    sb.append(',');
                }
            }
            return sb.toString();
        }
    }

    /* renamed from: m */
    public int m10502m() {
        return this.f15819n;
    }

    /* renamed from: n */
    public ArrayList<C6307b> m10501n() {
        return this.f15820o;
    }

    /* renamed from: o */
    public ArrayList<C6306a> m10500o() {
        return this.f15821p;
    }
}
