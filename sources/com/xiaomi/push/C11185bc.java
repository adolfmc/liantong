package com.xiaomi.push;

import java.util.concurrent.Callable;

/* renamed from: com.xiaomi.push.bc */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11185bc<T> {

    /* renamed from: a */
    private final long f21580a;

    /* renamed from: b */
    private long f21581b;

    /* renamed from: c */
    private long f21582c;

    /* renamed from: d */
    private long f21583d;

    /* renamed from: e */
    private long f21584e;

    /* renamed from: f */
    private long f21585f;

    /* renamed from: g */
    private long f21586g;

    /* renamed from: h */
    private long f21587h;

    /* renamed from: i */
    private final long f21588i;

    public C11185bc(long j, long j2) {
        this.f21588i = j * 1000000;
        this.f21580a = j2;
    }

    /* renamed from: a */
    public long m4746a() {
        return this.f21582c;
    }

    /* renamed from: b */
    public long m4744b() {
        return this.f21583d;
    }

    /* renamed from: c */
    public long m4743c() {
        long j = this.f21585f;
        if (j > 0) {
            long j2 = this.f21584e;
            if (j2 > 0) {
                return j / j2;
            }
        }
        return 0L;
    }

    /* renamed from: d */
    public long m4742d() {
        long j = this.f21587h;
        long j2 = this.f21586g;
        if (j > j2) {
            return j - j2;
        }
        return 0L;
    }

    /* renamed from: a */
    public T m4745a(Callable<T> callable) {
        long j = this.f21581b;
        long j2 = this.f21588i;
        if (j > j2) {
            long j3 = (j / j2) * this.f21580a;
            this.f21581b = 0L;
            if (j3 > 0) {
                try {
                    Thread.sleep(j3);
                } catch (Exception unused) {
                }
            }
        }
        long nanoTime = System.nanoTime();
        if (this.f21586g <= 0) {
            this.f21586g = nanoTime;
        }
        T t = null;
        try {
            t = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long nanoTime2 = System.nanoTime() - nanoTime;
        this.f21587h = System.nanoTime();
        this.f21584e++;
        if (this.f21582c < nanoTime2) {
            this.f21582c = nanoTime2;
        }
        if (nanoTime2 > 0) {
            this.f21585f += nanoTime2;
            long j4 = this.f21583d;
            if (j4 == 0 || j4 > nanoTime2) {
                this.f21583d = nanoTime2;
            }
        }
        this.f21581b += Math.max(nanoTime2, 0L);
        return t;
    }
}
