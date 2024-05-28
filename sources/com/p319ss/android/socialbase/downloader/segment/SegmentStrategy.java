package com.p319ss.android.socialbase.downloader.segment;

import android.support.annotation.NonNull;
import org.json.JSONObject;

/* renamed from: com.ss.android.socialbase.downloader.segment.SegmentStrategy */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SegmentStrategy {
    private static final int MAX_THREAD_COUNT = 16;
    private static final long MIN_CONNECT_TIMEOUT = 2000;
    private static final long MIN_READ_TIMEOUT = 4000;
    private static final long SEGMENT_MIN_INIT_SIZE = 5242880;
    private static final long SEGMENT_MIN_SIZE = 65536;
    public static final String TAG = "SegmentStrategy";
    private final JSONObject config;
    private int threadCount;

    private SegmentStrategy(JSONObject jSONObject) {
        this.config = jSONObject;
    }

    public void updateUrlCount(int i) {
        this.threadCount = calculateThreadCount(i);
    }

    public int getThreadCount() {
        return this.threadCount;
    }

    private int getUrlBalance() {
        return this.config.optInt("url_balance", 2);
    }

    public boolean urlBalance() {
        return getUrlBalance() > 0;
    }

    public boolean urlBalanceStrictly() {
        return getUrlBalance() == 1;
    }

    public int getBufferCount() {
        return this.config.optInt("buffer_count", 512);
    }

    public int getBufferSize() {
        return this.config.optInt("buffer_size", 8192);
    }

    public boolean segmentOneByOne() {
        return this.config.optInt("segment_mode", 1) == 0;
    }

    public long getSegmentMinSize() {
        long optInt = this.config.optInt("segment_min_kb", 512) * 1024;
        if (optInt < 65536) {
            return 65536L;
        }
        return optInt;
    }

    public long getSegmentMinInitSize() {
        long optInt = this.config.optInt("segment_min_init_mb", 10) * 1048576;
        return optInt < SEGMENT_MIN_INIT_SIZE ? SEGMENT_MIN_INIT_SIZE : optInt;
    }

    public long getSegmentMaxSize() {
        long optInt = this.config.optInt("segment_max_kb", 0) * 1048576;
        if (optInt < getSegmentMinSize()) {
            return -1L;
        }
        return optInt;
    }

    public long getConnectTimeout() {
        long optInt = this.config.optInt("connect_timeout", -1);
        if (optInt >= 2000) {
            return optInt;
        }
        return -1L;
    }

    public long getReadTimeout() {
        long optInt = this.config.optInt("read_timeout", -1);
        if (optInt >= MIN_READ_TIMEOUT) {
            return optInt;
        }
        return -1L;
    }

    public int getIpStrategy() {
        return this.config.optInt("ip_strategy", 0);
    }

    private int calculateThreadCount(int i) {
        int optInt = this.config.optInt("thread_count", 4);
        if (optInt > 16) {
            optInt = 16;
        }
        if (optInt > 0) {
            return getUrlBalance() == 1 ? Math.min(optInt, i) : optInt;
        } else if (getUrlBalance() > 0) {
            return i;
        } else {
            return 1;
        }
    }

    public float getMainRatio() {
        return (float) this.config.optDouble("main_ratio", 0.0d);
    }

    public int getRatioSegmentStrategy() {
        return this.config.optInt("ratio_segment", 0);
    }

    public float getPoorSpeedRatio() {
        return Math.min(Math.max(0.0f, (float) this.config.optDouble("poor_speed_ratio", 0.0d)), 1.0f);
    }

    @NonNull
    public static SegmentStrategy from(@NonNull JSONObject jSONObject) {
        return new SegmentStrategy(jSONObject);
    }
}
