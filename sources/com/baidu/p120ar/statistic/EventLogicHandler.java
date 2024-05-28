package com.baidu.p120ar.statistic;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.p120ar.bean.ARConfig;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.EventLogicHandler */
/* loaded from: E:\10201592_dexfile_execute.dex */
class EventLogicHandler extends Handler {
    public static final int EV_DEBOUNCE = 6;
    public static final int EV_END = 5;
    public static final int EV_EVENT = 2;
    public static final int EV_INIT = 1;
    public static final int EV_PERFORMANCE = 20;
    public static final int EV_START = 4;
    public static final int EV_STATUS = 3;
    public static final int PAUSE = 97;
    public static final int PERFORMANCE_CTRL = 21;
    public static final int RESUME = 98;
    public static final int SHUTDOWN = 99;
    private Map<String, String> mEventPubParams;
    private EventLogic mLogic;

    public EventLogicHandler(Looper looper, EventLogic eventLogic) {
        super(looper);
        this.mLogic = eventLogic;
        this.mEventPubParams = new HashMap();
    }

    public void setPubParam(String str, String str2) {
        this.mEventPubParams.put(str, str2);
    }

    public void setPubParams(Map<String, String> map) {
        if (map != null) {
            this.mEventPubParams.putAll(map);
        }
    }

    public void sendEvent(int i, Object obj) {
        if (obj instanceof EventData) {
            EventData eventData = (EventData) obj;
            if (!TextUtils.isEmpty(ARConfig.getARKey())) {
                eventData.setField("ar_key", ARConfig.getARKey());
            }
            eventData.setField("ar_id", ARConfig.getARId());
            eventData.setField("ar_from", ARConfig.getArFrom());
            eventData.setField("ar_type", String.valueOf(ARConfig.getARType()));
            if (!this.mEventPubParams.isEmpty()) {
                eventData.setFields(this.mEventPubParams);
            }
        }
        Message obtainMessage = obtainMessage();
        obtainMessage.what = i;
        obtainMessage.obj = obj;
        sendMessage(obtainMessage);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        int i = message.what;
        switch (i) {
            case 1:
                this.mLogic.init();
                return;
            case 2:
                this.mLogic.onEvent((EventData) message.obj);
                return;
            case 3:
                this.mLogic.onEventStatus((EventData) message.obj);
                return;
            case 4:
                this.mLogic.onEventStart((EventData) message.obj);
                return;
            case 5:
                this.mLogic.onEventEnd((EventData) message.obj);
                return;
            case 6:
                this.mLogic.onEventDebounce((EventData) message.obj);
                return;
            default:
                switch (i) {
                    case 20:
                        this.mLogic.onPerformance((EventData) message.obj);
                        return;
                    case 21:
                        this.mLogic.initPerformanceBufferControl((List) message.obj);
                        return;
                    default:
                        switch (i) {
                            case 97:
                                this.mLogic.onPause(((Long) message.obj).longValue());
                                return;
                            case 98:
                                this.mLogic.onResume(((Long) message.obj).longValue());
                                return;
                            case 99:
                                this.mLogic.flushPerformance();
                                StatisticApiImpl statisticApiImpl = (StatisticApiImpl) message.obj;
                                if (statisticApiImpl != null) {
                                    statisticApiImpl.doRelease();
                                }
                                message.obj = null;
                                return;
                            default:
                                return;
                        }
                }
        }
    }
}
