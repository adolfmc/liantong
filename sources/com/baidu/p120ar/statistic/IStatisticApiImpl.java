package com.baidu.p120ar.statistic;

import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.IStatisticApiImpl */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IStatisticApiImpl {
    boolean isAllowPerformanceEvent(String str);

    void onEvent(String str, String str2);

    void onEvent(String str, Map<String, String> map);

    void onEventDebounce(String str, long j, String str2);

    void onEventDebounce(String str, long j, Map<String, String> map);

    void onEventEnd(String str);

    void onEventStart(String str);

    void onEventStatus(String str, String str2, boolean z);

    void onPerformance(String str, Map<String, String> map);

    void onPerformance(String str, JSONObject jSONObject);

    void pause();

    void release();

    void resume();

    void setPubParam(String str, String str2);

    void setPubParams(Map<String, String> map);
}
