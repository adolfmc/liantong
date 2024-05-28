package com.baidu.p120ar.statistic;

import android.content.Context;
import com.baidu.p120ar.ihttp.HttpException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.LogSendTask */
/* loaded from: E:\10201592_dexfile_execute.dex */
class LogSendTask {
    protected EventRequestCache mCache;
    protected int mCountPerBatch;
    protected boolean mIsNeedPersist;
    protected ILogSender mSender;

    public LogSendTask(EventRequestCache eventRequestCache, int i, ILogSender iLogSender, boolean z) {
        if (eventRequestCache == null || iLogSender == null) {
            throw new NullPointerException();
        }
        this.mCache = eventRequestCache;
        this.mSender = iLogSender;
        this.mCountPerBatch = i <= 0 ? 10 : i;
        this.mIsNeedPersist = z;
    }

    public List<List<EventData>> getSendBatches() {
        synchronized (this.mCache) {
            ArrayList arrayList = new ArrayList();
            if (this.mCache.isEmpty()) {
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            HashMap hashMap = new HashMap();
            int size = this.mCache.size();
            ArrayList arrayList3 = new ArrayList();
            for (int i = 0; i < size; i++) {
                EventData eventData = this.mCache.get(i);
                String uniqTag = eventData.getUniqTag();
                if (!hashMap.containsKey(uniqTag)) {
                    if (isSkipEvent(eventData)) {
                        arrayList3.add(Integer.valueOf(i));
                    } else {
                        hashMap.put(uniqTag, 1);
                        arrayList2.add(eventData);
                        if (arrayList2.size() == this.mCountPerBatch) {
                            arrayList.add(arrayList2);
                            arrayList2 = new ArrayList();
                        }
                    }
                }
            }
            if (!arrayList2.isEmpty()) {
                arrayList.add(arrayList2);
            }
            if (!arrayList3.isEmpty()) {
                ArrayList arrayList4 = new ArrayList(arrayList3.size());
                for (int size2 = arrayList3.size() - 1; size2 >= 0; size2--) {
                    arrayList4.add(this.mCache.remove(((Integer) arrayList3.get(size2)).intValue()));
                }
                for (int size3 = arrayList4.size() - 1; size3 >= 0; size3--) {
                    this.mCache.put((EventData) arrayList4.get(size3));
                }
            }
            return arrayList;
        }
    }

    private boolean isSkipEvent(EventData eventData) {
        Object field = eventData.getField("_db_period");
        if (field == null || !(field instanceof Number)) {
            return false;
        }
        return System.currentTimeMillis() - eventData.getTimestamp() < ((Number) field).longValue();
    }

    public boolean hasData() {
        return !this.mCache.isEmpty();
    }

    public void send(Context context, List<EventData> list) throws IOException, HttpException {
        if (list.size() == 0) {
            return;
        }
        if (list.size() == 1) {
            this.mSender.send(context, list.get(0));
        } else {
            this.mSender.send(context, list);
        }
    }

    public void update(int i) {
        synchronized (this.mCache) {
            this.mCache.removeFromHead(i);
        }
    }

    public void save() {
        if (this.mIsNeedPersist) {
            synchronized (this.mCache) {
                this.mCache.flush();
            }
        }
    }
}
