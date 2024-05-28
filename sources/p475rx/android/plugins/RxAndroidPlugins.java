package p475rx.android.plugins;

import java.util.concurrent.atomic.AtomicReference;
import p475rx.annotations.Beta;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.android.plugins.RxAndroidPlugins */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class RxAndroidPlugins {
    private static final RxAndroidPlugins INSTANCE = new RxAndroidPlugins();
    private final AtomicReference<RxAndroidSchedulersHook> schedulersHook = new AtomicReference<>();

    public static RxAndroidPlugins getInstance() {
        return INSTANCE;
    }

    RxAndroidPlugins() {
    }

    @Beta
    public void reset() {
        this.schedulersHook.set(null);
    }

    public RxAndroidSchedulersHook getSchedulersHook() {
        if (this.schedulersHook.get() == null) {
            this.schedulersHook.compareAndSet(null, RxAndroidSchedulersHook.getDefaultInstance());
        }
        return this.schedulersHook.get();
    }

    public void registerSchedulersHook(RxAndroidSchedulersHook rxAndroidSchedulersHook) {
        if (this.schedulersHook.compareAndSet(null, rxAndroidSchedulersHook)) {
            return;
        }
        throw new IllegalStateException("Another strategy was already registered: " + this.schedulersHook.get());
    }
}
