package cn.finalteam.galleryfinal.widget.crop;

import android.os.Bundle;
import cn.finalteam.galleryfinal.PhotoBaseActivity;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class MonitoredActivity extends PhotoBaseActivity {
    private final ArrayList<LifeCycleListener> listeners = new ArrayList<>();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class LifeCycleAdapter implements LifeCycleListener {
        @Override // cn.finalteam.galleryfinal.widget.crop.MonitoredActivity.LifeCycleListener
        public void onActivityCreated(MonitoredActivity monitoredActivity) {
        }

        @Override // cn.finalteam.galleryfinal.widget.crop.MonitoredActivity.LifeCycleListener
        public void onActivityDestroyed(MonitoredActivity monitoredActivity) {
        }

        @Override // cn.finalteam.galleryfinal.widget.crop.MonitoredActivity.LifeCycleListener
        public void onActivityStarted(MonitoredActivity monitoredActivity) {
        }

        @Override // cn.finalteam.galleryfinal.widget.crop.MonitoredActivity.LifeCycleListener
        public void onActivityStopped(MonitoredActivity monitoredActivity) {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface LifeCycleListener {
        void onActivityCreated(MonitoredActivity monitoredActivity);

        void onActivityDestroyed(MonitoredActivity monitoredActivity);

        void onActivityStarted(MonitoredActivity monitoredActivity);

        void onActivityStopped(MonitoredActivity monitoredActivity);
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

    @Override // cn.finalteam.galleryfinal.PhotoBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Iterator<LifeCycleListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onActivityCreated(this);
        }
    }

    @Override // cn.finalteam.galleryfinal.PhotoBaseActivity, android.app.Activity
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
