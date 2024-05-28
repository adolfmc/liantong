package com.networkbench.agent.impl.plugin.p272c;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.c.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6543a {
    /* renamed from: a */
    public double m9467a(double[] dArr) {
        double d = 0.0d;
        for (double d2 : dArr) {
            d += d2;
        }
        return d;
    }

    /* renamed from: b */
    public double m9466b(double[] dArr) {
        return m9467a(dArr) / dArr.length;
    }

    /* renamed from: c */
    public double m9465c(double[] dArr) {
        double d = 0.0d;
        for (double d2 : dArr) {
            d += Math.pow(d2 - m9466b(dArr), 2.0d);
        }
        return d / dArr.length;
    }

    /* renamed from: d */
    public double m9464d(double[] dArr) {
        return Math.sqrt(m9465c(dArr));
    }

    /* renamed from: e */
    public double m9463e(double[] dArr) {
        double d = 0.0d;
        for (double d2 : dArr) {
            d += Math.pow(d2 - m9466b(dArr), 2.0d);
        }
        return d / (dArr.length - 1);
    }

    /* renamed from: f */
    public double m9462f(double[] dArr) {
        return Math.sqrt(m9463e(dArr));
    }
}
