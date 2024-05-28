package io.reactivex.internal.util;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.concurrent.CountDownLatch;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class BlockingIgnoringReceiver extends CountDownLatch implements Action, Consumer<Throwable> {
    public Throwable error;

    public BlockingIgnoringReceiver() {
        super(1);
    }

    @Override // io.reactivex.functions.Consumer
    public void accept(Throwable th) {
        this.error = th;
        countDown();
    }

    @Override // io.reactivex.functions.Action
    public void run() {
        countDown();
    }
}
