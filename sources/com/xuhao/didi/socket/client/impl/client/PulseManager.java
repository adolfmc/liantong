package com.xuhao.didi.socket.client.impl.client;

import com.xuhao.didi.core.iocore.interfaces.IPulseSendable;
import com.xuhao.didi.socket.client.impl.exceptions.DogDeadException;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.bean.IPulse;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class PulseManager implements IPulse {
    private volatile long mCurrentFrequency;
    private volatile OkSocketOptions.IOThreadMode mCurrentThreadMode;
    private volatile IConnectionManager mManager;
    private volatile OkSocketOptions mOkOptions;
    private IPulseSendable mSendable;
    private volatile boolean isDead = false;
    private volatile AtomicInteger mLoseTimes = new AtomicInteger(-1);
    private PulseThread mPulseThread = new PulseThread();

    /* JADX INFO: Access modifiers changed from: package-private */
    public PulseManager(IConnectionManager iConnectionManager, OkSocketOptions okSocketOptions) {
        this.mManager = iConnectionManager;
        this.mOkOptions = okSocketOptions;
        this.mCurrentThreadMode = this.mOkOptions.getIOThreadMode();
    }

    public synchronized IPulse setPulseSendable(IPulseSendable iPulseSendable) {
        if (iPulseSendable != null) {
            this.mSendable = iPulseSendable;
        }
        return this;
    }

    public IPulseSendable getPulseSendable() {
        return this.mSendable;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.bean.IPulse
    public synchronized void pulse() {
        privateDead();
        updateFrequency();
        if (this.mCurrentThreadMode != OkSocketOptions.IOThreadMode.SIMPLEX && this.mPulseThread.isShutdown()) {
            this.mPulseThread.start();
        }
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.bean.IPulse
    public synchronized void trigger() {
        if (this.isDead) {
            return;
        }
        if (this.mCurrentThreadMode != OkSocketOptions.IOThreadMode.SIMPLEX && this.mManager != null && this.mSendable != null) {
            this.mManager.send(this.mSendable);
        }
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.bean.IPulse
    public synchronized void dead() {
        this.mLoseTimes.set(0);
        this.isDead = true;
        privateDead();
    }

    private synchronized void updateFrequency() {
        if (this.mCurrentThreadMode != OkSocketOptions.IOThreadMode.SIMPLEX) {
            this.mCurrentFrequency = this.mOkOptions.getPulseFrequency();
            long j = 1000;
            if (this.mCurrentFrequency >= 1000) {
                j = this.mCurrentFrequency;
            }
            this.mCurrentFrequency = j;
        } else {
            privateDead();
        }
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.bean.IPulse
    public synchronized void feed() {
        this.mLoseTimes.set(-1);
    }

    private void privateDead() {
        PulseThread pulseThread = this.mPulseThread;
        if (pulseThread != null) {
            pulseThread.shutdown();
        }
    }

    public int getLoseTimes() {
        return this.mLoseTimes.get();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void setOkOptions(OkSocketOptions okSocketOptions) {
        this.mOkOptions = okSocketOptions;
        this.mCurrentThreadMode = this.mOkOptions.getIOThreadMode();
        updateFrequency();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class PulseThread extends AbsLoopThread {
        @Override // com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread
        public void loopFinish(Exception exc) {
        }

        private PulseThread() {
        }

        @Override // com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread
        public void runInLoopThread() throws Exception {
            if (!PulseManager.this.isDead) {
                if (PulseManager.this.mManager != null && PulseManager.this.mSendable != null) {
                    if (PulseManager.this.mOkOptions.getPulseFeedLoseTimes() == -1 || PulseManager.this.mLoseTimes.incrementAndGet() < PulseManager.this.mOkOptions.getPulseFeedLoseTimes()) {
                        PulseManager.this.mManager.send(PulseManager.this.mSendable);
                    } else {
                        PulseManager.this.mManager.disconnect(new DogDeadException("you need feed dog on time,otherwise he will die"));
                    }
                }
                Thread.sleep(PulseManager.this.mCurrentFrequency);
                return;
            }
            shutdown();
        }
    }
}
