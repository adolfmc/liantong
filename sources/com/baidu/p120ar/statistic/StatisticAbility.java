package com.baidu.p120ar.statistic;

import android.text.TextUtils;
import com.baidu.p120ar.lua.EngineMsgBridge;
import com.baidu.p120ar.lua.EngineMsgListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.StatisticAbility */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class StatisticAbility {
    private static final List<String> debounceEvents = Arrays.asList("event_filter_adjust", "event_filter_switch", "event_beautify_adjust");
    private static EngineMsgListener sMsgListener;

    public static void init(EngineMsgBridge engineMsgBridge) {
        sMsgListener = new EngineMsgListener() { // from class: com.baidu.ar.statistic.StatisticAbility.1
            @Override // com.baidu.p120ar.lua.EngineMsgListener
            public List<Integer> getMsgTypesListened() {
                ArrayList arrayList = new ArrayList();
                arrayList.add(1801);
                arrayList.add(1901);
                return arrayList;
            }

            @Override // com.baidu.p120ar.lua.EngineMsgListener
            public void onEngineMessage(int i, int i2, HashMap<String, Object> hashMap) {
                if (i == 1801) {
                    StatisticAbility.processEngineStatistic(hashMap);
                } else if (i == 1901) {
                    StatisticAbility.processLuaStatistic(hashMap);
                }
            }
        };
        engineMsgBridge.addEngineMsgListener(sMsgListener);
    }

    public static void release(EngineMsgBridge engineMsgBridge) {
        EngineMsgListener engineMsgListener = sMsgListener;
        if (engineMsgListener != null) {
            if (engineMsgBridge != null) {
                engineMsgBridge.removeEngineMsgListener(engineMsgListener);
            }
            sMsgListener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void processLuaStatistic(HashMap<String, Object> hashMap) {
        if (hashMap != null && hashMap.containsKey("event_name") && hashMap.containsKey("event_id") && "statistic_lua_event".equals((String) hashMap.get("event_name"))) {
            String str = (String) hashMap.get("event_id");
            Object obj = hashMap.get("event_map");
            if (obj != null && (obj instanceof Map)) {
                StatisticApi.onEvent(str, (Map) obj);
            } else {
                StatisticApi.onEvent(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void processEngineStatistic(HashMap<String, Object> hashMap) {
        if (hashMap != null) {
            String str = (String) hashMap.get("id");
            String str2 = (String) hashMap.get("type");
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            HashMap hashMap2 = new HashMap();
            if (hashMap.size() > 1) {
                for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                    if (entry.getValue() != null) {
                        hashMap2.put(entry.getKey(), entry.getValue().toString());
                    }
                }
                hashMap2.remove("type");
                if (hashMap2.containsKey("id")) {
                    hashMap2.remove("id");
                    hashMap2.put("event_param", str);
                }
            }
            if (debounceEvents.contains(str2)) {
                StatisticApi.onEventDebounce(str2, 200L, hashMap2);
            } else {
                StatisticApi.onEvent(str2, hashMap2);
            }
        }
    }
}
