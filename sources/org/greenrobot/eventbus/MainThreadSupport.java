package org.greenrobot.eventbus;

import android.os.Looper;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface MainThreadSupport {
    Poster createPoster(EventBus eventBus);

    boolean isMainThread();

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class AndroidHandlerMainThreadSupport implements MainThreadSupport {
        private final Looper looper;

        public AndroidHandlerMainThreadSupport(Looper looper) {
            this.looper = looper;
        }

        @Override // org.greenrobot.eventbus.MainThreadSupport
        public boolean isMainThread() {
            return this.looper == Looper.myLooper();
        }

        @Override // org.greenrobot.eventbus.MainThreadSupport
        public Poster createPoster(EventBus eventBus) {
            return new HandlerPoster(eventBus, this.looper, 10);
        }
    }
}
