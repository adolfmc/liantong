package com.baidu.p120ar.statistic;

import android.content.Context;
import android.util.Pair;
import com.baidu.p120ar.statistic.PerformanceBufferControl;
import com.baidu.p120ar.utils.DeviceUuidFactory;
import com.baidu.p120ar.utils.MD5Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.EventLogic */
/* loaded from: E:\10201592_dexfile_execute.dex */
class EventLogic implements PerformanceBufferControl.IFlushListener {
    private EventRequestCache mCache;
    private WeakReference<Context> mContextRef;
    private Map<String, String> mEventLabels;
    private String[][] mEventPairs;
    private Map<String, EventData> mEventsData = new HashMap();
    private long mLatestPauseTime = 0;
    private long mLatestResumeTime = 0;
    private PerformanceBufferControl mPerformanceBufferCtrl;
    private String mRequestId;
    private String mUUID;

    public EventLogic(Context context, EventRequestCache eventRequestCache, String[][] strArr, Map<String, String> map, PerformanceBufferControl performanceBufferControl) {
        this.mContextRef = new WeakReference<>(context);
        this.mCache = eventRequestCache;
        this.mEventPairs = strArr;
        this.mEventLabels = map == null ? new HashMap<>() : map;
        this.mPerformanceBufferCtrl = performanceBufferControl;
        this.mPerformanceBufferCtrl.setFlushListener(this);
    }

    public void init() {
        if (this.mContextRef.get() == null) {
            return;
        }
        this.mRequestId = MD5Utils.getMD5String(getUUID() + String.valueOf(System.currentTimeMillis()));
    }

    private String getUUID() {
        if (this.mUUID == null) {
            Context context = this.mContextRef.get();
            if (context == null) {
                return "";
            }
            UUID deviceUuid = new DeviceUuidFactory(context).getDeviceUuid();
            this.mUUID = deviceUuid == null ? "" : deviceUuid.toString();
        }
        return this.mUUID;
    }

    public void onEvent(EventData eventData) {
        prePutEvent(eventData);
        putInCache(eventData);
    }

    private void prePutEvent(EventData eventData) {
        setPublicField(eventData);
        if (eventData.containsField("event_label") || !this.mEventLabels.containsKey(eventData.getEventId())) {
            return;
        }
        eventData.setField("event_label", this.mEventLabels.get(eventData.getEventId()));
    }

    public void onEventDebounce(EventData eventData) {
        prePutEvent(eventData);
        synchronized (this.mCache) {
            this.mCache.checkLoaded();
            String eventId = eventData.getEventId();
            long longValue = ((Number) eventData.getField("_db_period")).longValue();
            ArrayList arrayList = new ArrayList();
            int size = this.mCache.size();
            for (int i = 0; i < size; i++) {
                EventData eventData2 = this.mCache.get(i);
                if (eventId.equals(eventData2.getEventId()) && longValue == eventData2.getFieldLong("_db_period") && eventData.getTimestamp() - eventData2.getTimestamp() < longValue) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
            if (!arrayList.isEmpty()) {
                for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                    int intValue = ((Integer) arrayList.get(size2)).intValue();
                    this.mCache.remove(intValue);
                    EventData.recycle(this.mCache.get(intValue));
                }
            }
            this.mCache.put(eventData);
            this.mCache.notifyAll();
        }
    }

    private void setPublicField(EventData eventData) {
        eventData.setField("request_id", this.mRequestId);
    }

    private void putInCache(EventData... eventDataArr) {
        synchronized (this.mCache) {
            this.mCache.checkLoaded();
            for (EventData eventData : eventDataArr) {
                this.mCache.put(eventData);
            }
            this.mCache.notifyAll();
        }
    }

    public void onEventStatus(EventData eventData) {
        String eventId = eventData.getEventId();
        EventData eventData2 = this.mEventsData.get(eventId);
        boolean equals = "1".equals(eventData.getField("__stt"));
        if (eventData2 == null) {
            if (equals) {
                this.mEventsData.put(eventId, eventData);
                EventData m24464clone = eventData.m24464clone();
                m24464clone.setEventId(eventId);
                m24464clone.removeField("__stt");
                m24464clone.removeField("__falseev");
                onEvent(m24464clone);
            }
        } else if (equals) {
        } else {
            String stringField = eventData.getStringField("__falseev");
            if (stringField != null && !stringField.isEmpty()) {
                EventData m24464clone2 = eventData.m24464clone();
                m24464clone2.setEventId(stringField);
                m24464clone2.removeField("__stt");
                m24464clone2.removeField("__falseev");
                onEvent(m24464clone2);
            }
            this.mEventsData.remove(eventId);
            EventData.recycle(eventData2);
        }
    }

    public void onEventStart(EventData eventData) {
        String eventId = eventData.getEventId();
        ArrayList<String> findMatchEndEvents = findMatchEndEvents(eventId);
        if (findMatchEndEvents.isEmpty()) {
            return;
        }
        onEvent(eventData.m24464clone());
        Iterator<String> it = findMatchEndEvents.iterator();
        while (it.hasNext()) {
            Map<String, EventData> map = this.mEventsData;
            map.put(eventId + ":" + it.next(), eventData);
        }
    }

    public void onEventEnd(EventData eventData) {
        String eventId = eventData.getEventId();
        String findMatchStartEvent = findMatchStartEvent(eventId);
        if (findMatchStartEvent == null || findMatchStartEvent.isEmpty()) {
            return;
        }
        String str = findMatchStartEvent + ":" + eventId;
        EventData eventData2 = this.mEventsData.get(str);
        if (eventData2 != null) {
            Pair<Long, Long> calcDuration = calcDuration(eventData2.getTimestamp(), eventData.getTimestamp());
            eventData.setField("st", String.valueOf(eventData2.getTimestamp()));
            eventData.setField("duration", String.valueOf(((Long) calcDuration.first).longValue()));
            eventData.setField("_pausedt", String.valueOf(((Long) calcDuration.second).longValue()));
            onEvent(eventData);
            this.mEventsData.remove(str);
            EventData.recycle(eventData2);
        }
    }

    private ArrayList<String> findMatchEndEvents(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[][] strArr = this.mEventPairs;
        if (strArr != null && strArr.length > 0) {
            for (String[] strArr2 : strArr) {
                if (strArr2 != null && strArr2.length >= 2 && str.equals(strArr2[0])) {
                    for (int i = 1; i < strArr2.length; i++) {
                        arrayList.add(strArr2[i]);
                    }
                }
            }
        }
        return arrayList;
    }

    private String findMatchStartEvent(String str) {
        Object[][] objArr = this.mEventPairs;
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        for (Object[] objArr2 : objArr) {
            if (objArr2 != null && objArr2.length >= 2) {
                for (int i = 1; i < objArr2.length; i++) {
                    if (str.equals(objArr2[i])) {
                        return objArr2[0];
                    }
                }
                continue;
            }
        }
        return null;
    }

    private void setLatestPauseTime(long j) {
        this.mLatestPauseTime = j;
    }

    private void setLatestResumeTime(long j) {
        long j2 = this.mLatestPauseTime;
        if (j < j2) {
            j = j2;
        }
        this.mLatestResumeTime = j;
    }

    private Pair<Long, Long> calcDuration(long j, long j2) {
        long j3;
        long j4;
        long j5 = this.mLatestPauseTime;
        if (j5 <= 0 || j > j5) {
            j3 = j2 - j;
            j4 = 0;
        } else {
            long j6 = this.mLatestResumeTime;
            if (j6 <= 0 || j2 < j6) {
                long j7 = this.mLatestPauseTime;
                j3 = j7 - j;
                j4 = j2 - j7;
            } else {
                j3 = (j5 - j) + (j6 - j2);
                j4 = j6 - j5;
            }
        }
        if (j3 < 0) {
            j3 = 0;
        }
        Long valueOf = Long.valueOf(j3);
        if (j4 < 0) {
            j4 = 0;
        }
        return new Pair<>(valueOf, Long.valueOf(j4));
    }

    public void onPerformance(EventData eventData) {
        boolean tryPut;
        setPublicField(eventData);
        synchronized (this.mPerformanceBufferCtrl) {
            tryPut = this.mPerformanceBufferCtrl.tryPut(eventData);
        }
        if (tryPut) {
            return;
        }
        EventData.recycle(eventData);
    }

    public void flushPerformance() {
        this.mPerformanceBufferCtrl.flush();
    }

    public void initPerformanceBufferControl(List<String> list) {
        this.mPerformanceBufferCtrl.setEventWhiteList(list);
    }

    @Override // com.baidu.p120ar.statistic.PerformanceBufferControl.IFlushListener
    public void onPerformanceBufferFlushed(int i) {
        if (i > 0) {
            synchronized (this.mCache) {
                this.mCache.notifyAll();
            }
        }
    }

    public void onPause(long j) {
        setLatestPauseTime(j);
        synchronized (this.mCache) {
            this.mCache.flush();
        }
        flushPerformance();
    }

    public void onResume(long j) {
        setLatestResumeTime(j);
    }
}
