package com.liulishuo.okdownload.core.exception;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class InterruptException extends IOException {
    public static final InterruptException SIGNAL = new InterruptException() { // from class: com.liulishuo.okdownload.core.exception.InterruptException.1
        @Override // java.lang.Throwable
        public void printStackTrace() {
            throw new IllegalAccessError("Stack is ignored for signal");
        }
    };

    private InterruptException() {
        super("Interrupted");
    }
}
