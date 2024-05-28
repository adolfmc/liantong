package com.networkbench.agent.impl.instrumentation;

import android.text.TextUtils;
import android.view.View;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HarvestData;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p243c.p244a.C6255f;
import com.networkbench.agent.impl.p243c.p247d.C6275g;
import com.networkbench.agent.impl.p243c.p247d.C6277i;
import com.networkbench.agent.impl.p243c.p248e.C6289g;
import com.networkbench.agent.impl.p243c.p248e.C6293k;
import com.networkbench.agent.impl.p243c.p248e.C6294l;
import com.networkbench.agent.impl.p254f.C6395g;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p267m.C6501g;
import com.networkbench.agent.impl.util.C6638h;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSFragmentSession {
    private static NBSFragmentSession instance;
    private ConcurrentHashMap<String, C6501g> fragmentPageSpans = new ConcurrentHashMap<>();
    private static final InterfaceC6393e log = new C6395g("NBSFragmentSession");
    public static String custPageName = null;
    private static ConcurrentHashMap<String, C6275g> storeFragmentPageData = new ConcurrentHashMap<>();
    private static String beforeFragmentInstanceName = "";
    private static ConcurrentHashMap<String, C6294l> fragmentTraces = new ConcurrentHashMap<>();

    public static void fragmentOnCreateEnd(String str) {
    }

    public ConcurrentHashMap<String, C6501g> getFragmentPageSpans() {
        return this.fragmentPageSpans;
    }

    public static void fragmentOnCreateViewBegin(String str, String str2, Object obj) {
        try {
            if (C6638h.m8963w().m9037ai()) {
                C6289g c6289g = new C6289g();
                c6289g.f15726a.m10593a(str, str2);
                if (obj != null && (obj instanceof View)) {
                    ((View) obj).setTag(214748366, str);
                }
                c6289g.mo10539a(str, "#onCreateView");
                fragmentTraces.put(str, c6289g);
            }
        } catch (Throwable th) {
            C6396h.m10131k("fragmentOnCreateViewBegin error : " + th);
        }
    }

    private static C6289g getFragmentTrace(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (C6289g) fragmentTraces.get(str);
    }

    public static void fragmentOnCreateViewEnd(String str, String str2) {
        C6289g fragmentTrace;
        try {
            if (C6638h.m8963w().m9037ai() && (fragmentTrace = getFragmentTrace(str)) != null) {
                fragmentTrace.f15726a.m10593a(str, str2);
                fragmentTrace.mo10538b();
            }
        } catch (Throwable th) {
            C6396h.m10131k("fragmentOnCreateViewEnd error : " + th);
        }
    }

    public void fragmentSessionStarted(String str, String str2, Object obj) {
        try {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10122a("fragmentSessionStarted fragmentName:" + str + ", instrumentClassName:" + str2);
            if (C6638h.m8963w().m9038ah() && !TextUtils.isEmpty(str)) {
                if (C6638h.m8963w().m9037ai()) {
                    C6289g fragmentTrace = getFragmentTrace(str);
                    if (fragmentTrace == null) {
                        return;
                    }
                    fragmentTrace.f15727b.m10593a(str, str2);
                    fragmentTrace.mo10537b(str);
                }
                Harvest.currentActivityName = str;
                if (Harvest.isUser_action_enabled()) {
                    C6501g c6501g = new C6501g();
                    c6501g.m9735a(System.currentTimeMillis());
                    c6501g.m9734a(str);
                    ConcurrentHashMap<String, C6501g> concurrentHashMap = this.fragmentPageSpans;
                    concurrentHashMap.put(str + obj.hashCode(), c6501g);
                }
            }
        } catch (Throwable th) {
            C6396h.m10131k("NBSFragmentSession  fragmentSessionStarted() has an error : " + th);
        }
    }

    public static void fragmentStartEnd(String str, String str2) {
        C6289g fragmentTrace;
        try {
            if (C6638h.m8963w().m9037ai() && (fragmentTrace = getFragmentTrace(str)) != null) {
                fragmentTrace.f15727b.m10593a(str, str2);
                fragmentTrace.mo10536c();
            }
        } catch (Throwable th) {
            C6396h.m10131k("fragmentStartEnd error : " + th);
        }
    }

    public static void fragmentSessionResumeBegin(String str, String str2) {
        C6289g fragmentTrace;
        try {
            if (C6638h.m8963w().m9037ai() && (fragmentTrace = getFragmentTrace(str)) != null) {
                fragmentTrace.f15728c.m10593a(str, str2);
                fragmentTrace.mo10540a(str);
            }
        } catch (Throwable th) {
            C6396h.m10131k("fragmentSessionResumeBegin error : " + th);
        }
    }

    public static void fragmentSessionResumeEnd(String str, String str2) {
        C6289g fragmentTrace;
        try {
            if (C6638h.m8963w().m9037ai() && (fragmentTrace = getFragmentTrace(str)) != null) {
                fragmentTrace.f15728c.m10593a(str, str2);
                C6293k mo10541a = fragmentTrace.mo10541a();
                if (custPageName != null) {
                    mo10541a.f15745e = custPageName;
                    custPageName = null;
                }
                if (mo10541a != null) {
                    C6275g c6275g = new C6275g(0, str, mo10541a);
                    if (!TextUtils.isEmpty(str)) {
                        storeFragmentPageData.put(str, c6275g);
                    }
                    if (c6275g.m10686j()) {
                        return;
                    }
                    C6396h.m10131k("add data in harvest data ");
                    HarvestData.getPageDatas().mo10631a((HarvestableArray) c6275g);
                    C6277i.m10677a(c6275g, C6255f.f15554c.m10724a());
                }
            }
        } catch (Throwable th) {
            C6396h.m10131k("fragmentSessionResumeEnd error : " + th);
        }
    }

    public void fragmentSessionPause(String str, Object obj) {
        try {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10122a("fragmentSessionPause fragmentName:" + str);
            if (!TextUtils.isEmpty(str) && Harvest.isUser_action_enabled()) {
                Harvest.currentActivityName = str;
                ConcurrentHashMap<String, C6501g> concurrentHashMap = this.fragmentPageSpans;
                C6501g c6501g = concurrentHashMap.get(str + obj.hashCode());
                if (c6501g == null) {
                    return;
                }
                c6501g.m9732b(System.currentTimeMillis());
                NBSApplicationStateMonitor.getPageSpanStack().add(c6501g);
                ConcurrentHashMap<String, C6501g> concurrentHashMap2 = this.fragmentPageSpans;
                concurrentHashMap2.remove(str + obj.hashCode());
            }
        } catch (Throwable th) {
            C6396h.m10131k("NBSFragmentSession  fragmentSessionStopped() has an error : " + th);
        }
    }

    public static NBSFragmentSession getInstance() {
        if (instance == null) {
            instance = new NBSFragmentSession();
        }
        return instance;
    }

    public static void setUserVisibleHint(boolean z, String str) {
        try {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10122a("isVisible:" + z + ", fragmentInstanceName:" + str);
            if (C6638h.m8963w().m9037ai() && z && storeFragmentPageData.get(str) != null) {
                if (TextUtils.isEmpty(beforeFragmentInstanceName) || !beforeFragmentInstanceName.equals(str)) {
                    beforeFragmentInstanceName = str;
                    C6275g c6275g = new C6275g(storeFragmentPageData.get(str));
                    c6275g.m10693c().m10721b(str);
                    C6275g.f15646a = c6275g.m10693c();
                    Harvest.getInstance().getHarvestData();
                    HarvestData.getPageDatas().mo10631a((HarvestableArray) c6275g);
                }
            }
        } catch (Throwable th) {
            C6638h.f17124y.mo10121a("setUserVisibleHint error", th);
        }
    }
}
