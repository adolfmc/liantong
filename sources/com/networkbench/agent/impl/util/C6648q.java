package com.networkbench.agent.impl.util;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.ActionData;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HarvestAdapter;
import com.networkbench.agent.impl.instrumentation.NBSJavaScriptBridge;
import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.p241b.C6239i;
import com.networkbench.agent.impl.p241b.C6241k;
import com.networkbench.agent.impl.p243c.C6309j;
import com.networkbench.agent.impl.p243c.p246c.C6265b;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.C6422g;
import com.networkbench.agent.impl.p255g.p257b.C6410a;
import com.networkbench.agent.impl.p255g.p257b.C6412c;
import com.networkbench.agent.impl.p268n.AbstractC6527l;
import com.networkbench.agent.impl.p268n.C6529m;
import com.networkbench.agent.impl.plugin.AbstractC6590h;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.util.q */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6648q extends HarvestAdapter {

    /* renamed from: f */
    private static final long f17236f = 1000;

    /* renamed from: l */
    private static Future<?> f17242l;

    /* renamed from: g */
    private static final ScheduledExecutorService f17237g = Executors.newSingleThreadScheduledExecutor();

    /* renamed from: h */
    private static final ConcurrentLinkedQueue<Object> f17238h = new ConcurrentLinkedQueue<>();

    /* renamed from: a */
    public static final ConcurrentHashMap<String, C6309j> f17231a = new ConcurrentHashMap<>();

    /* renamed from: b */
    public static final ConcurrentHashMap<List<String>, String> f17232b = new ConcurrentHashMap<>();

    /* renamed from: c */
    public static final ConcurrentHashMap<String, Integer> f17233c = new ConcurrentHashMap<>();

    /* renamed from: d */
    public static final List<String> f17234d = Collections.synchronizedList(new ArrayList());

    /* renamed from: e */
    public static final ConcurrentLinkedQueue<Object> f17235e = new ConcurrentLinkedQueue<>();

    /* renamed from: i */
    private static ConcurrentLinkedQueue<ActionData> f17239i = new ConcurrentLinkedQueue<>();

    /* renamed from: j */
    private static InterfaceC6393e f17240j = C6394f.m10150a();

    /* renamed from: k */
    private static final Runnable f17241k = new Runnable() { // from class: com.networkbench.agent.impl.util.q.1
        @Override // java.lang.Runnable
        public void run() {
            C6648q.m8767j();
            C6648q.m8768i();
        }
    };

    /* renamed from: a */
    public static void m8781a(Object obj) {
        f17238h.add(obj);
    }

    /* renamed from: a */
    public static void m8782a(C6309j c6309j) {
        f17231a.put(c6309j.m10493c(), c6309j);
    }

    /* renamed from: a */
    public static void m8783a() {
        C6309j value;
        for (Map.Entry<String, C6309j> entry : f17231a.entrySet()) {
            if (entry != null && (value = entry.getValue()) != null && !value.m10499a() && !value.m10496b()) {
                InterfaceC6393e interfaceC6393e = f17240j;
                interfaceC6393e.mo10122a("connect info add in socketData:" + value.toString());
                Harvest.addSocketDatasInfo(value);
            }
        }
    }

    /* renamed from: a */
    public static int m8780a(String str) {
        if (TextUtils.isEmpty(str) || f17231a.get(str) == null || f17231a.get(str).m9211l()) {
            return -1;
        }
        f17231a.get(str).m9220c(true);
        if (f17231a.get(str) == null) {
            return -1;
        }
        return f17231a.get(str).m10489f();
    }

    /* renamed from: b */
    public static int m8778b(String str) {
        if (TextUtils.isEmpty(str) || f17231a.get(str) == null || f17231a.get(str) == null || f17231a.get(str).m10489f() == -1) {
            return 0;
        }
        return f17231a.get(str).m10489f();
    }

    /* renamed from: c */
    public static String m8776c(String str) {
        return (TextUtils.isEmpty(str) || f17231a.get(str) == null || f17231a.get(str) == null) ? "" : f17231a.get(str).m10491d();
    }

    /* renamed from: d */
    public static int m8774d(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        for (Map.Entry<String, C6309j> entry : f17231a.entrySet()) {
            if (!entry.getValue().m9211l() && str.contains(entry.getKey().toString())) {
                entry.getValue().m9220c(true);
                return entry.getValue().m10489f();
            }
        }
        return -1;
    }

    /* renamed from: e */
    public static String m8772e(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        for (Map.Entry<String, C6309j> entry : f17231a.entrySet()) {
            if (str.contains(entry.getKey().toString())) {
                return entry.getValue().m10491d();
            }
        }
        return "";
    }

    /* renamed from: b */
    public static void m8779b() {
        try {
            f17237g.submit(f17241k).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: c */
    public static void m8777c() {
        if (f17242l != null) {
            return;
        }
        f17242l = f17237g.scheduleAtFixedRate(f17241k, 0L, 1000L, TimeUnit.MILLISECONDS);
    }

    /* renamed from: d */
    public static void m8775d() {
        Future<?> future = f17242l;
        if (future == null) {
            return;
        }
        future.cancel(true);
        f17242l = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public static void m8768i() {
        ArrayList arrayList = new ArrayList();
        Iterator<Object> it = f17235e.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof NBSTransactionState) {
                NBSTransactionState nBSTransactionState = (NBSTransactionState) next;
                if (System.currentTimeMillis() - nBSTransactionState.getEndTime() > 10000) {
                    arrayList.add(next);
                    if (nBSTransactionState.end() == null) {
                        return;
                    }
                    m8781a(new C6412c(nBSTransactionState));
                } else {
                    continue;
                }
            } else {
                f17240j.mo10116d("error queueOkNetwork object is not NBSTransactionState");
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        f17235e.removeAll(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public static void m8767j() {
        if (f17238h.size() == 0) {
            return;
        }
        C6422g.m10011a(false);
        while (!f17238h.isEmpty()) {
            try {
                Object remove = f17238h.remove();
                if (remove instanceof C6410a) {
                    C6422g.m10013a((C6410a) remove);
                } else if (remove instanceof C6412c) {
                    C6422g.m10012a((C6412c) remove);
                } else if (remove instanceof NBSJavaScriptBridge.NBSWebViewResult) {
                    NBSJavaScriptBridge.NBSWebViewResult nBSWebViewResult = (NBSJavaScriptBridge.NBSWebViewResult) remove;
                    if (nBSWebViewResult.resultType.equals("ajax_metric")) {
                        C6529m.m9517a(nBSWebViewResult.resultData);
                    } else if (nBSWebViewResult.resultType.equals("pageInfo")) {
                        C6529m.m9509c(nBSWebViewResult.resultData);
                    } else if (nBSWebViewResult.resultType.equals("browserData")) {
                        C6529m.m9506f(nBSWebViewResult.resultData);
                    }
                } else if (remove instanceof AbstractC6527l) {
                    ((AbstractC6527l) remove).m9521c();
                } else if (remove instanceof C6239i) {
                    C6241k.m10902a().m10901a((C6239i) remove);
                } else if (remove instanceof AbstractC6590h) {
                    Harvest.getInstance().getHarvestData().getPluginData().m10744a(new C6265b((AbstractC6590h) remove));
                } else if (remove instanceof ActionData) {
                    f17239i.add((ActionData) remove);
                }
            } catch (Exception e) {
                f17240j.mo10121a("error happened in TaskQueue dequeue e:", e);
            }
        }
        C6422g.m10009c();
        C6422g.m10011a(true);
        while (!f17239i.isEmpty()) {
            Harvest.addHttpTransactionForScene(f17239i.remove());
        }
    }

    /* renamed from: e */
    public static int m8773e() {
        return f17238h.size();
    }

    /* renamed from: f */
    public static void m8771f() {
        f17238h.clear();
    }
}
