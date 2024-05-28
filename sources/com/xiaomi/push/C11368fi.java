package com.xiaomi.push;

import java.io.PrintStream;
import java.io.PrintWriter;

/* renamed from: com.xiaomi.push.fi */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11368fi extends Exception {

    /* renamed from: a */
    private C11380fr f22290a;

    /* renamed from: a */
    private C11381fs f22291a;

    /* renamed from: a */
    private Throwable f22292a;

    public C11368fi() {
        this.f22290a = null;
        this.f22291a = null;
        this.f22292a = null;
    }

    public C11368fi(String str) {
        super(str);
        this.f22290a = null;
        this.f22291a = null;
        this.f22292a = null;
    }

    public C11368fi(Throwable th) {
        this.f22290a = null;
        this.f22291a = null;
        this.f22292a = null;
        this.f22292a = th;
    }

    public C11368fi(C11380fr c11380fr) {
        this.f22290a = null;
        this.f22291a = null;
        this.f22292a = null;
        this.f22290a = c11380fr;
    }

    public C11368fi(String str, Throwable th) {
        super(str);
        this.f22290a = null;
        this.f22291a = null;
        this.f22292a = null;
        this.f22292a = th;
    }

    /* renamed from: a */
    public Throwable m3835a() {
        return this.f22292a;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this.f22292a != null) {
            printStream.println("Nested Exception: ");
            this.f22292a.printStackTrace(printStream);
        }
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (this.f22292a != null) {
            printWriter.println("Nested Exception: ");
            this.f22292a.printStackTrace(printWriter);
        }
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        C11380fr c11380fr;
        C11381fs c11381fs;
        String message = super.getMessage();
        if (message != null || (c11381fs = this.f22291a) == null) {
            return (message != null || (c11380fr = this.f22290a) == null) ? message : c11380fr.toString();
        }
        return c11381fs.toString();
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String message = super.getMessage();
        if (message != null) {
            sb.append(message);
            sb.append(": ");
        }
        C11381fs c11381fs = this.f22291a;
        if (c11381fs != null) {
            sb.append(c11381fs);
        }
        C11380fr c11380fr = this.f22290a;
        if (c11380fr != null) {
            sb.append(c11380fr);
        }
        if (this.f22292a != null) {
            sb.append("\n  -- caused by: ");
            sb.append(this.f22292a);
        }
        return sb.toString();
    }
}
