package p475rx;

import p475rx.annotations.Experimental;
import p475rx.exceptions.MissingBackpressureException;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@Experimental
/* renamed from: rx.BackpressureOverflow */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class BackpressureOverflow {
    public static final Strategy ON_OVERFLOW_DEFAULT = Error.INSTANCE;
    public static final Strategy ON_OVERFLOW_ERROR = Error.INSTANCE;
    public static final Strategy ON_OVERFLOW_DROP_OLDEST = DropOldest.INSTANCE;
    public static final Strategy ON_OVERFLOW_DROP_LATEST = DropLatest.INSTANCE;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.BackpressureOverflow$Strategy */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface Strategy {
        boolean mayAttemptDrop() throws MissingBackpressureException;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.BackpressureOverflow$DropOldest */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class DropOldest implements Strategy {
        static final DropOldest INSTANCE = new DropOldest();

        @Override // p475rx.BackpressureOverflow.Strategy
        public boolean mayAttemptDrop() {
            return true;
        }

        private DropOldest() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.BackpressureOverflow$DropLatest */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class DropLatest implements Strategy {
        static final DropLatest INSTANCE = new DropLatest();

        @Override // p475rx.BackpressureOverflow.Strategy
        public boolean mayAttemptDrop() {
            return false;
        }

        private DropLatest() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.BackpressureOverflow$Error */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class Error implements Strategy {
        static final Error INSTANCE = new Error();

        private Error() {
        }

        @Override // p475rx.BackpressureOverflow.Strategy
        public boolean mayAttemptDrop() throws MissingBackpressureException {
            throw new MissingBackpressureException("Overflowed buffer");
        }
    }
}
