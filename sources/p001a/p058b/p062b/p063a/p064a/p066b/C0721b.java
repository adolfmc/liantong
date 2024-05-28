package p001a.p058b.p062b.p063a.p064a.p066b;

import com.baidu.uaq.agent.android.customtransmission.APMUploadConfigure;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import p001a.p058b.p062b.p063a.p064a.p067c.p070c.C0738a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.b.b.a.a.b.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0721b {

    /* renamed from: a */
    public static final InterfaceC3321a f2189a = C0749a.f2299a;

    /* renamed from: b */
    public static final ConcurrentHashMap<String, APMUploadConfigure> f2190b = new ConcurrentHashMap<>();

    /* renamed from: c */
    public static final ConcurrentHashMap<String, ArrayList<C0720a>> f2191c = new ConcurrentHashMap<>();

    /* renamed from: a */
    public static synchronized ArrayList<String> m22319a(String str, Boolean bool) {
        synchronized (C0721b.class) {
            ArrayList<String> arrayList = new ArrayList<>();
            ArrayList arrayList2 = new ArrayList();
            synchronized (f2191c) {
                ArrayList<C0720a> arrayList3 = f2191c.get(str);
                if (arrayList3 != null && arrayList3.size() != 0) {
                    if (bool.booleanValue()) {
                        for (int i = 0; i < arrayList3.size(); i++) {
                            if (arrayList3.get(i).f2188b < 3) {
                                arrayList2.add(arrayList3.get(i));
                                arrayList3.get(i).f2188b++;
                                arrayList.add(arrayList3.get(i).f2187a);
                            }
                        }
                        m22317a(str, arrayList2);
                    } else {
                        Iterator<C0720a> it = arrayList3.iterator();
                        while (it.hasNext()) {
                            arrayList.add(it.next().f2187a);
                        }
                        f2191c.remove(str);
                    }
                    return arrayList;
                }
                return null;
            }
        }
    }

    /* renamed from: a */
    public static synchronized ConcurrentHashMap<String, APMUploadConfigure> m22322a() {
        ConcurrentHashMap<String, APMUploadConfigure> concurrentHashMap;
        synchronized (C0721b.class) {
            concurrentHashMap = f2190b;
        }
        return concurrentHashMap;
    }

    @Deprecated
    /* renamed from: a */
    public static synchronized void m22320a(String str) {
        synchronized (C0721b.class) {
            f2190b.remove(str);
        }
    }

    /* renamed from: a */
    public static synchronized void m22317a(String str, ArrayList<C0720a> arrayList) {
        synchronized (C0721b.class) {
            synchronized (f2191c) {
                if (arrayList != null) {
                    f2191c.put(str, arrayList);
                } else {
                    f2191c.remove(str);
                }
            }
        }
    }

    /* renamed from: b */
    public static synchronized ConcurrentHashMap<String, ArrayList<C0720a>> m22316b() {
        ConcurrentHashMap<String, ArrayList<C0720a>> concurrentHashMap;
        synchronized (C0721b.class) {
            concurrentHashMap = f2191c;
        }
        return concurrentHashMap;
    }

    /* renamed from: b */
    public static synchronized void m22315b(String str, Boolean bool) {
        synchronized (C0721b.class) {
            ArrayList<C0720a> arrayList = f2191c.get(str);
            if (bool.booleanValue()) {
                ArrayList arrayList2 = new ArrayList();
                Iterator<C0720a> it = arrayList.iterator();
                while (it.hasNext()) {
                    C0720a next = it.next();
                    if (next.f2188b == 0) {
                        arrayList2.add(next);
                    }
                }
                m22317a(str, arrayList2);
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m22321a(APMUploadConfigure aPMUploadConfigure) {
        synchronized (C0721b.class) {
            if (C0738a.f2254b.f2258f < 1) {
                f2189a.error("Agent has shutdown when add Upload Configure");
            } else {
                f2190b.put(aPMUploadConfigure.getUploadName(), aPMUploadConfigure);
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m22318a(String str, String str2) {
        synchronized (C0721b.class) {
            if (C0738a.f2254b.f2258f < 1) {
                f2189a.error("Agent has shutdown when add Upload Block");
            } else {
                synchronized (f2191c) {
                    ArrayList<C0720a> arrayList = f2191c.get(str);
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    }
                    arrayList.add(new C0720a(str2));
                    f2191c.put(str, arrayList);
                }
            }
        }
    }
}
