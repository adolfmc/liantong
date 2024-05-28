package p475rx.internal.util;

import java.util.concurrent.CountDownLatch;
import p475rx.Subscription;
import p475rx.annotations.Experimental;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@Experimental
/* renamed from: rx.internal.util.BlockingUtils */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class BlockingUtils {
    private BlockingUtils() {
    }

    @Experimental
    public static void awaitForComplete(CountDownLatch countDownLatch, Subscription subscription) {
        if (countDownLatch.getCount() == 0) {
            return;
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            subscription.unsubscribe();
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for subscription to complete.", e);
        }
    }
}
