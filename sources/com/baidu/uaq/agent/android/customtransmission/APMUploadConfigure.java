package com.baidu.uaq.agent.android.customtransmission;

import android.support.annotation.Keep;
import com.baidu.uaq.agent.android.UAQ;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
@Keep
/* loaded from: E:\567196_dexfile_execute.dex */
public class APMUploadConfigure {
    public static final UAQ AGENT = UAQ.getInstance();
    public static final String APMUPLOADNAME = "APMPerformanceConfigurationName";
    public static final long MAXBYTESPERIOD = 86400000;
    public static final long MAXBYTESWIFI = 0;
    public static final int MAXUPLOADRETRYCOUNT = 3;
    public static final int MSEC = 1000;
    public APMUploadHandler apmUploadHandler;
    public boolean enableRetransmission;
    public HashMap<String, String> headerMap = new HashMap<>();
    public long interval4g;
    public long intervalWifi;
    public long maxBytes4g;
    public long maxBytesPeriod4g;
    public long maxBytesPeriodWifi;
    public long maxBytesWifi;
    public MergeBlockCallBack mergeBlockCallBack;
    public String uploadName;
    public String url;

    public APMUploadConfigure(String str, String str2, MergeBlockCallBack mergeBlockCallBack) {
        this.headerMap.put("Content-Type", "application/json");
        this.headerMap.put("Content-Encoding", "deflate");
        this.uploadName = str;
        this.url = str2;
        this.mergeBlockCallBack = mergeBlockCallBack;
        this.enableRetransmission = false;
        this.interval4g = AGENT.getConfig().getDataReportPeriod();
        this.intervalWifi = AGENT.getConfig().getDataReportPeriod();
        this.maxBytes4g = AGENT.getConfig().getDataReportLimit();
        this.maxBytesWifi = 0L;
        this.maxBytesPeriod4g = 86400000L;
        this.maxBytesPeriodWifi = 86400000L;
        this.apmUploadHandler = new APMUploadHandler(this.uploadName);
    }

    public void enableRetransmission(boolean z) {
        this.enableRetransmission = z;
    }

    public APMUploadHandler getApmUploadHandler() {
        return this.apmUploadHandler;
    }

    public HashMap<String, String> getHeaderMap() {
        return this.headerMap;
    }

    public long getInterval4g() {
        return this.interval4g;
    }

    public long getIntervalWifi() {
        return this.intervalWifi;
    }

    public long getMaxBytes4g() {
        return this.maxBytes4g;
    }

    public long getMaxBytesPeriod4g() {
        return this.maxBytesPeriod4g;
    }

    public long getMaxBytesPeriodWifi() {
        return this.maxBytesPeriodWifi;
    }

    public long getMaxBytesWifi() {
        return this.maxBytesWifi;
    }

    public MergeBlockCallBack getMergeBlockCallBack() {
        return this.mergeBlockCallBack;
    }

    public String getUploadName() {
        return this.uploadName;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isEnableRetransmission() {
        return this.enableRetransmission;
    }

    public void setHeaderMap(HashMap<String, String> hashMap) {
        this.headerMap = hashMap;
    }

    public void setInterval4g(long j) {
        this.interval4g = j * 1000;
    }

    public void setIntervalWifi(long j) {
        this.intervalWifi = j * 1000;
    }

    public void setMaxbytes4g(long j, long j2) {
        this.maxBytes4g = j;
        this.maxBytesPeriod4g = j2 * 1000;
    }

    public void setMaxbyteswifi(long j, long j2) {
        this.maxBytesWifi = j;
        this.maxBytesPeriodWifi = j2 * 1000;
    }
}
