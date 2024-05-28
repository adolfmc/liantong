package com.baidu.p120ar.statistic;

import android.content.Context;
import android.os.HandlerThread;
import com.baidu.p120ar.utils.ARLog;
import com.baidu.p120ar.utils.ReflectionUtils;
import java.lang.reflect.Constructor;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.StatisticApi */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class StatisticApi {
    private static final String IMPL_CLASS = "com.baidu.ar.statistic.StatisticApiImpl";
    private static IStatisticApiImpl sApi;
    private static IPerformanceStatisticApi sPerformanceApi;

    public static void init(Context context) {
        if (sApi == null) {
            try {
                Constructor<?> constructor = ReflectionUtils.getConstructor("com.baidu.ar.statistic.StatisticApiImpl", Context.class, HandlerThread.class);
                if (constructor != null) {
                    sApi = (IStatisticApiImpl) ReflectionUtils.getNewInstance(constructor, context, null);
                }
            } catch (Throwable th) {
                sApi = null;
                ARLog.m20420e("Statistic init fail");
                th.printStackTrace();
            }
        }
        if (sPerformanceApi == null) {
            sPerformanceApi = new PerformanceStatisticApiProxy();
        }
    }

    public static void setPubParam(String str, String str2) {
        IStatisticApiImpl iStatisticApiImpl = sApi;
        if (iStatisticApiImpl != null) {
            iStatisticApiImpl.setPubParam(str, str2);
        }
    }

    public static void setPubParams(Map<String, String> map) {
        IStatisticApiImpl iStatisticApiImpl = sApi;
        if (iStatisticApiImpl != null) {
            iStatisticApiImpl.setPubParams(map);
        }
    }

    public static void onEvent(String str) {
        onEvent(str, "");
    }

    public static void onEvent(String str, String str2) {
        IStatisticApiImpl iStatisticApiImpl = sApi;
        if (iStatisticApiImpl != null) {
            iStatisticApiImpl.onEvent(str, str2);
        }
    }

    public static void onEvent(String str, Map<String, String> map) {
        IStatisticApiImpl iStatisticApiImpl = sApi;
        if (iStatisticApiImpl != null) {
            iStatisticApiImpl.onEvent(str, map);
        }
    }

    public static void onEventStatus(String str, String str2, boolean z) {
        IStatisticApiImpl iStatisticApiImpl = sApi;
        if (iStatisticApiImpl != null) {
            iStatisticApiImpl.onEventStatus(str, str2, z);
        }
    }

    public static void onEventStart(String str) {
        IStatisticApiImpl iStatisticApiImpl = sApi;
        if (iStatisticApiImpl != null) {
            iStatisticApiImpl.onEventStart(str);
        }
    }

    public static void onEventEnd(String str) {
        IStatisticApiImpl iStatisticApiImpl = sApi;
        if (iStatisticApiImpl != null) {
            iStatisticApiImpl.onEventEnd(str);
        }
    }

    public static void onEventDebounce(String str, long j, String str2) {
        IStatisticApiImpl iStatisticApiImpl = sApi;
        if (iStatisticApiImpl != null) {
            iStatisticApiImpl.onEventDebounce(str, j, str2);
        }
    }

    public static void onEventDebounce(String str, long j, Map<String, String> map) {
        IStatisticApiImpl iStatisticApiImpl = sApi;
        if (iStatisticApiImpl != null) {
            iStatisticApiImpl.onEventDebounce(str, j, map);
        }
    }

    public static void onPerformance(String str, Map<String, String> map) {
        IStatisticApiImpl iStatisticApiImpl = sApi;
        if (iStatisticApiImpl != null) {
            iStatisticApiImpl.onPerformance(str, map);
        }
    }

    public static void onPerformance(String str, JSONObject jSONObject) {
        IStatisticApiImpl iStatisticApiImpl = sApi;
        if (iStatisticApiImpl != null) {
            iStatisticApiImpl.onPerformance(str, jSONObject);
        }
    }

    public static boolean isAllowPerformanceEvent(String str) {
        IStatisticApiImpl iStatisticApiImpl = sApi;
        if (iStatisticApiImpl != null) {
            return iStatisticApiImpl.isAllowPerformanceEvent(str);
        }
        return true;
    }

    public static IPerformanceStatisticApi getPerformanceApi() {
        if (sPerformanceApi == null) {
            sPerformanceApi = new PerformanceStatisticApiProxy();
        }
        return sPerformanceApi;
    }

    public static void pause() {
        IStatisticApiImpl iStatisticApiImpl = sApi;
        if (iStatisticApiImpl != null) {
            iStatisticApiImpl.pause();
        }
    }

    public static void resume() {
        IStatisticApiImpl iStatisticApiImpl = sApi;
        if (iStatisticApiImpl != null) {
            iStatisticApiImpl.resume();
        }
    }

    public static void release() {
        sPerformanceApi = null;
        IStatisticApiImpl iStatisticApiImpl = sApi;
        if (iStatisticApiImpl != null) {
            iStatisticApiImpl.release();
            sApi = null;
        }
    }
}
