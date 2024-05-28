package com.p319ss.android.socialbase.downloader.network;

import android.net.TrafficStats;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.thread.DownloadWatchDog;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.ss.android.socialbase.downloader.network.DeviceBandwidthSampler */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DeviceBandwidthSampler {
    private static final String TAG = "DeviceBandwidthSampler";
    private static volatile DeviceBandwidthSampler instance = null;
    public static volatile boolean isWifi = false;
    private static long sPreviousBytes = -1;
    private long mLastTimeReading;
    private final NetTrafficManager mNetTrafficManager = NetTrafficManager.getInstance();
    private final AtomicInteger mSamplingCounter = new AtomicInteger();
    private final SamplingHandler mHandler = new SamplingHandler(DownloadWatchDog.getThreadLooper());

    public static DeviceBandwidthSampler getInstance() {
        if (instance == null) {
            synchronized (DeviceBandwidthSampler.class) {
                if (instance == null) {
                    instance = new DeviceBandwidthSampler();
                }
            }
        }
        return instance;
    }

    private DeviceBandwidthSampler() {
    }

    public void startSampling() {
        try {
            String str = TAG;
            Logger.m6469i(str, "startSampling: mSamplingCounter = " + this.mSamplingCounter);
            if (this.mSamplingCounter.getAndIncrement() == 0) {
                this.mHandler.startSamplingThread();
                this.mLastTimeReading = SystemClock.uptimeMillis();
            }
        } catch (Throwable unused) {
        }
    }

    public void stopSampling() {
        try {
            String str = TAG;
            Logger.m6469i(str, "stopSampling: mSamplingCounter = " + this.mSamplingCounter);
            if (this.mSamplingCounter.decrementAndGet() == 0) {
                this.mHandler.stopSamplingThread();
                addFinalSample();
            }
        } catch (Throwable unused) {
        }
    }

    public static long getAllRxBytesWifi() {
        return TrafficStats.getTotalRxBytes() - TrafficStats.getMobileRxBytes();
    }

    public static void updateWifiStatus() {
        isWifi = DownloadUtils.isWifi(DownloadComponentManager.getAppContext());
    }

    protected void addSample() {
        long mobileRxBytes;
        try {
            updateWifiStatus();
            if (isWifi) {
                mobileRxBytes = getAllRxBytesWifi();
            } else {
                mobileRxBytes = TrafficStats.getMobileRxBytes();
            }
            long j = mobileRxBytes - sPreviousBytes;
            if (sPreviousBytes >= 0) {
                synchronized (this) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    this.mNetTrafficManager.addBandwidth(j, uptimeMillis - this.mLastTimeReading);
                    this.mLastTimeReading = uptimeMillis;
                }
            }
            sPreviousBytes = mobileRxBytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void addFinalSample() {
        addSample();
        sPreviousBytes = -1L;
    }

    public boolean isSampling() {
        return this.mSamplingCounter.get() != 0;
    }

    /* renamed from: com.ss.android.socialbase.downloader.network.DeviceBandwidthSampler$SamplingHandler */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class SamplingHandler extends Handler {
        private static final int MSG_START = 1;
        static final long SAMPLE_TIME = 1000;

        public SamplingHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            DeviceBandwidthSampler.this.addSample();
            sendEmptyMessageDelayed(1, 1000L);
        }

        public void startSamplingThread() {
            sendEmptyMessage(1);
        }

        public void stopSamplingThread() {
            removeMessages(1);
        }
    }
}
