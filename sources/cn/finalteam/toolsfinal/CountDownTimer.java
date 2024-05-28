package cn.finalteam.toolsfinal;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class CountDownTimer {
    private static final int MSG = 1;
    private final long mCountdownInterval;
    private final long mMillisInFuture;
    private long mStopTimeInFuture;
    private boolean mCanceled = false;
    private Handler mHandler = new Handler() { // from class: cn.finalteam.toolsfinal.CountDownTimer.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            synchronized (CountDownTimer.this) {
                long elapsedRealtime = CountDownTimer.this.mStopTimeInFuture - SystemClock.elapsedRealtime();
                if (elapsedRealtime > 0 && !CountDownTimer.this.mCanceled) {
                    if (elapsedRealtime < CountDownTimer.this.mCountdownInterval) {
                        sendMessageDelayed(obtainMessage(1), elapsedRealtime);
                    } else {
                        long elapsedRealtime2 = SystemClock.elapsedRealtime();
                        CountDownTimer.this.onTick(elapsedRealtime);
                        long elapsedRealtime3 = (elapsedRealtime2 + CountDownTimer.this.mCountdownInterval) - SystemClock.elapsedRealtime();
                        while (elapsedRealtime3 < 0) {
                            elapsedRealtime3 += CountDownTimer.this.mCountdownInterval;
                        }
                        sendMessageDelayed(obtainMessage(1), elapsedRealtime3);
                    }
                }
                CountDownTimer.this.onFinish();
            }
        }
    };

    public abstract void onFinish();

    public abstract void onTick(long j);

    public CountDownTimer(long j, long j2) {
        this.mMillisInFuture = j;
        this.mCountdownInterval = j2;
    }

    public final void cancel() {
        this.mHandler.removeMessages(1);
        this.mCanceled = true;
    }

    public final synchronized CountDownTimer start() {
        if (this.mMillisInFuture <= 0) {
            onFinish();
            return this;
        }
        this.mStopTimeInFuture = SystemClock.elapsedRealtime() + this.mMillisInFuture;
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1));
        this.mCanceled = false;
        return this;
    }
}
