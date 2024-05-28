package com.uber.autodispose;

import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicLong;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class AutoDisposeBackpressureHelper {
    static long addCap(long j, long j2) {
        long j3 = j + j2;
        if (j3 < 0) {
            return Long.MAX_VALUE;
        }
        return j3;
    }

    private AutoDisposeBackpressureHelper() {
        throw new IllegalStateException("No instances!");
    }

    static long multiplyCap(long j, long j2) {
        long j3 = j * j2;
        if (((j | j2) >>> 31) == 0 || j3 / j == j2) {
            return j3;
        }
        return Long.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long add(AtomicLong atomicLong, long j) {
        long j2;
        do {
            j2 = atomicLong.get();
            if (j2 == Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
        } while (!atomicLong.compareAndSet(j2, addCap(j2, j)));
        return j2;
    }

    static long addCancel(AtomicLong atomicLong, long j) {
        long j2;
        do {
            j2 = atomicLong.get();
            if (j2 == Long.MIN_VALUE) {
                return Long.MIN_VALUE;
            }
            if (j2 == Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
        } while (!atomicLong.compareAndSet(j2, addCap(j2, j)));
        return j2;
    }

    static long produced(AtomicLong atomicLong, long j) {
        long j2;
        long j3;
        do {
            j2 = atomicLong.get();
            if (j2 == Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
            j3 = j2 - j;
            if (j3 < 0) {
                RxJavaPlugins.onError(new IllegalStateException("More produced than requested: " + j3));
                j3 = 0L;
            }
        } while (!atomicLong.compareAndSet(j2, j3));
        return j3;
    }

    static long producedCancel(AtomicLong atomicLong, long j) {
        long j2;
        long j3;
        do {
            j2 = atomicLong.get();
            if (j2 == Long.MIN_VALUE) {
                return Long.MIN_VALUE;
            }
            if (j2 == Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
            j3 = j2 - j;
            if (j3 < 0) {
                RxJavaPlugins.onError(new IllegalStateException("More produced than requested: " + j3));
                j3 = 0L;
            }
        } while (!atomicLong.compareAndSet(j2, j3));
        return j3;
    }
}
