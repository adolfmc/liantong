package com.mob.commons.p231cc;

import java.io.PrintStream;
import java.io.PrintWriter;

/* renamed from: com.mob.commons.cc.s */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C5814s extends RuntimeException {
    public C5814s(String str, Throwable th) {
        super(str, th);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        printStream.println("" + getMessage());
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        printWriter.println("" + getMessage());
    }
}
