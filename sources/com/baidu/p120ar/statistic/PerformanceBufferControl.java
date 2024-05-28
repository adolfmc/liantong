package com.baidu.p120ar.statistic;

import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.PerformanceBufferControl */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class PerformanceBufferControl {
    private int mBufferSize;
    private List<String> mEventWhiteList;
    private boolean mIsControlInited;
    private IFlushListener mListener;
    private int mMaxBufferSize;
    private ArrayList<EventData> mPerformanceBuffer;
    private EventRequestCache mPerformanceCache;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.statistic.PerformanceBufferControl$IFlushListener */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface IFlushListener {
        void onPerformanceBufferFlushed(int i);
    }

    public PerformanceBufferControl(EventRequestCache eventRequestCache, int i, int i2) {
        this.mPerformanceCache = eventRequestCache;
        this.mBufferSize = i <= 0 ? 20 : i;
        int i3 = this.mBufferSize;
        this.mMaxBufferSize = i2 >= i3 ? i2 : i3;
        this.mPerformanceBuffer = new ArrayList<>(this.mBufferSize);
        this.mIsControlInited = false;
        this.mEventWhiteList = null;
        this.mListener = null;
    }

    public void setFlushListener(IFlushListener iFlushListener) {
        this.mListener = iFlushListener;
    }

    public void setEventWhiteList(List<String> list) {
        this.mEventWhiteList = list;
        this.mIsControlInited = true;
        if (this.mPerformanceBuffer.isEmpty()) {
            return;
        }
        List<String> list2 = this.mEventWhiteList;
        if (list2 == null || list2.isEmpty()) {
            this.mPerformanceBuffer.clear();
            return;
        }
        for (int size = this.mPerformanceBuffer.size() - 1; size >= 0; size--) {
            if (!this.mEventWhiteList.contains(this.mPerformanceBuffer.get(size).getEventId())) {
                this.mEventWhiteList.remove(size);
            }
        }
        if (this.mPerformanceBuffer.size() >= this.mBufferSize) {
            flush();
        }
    }

    public boolean isAllowPut(String str) {
        return !this.mIsControlInited || isAllowEvent(str);
    }

    public boolean isAllowEvent(String str) {
        List<String> list = this.mEventWhiteList;
        return list != null && list.contains(str);
    }

    private boolean isAllowEvent(EventData eventData) {
        return isAllowEvent(eventData.getEventId());
    }

    public boolean tryPut(EventData eventData) {
        if (!this.mIsControlInited) {
            if (this.mPerformanceBuffer.size() < this.mMaxBufferSize) {
                this.mPerformanceBuffer.add(eventData);
                return true;
            }
            return false;
        } else if (isAllowEvent(eventData)) {
            this.mPerformanceBuffer.add(eventData);
            if (this.mPerformanceBuffer.size() >= this.mBufferSize) {
                flush();
            }
            return true;
        } else {
            return false;
        }
    }

    public int flush() {
        int size = this.mPerformanceBuffer.size();
        if (size > 0) {
            synchronized (this.mPerformanceCache) {
                this.mPerformanceCache.addAll(this.mPerformanceBuffer);
            }
            this.mPerformanceBuffer.clear();
            IFlushListener iFlushListener = this.mListener;
            if (iFlushListener != null) {
                iFlushListener.onPerformanceBufferFlushed(size);
            }
        }
        return size;
    }
}
