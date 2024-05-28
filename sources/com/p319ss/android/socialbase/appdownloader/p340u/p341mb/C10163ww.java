package com.p319ss.android.socialbase.appdownloader.p340u.p341mb;

import java.io.PrintStream;

/* renamed from: com.ss.android.socialbase.appdownloader.u.mb.ww */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C10163ww extends Exception {

    /* renamed from: b */
    protected int f19654b;

    /* renamed from: mb */
    protected Throwable f19655mb;

    /* renamed from: ox */
    protected int f19656ox;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public C10163ww(java.lang.String r3, com.p319ss.android.socialbase.appdownloader.p340u.p341mb.InterfaceC10157ko r4, java.lang.Throwable r5) {
        /*
            r2 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            if (r3 != 0) goto La
            java.lang.String r3 = ""
            goto L1b
        La:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            java.lang.String r3 = " "
            r1.append(r3)
            java.lang.String r3 = r1.toString()
        L1b:
            r0.append(r3)
            if (r4 != 0) goto L23
            java.lang.String r3 = ""
            goto L3d
        L23:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r1 = "(position:"
            r3.append(r1)
            java.lang.String r1 = r4.mo6531hj()
            r3.append(r1)
            java.lang.String r1 = ") "
            r3.append(r1)
            java.lang.String r3 = r3.toString()
        L3d:
            r0.append(r3)
            if (r5 != 0) goto L45
            java.lang.String r3 = ""
            goto L56
        L45:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r1 = "caused by: "
            r3.append(r1)
            r3.append(r5)
            java.lang.String r3 = r3.toString()
        L56:
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r2.<init>(r3)
            r3 = -1
            r2.f19656ox = r3
            r2.f19654b = r3
            if (r4 == 0) goto L73
            int r3 = r4.mo6535b()
            r2.f19656ox = r3
            int r3 = r4.mo6523u()
            r2.f19654b = r3
        L73:
            r2.f19655mb = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.appdownloader.p340u.p341mb.C10163ww.<init>(java.lang.String, com.ss.android.socialbase.appdownloader.u.mb.ko, java.lang.Throwable):void");
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        if (this.f19655mb == null) {
            super.printStackTrace();
            return;
        }
        synchronized (System.err) {
            PrintStream printStream = System.err;
            printStream.println(super.getMessage() + "; nested exception is:");
            this.f19655mb.printStackTrace();
        }
    }
}
