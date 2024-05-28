package org.aspectj.lang;

import java.io.PrintStream;
import java.io.PrintWriter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SoftException extends RuntimeException {
    private static final boolean HAVE_JAVA_14;
    Throwable inner;

    static {
        boolean z;
        try {
            Class.forName("java.nio.Buffer");
            z = true;
        } catch (Throwable unused) {
            z = false;
        }
        HAVE_JAVA_14 = z;
    }

    public SoftException(Throwable th) {
        this.inner = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.inner;
    }

    public Throwable getWrappedThrowable() {
        return this.inner;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        Throwable th = this.inner;
        if (HAVE_JAVA_14 || th == null) {
            return;
        }
        printStream.print("Caused by: ");
        th.printStackTrace(printStream);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        Throwable th = this.inner;
        if (HAVE_JAVA_14 || th == null) {
            return;
        }
        printWriter.print("Caused by: ");
        th.printStackTrace(printWriter);
    }
}
