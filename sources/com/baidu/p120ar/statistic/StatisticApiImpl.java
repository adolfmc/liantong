package com.baidu.p120ar.statistic;

import android.content.Context;
import android.os.AsyncTask;
import android.os.HandlerThread;
import com.baidu.p120ar.statistic.PerformanceControlRequestTask;
import java.lang.Thread;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.StatisticApiImpl */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class StatisticApiImpl implements IStatisticApiImpl, PerformanceControlRequestTask.IFinishedListener {
    private static final int MAX_CACHE_COUNT = 100;
    private static final int MAX_EVENTS_PER_BATCH = 5;
    private static final int MAX_PERSIST_COUNT = 40;
    private static final int PERFORMANCE_BUFFER_SIZE = 20;
    private static final int PERFORMANCE_MAX_CACHE_COUNT = 200;
    private EventRequestCache mCache;
    private HandlerThread mEventThread;
    private EventLogicHandler mHandler;
    private LogSendRunnable mLogSendRunnable;
    private Thread mLogSendThread;
    private volatile PerformanceBufferControl mPerfCtrl;

    public StatisticApiImpl(Context context, HandlerThread handlerThread) {
        this.mCache = new EventRequestCache(context, "ar_stats_local.json", 100, 40);
        EventRequestCache eventRequestCache = new EventRequestCache(context, "ar_stats_pfm_local.json", 200, 0);
        this.mPerfCtrl = new PerformanceBufferControl(eventRequestCache, 20, 200);
        startEventReceiveThread(handlerThread, new EventLogic(context, this.mCache, StatisticEventPairs.EVENT_PAIRS, StatisticLabels.getDefineLables(), this.mPerfCtrl));
        startLogSendThread(context, eventRequestCache);
        this.mHandler.sendEvent(1, null);
        requestPerformanceControl(context);
    }

    private void startEventReceiveThread(HandlerThread handlerThread, EventLogic eventLogic) {
        if (handlerThread == null) {
            this.mEventThread = new HandlerThread("StatsEventThd", 10);
            this.mEventThread.start();
        } else {
            this.mEventThread = handlerThread;
            if (this.mEventThread.getState() == Thread.State.NEW) {
                this.mEventThread.start();
            }
        }
        this.mHandler = new EventLogicHandler(this.mEventThread.getLooper(), eventLogic);
    }

    private void startLogSendThread(Context context, EventRequestCache eventRequestCache) {
        EventRequestCache eventRequestCache2 = this.mCache;
        this.mLogSendRunnable = new LogSendRunnable(context, eventRequestCache2, new LogSendTask[]{new LogSendTask(eventRequestCache2, 5, new LogSender(), true), new PerformanceLogSendTask(eventRequestCache, 20, new PerformanceLogSender(), false)});
        this.mLogSendThread = new Thread(this.mLogSendRunnable, "StatsLogSendThd");
        this.mLogSendThread.start();
    }

    @Override // com.baidu.p120ar.statistic.IStatisticApiImpl
    public void setPubParam(String str, String str2) {
        this.mHandler.setPubParam(str, str2);
    }

    @Override // com.baidu.p120ar.statistic.IStatisticApiImpl
    public void setPubParams(Map<String, String> map) {
        this.mHandler.setPubParams(map);
    }

    @Override // com.baidu.p120ar.statistic.IStatisticApiImpl
    public void onEvent(String str, String str2) {
        EventData obtain = EventData.obtain(str);
        obtain.setField("event_param", str2);
        this.mHandler.sendEvent(2, obtain);
    }

    @Override // com.baidu.p120ar.statistic.IStatisticApiImpl
    public void onEvent(String str, Map<String, String> map) {
        EventData obtain = EventData.obtain(str);
        obtain.setFields(map);
        this.mHandler.sendEvent(2, obtain);
    }

    @Override // com.baidu.p120ar.statistic.IStatisticApiImpl
    public void onEventStatus(String str, String str2, boolean z) {
        EventData obtain = EventData.obtain(str);
        obtain.setField("__stt", z ? "1" : "0");
        obtain.setField("__falseev", str2);
        this.mHandler.sendEvent(3, obtain);
    }

    @Override // com.baidu.p120ar.statistic.IStatisticApiImpl
    public void onEventStart(String str) {
        this.mHandler.sendEvent(4, EventData.obtain(str));
    }

    @Override // com.baidu.p120ar.statistic.IStatisticApiImpl
    public void onEventEnd(String str) {
        this.mHandler.sendEvent(5, EventData.obtain(str));
    }

    @Override // com.baidu.p120ar.statistic.IStatisticApiImpl
    public void onEventDebounce(String str, long j, String str2) {
        EventData obtain = EventData.obtain(str);
        obtain.setField("_db_period", Long.valueOf(j));
        obtain.setField("event_param", str2);
        this.mHandler.sendEvent(6, obtain);
    }

    @Override // com.baidu.p120ar.statistic.IStatisticApiImpl
    public void onEventDebounce(String str, long j, Map<String, String> map) {
        EventData obtain = EventData.obtain(str);
        obtain.setFields(map);
        obtain.setField("_db_period", Long.valueOf(j));
        this.mHandler.sendEvent(6, obtain);
    }

    @Override // com.baidu.p120ar.statistic.IStatisticApiImpl
    public void onPerformance(String str, Map<String, String> map) {
        synchronized (this.mPerfCtrl) {
            if (this.mPerfCtrl.isAllowPut(str)) {
                EventData obtain = EventData.obtain(str);
                obtain.setFields(map);
                this.mHandler.sendEvent(20, obtain);
            }
        }
    }

    @Override // com.baidu.p120ar.statistic.IStatisticApiImpl
    public void onPerformance(String str, JSONObject jSONObject) {
        synchronized (this.mPerfCtrl) {
            if (this.mPerfCtrl.isAllowPut(str)) {
                EventData obtain = EventData.obtain(str);
                obtain.setField("data", jSONObject);
                this.mHandler.sendEvent(20, obtain);
            }
        }
    }

    @Override // com.baidu.p120ar.statistic.IStatisticApiImpl
    public boolean isAllowPerformanceEvent(String str) {
        return this.mPerfCtrl.isAllowPut(str);
    }

    @Override // com.baidu.p120ar.statistic.IStatisticApiImpl
    public void pause() {
        this.mHandler.sendEvent(97, Long.valueOf(System.currentTimeMillis()));
    }

    @Override // com.baidu.p120ar.statistic.IStatisticApiImpl
    public void resume() {
        this.mHandler.sendEvent(98, Long.valueOf(System.currentTimeMillis()));
    }

    @Override // com.baidu.p120ar.statistic.IStatisticApiImpl
    public void release() {
        this.mHandler.sendEvent(99, this);
    }

    public void doRelease() {
        synchronized (this) {
            if (this.mEventThread != null) {
                this.mEventThread.quitSafely();
            }
            if (this.mLogSendRunnable != null) {
                this.mLogSendRunnable.shutdown();
            }
            if (this.mLogSendThread != null) {
                synchronized (this.mCache) {
                    if (this.mCache.isEmpty()) {
                        this.mLogSendThread.interrupt();
                    }
                }
            }
        }
        EventData.release();
    }

    private void requestPerformanceControl(Context context) {
        new PerformanceControlRequestTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
    }

    @Override // com.baidu.p120ar.statistic.PerformanceControlRequestTask.IFinishedListener
    public void onPerformanceRequestFinished(List<String> list) {
        this.mHandler.sendEvent(21, list);
    }
}
