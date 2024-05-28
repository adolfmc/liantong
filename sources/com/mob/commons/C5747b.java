package com.mob.commons;

import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.mob.MobSDK;
import com.mob.commons.p229a.AbstractRunnableC5704c;
import com.mob.commons.p229a.C5701a;
import com.mob.commons.p229a.C5702b;
import com.mob.commons.p229a.C5710d;
import com.mob.commons.p229a.C5713e;
import com.mob.commons.p229a.C5717f;
import com.mob.commons.p229a.C5718g;
import com.mob.commons.p229a.C5720h;
import com.mob.commons.p229a.C5724i;
import com.mob.commons.p229a.C5726j;
import com.mob.commons.p229a.C5728k;
import com.mob.commons.p229a.C5732m;
import com.mob.commons.p229a.C5735n;
import com.mob.commons.p229a.C5738o;
import com.mob.commons.p231cc.C5791a;
import com.mob.mgs.impl.C5965b;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.NetCommunicator;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.p237a.C6032d;
import com.mob.tools.p238b.C6120a;
import com.mob.tools.utils.AbstractC6201c;
import com.mob.tools.utils.AbstractC6218i;
import com.mob.tools.utils.AbstractRunnableC6217h;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.C6212e;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.HashonHelper;
import com.mob.tools.utils.ResHelper;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.commons.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5747b {

    /* renamed from: c */
    private static AtomicBoolean f14168c = new AtomicBoolean(false);

    /* renamed from: d */
    private static AtomicBoolean f14169d = new AtomicBoolean(false);

    /* renamed from: e */
    private static AtomicBoolean f14170e = new AtomicBoolean(false);

    /* renamed from: f */
    private static final AtomicBoolean f14171f = new AtomicBoolean(false);

    /* renamed from: g */
    private static volatile HashMap<String, Object> f14172g = null;

    /* renamed from: a */
    public static volatile HashSet<Class<? extends AbstractRunnableC5704c>> f14166a = new HashSet<>();

    /* renamed from: h */
    private static ConcurrentHashMap<String, Object> f14173h = new ConcurrentHashMap<>();

    /* renamed from: i */
    private static ConcurrentHashMap<String, Object> f14174i = new ConcurrentHashMap<>();

    /* renamed from: j */
    private static CountDownLatch f14175j = new CountDownLatch(1);

    /* renamed from: k */
    private static CountDownLatch f14176k = new CountDownLatch(1);

    /* renamed from: b */
    public static volatile boolean f14167b = false;

    /* renamed from: l */
    private static volatile boolean f14177l = false;

    /* renamed from: m */
    private static final AtomicBoolean f14178m = new AtomicBoolean(false);

    /* renamed from: n */
    private static volatile boolean f14179n = false;

    /* renamed from: a */
    public static void m12576a(CountDownLatch countDownLatch) {
        m12564b(countDownLatch);
    }

    /* renamed from: a */
    public static <T> T m12583a(String str, T t) {
        if (TextUtils.isEmpty(str) || f14172g == null) {
            return t;
        }
        if (m12566b(f14172g)) {
            f14172g.clear();
            f14172g = new HashMap<>();
            m12561c(2);
        }
        return (T) ResHelper.forceCast(f14172g.get(str), t);
    }

    /* renamed from: b */
    public static <T> T m12567b(String str, T t) {
        if (TextUtils.isEmpty(str)) {
            return t;
        }
        if (f14172g != null) {
            return (T) m12579a(f14172g, str, t);
        }
        return (T) m12579a(HashonHelper.fromJson(C5741aa.m12650a().m12629c()), str, t);
    }

    /* renamed from: a */
    private static <T> T m12579a(HashMap<String, Object> hashMap, String str, T t) {
        return (TextUtils.isEmpty(str) || m12566b(hashMap) || !m12581a(hashMap)) ? t : (T) ResHelper.forceCast(hashMap.get(str), t);
    }

    /* renamed from: a */
    public static <T> T m12582a(String str, T t, long j) {
        try {
            if ((f14172g == null || f14172g.isEmpty()) && f14175j.getCount() > 0) {
                if (j > 0) {
                    f14175j.await(j, TimeUnit.MILLISECONDS);
                } else {
                    f14175j.await();
                }
            }
            if (!m12556d(str) && f14176k.getCount() > 0) {
                if (j > 0) {
                    f14176k.await(j, TimeUnit.MILLISECONDS);
                } else {
                    f14176k.await();
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
        return (T) m12583a(str, t);
    }

    /* renamed from: a */
    private static boolean m12581a(HashMap<String, Object> hashMap) {
        return hashMap == null || ((Integer) ResHelper.forceCast(hashMap.get(C5857m.m12226a("002g=cb")), 0)).intValue() == 0;
    }

    /* renamed from: a */
    public static boolean m12589a() {
        return ((Integer) m12583a(C5857m.m12226a("002gWcb"), 0)).intValue() == 0;
    }

    /* renamed from: b */
    public static boolean m12572b() {
        return ((Integer) m12583a(C5857m.m12226a("004aVcb6cc"), 0)).intValue() == 1;
    }

    /* renamed from: c */
    public static boolean m12562c() {
        return (((Integer) m12583a(C5857m.m12226a("002c;bh"), 0)).intValue() == 1) || C5879w.m12167a();
    }

    /* renamed from: d */
    public static boolean m12557d() {
        return m12562c();
    }

    /* renamed from: a */
    public static boolean m12585a(String str) {
        return !TextUtils.isEmpty(str) && m12589a() && m12572b() && ((Integer) m12583a(str, 0)).intValue() != 0;
    }

    /* renamed from: e */
    public static ConcurrentHashMap<String, Object> m12554e() {
        return f14173h;
    }

    /* renamed from: f */
    public static ConcurrentHashMap<String, Object> m12552f() {
        return f14174i;
    }

    /* renamed from: g */
    public static ArrayList<String> m12550g() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(C5857m.m12226a("004]chchcgdi"));
        arrayList.add(C5857m.m12226a("005Adgchchcgdi"));
        arrayList.add(C5857m.m12226a("005ed=bbYde"));
        arrayList.add(C5857m.m12226a("009.cdbhRd$bcbe4dca@bi"));
        arrayList.add(C5857m.m12226a("010 bfbfbf1a5bebhehcbCcc"));
        return (ArrayList) m12583a(C5857m.m12226a("0048ddbgdfVa"), arrayList);
    }

    /* renamed from: h */
    public static void m12549h() {
        if (m12589a()) {
            m12561c(3);
        }
    }

    /* renamed from: b */
    private static void m12564b(CountDownLatch countDownLatch) {
        HashMap fromJson = HashonHelper.fromJson(C5741aa.m12650a().m12629c());
        if (m12566b(fromJson)) {
            C5741aa.m12650a().m12628c((String) null);
            fromJson = null;
        }
        if (m12589a()) {
            m12577a((HashMap<String, Object>) fromJson, false);
            if (fromJson == null || fromJson.isEmpty()) {
                MobLog.getInstance().m11342d("g ch: n", new Object[0]);
                m12561c(1);
                return;
            }
            MobLog.getInstance().m11342d("g ch: y", new Object[0]);
            boolean z = System.currentTimeMillis() - C5741aa.m12650a().m12634b(C5741aa.f14146m, 0L) < 2000;
            MobLog.getInstance().m11342d("g ch fre: " + z, new Object[0]);
            if (!z) {
                m12561c(2);
            }
            if (countDownLatch != null) {
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    MobLog.getInstance().m11342d("g dhs_w cdl: " + countDownLatch, new Object[0]);
                    countDownLatch.await(3500L, TimeUnit.MILLISECONDS);
                    MobLog.getInstance().m11342d("g dhs_w end, dur: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
                } catch (Throwable th) {
                    MobLog.getInstance().m11341d(th);
                }
            }
            m12574a(true, false, z, 2);
        }
    }

    /* renamed from: a */
    private static void m12577a(HashMap<String, Object> hashMap, boolean z) {
        f14172g = new HashMap<>();
        if (hashMap != null) {
            f14172g.putAll(hashMap);
        }
        try {
            if (z) {
                f14175j.countDown();
                f14176k.countDown();
            } else {
                f14175j.countDown();
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static void m12558c(boolean z) {
        if (m12572b()) {
            MobLog.getInstance().m11342d("b db st", new Object[0]);
            C5831e.m12319a((MobProduct) null);
            m12555d(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.mob.commons.b$2] */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.mob.commons.b$1] */
    /* renamed from: b */
    public static void m12571b(int i) {
        MobLog.getInstance().m11342d("b ob st", new Object[0]);
        if (!m12589a() || !m12572b()) {
            if (i == 3 || C5879w.m12162b()) {
                m12540q();
            }
            m12541p();
            return;
        }
        final String str = (String) m12583a(C5857m.m12226a("003Hcd!ca"), (Object) null);
        if (TextUtils.isEmpty(str)) {
            if (i == 3 || C5879w.m12162b()) {
                m12540q();
            }
            m12541p();
        } else if (i == 3 || f14168c.compareAndSet(false, true)) {
            new AbstractC6218i(C5857m.m12226a("003Sdigifi") + i) { // from class: com.mob.commons.b.1
                @Override // com.mob.tools.utils.AbstractC6218i
                /* renamed from: a */
                public void mo10997a() {
                    C5859o.m12224a(C5859o.m12223a(C5859o.f14438d), false, new InterfaceC5858n() { // from class: com.mob.commons.b.1.1
                        @Override // com.mob.commons.InterfaceC5858n
                        /* renamed from: a */
                        public boolean mo11219a(FileLocker fileLocker) {
                            synchronized (C5747b.f14173h) {
                                C5747b.m12553e(str);
                            }
                            return false;
                        }
                    });
                }
            }.start();
        }
        if (!m12589a() || !m12572b()) {
            m12539r();
            m12544m();
            return;
        }
        final String str2 = (String) m12583a("sbr", (Object) null);
        if (TextUtils.isEmpty(str2)) {
            m12544m();
            m12539r();
        } else if (i == 3 || f14169d.compareAndSet(false, true)) {
            new AbstractC6218i("DS-" + i) { // from class: com.mob.commons.b.2
                @Override // com.mob.tools.utils.AbstractC6218i
                /* renamed from: a */
                public void mo10997a() {
                    C5859o.m12224a(C5859o.m12223a(C5859o.f14439e), false, new InterfaceC5858n() { // from class: com.mob.commons.b.2.1
                        @Override // com.mob.commons.InterfaceC5858n
                        /* renamed from: a */
                        public boolean mo11219a(FileLocker fileLocker) {
                            synchronized (C5747b.f14174i) {
                                C5747b.m12551f(str2);
                            }
                            return false;
                        }
                    });
                }
            }.start();
        }
    }

    /* renamed from: m */
    private static void m12544m() {
        m12573a(C5857m.m12226a("003+dfdcBe"), C5857m.m12226a("007GdbbhdfQe+dcbhVa"));
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [com.mob.commons.b$3] */
    /* renamed from: a */
    private static void m12574a(final boolean z, final boolean z2, final boolean z3, final int i) {
        new AbstractC6218i("PY-B" + i) { // from class: com.mob.commons.b.3
            @Override // com.mob.tools.utils.AbstractC6218i
            /* renamed from: a */
            public void mo10997a() {
                NLog mobLog = MobLog.getInstance();
                mobLog.m11342d("b enter:" + Process.myPid() + ", lbms: " + C5747b.f14177l + ", fc" + z + ", ol: " + z2 + ", gf: " + z3 + ", in: " + i, new Object[0]);
                if (!C5747b.f14177l) {
                    NLog mobLog2 = MobLog.getInstance();
                    mobLog2.m11342d("b lk st: " + Process.myPid(), new Object[0]);
                    C5859o.m12225a(C5859o.m12223a(C5859o.f14440f), new InterfaceC5858n() { // from class: com.mob.commons.b.3.1
                        @Override // com.mob.commons.InterfaceC5858n
                        /* renamed from: a */
                        public boolean mo11219a(FileLocker fileLocker) {
                            boolean unused = C5747b.f14177l = true;
                            NLog mobLog3 = MobLog.getInstance();
                            mobLog3.m11342d("b lk: " + Process.myPid() + ", proc st", new Object[0]);
                            long currentTimeMillis = System.currentTimeMillis();
                            C5747b.m12558c(z2);
                            if (!z || z3) {
                                C5747b.m12571b(i);
                            }
                            NLog mobLog4 = MobLog.getInstance();
                            mobLog4.m11342d("b lk: " + Process.myPid() + ", proc ed, dur: " + (System.currentTimeMillis() - currentTimeMillis) + ", release: n", new Object[0]);
                            Looper.prepare();
                            Looper.loop();
                            return true;
                        }
                    });
                    return;
                }
                NLog mobLog3 = MobLog.getInstance();
                mobLog3.m11342d("b lked already: " + Process.myPid(), new Object[0]);
                C5747b.m12558c(z2);
                if (!z || z3) {
                    C5747b.m12571b(i);
                }
            }
        }.start();
    }

    /* renamed from: d */
    private static synchronized void m12555d(boolean z) {
        synchronized (C5747b.class) {
            HashSet<Class<? extends AbstractRunnableC5704c>> m12542o = m12542o();
            Iterator<Class<? extends AbstractRunnableC5704c>> it = m12542o.iterator();
            while (it.hasNext()) {
                Class<? extends AbstractRunnableC5704c> next = it.next();
                if (!f14166a.contains(next)) {
                    AbstractRunnableC5704c newInstance = next.newInstance();
                    if (z || m12556d(newInstance.m12756d())) {
                        if (newInstance.m12753g()) {
                            f14166a.add(next);
                        }
                    }
                }
            }
            m12542o.clear();
            NLog mobLog = MobLog.getInstance();
            mobLog.m11342d("ct(" + z + ") over", new Object[0]);
            m12543n();
        }
    }

    /* renamed from: n */
    private static void m12543n() {
        if (f14171f.compareAndSet(false, true)) {
            boolean m12589a = m12589a();
            boolean m12572b = m12572b();
            if (m12589a && m12572b && ((Integer) m12583a("sps", 0)).intValue() != 0) {
                for (HashMap<String, Object> hashMap : C6212e.m11053a().m11048b().values()) {
                    new C5717f(hashMap).m12725c();
                }
            }
        }
    }

    /* renamed from: d */
    private static boolean m12556d(String str) {
        List list = (List) m12583a(C5857m.m12226a("002ae"), (Object) null);
        return (list == null || list.size() == 0 || !list.contains(str)) ? false : true;
    }

    /* renamed from: o */
    private static HashSet<Class<? extends AbstractRunnableC5704c>> m12542o() {
        HashSet<Class<? extends AbstractRunnableC5704c>> hashSet = new HashSet<>();
        try {
            hashSet.add(C5701a.class);
        } catch (Throwable unused) {
        }
        try {
            hashSet.add(C5702b.class);
        } catch (Throwable unused2) {
        }
        try {
            hashSet.add(C5710d.class);
        } catch (Throwable unused3) {
        }
        try {
            hashSet.add(C5713e.class);
        } catch (Throwable unused4) {
        }
        try {
            hashSet.add(C5728k.class);
        } catch (Throwable unused5) {
        }
        try {
            hashSet.add(C5718g.class);
        } catch (Throwable unused6) {
        }
        try {
            hashSet.add(C5720h.class);
        } catch (Throwable unused7) {
        }
        try {
            hashSet.add(C5724i.class);
        } catch (Throwable unused8) {
        }
        try {
            hashSet.add(C5726j.class);
        } catch (Throwable unused9) {
        }
        try {
            hashSet.add(C5732m.class);
        } catch (Throwable unused10) {
        }
        try {
            hashSet.add(C5735n.class);
        } catch (Throwable unused11) {
        }
        try {
            hashSet.add(C5738o.class);
        } catch (Throwable unused12) {
        }
        return hashSet;
    }

    /* renamed from: c */
    private static void m12561c(int i) {
        if (f14170e.compareAndSet(false, true)) {
            String format = String.format(C5857m.m12226a("005Yehdjfihidf"), Integer.valueOf(i));
            if (i == 2) {
                C5892y.f14525c.execute(m12584a(format, i));
            } else {
                m12584a(format, i).run();
            }
        }
    }

    /* renamed from: a */
    private static AbstractRunnableC6217h m12584a(final String str, final int i) {
        return new AbstractRunnableC6217h() { // from class: com.mob.commons.b.4
            @Override // com.mob.tools.utils.AbstractRunnableC6217h
            /* renamed from: a */
            public void mo10991a() {
                C6120a.f14983b.set(true);
                if (!TextUtils.isEmpty("M-")) {
                    Thread currentThread = Thread.currentThread();
                    currentThread.setName("M-" + str);
                }
                C5747b.m12569b(new AbstractC6201c<HashMap<String, Object>>() { // from class: com.mob.commons.b.4.1
                    @Override // com.mob.tools.utils.AbstractC6201c
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void mo11088a(HashMap<String, Object> hashMap) {
                        try {
                            C5747b.m12565b(hashMap, i);
                        } finally {
                            C5747b.f14170e.set(false);
                        }
                    }
                });
                C6120a.f14983b.set(false);
            }
        };
    }

    /* renamed from: p */
    private static void m12541p() {
        m12573a(C5857m.m12226a("003'dfWaa"), C5857m.m12226a("0092facbdcOj,dbbdbd>aa"), C5857m.m12226a("016!facbdc,ja3cbbdbdLjLbadcdfNjHbdba?a"), C5857m.m12226a("0057fabedi+eDbb"), C5857m.m12226a("012Edbbj[g-fdfefbghfcfgghdefb"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m12565b(HashMap<String, Object> hashMap, int i) {
        if (hashMap == null) {
            HashMap<String, Object> fromJson = HashonHelper.fromJson(C5741aa.m12650a().m12625d());
            if (!m12566b(fromJson)) {
                hashMap = fromJson;
            }
            C5741aa.m12650a().m12622e();
        }
        CountDownLatch countDownLatch = null;
        if (hashMap != null && !hashMap.isEmpty()) {
            countDownLatch = m12559c(hashMap);
        }
        m12577a(hashMap, true);
        C6120a.f14983b.set(false);
        if (!f14179n) {
            m12538s();
        }
        if (countDownLatch == null) {
            countDownLatch = C6032d.m11700a(MobSDK.getContext()).m11702a();
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            NLog mobLog = MobLog.getInstance();
            mobLog.m11342d("ge dhs_w cdl: " + countDownLatch, new Object[0]);
            countDownLatch.await(3500L, TimeUnit.MILLISECONDS);
            NLog mobLog2 = MobLog.getInstance();
            mobLog2.m11342d("ge dhs_w end, dur: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
        m12574a(false, true, true, i);
    }

    /* renamed from: q */
    private static void m12540q() {
        synchronized (C5859o.f14443i) {
            C5843h.m12291a().m12290a(10);
            C5859o.f14443i.notifyAll();
        }
    }

    /* renamed from: r */
    private static void m12539r() {
        synchronized (C5859o.f14444j) {
            C5859o.f14444j.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m12569b(final AbstractC6201c<HashMap<String, Object>> abstractC6201c) {
        C6120a.f14983b.set(true);
        C6152DH.requester(MobSDK.getContext()).getDetailNetworkTypeForStatic().getODH().request(new C6152DH.DHResponder() { // from class: com.mob.commons.b.5
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                C6120a.f14983b.set(true);
                try {
                    HashMap m12570b = C5747b.m12570b(dHResponse);
                    long j = 2;
                    long j2 = 1;
                    while (true) {
                        if (m12570b != null && !m12570b.isEmpty()) {
                            break;
                        }
                        Thread.sleep(1000 * j2);
                        m12570b = C5747b.m12570b(dHResponse);
                        if (m12570b == null || m12570b.isEmpty()) {
                            if (j <= 0) {
                                break;
                            }
                            j--;
                            j2 *= 2;
                            if (j2 > 300) {
                                j2 = 8;
                            }
                        }
                    }
                    AbstractC6201c.this.mo11088a(m12570b);
                } catch (Throwable th) {
                    MobLog.getInstance().m11341d(th);
                    AbstractC6201c.this.mo11088a(null);
                }
            }
        });
    }

    /* renamed from: b */
    private static boolean m12566b(HashMap<String, Object> hashMap) {
        if (hashMap != null) {
            long longValue = ((Long) ResHelper.forceCast(hashMap.get(C5857m.m12226a("0101baNdXbbbg2adTcibgbdFd")), 0L)).longValue();
            return longValue != 0 && SystemClock.elapsedRealtime() - longValue >= 86400000;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static HashMap<String, Object> m12570b(C6152DH.DHResponse dHResponse) {
        try {
            String packageName = C6152DH.SyncMtd.getPackageName();
            String m12196a = C5871t.m12196a();
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(C5857m.m12226a("003?bj7dRbi"), m12196a);
            hashMap.put(C5857m.m12226a("0130cedfAd-bhficgba[dcgQbgOg!bi"), C5895z.m12117f());
            hashMap.put(C5857m.m12226a("0048bdcbbgba"), dHResponse.getODH());
            HashMap<String, Object> m12195a = C5871t.m12195a(dHResponse.getDetailNetworkTypeForStatic());
            m12195a.put(C5857m.m12226a("002g df"), String.valueOf(System.currentTimeMillis()));
            int privacyGrantedStatus = MobSDK.getPrivacyGrantedStatus();
            if (privacyGrantedStatus != -1) {
                m12195a.put(C5857m.m12226a("009-bgdfcjccbh]dd)ei[h"), String.valueOf(privacyGrantedStatus == 1));
            }
            m12195a.put(C5857m.m12226a("002;bbfe"), String.valueOf(MobSDK.checkV6() ? 1 : -1));
            m12195a.put("ait", Long.valueOf(C5741aa.m12650a().m12607q()));
            String m12317b = C5831e.m12317b();
            if (!TextUtils.isEmpty(m12317b)) {
                m12195a.put("psid", m12317b + packageName);
            }
            String httpGet = new NetworkHelper().httpGet(C5847i.m12275a().m12272a("gcfg") + "/v6/gcf", m12195a, hashMap);
            HashMap fromJson = HashonHelper.fromJson(httpGet);
            if (fromJson.isEmpty()) {
                return null;
            }
            if (!"200".equals(String.valueOf(fromJson.get(C5857m.m12226a("006YdfKgbgZbedf"))))) {
                throw new Throwable("RS is illegal: " + httpGet);
            }
            byte[] rawMD5 = Data.rawMD5((m12196a + ":" + packageName + ":" + fromJson.get(C5857m.m12226a("009g;bgbd3d1df<gbBbdQh"))).getBytes("utf-8"));
            String str = (String) ResHelper.forceCast(fromJson.get(C5857m.m12226a("002@df'a")));
            if (str == null) {
                throw new Throwable("RS is illegal: " + httpGet);
            }
            String str2 = new String(Data.AES128Decode(rawMD5, Base64.decode(str, 2)), "utf-8");
            NLog mobLog = MobLog.getInstance();
            mobLog.m11342d("sw: " + str2, new Object[0]);
            HashMap<String, Object> fromJson2 = HashonHelper.fromJson(str2);
            if (fromJson2.isEmpty()) {
                throw new Throwable("RS is illegal: " + httpGet);
            }
            fromJson2.put(C5857m.m12226a("010Yba+dAbbbgBad^cibgbd<d"), Long.valueOf(SystemClock.elapsedRealtime()));
            C5741aa.m12650a().m12624d(HashonHelper.fromHashMap(fromJson2));
            return fromJson2;
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return null;
        }
    }

    /* renamed from: c */
    private static CountDownLatch m12559c(HashMap<String, Object> hashMap) {
        String str = (String) ResHelper.forceCast(hashMap.get(C5857m.m12226a("002=dfdf")), null);
        CountDownLatch m11691a = C6032d.m11700a(MobSDK.getContext()).m11691a(str);
        try {
            HashMap<String, Object> hashMap2 = (HashMap) hashMap.get(C5857m.m12226a("002dXbd"));
            String str2 = (String) ResHelper.forceCast((String) hashMap.get(C5857m.m12226a("002aOba")), C5857m.m12226a("006Hfcfcfdfdfdfd"));
            long longValue = ((Long) ResHelper.forceCast(hashMap.get(C5857m.m12226a("004dad2ca")), 5L)).longValue();
            HashMap<String, Object> hashMap3 = (HashMap) hashMap.get(C5857m.m12226a("002Tcc:h"));
            HashMap hashMap4 = (HashMap) hashMap.get(C5857m.m12226a("0044cc[ha)ba"));
            Integer num = (Integer) hashMap.get(C5857m.m12226a("004;ccAd'cb.g"));
            HashMap hashMap5 = new HashMap();
            hashMap5.put(C5857m.m12226a("002Gcb)f"), hashMap.get(C5857m.m12226a("002Gcb)f")));
            hashMap5.put(C5857m.m12226a("002,dfdf"), str);
            hashMap5.put(C5857m.m12226a("002d5bd"), hashMap2);
            hashMap5.put(C5857m.m12226a("002a=ba"), str2);
            hashMap5.put(C5857m.m12226a("004dadFca"), Long.valueOf(longValue));
            hashMap5.put(C5857m.m12226a("004(ccAd_cb^g"), num);
            hashMap5.put(C5857m.m12226a("003:bhbgba"), ResHelper.forceCast((String) hashMap.get(C5857m.m12226a("003:bhbgba")), null));
            hashMap5.put(C5857m.m12226a("003SdfcbQa"), hashMap.get(C5857m.m12226a("003SdfcbQa")));
            hashMap5.put(C5857m.m12226a("003^dfbgFg"), hashMap.get(C5857m.m12226a("003^dfbgFg")));
            hashMap5.put("aps", hashMap.get("aps"));
            hashMap5.put("sti", hashMap.get("sti"));
            if ((hashMap2 != null && hashMap2.size() > 0 && !TextUtils.isEmpty(str2)) || (hashMap3 != null && hashMap3.size() > 0 && hashMap4 != null && hashMap4.size() > 0)) {
                m12578a(hashMap5, hashMap, hashMap2, hashMap3, hashMap4, num, m11691a);
                C5849j.m12264a().m12255a(hashMap, hashMap2, hashMap3);
            }
            hashMap.put(C5857m.m12226a("010Uba?dSbbbgIad?cibgbd.d"), Long.valueOf(SystemClock.elapsedRealtime()));
            C5741aa.m12650a().m12628c(HashonHelper.fromHashMap(hashMap));
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
        return m11691a;
    }

    /* renamed from: a */
    private static void m12578a(HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2, HashMap<String, Object> hashMap3, HashMap<String, Object> hashMap4, HashMap<String, Object> hashMap5, Integer num, CountDownLatch countDownLatch) {
        if (num != null && num.intValue() == 2) {
            C6120a.f14983b.set(false);
            try {
                countDownLatch.await(3500L, TimeUnit.MILLISECONDS);
                MobLog.getInstance().m11342d("dhs wt geot.2 ovr", new Object[0]);
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        boolean m12254a = C5849j.m12264a().m12254a(true);
        C5849j.m12264a().m12249c().put(C5857m.m12226a("006dObdbfbh=dFdf"), Boolean.valueOf(m12254a));
        if (hashMap3 != null && hashMap3.size() > 0 && !m12254a) {
            MobLog.getInstance().m11342d("dhs em dg", new Object[0]);
            hashMap2.clear();
            hashMap2.putAll(hashMap);
            hashMap2.putAll(hashMap3);
        } else if (hashMap4 != null && hashMap4.size() > 0 && !C5849j.m12264a().m12256a(hashMap5)) {
            MobLog.getInstance().m11342d("dhs gpe dg", new Object[0]);
            hashMap2.clear();
            hashMap2.putAll(hashMap);
            hashMap2.putAll(hashMap4);
        } else {
            hashMap2.remove(C5857m.m12226a("0026ccGh"));
            hashMap2.remove(C5857m.m12226a("002d4bd"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public static void m12553e(String str) {
        File file;
        ByteArrayOutputStream byteArrayOutputStream;
        FileOutputStream fileOutputStream;
        try {
            C5843h.m12291a().m12290a(0);
            String m12175b = C5873u.m12175b(str);
            file = new File(MobSDK.getContext().getFilesDir(), C5857m.m12226a("0034df9aa"));
            try {
                if (!C5849j.m12264a().m12253b()) {
                    C5843h.m12291a().m12290a(18);
                    m12541p();
                } else if (TextUtils.isEmpty(m12175b)) {
                    C5843h.m12291a().m12290a(1);
                } else if (m12562c()) {
                    C5843h.m12291a().m12290a(2);
                    HashMap hashMap = (HashMap) new NetCommunicator(1024, "9e87e8d4b8f52f2916d0fb4342aa6b54a81a05666d0bdb23cc5ebf3a07440bc3976adff1ce11c64ddcdbfc017920648217196d51e3165e780e58b5460c525ee9", "13bda4b87eb42ab9e64e6b4f3d17cf8005a4ae94af37bc9fd76ebd91a828f017c81bd63cbe2924e361e20003b9e5f47cdac1f5fba5fca05730a32c5c65869590287207e79a604a2aac429e55f0d35c211367bd226dd5e57df7810f036071854aa1061a0f34b418b9178895a531107c652a428cfa6ecfa65333580ae7e0edf0e1").requestSynchronized(C5871t.m12189e(), m12175b, false);
                    C5843h.m12291a().m12290a(3);
                    String str2 = (String) hashMap.get(C5857m.m12226a("002Ocd5e"));
                    String str3 = (String) hashMap.get("m");
                    Boolean bool = (Boolean) hashMap.get(C5857m.m12226a("002bYdf"));
                    boolean booleanValue = bool != null ? bool.booleanValue() : false;
                    String str4 = (String) hashMap.get(C5857m.m12226a("002bPbj"));
                    String str5 = (String) hashMap.get(C5857m.m12226a("002ac"));
                    String str6 = (String) hashMap.get(C5857m.m12226a("002=cdVc"));
                    if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str4)) {
                        synchronized (C5859o.f14443i) {
                            f14173h.clear();
                            f14173h.put("h", str3);
                            f14173h.put("k", str4);
                            f14173h.put(C5857m.m12226a("002ac"), str5);
                            f14173h.put(C5857m.m12226a("002Zcd9c"), str6);
                            String checkHttpRequestUrl = NetCommunicator.checkHttpRequestUrl(str2);
                            if (booleanValue) {
                                C5843h.m12291a().m12290a(5);
                                File file2 = new File(file, C5857m.m12226a("008aRcb=cLcddbdf aa"));
                                if (!file2.exists() || !str3.equals(Data.MD5(file2))) {
                                    C5843h.m12291a().m12290a(6);
                                    ResHelper.deleteFileAndFolder(file);
                                    file.mkdirs();
                                    try {
                                        fileOutputStream = new FileOutputStream(file2);
                                    } catch (Throwable th) {
                                        th = th;
                                        fileOutputStream = null;
                                    }
                                    try {
                                        new NetworkHelper().download(checkHttpRequestUrl, fileOutputStream, null);
                                        C5843h.m12291a().m12290a(7);
                                        C5873u.m12179a(fileOutputStream);
                                    } catch (Throwable th2) {
                                        th = th2;
                                        C5873u.m12179a(fileOutputStream);
                                        throw th;
                                    }
                                }
                            } else {
                                C5843h.m12291a().m12290a(8);
                                ResHelper.deleteFileAndFolder(file);
                                final byte[][] bArr = new byte[1];
                                final int[] iArr = new int[1];
                                try {
                                    byteArrayOutputStream = new ByteArrayOutputStream() { // from class: com.mob.commons.b.6
                                        @Override // java.io.ByteArrayOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
                                        public void close() throws IOException {
                                            super.close();
                                            bArr[0] = this.buf;
                                            iArr[0] = this.count;
                                        }
                                    };
                                } catch (Throwable th3) {
                                    th = th3;
                                    byteArrayOutputStream = null;
                                }
                                try {
                                    new NetworkHelper().download(checkHttpRequestUrl, byteArrayOutputStream, null);
                                    C5843h.m12291a().m12290a(9);
                                    C5873u.m12179a(byteArrayOutputStream);
                                    f14173h.put(C5857m.m12226a("001Vdc"), bArr[0]);
                                    f14173h.put("s", Integer.valueOf(iArr[0]));
                                } catch (Throwable th4) {
                                    th = th4;
                                    C5873u.m12179a(byteArrayOutputStream);
                                    throw th;
                                }
                            }
                        }
                        m12540q();
                    }
                    C5843h.m12291a().m12290a(4);
                    ResHelper.deleteFileAndFolder(file);
                    m12540q();
                } else {
                    m12540q();
                }
            } catch (Throwable th5) {
                th = th5;
                ResHelper.deleteFileAndFolder(file);
                C5843h.m12291a().m12288a(2, th);
            }
        } catch (Throwable th6) {
            th = th6;
            file = null;
        }
    }

    /* renamed from: a */
    private static void m12573a(String... strArr) {
        File filesDir = MobSDK.getContext().getFilesDir();
        for (String str : strArr) {
            try {
                C5873u.m12182a(new File(filesDir, str));
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public static void m12551f(String str) {
        File file;
        String m12175b;
        try {
            m12175b = C5873u.m12175b(str);
            file = new File(MobSDK.getContext().getFilesDir(), C5857m.m12226a("003.dfdc=e"));
        } catch (Throwable th) {
            th = th;
            file = null;
        }
        try {
            File file2 = new File(MobSDK.getContext().getFilesDir(), C5857m.m12226a("007Pdbbhdf;e1dcbhTa"));
            if (!C5849j.m12264a().m12253b()) {
                ResHelper.deleteFileAndFolder(file);
                ResHelper.deleteFileAndFolder(file2);
            } else if (TextUtils.isEmpty(m12175b)) {
                ResHelper.deleteFileAndFolder(file);
            } else if (m12562c()) {
                HashMap<String, Object> m12189e = C5871t.m12189e();
                m12189e.put(C5857m.m12226a("007-bb8d5bhdfbgcb4c"), String.valueOf(C5791a.m12462a()));
                ArrayList arrayList = (ArrayList) ((HashMap) new NetCommunicator(1024, "9e87e8d4b8f52f2916d0fb4342aa6b54a81a05666d0bdb23cc5ebf3a07440bc3976adff1ce11c64ddcdbfc017920648217196d51e3165e780e58b5460c525ee9", "13bda4b87eb42ab9e64e6b4f3d17cf8005a4ae94af37bc9fd76ebd91a828f017c81bd63cbe2924e361e20003b9e5f47cdac1f5fba5fca05730a32c5c65869590287207e79a604a2aac429e55f0d35c211367bd226dd5e57df7810f036071854aa1061a0f34b418b9178895a531107c652a428cfa6ecfa65333580ae7e0edf0e1").requestSynchronized(m12189e, m12175b, false)).get(C5857m.m12226a("004e,bgdfYg"));
                if (arrayList != null && !arrayList.isEmpty()) {
                    synchronized (C5859o.f14444j) {
                        f14174i.clear();
                        f14174i.put(C5857m.m12226a("002eg"), arrayList);
                    }
                }
                ResHelper.deleteFileAndFolder(file);
                ResHelper.deleteFileAndFolder(file2);
            }
        } catch (Throwable th2) {
            th = th2;
            try {
                C5839g.m12306a().m12304a(9, -1, th, "-1");
                ResHelper.deleteFileAndFolder(file);
            } finally {
                m12539r();
            }
        }
    }

    /* renamed from: s */
    private static void m12538s() {
        if (C5879w.m12152h() && f14178m.compareAndSet(false, true)) {
            try {
                C5965b.m11927a();
            } catch (Throwable unused) {
            }
        }
    }
}
