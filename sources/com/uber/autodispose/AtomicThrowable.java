package com.uber.autodispose;

import java.util.concurrent.atomic.AtomicReference;
import org.jetbrains.annotations.Nullable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class AtomicThrowable extends AtomicReference<Throwable> {
    private static final long serialVersionUID = 3949248817947090603L;

    public boolean addThrowable(Throwable th) {
        return ExceptionHelper.addThrowable(this, th);
    }

    @Nullable
    public Throwable terminate() {
        return ExceptionHelper.terminate(this);
    }

    public boolean isTerminated() {
        return get() == ExceptionHelper.TERMINATED;
    }
}
