package com.p319ss.android.socialbase.downloader.network;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.ss.android.socialbase.downloader.network.NetTrafficManager */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class NetTrafficManager {
    private static final long BANDWIDTH_LOWER_BOUND = 3;
    private static final int BYTES_TO_BITS = 8;
    private static final double DEFAULT_DECAY_CONSTANT = 0.05d;
    private static final int DEFAULT_GOOD_BANDWIDTH = 2000;
    private static final long DEFAULT_HYSTERESIS_PERCENT = 20;
    private static final int DEFAULT_MODERATE_BANDWIDTH = 550;
    private static final int DEFAULT_POOR_BANDWIDTH = 150;
    private static final double DEFAULT_SAMPLES_TO_QUALITY_CHANGE = 5.0d;
    private static final double HYSTERESIS_BOTTOM_MULTIPLIER = 0.8d;
    private static final double HYSTERESIS_TOP_MULTIPLIER = 1.25d;
    private static final String TAG = "NetTrafficManager";
    private final AtomicReference<NetworkQuality> currentNetworkQuality;
    private volatile boolean initiateStateChange;
    private final ArrayList<NetworkStateChangeListener> listenerList;
    private final ExponentialGeometricAverage mDownloadBandwidth;
    private AtomicReference<NetworkQuality> nextNetworkQuality;
    private int sampleCount;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.network.NetTrafficManager$NetworkStateChangeListener */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface NetworkStateChangeListener {
        void onBandwidthStateChange(NetworkQuality networkQuality);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.network.NetTrafficManager$ConnectionClassManagerHolder */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class ConnectionClassManagerHolder {
        public static final NetTrafficManager instance = new NetTrafficManager();

        private ConnectionClassManagerHolder() {
        }
    }

    public static NetTrafficManager getInstance() {
        return ConnectionClassManagerHolder.instance;
    }

    private NetTrafficManager() {
        this.mDownloadBandwidth = new ExponentialGeometricAverage(DEFAULT_DECAY_CONSTANT);
        this.initiateStateChange = false;
        this.currentNetworkQuality = new AtomicReference<>(NetworkQuality.UNKNOWN);
        this.listenerList = new ArrayList<>();
    }

    public synchronized void addBandwidth(long j, long j2) {
        NetworkQuality currentNetworkQuality;
        double d = ((j * 1.0d) / j2) * 8.0d;
        if (j2 == 0 || d < 3.0d) {
            return;
        }
        try {
            this.mDownloadBandwidth.addMeasurement(d);
            currentNetworkQuality = getCurrentNetworkQuality();
        } catch (Throwable unused) {
        }
        if (this.initiateStateChange) {
            this.sampleCount++;
            if (currentNetworkQuality != this.nextNetworkQuality.get()) {
                this.initiateStateChange = false;
                this.sampleCount = 1;
            }
            if (this.sampleCount >= DEFAULT_SAMPLES_TO_QUALITY_CHANGE && significantlyOutsideCurrentBand()) {
                this.initiateStateChange = false;
                this.sampleCount = 1;
                this.currentNetworkQuality.set(this.nextNetworkQuality.get());
                notifyListeners();
            }
            return;
        }
        if (this.currentNetworkQuality.get() != currentNetworkQuality) {
            this.initiateStateChange = true;
            this.nextNetworkQuality = new AtomicReference<>(currentNetworkQuality);
        }
    }

    private boolean significantlyOutsideCurrentBand() {
        if (this.mDownloadBandwidth == null) {
            return false;
        }
        try {
            double d = 2000.0d;
            double d2 = 550.0d;
            switch (this.currentNetworkQuality.get()) {
                case POOR:
                    d = 0.0d;
                    d2 = 150.0d;
                    break;
                case MODERATE:
                    d = 150.0d;
                    break;
                case GOOD:
                    d = 550.0d;
                    d2 = 2000.0d;
                    break;
                case EXCELLENT:
                    d2 = 3.4028234663852886E38d;
                    break;
                default:
                    return true;
            }
            double average = this.mDownloadBandwidth.getAverage();
            if (average > d2) {
                if (average > d2 * HYSTERESIS_TOP_MULTIPLIER) {
                    return true;
                }
            } else if (average < d * HYSTERESIS_BOTTOM_MULTIPLIER) {
                return true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }

    public void reset() {
        try {
            if (this.mDownloadBandwidth != null) {
                this.mDownloadBandwidth.reset();
            }
            this.currentNetworkQuality.set(NetworkQuality.UNKNOWN);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public synchronized NetworkQuality getCurrentNetworkQuality() {
        if (this.mDownloadBandwidth == null) {
            return NetworkQuality.UNKNOWN;
        }
        return mapBandwidthQuality(this.mDownloadBandwidth.getAverage());
    }

    private NetworkQuality mapBandwidthQuality(double d) {
        if (d < 0.0d) {
            return NetworkQuality.UNKNOWN;
        }
        if (d < 150.0d) {
            return NetworkQuality.POOR;
        }
        if (d < 550.0d) {
            return NetworkQuality.MODERATE;
        }
        if (d < 2000.0d) {
            return NetworkQuality.GOOD;
        }
        return NetworkQuality.EXCELLENT;
    }

    public synchronized double getDownloadKBitsPerSecond() {
        return this.mDownloadBandwidth == null ? -1.0d : this.mDownloadBandwidth.getAverage();
    }

    public NetworkQuality register(NetworkStateChangeListener networkStateChangeListener) {
        if (networkStateChangeListener != null) {
            this.listenerList.add(networkStateChangeListener);
        }
        return this.currentNetworkQuality.get();
    }

    public void remove(NetworkStateChangeListener networkStateChangeListener) {
        if (networkStateChangeListener != null) {
            this.listenerList.remove(networkStateChangeListener);
        }
    }

    private void notifyListeners() {
        try {
            int size = this.listenerList.size();
            for (int i = 0; i < size; i++) {
                this.listenerList.get(i).onBandwidthStateChange(this.currentNetworkQuality.get());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
