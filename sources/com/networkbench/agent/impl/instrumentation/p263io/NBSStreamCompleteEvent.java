package com.networkbench.agent.impl.instrumentation.p263io;

import java.util.EventObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.instrumentation.io.NBSStreamCompleteEvent */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class NBSStreamCompleteEvent extends EventObject {
    private static final long serialVersionUID = 1;
    private final long bytes;
    private final Exception exception;

    public NBSStreamCompleteEvent(Object obj, long j, Exception exc) {
        super(obj);
        this.bytes = j;
        this.exception = exc;
    }

    public NBSStreamCompleteEvent(Object obj, long j) {
        this(obj, j, null);
    }

    public long getBytes() {
        return this.bytes;
    }

    public Exception getException() {
        return this.exception;
    }

    public boolean isError() {
        return this.exception != null;
    }
}
