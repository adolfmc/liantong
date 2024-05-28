package com.networkbench.agent.impl.background;

import com.networkbench.agent.impl.crash.C6317b;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.p241b.C6229b;
import com.networkbench.agent.impl.p243c.p244a.C6255f;
import com.networkbench.agent.impl.p243c.p248e.C6287e;
import com.networkbench.agent.impl.p243c.p248e.EnumC6288f;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p267m.C6501g;
import com.networkbench.agent.impl.plugin.EnumC6558e;
import com.networkbench.agent.impl.plugin.p274e.C6564f;
import com.networkbench.agent.impl.plugin.p274e.C6565g;
import com.networkbench.agent.impl.util.C6638h;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSApplicationStateMonitor implements Runnable {
    public static final int ALTERNATEPERIOD = 30000;

    /* renamed from: e */
    private static final String f15478e = "InversionScreen#";

    /* renamed from: k */
    private static NBSApplicationStateMonitor f15479k;

    /* renamed from: b */
    private long f15481b;

    /* renamed from: c */
    private long f15482c;

    /* renamed from: d */
    private final Object f15483d;

    /* renamed from: f */
    private final int f15484f;

    /* renamed from: g */
    private final ArrayList<InterfaceC6247b> f15485g;

    /* renamed from: h */
    private boolean f15486h;

    /* renamed from: i */
    private final Object f15487i;

    /* renamed from: j */
    private C6501g f15488j;

    /* renamed from: a */
    private static final InterfaceC6393e f15477a = C6394f.m10150a();

    /* renamed from: l */
    private static Collection<C6501g> f15480l = new ConcurrentLinkedQueue();

    @Deprecated
    /* renamed from: a */
    void m10881a() {
    }

    private NBSApplicationStateMonitor() {
        this(5, 5, TimeUnit.SECONDS, 30000);
    }

    NBSApplicationStateMonitor(int i, int i2, TimeUnit timeUnit, int i3) {
        this.f15481b = 0L;
        this.f15482c = 0L;
        this.f15483d = new Object();
        this.f15485g = new ArrayList<>();
        this.f15486h = true;
        this.f15487i = new Object();
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new ThreadFactory() { // from class: com.networkbench.agent.impl.background.NBSApplicationStateMonitor.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "[NBSAgent] App State Monitor");
            }
        });
        this.f15484f = i3;
        scheduledThreadPoolExecutor.scheduleAtFixedRate(this, i, i2, timeUnit);
    }

    public static NBSApplicationStateMonitor getInstance() {
        if (f15479k == null) {
            f15479k = new NBSApplicationStateMonitor();
        }
        return f15479k;
    }

    public void addApplicationStateListener(InterfaceC6247b interfaceC6247b) {
        synchronized (this.f15485g) {
            this.f15485g.add(interfaceC6247b);
        }
    }

    public void removeApplicationStateListener(InterfaceC6247b interfaceC6247b) {
        synchronized (this.f15485g) {
            this.f15485g.remove(interfaceC6247b);
        }
    }

    public static Collection<C6501g> getPageSpanStack() {
        return f15480l;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.f15487i) {
            if (getSnoozeTime() >= this.f15484f && this.f15486h) {
                notifyApplicationInBackground();
                this.f15486h = false;
            }
        }
    }

    public void activityStopped(String str) {
        synchronized (this.f15487i) {
            synchronized (this.f15483d) {
                this.f15481b--;
                if (this.f15481b == 0) {
                    if (C6638h.m8963w().m9061Z()) {
                        C6229b.m10939a().mo10919e();
                    }
                    C6638h.f17113m.set(EnumC6288f.BACKGROUND.m10597a());
                    C6287e.f15696u = System.currentTimeMillis();
                    NBSAppInstrumentation.isAppInBackground = true;
                    Harvest.addActionAndInteraction("ApplicationInBackground", null, null);
                    executePluginLogicOnBackground();
                    this.f15482c = System.currentTimeMillis();
                }
                if (this.f15481b < 0) {
                    this.f15481b = 0L;
                }
            }
        }
    }

    private void executePluginLogicOnBackground() {
        if (C6565g.f16792d) {
            C6564f c6564f = new C6564f(EnumC6558e.on_background);
            C6565g.m9380a(c6564f);
            c6564f.m9372d();
        }
    }

    private void executePluginLogicOnForeground() {
        if (C6565g.f16793e) {
            C6564f c6564f = new C6564f(EnumC6558e.on_background);
            C6565g.m9380a(c6564f);
            c6564f.m9372d();
        }
    }

    private void setFeatureFunction() {
        if (C6638h.m8963w().m9037ai()) {
            boolean m10449b = C6317b.m10449b(C6638h.m8963w().m9076K());
            if (C6638h.m8963w().m8965u() == m10449b) {
                C6255f.m10807a("ApplicationInForeground", "0", "", -1);
            } else {
                String str = m10449b ? "vertical" : "horizontal";
                C6255f.m10807a("InversionScreen#" + str, "0", "", -1);
            }
            C6638h.m8963w().m8994i(C6317b.m10449b(C6638h.m8963w().m9076K()));
        }
    }

    public void activityStarted(String str) {
        NBSAppInstrumentation.activityStartBeginIns(str);
        Harvest.currentActivityName = str;
        C6255f.f15552a = str;
        addPageSpanStart(str);
        synchronized (this.f15487i) {
            synchronized (this.f15483d) {
                this.f15481b++;
                if (this.f15481b == 1) {
                    this.f15482c = 0L;
                    Harvest.addActionAndInteraction("ApplicationInForeground", null, null);
                    C6287e.f15696u = 0L;
                    setFeatureFunction();
                    executePluginLogicOnForeground();
                }
            }
            if (!this.f15486h) {
                NBSAppInstrumentation.appStateTimeInfo.f15714t = true;
                notifyApplicationInForeground();
                this.f15486h = true;
            }
        }
    }

    private synchronized void addPageSpanStart(String str) {
        if (Harvest.isUser_action_enabled()) {
            C6396h.m10130l("useraction  addPageSpanStart gather begin!!");
            if (this.f15488j != null) {
                this.f15488j.m9732b(System.currentTimeMillis());
                Harvest.mSessionInfo.m9726a(new C6501g(this.f15488j));
                addFragmentInfo();
                this.f15488j.m9730d();
            } else {
                this.f15488j = new C6501g();
            }
            this.f15488j.m9734a(str);
            this.f15488j.m9735a(System.currentTimeMillis());
        }
    }

    private void notifyApplicationInBackground() {
        ArrayList arrayList;
        addPageSpanStop();
        synchronized (this.f15485g) {
            arrayList = new ArrayList(this.f15485g);
        }
        C6246a c6246a = new C6246a(this);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((InterfaceC6247b) it.next()).mo9166b(c6246a);
        }
    }

    private synchronized void addPageSpanStop() {
        try {
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = f15477a;
            interfaceC6393e.mo10116d("addPageSpanStop error!" + e.getMessage());
        }
        if (Harvest.isUser_action_enabled()) {
            C6396h.m10130l("Useraction addPageSpanStop gather  begin !!");
            if (this.f15488j != null) {
                this.f15488j.m9732b(System.currentTimeMillis() - 30000);
                Harvest.mSessionInfo.m9726a(this.f15488j);
                addFragmentInfo();
                this.f15488j = null;
            }
        }
    }

    private void notifyApplicationInForeground() {
        ArrayList arrayList;
        synchronized (this.f15485g) {
            arrayList = new ArrayList(this.f15485g);
        }
        C6246a c6246a = new C6246a(this);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((InterfaceC6247b) it.next()).mo9174a(c6246a);
        }
    }

    private long getSnoozeTime() {
        synchronized (this.f15487i) {
            synchronized (this.f15483d) {
                if (this.f15482c == 0) {
                    return 0L;
                }
                return System.currentTimeMillis() - this.f15482c;
            }
        }
    }

    private void addFragmentInfo() {
        Collection<C6501g> collection = f15480l;
        if (collection == null || collection.isEmpty()) {
            return;
        }
        for (C6501g c6501g : f15480l) {
            Harvest.mSessionInfo.m9726a(c6501g);
        }
        f15480l.clear();
        NBSFragmentSession.getInstance().getFragmentPageSpans().clear();
    }
}
