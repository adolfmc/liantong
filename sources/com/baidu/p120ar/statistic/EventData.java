package com.baidu.p120ar.statistic;

import com.baidu.p120ar.utils.MD5Utils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.ar.statistic.EventData */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class EventData implements Cloneable {
    private static final int MAX_POOL_SIZE = 500;
    private static EventData sPool;
    private static final Object sPoolSync = new Object();
    private static int sPoolSize = 0;
    private static volatile boolean sIsReleased = false;
    private EventData mNext = null;
    private JSONObject mInnerData = new JSONObject();

    public static EventData obtain(String str) {
        if (!sIsReleased) {
            synchronized (sPoolSync) {
                if (sPool != null) {
                    EventData eventData = sPool;
                    sPool = eventData.mNext;
                    eventData.mNext = null;
                    sPoolSize--;
                    if (eventData.mInnerData == null) {
                        eventData.mInnerData = new JSONObject();
                    }
                    eventData.setEventId(str);
                    eventData.setTimestamp(System.currentTimeMillis());
                    return eventData;
                }
            }
        }
        return new EventData(str);
    }

    public static void recycle(EventData eventData) {
        if (sIsReleased) {
            return;
        }
        synchronized (sPoolSync) {
            eventData.recycleObj();
        }
    }

    public static void recycle(EventData... eventDataArr) {
        if (sIsReleased) {
            return;
        }
        synchronized (sPoolSync) {
            if (eventDataArr != null) {
                for (EventData eventData : eventDataArr) {
                    eventData.recycleObj();
                }
            }
        }
    }

    private void recycleObj() {
        this.mInnerData = null;
        int i = sPoolSize;
        if (i < 500) {
            this.mNext = sPool;
            sPool = this;
            sPoolSize = i + 1;
        }
    }

    public static void release() {
        if (sIsReleased) {
            return;
        }
        synchronized (sPoolSync) {
            sIsReleased = true;
            sPool = null;
            sPoolSize = 0;
        }
    }

    private EventData(String str) {
        setEventId(str);
        setTimestamp(System.currentTimeMillis());
    }

    public String getEventId() {
        return this.mInnerData.optString("event_id");
    }

    public void setEventId(String str) {
        try {
            this.mInnerData.putOpt("event_id", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public long getTimestamp() {
        return this.mInnerData.optLong("time");
    }

    public void setTimestamp(long j) {
        try {
            this.mInnerData.putOpt("time", Long.valueOf(j));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getStringField(String str) {
        return this.mInnerData.optString(str);
    }

    public Object getField(String str) {
        return this.mInnerData.opt(str);
    }

    public long getFieldLong(String str) {
        Object field = getField(str);
        if (field instanceof Number) {
            return ((Number) field).longValue();
        }
        return 0L;
    }

    public void setField(String str, Object obj) {
        try {
            this.mInnerData.putOpt(str, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean containsField(String str) {
        return this.mInnerData.has(str);
    }

    public void removeField(String str) {
        this.mInnerData.remove(str);
    }

    public void setFields(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                this.mInnerData.putOpt(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getUniqTag() {
        String serialize = serialize(this);
        if (serialize == null) {
            return null;
        }
        return MD5Utils.getMD5String(serialize);
    }

    public Iterator<String> keys() {
        return this.mInnerData.keys();
    }

    public JSONObject toJson() {
        return toJson(null, null);
    }

    public JSONObject toJson(Collection<String> collection) {
        return toJson(null, collection);
    }

    public JSONObject toJson(JSONObject jSONObject, Collection<String> collection) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            Iterator<String> keys = this.mInnerData.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (collection == null || !collection.contains(next)) {
                    jSONObject.put(next, this.mInnerData.get(next));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* renamed from: clone */
    public EventData m24464clone() {
        EventData obtain = obtain("");
        try {
            JSONObject jSONObject = this.mInnerData;
            obtain.mInnerData = new JSONObject(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obtain;
    }

    public static String serialize(EventData eventData) {
        JSONObject jSONObject;
        if (eventData == null || (jSONObject = eventData.mInnerData) == null) {
            return null;
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
    }

    public static EventData deserialize(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str.trim());
            EventData obtain = obtain(jSONObject.getString("event_id"));
            obtain.mInnerData = jSONObject;
            return obtain;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
