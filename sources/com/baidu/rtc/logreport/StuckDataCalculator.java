package com.baidu.rtc.logreport;

import android.os.Handler;
import android.os.Looper;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class StuckDataCalculator {
    private int stuckInterval;
    private Handler stuckTimer;
    private long frameStartTime = 0;
    private SLIReportInterface mStuckEvent = null;
    private Runnable stuckRunnable = new Runnable() { // from class: com.baidu.rtc.logreport.StuckDataCalculator.1
        @Override // java.lang.Runnable
        public void run() {
            StuckDataCalculator.this.reportStuckData();
            StuckDataCalculator.this.stuckTimer.postDelayed(StuckDataCalculator.this.stuckRunnable, 5000L);
        }
    };

    public StuckDataCalculator(int i) {
        this.stuckTimer = null;
        this.stuckInterval = 600;
        this.stuckTimer = new Handler(Looper.getMainLooper());
        this.stuckInterval = i;
    }

    public void setStuckEventListener(SLIReportInterface sLIReportInterface) {
        this.mStuckEvent = sLIReportInterface;
    }

    public void calculateStuck() {
        if (this.frameStartTime > 0) {
            this.stuckTimer.removeCallbacks(this.stuckRunnable);
            reportStuckData();
            this.stuckTimer.postDelayed(this.stuckRunnable, 5000L);
            return;
        }
        this.stuckTimer.postDelayed(this.stuckRunnable, 5000L);
        this.frameStartTime = System.currentTimeMillis();
    }

    public void reset() {
        this.frameStartTime = 0L;
        this.stuckTimer.removeCallbacks(this.stuckRunnable);
        this.mStuckEvent = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportStuckData() {
        SLIReportInterface sLIReportInterface;
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.frameStartTime;
        if (currentTimeMillis - j > this.stuckInterval && (sLIReportInterface = this.mStuckEvent) != null) {
            sLIReportInterface.onStuckData(j, currentTimeMillis);
        }
        this.frameStartTime = System.currentTimeMillis();
    }
}
