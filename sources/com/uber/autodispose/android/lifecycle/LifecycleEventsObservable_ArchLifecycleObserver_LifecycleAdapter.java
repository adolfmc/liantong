package com.uber.autodispose.android.lifecycle;

import android.arch.lifecycle.GeneratedAdapter;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MethodCallsLogger;
import com.uber.autodispose.android.lifecycle.LifecycleEventsObservable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class LifecycleEventsObservable_ArchLifecycleObserver_LifecycleAdapter implements GeneratedAdapter {
    final LifecycleEventsObservable.ArchLifecycleObserver mReceiver;

    LifecycleEventsObservable_ArchLifecycleObserver_LifecycleAdapter(LifecycleEventsObservable.ArchLifecycleObserver archLifecycleObserver) {
        this.mReceiver = archLifecycleObserver;
    }

    @Override // android.arch.lifecycle.GeneratedAdapter
    public void callMethods(LifecycleOwner lifecycleOwner, Lifecycle.Event event, boolean z, MethodCallsLogger methodCallsLogger) {
        boolean z2 = methodCallsLogger != null;
        if (z) {
            if (!z2 || methodCallsLogger.approveCall("onStateChange", 4)) {
                this.mReceiver.onStateChange(lifecycleOwner, event);
            }
        }
    }
}
