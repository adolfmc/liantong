package com.sinovatech.unicom.separatemodule.audience.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import java.lang.ref.WeakReference;
import org.jetbrains.annotations.NotNull;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ComFortView extends RecyclerView {
    private static final long TIME_AUTO_POLL = 16;
    AutoPollTask autoPollTask;
    private boolean canRun;
    private boolean running;

    public ComFortView(@NonNull @NotNull Context context) {
        super(context);
    }

    public ComFortView(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.autoPollTask = new AutoPollTask(this);
    }

    public ComFortView(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class AutoPollTask implements Runnable {
        private final WeakReference<ComFortView> mReference;

        public AutoPollTask(ComFortView comFortView) {
            this.mReference = new WeakReference<>(comFortView);
        }

        @Override // java.lang.Runnable
        public void run() {
            ComFortView comFortView = this.mReference.get();
            if (comFortView != null && comFortView.running && comFortView.canRun) {
                comFortView.scrollBy(2, 2);
                comFortView.postDelayed(comFortView.autoPollTask, 16L);
            }
        }
    }

    public void start() {
        if (this.running) {
            stop();
        }
        this.canRun = true;
        this.running = true;
        postDelayed(this.autoPollTask, 16L);
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
