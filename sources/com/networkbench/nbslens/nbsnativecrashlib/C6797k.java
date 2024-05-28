package com.networkbench.nbslens.nbsnativecrashlib;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.nbslens.nbsnativecrashlib.k */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6797k {

    /* renamed from: b */
    private static int f17613b = 50;

    /* renamed from: a */
    private Thread f17614a;

    public C6797k(Thread thread) {
        if (thread == null) {
            throw new IllegalArgumentException("error param");
        }
        this.f17614a = thread;
    }

    /* renamed from: a */
    public StringBuilder m8415a() {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = this.f17614a.getStackTrace();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= stackTrace.length) {
                break;
            } else if (i2 >= f17613b) {
                sb.append("\t... ");
                sb.append(stackTrace.length - i);
                sb.append(" more");
                break;
            } else {
                i2++;
                sb.append("\t1##at " + stackTrace[i] + "\n");
                i++;
            }
        }
        return sb;
    }
}
