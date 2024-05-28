package com.soundcloud.android.crop;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import com.bytedance.applog.tracker.Tracker;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class MonitoredActivity extends Activity {
    private final ArrayList<LifeCycleListener> listeners = new ArrayList<>();

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class LifeCycleAdapter implements LifeCycleListener {
        @Override // com.soundcloud.android.crop.MonitoredActivity.LifeCycleListener
        public void onActivityCreated(MonitoredActivity monitoredActivity) {
        }

        @Override // com.soundcloud.android.crop.MonitoredActivity.LifeCycleListener
        public void onActivityDestroyed(MonitoredActivity monitoredActivity) {
        }

        @Override // com.soundcloud.android.crop.MonitoredActivity.LifeCycleListener
        public void onActivityStarted(MonitoredActivity monitoredActivity) {
        }

        @Override // com.soundcloud.android.crop.MonitoredActivity.LifeCycleListener
        public void onActivityStopped(MonitoredActivity monitoredActivity) {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface LifeCycleListener {
        void onActivityCreated(MonitoredActivity monitoredActivity);

        void onActivityDestroyed(MonitoredActivity monitoredActivity);

        void onActivityStarted(MonitoredActivity monitoredActivity);

        void onActivityStopped(MonitoredActivity monitoredActivity);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    public void addLifeCycleListener(LifeCycleListener lifeCycleListener) {
        if (this.listeners.contains(lifeCycleListener)) {
            return;
        }
        this.listeners.add(lifeCycleListener);
    }

    public void removeLifeCycleListener(LifeCycleListener lifeCycleListener) {
        this.listeners.remove(lifeCycleListener);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Iterator<LifeCycleListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onActivityCreated(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Iterator<LifeCycleListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onActivityDestroyed(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        Iterator<LifeCycleListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onActivityStarted(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        Iterator<LifeCycleListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onActivityStopped(this);
        }
    }
}
