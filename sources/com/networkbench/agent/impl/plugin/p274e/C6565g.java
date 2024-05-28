package com.networkbench.agent.impl.plugin.p274e;

import android.text.TextUtils;
import com.networkbench.agent.impl.p243c.p246c.C6264a;
import com.networkbench.agent.impl.p243c.p246c.C6267d;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.plugin.AbstractC6590h;
import com.networkbench.agent.impl.plugin.C6535b;
import com.networkbench.agent.impl.plugin.C6593j;
import com.networkbench.agent.impl.plugin.C6594k;
import com.networkbench.agent.impl.plugin.C6595l;
import com.networkbench.agent.impl.plugin.p270a.C6532b;
import com.networkbench.agent.impl.plugin.p271b.C6538a;
import com.networkbench.agent.impl.plugin.p272c.C6544b;
import com.networkbench.agent.impl.plugin.p273d.C6556c;
import com.networkbench.agent.impl.plugin.p275f.C6568a;
import com.networkbench.agent.impl.util.C6638h;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.e.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6565g {

    /* renamed from: a */
    public static List<C6267d> f16789a = new CopyOnWriteArrayList();

    /* renamed from: b */
    public static boolean f16790b = false;

    /* renamed from: c */
    public static boolean f16791c = false;

    /* renamed from: d */
    public static boolean f16792d = false;

    /* renamed from: e */
    public static boolean f16793e = false;

    /* renamed from: f */
    public static boolean f16794f = false;

    /* renamed from: g */
    public static boolean f16795g = false;

    /* renamed from: h */
    public static boolean f16796h = false;

    /* renamed from: i */
    public static final Map<String, Boolean> f16797i = new ConcurrentHashMap();

    /* renamed from: a */
    public static void m9383a(C6264a c6264a) {
        if (C6638h.m8963w().m9039ag()) {
            Iterator<C6267d> it = c6264a.f15609b.iterator();
            while (it.hasNext()) {
                m9382a(it.next());
            }
            return;
        }
        C6396h.m10137e("SceneRegisterObserver   plugin switch  is " + C6638h.m8963w().m9039ag());
    }

    /* renamed from: a */
    public static synchronized void m9382a(C6267d c6267d) {
        synchronized (C6565g.class) {
            String str = c6267d.f15614b;
            char c = 65535;
            switch (str.hashCode()) {
                case -1432646770:
                    if (str.equals("on_background")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1325812763:
                    if (str.equals("on_task")) {
                        c = 6;
                        break;
                    }
                    break;
                case 554628996:
                    if (str.equals("after_crash")) {
                        c = 5;
                        break;
                    }
                    break;
                case 556492261:
                    if (str.equals("after_error")) {
                        c = 1;
                        break;
                    }
                    break;
                case 1019568386:
                    if (str.equals("after_anr")) {
                        c = 4;
                        break;
                    }
                    break;
                case 1019580602:
                    if (str.equals("after_net")) {
                        c = 0;
                        break;
                    }
                    break;
                case 1884004259:
                    if (str.equals("on_foreground")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    f16790b = true;
                    break;
                case 1:
                    f16791c = true;
                    break;
                case 2:
                    f16792d = true;
                    break;
                case 3:
                    f16793e = true;
                    break;
                case 4:
                    f16794f = true;
                    break;
                case 5:
                    f16795g = true;
                    break;
                case 6:
                    f16796h = true;
                    break;
            }
            f16789a.add(c6267d);
        }
    }

    /* renamed from: a */
    private static boolean m9381a(C6267d c6267d, AbstractC6590h abstractC6590h) {
        if (((abstractC6590h instanceof C6535b) || (abstractC6590h instanceof C6568a) || (abstractC6590h instanceof C6538a)) && c6267d.m10736a()) {
            Map<String, Boolean> map = f16797i;
            if (map.containsKey(c6267d.f15615c + c6267d.f15614b + abstractC6590h.f16888g)) {
                return false;
            }
        }
        return c6267d.m10736a() || !c6267d.m10733b();
    }

    /* renamed from: a */
    public static synchronized void m9380a(AbstractC6566h abstractC6566h) {
        synchronized (C6565g.class) {
            ArrayList arrayList = new ArrayList();
            for (C6267d c6267d : f16789a) {
                if (c6267d.f15614b.equals(abstractC6566h.f16801e.name())) {
                    C6396h.m10137e("registerObserver : " + c6267d.toString());
                    AbstractC6590h abstractC6590h = null;
                    String str = c6267d.f15615c;
                    char c = 65535;
                    switch (str.hashCode()) {
                        case -1546464044:
                            if (str.equals("tailLog")) {
                                c = 2;
                                break;
                            }
                            break;
                        case -1483547405:
                            if (str.equals("tcpPing")) {
                                c = 1;
                                break;
                            }
                            break;
                        case -744059407:
                            if (str.equals("allCellInfo")) {
                                c = 6;
                                break;
                            }
                            break;
                        case -9845937:
                            if (str.equals("ICMPPing")) {
                                c = 7;
                                break;
                            }
                            break;
                        case 76683:
                            if (str.equals("MTR")) {
                                c = '\t';
                                break;
                            }
                            break;
                        case 99625:
                            if (str.equals("dns")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 108459:
                            if (str.equals("mtr")) {
                                c = '\b';
                                break;
                            }
                            break;
                        case 1149719823:
                            if (str.equals("requestHead")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 1427818632:
                            if (str.equals("download")) {
                                c = 3;
                                break;
                            }
                            break;
                        case 1438862785:
                            if (str.equals("responseHead")) {
                                c = 5;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            abstractC6590h = new C6535b(abstractC6566h, c6267d);
                            break;
                        case 1:
                            abstractC6590h = new C6568a(abstractC6566h, c6267d);
                            break;
                        case 2:
                            abstractC6590h = new C6595l(abstractC6566h, c6267d);
                            break;
                        case 3:
                            abstractC6590h = new C6538a(abstractC6566h, c6267d);
                            break;
                        case 4:
                            abstractC6590h = new C6593j(abstractC6566h, c6267d);
                            break;
                        case 5:
                            abstractC6590h = new C6594k(abstractC6566h, c6267d);
                            break;
                        case 6:
                            abstractC6590h = new C6532b(abstractC6566h, c6267d);
                            break;
                        case 7:
                            abstractC6590h = new C6556c(abstractC6566h, c6267d);
                            break;
                        case '\b':
                        case '\t':
                            abstractC6590h = new C6544b(abstractC6566h, c6267d);
                            break;
                    }
                    if (abstractC6590h != null) {
                        C6396h.m10137e(" isClientConditionAvalid :" + abstractC6590h.mo9299d() + " ----, getIsUsed() : " + c6267d.m10733b());
                        if (abstractC6590h.mo9299d()) {
                            if (!c6267d.m10736a()) {
                                arrayList.add(c6267d);
                            }
                            if (m9381a(c6267d, abstractC6590h)) {
                                if (!TextUtils.isEmpty(abstractC6590h.f16888g)) {
                                    f16797i.put(c6267d.f15615c + c6267d.f15614b + abstractC6590h.f16888g, true);
                                }
                                abstractC6566h.m9376a(abstractC6590h, Boolean.valueOf(c6267d.m10736a()));
                            }
                            c6267d.m10734a(true);
                        }
                    }
                }
            }
            f16789a.removeAll(arrayList);
        }
    }

    /* renamed from: a */
    public static void m9384a() {
        for (C6267d c6267d : f16789a) {
            if (c6267d != null && c6267d.m10733b()) {
                c6267d.m10734a(false);
            }
        }
        f16797i.clear();
    }

    /* renamed from: b */
    public static void m9379b() {
        f16789a.clear();
    }
}
