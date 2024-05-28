package com.sinovatech.unicom.separatemodule.user.view;

import android.content.Context;
import android.support.p086v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import java.lang.ref.WeakReference;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AutoPollRecyclerView extends RecyclerView {
    private static final long TIME_AUTO_POLL = 10;
    AutoPollTask autoPollTask;
    private boolean canRun;
    private boolean running;

    public AutoPollRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.autoPollTask = new AutoPollTask(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class AutoPollTask implements Runnable {
        private final WeakReference<AutoPollRecyclerView> mReference;

        public AutoPollTask(AutoPollRecyclerView autoPollRecyclerView) {
            this.mReference = new WeakReference<>(autoPollRecyclerView);
        }

        @Override // java.lang.Runnable
        public void run() {
            AutoPollRecyclerView autoPollRecyclerView = this.mReference.get();
            if (autoPollRecyclerView != null && autoPollRecyclerView.running && autoPollRecyclerView.canRun) {
                autoPollRecyclerView.scrollBy(2, 2);
                autoPollRecyclerView.postDelayed(autoPollRecyclerView.autoPollTask, AutoPollRecyclerView.TIME_AUTO_POLL);
            }
        }
    }

    public void start() {
        if (this.running) {
            stop();
        }
        this.canRun = true;
        this.running = true;
        postDelayed(this.autoPollTask, TIME_AUTO_POLL);
    }

    public void stop() {
        this.running = false;
        removeCallbacks(this.autoPollTask);
    }

    @Override // android.support.p086v7.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (this.running) {
                    stop();
                    break;
                }
                break;
            case 1:
            case 3:
            case 4:
                if (this.canRun) {
                    start();
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }
}
