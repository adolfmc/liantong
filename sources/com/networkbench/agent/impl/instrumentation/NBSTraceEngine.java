package com.networkbench.agent.impl.instrumentation;

import com.networkbench.agent.impl.harvest.HarvestAdapter;
import com.networkbench.agent.impl.harvest.type.MetricCategory;
import com.networkbench.agent.impl.p243c.p244a.C6255f;
import com.networkbench.agent.impl.p243c.p248e.C6295m;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p257b.C6410a;
import com.networkbench.agent.impl.util.C6653u;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSTraceEngine extends HarvestAdapter {
    private static final InterfaceC6393e log = C6394f.m10150a();
    private static List<MetricEventListener> metricEventListeners = new CopyOnWriteArrayList();

    public static void startTracingInFragment(String str) {
    }

    @Deprecated
    /* renamed from: a */
    void m9875a() {
    }

    public static List<MetricEventListener> getMetricEventListeners() {
        return metricEventListeners;
    }

    public static void registerListener(MetricEventListener metricEventListener) {
        if (metricEventListener == null || metricEventListeners.contains(metricEventListener)) {
            return;
        }
        metricEventListeners.add(metricEventListener);
    }

    public static void removeListener(MetricEventListener metricEventListener) {
        if (metricEventListener == null) {
            return;
        }
        metricEventListeners.remove(metricEventListener);
    }

    public static void notifyObserverEnterMethod(NBSTraceUnit nBSTraceUnit) {
        for (MetricEventListener metricEventListener : metricEventListeners) {
            metricEventListener.enterMethod(nBSTraceUnit);
        }
    }

    public static void notifyObserverExitMethod() {
        for (MetricEventListener metricEventListener : metricEventListeners) {
            metricEventListener.exitMethod();
        }
    }

    public static void notifyObserverExitMethodCustom(String str) {
        for (MetricEventListener metricEventListener : metricEventListeners) {
            metricEventListener.exitMethodCustom(str);
        }
    }

    public static void notifyObserverAsyncEnterMethod(NBSTraceUnit nBSTraceUnit) {
        for (MetricEventListener metricEventListener : metricEventListeners) {
            metricEventListener.asyncEnterMethod(nBSTraceUnit);
        }
    }

    public static void notifyObserverAddNetworkToSegment(C6410a c6410a) {
        for (MetricEventListener metricEventListener : metricEventListeners) {
            metricEventListener.addNetworkToSegment(c6410a);
        }
    }

    public static void startTracing(String str) {
        NBSAppInstrumentation.activityCreateBeginIns(str);
    }

    public static void enterMethod(String str, ArrayList<String> arrayList) {
        try {
            enterMethod(null, str, arrayList);
        } catch (Throwable th) {
            C6396h.m10132j("NBSTraceEngine  enterMethod has an error :" + th);
        }
    }

    public static void enterMethod(NBSTraceUnit nBSTraceUnit, String str, ArrayList<String> arrayList) {
        try {
            if (shouldInvokeMethod(str)) {
                C6255f.m10812a(new NBSTraceUnit(str, getSegmentType(arrayList).m10531a()));
                if (C6653u.m8708e()) {
                    notifyObserverEnterMethod(new NBSTraceUnit(str, getSegmentType(arrayList).m10531a()));
                } else {
                    notifyObserverAsyncEnterMethod(new NBSTraceUnit(str, getSegmentType(arrayList).m10531a()));
                }
            }
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("error happend in enterMethod:" + th.getMessage());
        }
    }

    private static boolean shouldInvokeMethod(String str) {
        return (str == null || str.endsWith("#onCreate")) ? false : true;
    }

    public static void exitMethod() {
        try {
            C6255f.m10806b();
            notifyObserverExitMethod();
        } catch (Throwable th) {
            C6396h.m10132j("NBSTraceEngine  exitMethod has an error :" + th);
        }
    }

    public static void exitCustomApiMethod(String str) {
        try {
            C6396h.m10142b("exitCustomApiMethod");
            C6255f.m10809a(str);
            notifyObserverExitMethodCustom(str);
        } catch (Throwable th) {
            C6396h.m10132j("NBSTraceEngine  exitCustomApiMethod has an error :" + th);
        }
    }

    public static C6295m.EnumC6300e getSegmentType(List<String> list) {
        if (list != null && list.size() == 3) {
            String str = list.get(2);
            if (str.equalsIgnoreCase(MetricCategory.IMAGE.getCategoryName())) {
                return C6295m.EnumC6300e.BITMAP;
            }
            if (str.equalsIgnoreCase(MetricCategory.JSON.getCategoryName())) {
                return C6295m.EnumC6300e.JSON;
            }
            if (str.equalsIgnoreCase(MetricCategory.NETWORK.getCategoryName())) {
                return C6295m.EnumC6300e.NETWORK;
            }
            if (str.equalsIgnoreCase("DATABASE")) {
                return C6295m.EnumC6300e.DATABASE;
            }
            if (str.equalsIgnoreCase(MetricCategory.CUSTOMEVENT.getCategoryName())) {
                return C6295m.EnumC6300e.CUSTOM;
            }
        }
        return C6295m.EnumC6300e.OTHER;
    }
}
