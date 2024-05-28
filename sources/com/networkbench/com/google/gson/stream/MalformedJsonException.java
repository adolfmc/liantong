package com.networkbench.com.google.gson.stream;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class MalformedJsonException extends IOException {
    private static final long serialVersionUID = 1;

    public MalformedJsonException(String str) {
        super(str);
    }

    public MalformedJsonException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public MalformedJsonException(Throwable th) {
        initCause(th);
    }
}
