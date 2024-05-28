package com.mob.mcl.p235c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.mob.MobSDK;
import com.mob.apc.C5677a;
import com.mob.commons.C5741aa;
import com.mob.commons.C5747b;
import com.mob.commons.C5847i;
import com.mob.commons.C5869r;
import com.mob.commons.C5871t;
import com.mob.commons.C5895z;
import com.mob.commons.MobProduct;
import com.mob.mcl.BusinessCallBack;
import com.mob.mcl.BusinessMessageListener;
import com.mob.mcl.C5901a;
import com.mob.mcl.MobMCL;
import com.mob.mcl.p233a.C5903a;
import com.mob.mcl.p234b.C5906a;
import com.mob.mcl.p236d.C5957b;
import com.mob.mcl.p236d.C5958c;
import com.mob.mgs.OnIdChangeListener;
import com.mob.mgs.impl.C5995f;
import com.mob.tools.C6122c;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.AbstractC6201c;
import com.mob.tools.utils.ActivityTracker;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.HashonHelper;
import com.mob.tools.utils.UIHandler;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.mob.mcl.c.h */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5941h implements InterfaceC5937d {

    /* renamed from: m */
    private static volatile C5941h f14614m;

    /* renamed from: a */
    public long f14615a;

    /* renamed from: b */
    public String f14616b;

    /* renamed from: d */
    public ArrayList<String> f14618d;

    /* renamed from: f */
    public boolean f14620f;

    /* renamed from: g */
    public boolean f14621g;

    /* renamed from: h */
    public boolean f14622h;

    /* renamed from: i */
    public boolean f14623i;

    /* renamed from: j */
    public String f14624j;

    /* renamed from: k */
    public long f14625k;

    /* renamed from: l */
    public boolean f14626l;

    /* renamed from: n */
    private NetworkHelper f14627n;

    /* renamed from: o */
    private HashonHelper f14628o;

    /* renamed from: p */
    private C5939f f14629p;

    /* renamed from: q */
    private String f14630q;

    /* renamed from: r */
    private String f14631r;

    /* renamed from: s */
    private Context f14632s;

    /* renamed from: t */
    private MobMCL.ELPMessageListener f14633t;

    /* renamed from: u */
    private HashMap<Integer, HashSet<BusinessMessageListener>> f14634u;

    /* renamed from: v */
    private boolean f14635v;

    /* renamed from: w */
    private C5958c f14636w;

    /* renamed from: x */
    private OnIdChangeListener f14637x;

    /* renamed from: c */
    public AtomicLong f14617c = new AtomicLong(0);

    /* renamed from: e */
    public int f14619e = SubsamplingScaleImageView.ORIENTATION_270;

    /* renamed from: y */
    private int f14638y = 1;

    /* renamed from: z */
    private AtomicBoolean f14639z = new AtomicBoolean(false);

    /* renamed from: a */
    public boolean m12017a() {
        return (this.f14620f && this.f14621g && !this.f14635v) ? false : true;
    }

    /* renamed from: b */
    public static C5941h m11988b() {
        if (f14614m == null) {
            synchronized (C5941h.class) {
                if (f14614m == null) {
                    f14614m = new C5941h();
                }
            }
        }
        return f14614m;
    }

    /* renamed from: i */
    private String m11970i() {
        return this.f14631r + this.f14632s.getPackageName();
    }

    private C5941h() {
        C5957b.m11958a().m11954b("TP tpHelper init");
        this.f14629p = new C5939f(this);
        this.f14627n = new NetworkHelper();
        this.f14628o = new HashonHelper();
        this.f14634u = new HashMap<>();
        this.f14636w = new C5958c(MobSDK.getContext());
        this.f14632s = MobSDK.getContext();
    }

    /* renamed from: a */
    public void m11998a(OnIdChangeListener onIdChangeListener) {
        this.f14637x = onIdChangeListener;
    }

    /* renamed from: a */
    public void m11996a(String str) {
        this.f14636w.m11950b(str);
    }

    /* renamed from: a */
    public void m12005a(MobMCL.ELPMessageListener eLPMessageListener) {
        this.f14633t = eLPMessageListener;
    }

    /* renamed from: a */
    public void m12014a(int i, BusinessMessageListener businessMessageListener) {
        HashSet<BusinessMessageListener> hashSet;
        try {
            C5957b m11958a = C5957b.m11958a();
            m11958a.m11954b("TP tpHelper addBMListener: bisType = " + i + ", listener = " + businessMessageListener);
            Integer valueOf = Integer.valueOf(i);
            if (businessMessageListener == null) {
                C5957b m11958a2 = C5957b.m11958a();
                m11958a2.m11954b("TP tpHelper addBMListener: remove key = " + valueOf);
                this.f14634u.remove(valueOf);
                return;
            }
            if (this.f14634u.containsKey(valueOf)) {
                hashSet = this.f14634u.get(valueOf);
            } else {
                HashSet<BusinessMessageListener> hashSet2 = new HashSet<>();
                this.f14634u.put(valueOf, hashSet2);
                hashSet = hashSet2;
            }
            hashSet.add(businessMessageListener);
            if (C5940g.m12022a().m12020b()) {
                C5957b.m11958a().m11954b("TP tpHelper addBMListener: has cached msg");
                List<Map<String, Object>> m12018c = C5940g.m12022a().m12018c();
                ArrayList<Map<String, Object>> arrayList = new ArrayList();
                for (Map<String, Object> map : m12018c) {
                    Object obj = map.get("bisType");
                    int intValue = obj != null ? ((Integer) obj).intValue() : 0;
                    final String str = (String) map.get("workId");
                    final String str2 = (String) map.get("json");
                    C5957b m11958a3 = C5957b.m11958a();
                    m11958a3.m11954b("TP tpHelper addBMListener: cachedBisType = " + intValue + ", target bisType = " + valueOf);
                    if (intValue == valueOf.intValue()) {
                        Iterator<BusinessMessageListener> it = hashSet.iterator();
                        while (it.hasNext()) {
                            final BusinessMessageListener next = it.next();
                            final int i2 = intValue;
                            UIHandler.sendEmptyMessage(0, new Handler.Callback() { // from class: com.mob.mcl.c.h.1
                                @Override // android.os.Handler.Callback
                                public boolean handleMessage(Message message) {
                                    if (next != null) {
                                        C5957b m11958a4 = C5957b.m11958a();
                                        m11958a4.m11954b("TP tpHelper addBMListener: callback to messageReceived. bisType: " + i2 + ", workId: " + str + ", msg: " + str2);
                                        next.messageReceived(i2, str, str2);
                                        return false;
                                    }
                                    return false;
                                }
                            });
                        }
                        C5957b m11958a4 = C5957b.m11958a();
                        m11958a4.m11954b("TP tpHelper addBMListener: mark msg to rm. msg = " + map);
                        arrayList.add(map);
                    }
                }
                if (arrayList.isEmpty()) {
                    return;
                }
                for (Map<String, Object> map2 : arrayList) {
                    C5957b m11958a5 = C5957b.m11958a();
                    m11958a5.m11954b("TP tpHelper addBMListener: rm msg = " + map2);
                    C5940g.m12022a().m12019b(map2);
                }
                return;
            }
            C5957b.m11958a().m11954b("TP tpHelper addBMListener: no cached msg");
        } catch (Throwable th) {
            C5957b.m11958a().m11954b("TP tpHelper addBMListener: error");
            C5957b.m11958a().m11955a(th);
        }
    }

    /* renamed from: a */
    public void m12008a(Context context, String str, String str2) {
        this.f14632s = context;
        this.f14630q = str;
        if (!TextUtils.isEmpty(str2)) {
            this.f14631r = str2;
        }
        m11972g();
        ActivityTracker.getInstance(context).addTracker(C5901a.m12112a(new C5901a.C5902a() { // from class: com.mob.mcl.c.h.3
            @Override // com.mob.mcl.C5901a.C5902a
            /* renamed from: a */
            public void mo11883a() {
                C5941h.this.m11968k();
            }

            @Override // com.mob.mcl.C5901a.C5902a
            /* renamed from: b */
            public void mo11964b() {
                C5941h.this.m11968k();
            }
        }));
        this.f14636w.m11953a();
    }

    /* renamed from: a */
    public HashMap<String, Object> m11992a(String str, String str2, int i) throws Throwable {
        if (this.f14629p != null) {
            C5957b m11958a = C5957b.m11958a();
            m11958a.m11954b("TP rg main = " + str + " , bo = " + str2 + " , out = " + i);
            String[] split = str.split(":");
            this.f14629p.m12024a(new InetSocketAddress(split[0], Integer.parseInt(split[1])), true, true, 5000);
            this.f14617c.set(0L);
            C5938e c5938e = new C5938e(1001, m11976c(this.f14616b, str2));
            c5938e.f14607c = this.f14615a;
            C5938e c5938e2 = this.f14629p.m12025a(c5938e).get((long) i, TimeUnit.MILLISECONDS);
            if (c5938e2 != null && c5938e2.f14606b == 1000) {
                c5938e2.f14608d = m11993a(this.f14616b, c5938e2.f14608d);
                return m11982b(c5938e2.f14608d);
            }
            C5957b m11958a2 = C5957b.m11958a();
            m11958a2.m11954b("TP rp : " + c5938e2);
            return null;
        }
        return null;
    }

    /* renamed from: a */
    public HashMap<String, Object> m12012a(int i, String str) {
        return m12015a(i, 10000, str);
    }

    /* renamed from: a */
    public HashMap<String, Object> m12015a(int i, int i2, String str) {
        C5938e c5938e;
        if (this.f14629p != null) {
            try {
                String m11987b = m11987b(this.f14617c.get());
                C5957b m11958a = C5957b.m11958a();
                m11958a.m11954b("TP sd ty = " + i + " , bo = " + str + " , out = " + i2);
                if (TextUtils.isEmpty(str)) {
                    c5938e = new C5938e(i);
                } else {
                    c5938e = new C5938e(i, m11976c(m11987b, str));
                }
                FutureC5936c m12025a = this.f14629p.m12025a(c5938e);
                if (m12025a != null) {
                    C5938e c5938e2 = m12025a.get(i2, TimeUnit.MILLISECONDS);
                    if (c5938e2 != null && c5938e2.f14606b == 1000) {
                        c5938e2.f14608d = m11993a(m11987b, c5938e2.f14608d);
                        return m11982b(c5938e2.f14608d);
                    }
                    C5957b m11958a2 = C5957b.m11958a();
                    m11958a2.m11954b("TP rp : " + c5938e2);
                    return null;
                }
                C5957b.m11958a().m11954b("TP rp : null");
                return null;
            } catch (Throwable th) {
                C5957b.m11958a().m11955a(th);
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    private void m12009a(long j, boolean z) {
        if (this.f14629p != null) {
            try {
                String m11987b = m11987b(this.f14617c.get());
                HashMap hashMap = new HashMap();
                hashMap.put("state", Boolean.valueOf(z));
                HashonHelper hashonHelper = this.f14628o;
                String fromHashMap = HashonHelper.fromHashMap(hashMap);
                C5938e c5938e = new C5938e(1006, m11976c(m11987b, fromHashMap));
                C5957b m11958a = C5957b.m11958a();
                m11958a.m11954b("TP sd ty = " + c5938e.f14606b + " , u = " + j + " bo : " + fromHashMap);
                c5938e.f14607c = j;
                this.f14629p.m12025a(c5938e);
            } catch (Throwable th) {
                C5957b.m11958a().m11955a(th);
            }
        }
    }

    /* renamed from: a */
    private void m12011a(long j) {
        if (this.f14629p != null) {
            try {
                C5938e c5938e = new C5938e(1005);
                c5938e.f14607c = j;
                this.f14629p.m12025a(c5938e);
                C5957b m11958a = C5957b.m11958a();
                m11958a.m11954b("TP sd ty = " + c5938e.f14606b + " , u = " + j + " bo : " + c5938e.f14608d);
            } catch (Throwable th) {
                C5957b.m11958a().m11955a(th);
            }
        }
    }

    /* renamed from: b */
    private void m11986b(long j, boolean z) {
        if (this.f14629p != null) {
            try {
                String m11987b = m11987b(this.f14617c.get());
                HashMap hashMap = new HashMap();
                hashMap.put("repeat", Boolean.valueOf(z));
                HashonHelper hashonHelper = this.f14628o;
                String fromHashMap = HashonHelper.fromHashMap(hashMap);
                C5938e c5938e = new C5938e(1007, m11976c(m11987b, fromHashMap));
                c5938e.f14607c = j;
                this.f14629p.m12025a(c5938e);
                C5957b m11958a = C5957b.m11958a();
                m11958a.m11954b("TP sd ty = " + c5938e.f14606b + " , u = " + j + " bo : " + fromHashMap);
            } catch (Throwable th) {
                C5957b.m11958a().m11955a(th);
            }
        }
    }

    /* renamed from: a */
    public boolean m12016a(int i, int i2) {
        if (i2 < 4) {
            if (m12015a(1002, i, (String) null) == null) {
                if (i2 == 0 || i2 == 1) {
                    m12016a(1000, i2 + 1);
                    return false;
                }
                m12016a(3000, i2 + 1);
                return false;
            }
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public void m12006a(BusinessCallBack<Boolean> businessCallBack) {
        boolean m11979c = m11979c();
        if (businessCallBack != null) {
            businessCallBack.callback(Boolean.valueOf(m11979c));
        }
        if (m11979c) {
            return;
        }
        C5906a.f14560a.execute(new Runnable() { // from class: com.mob.mcl.c.h.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!C5941h.m11988b().m11975d()) {
                        C5941h.m11988b().m11973f();
                    }
                    C5941h.this.m11997a(new AbstractC6201c<Boolean>() { // from class: com.mob.mcl.c.h.4.1
                        @Override // com.mob.tools.utils.AbstractC6201c
                        /* renamed from: a  reason: avoid collision after fix types in other method */
                        public void mo11088a(Boolean bool) {
                        }
                    });
                } catch (Throwable unused) {
                }
            }
        });
    }

    /* renamed from: b */
    public void m11985b(final BusinessCallBack<Boolean> businessCallBack) {
        C5906a.f14560a.execute(new Runnable() { // from class: com.mob.mcl.c.h.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    final boolean m12016a = C5941h.this.m11979c() ? C5941h.m11988b().m12016a(3000, 3) : false;
                    UIHandler.sendEmptyMessage(0, new Handler.Callback() { // from class: com.mob.mcl.c.h.5.1
                        @Override // android.os.Handler.Callback
                        public boolean handleMessage(Message message) {
                            try {
                                if (businessCallBack != null) {
                                    businessCallBack.callback(Boolean.valueOf(m12016a));
                                    return false;
                                }
                                return false;
                            } catch (Throwable unused) {
                                return false;
                            }
                        }
                    });
                    if (m12016a) {
                        return;
                    }
                    if (!C5941h.m11988b().m11975d()) {
                        C5941h.m11988b().m11973f();
                    }
                    C5941h.this.m11997a(new AbstractC6201c<Boolean>() { // from class: com.mob.mcl.c.h.5.2
                        @Override // com.mob.tools.utils.AbstractC6201c
                        /* renamed from: a  reason: avoid collision after fix types in other method */
                        public void mo11088a(Boolean bool) {
                        }
                    });
                } catch (Throwable unused) {
                }
            }
        });
    }

    /* renamed from: c */
    public boolean m11979c() {
        C5939f c5939f = this.f14629p;
        return (c5939f == null || !c5939f.m12023b() || this.f14617c.get() == 0) ? false : true;
    }

    /* renamed from: d */
    public boolean m11975d() {
        ArrayList<String> arrayList;
        return this.f14620f && this.f14621g && !this.f14635v && (arrayList = this.f14618d) != null && arrayList.size() > 0 && !TextUtils.isEmpty(this.f14616b);
    }

    /* renamed from: e */
    public boolean m11974e() {
        return m11975d() && this.f14622h;
    }

    /* renamed from: f */
    public void m11973f() {
        HashMap<String, Object> fromJson;
        boolean isInMainProcess = C6152DH.SyncMtd.isInMainProcess();
        C5957b.m11958a().m11954b("TP cf, main p: " + isInMainProcess);
        if (isInMainProcess) {
            if (TextUtils.isEmpty(this.f14631r) || this.f14632s == null) {
                C5957b.m11958a().m11954b("TP mcl has not been initialized");
                return;
            }
            try {
                String m12632b = C5741aa.m12650a().m12632b("tcp_config", (String) null);
                if (!TextUtils.isEmpty(m12632b)) {
                    HashonHelper hashonHelper = this.f14628o;
                    HashMap<String, Object> fromJson2 = HashonHelper.fromJson(m12632b);
                    if (fromJson2.containsKey("requestTimes")) {
                        long j = 0;
                        Object obj = fromJson2.get("requestTimes");
                        if (obj != null && (obj instanceof Long)) {
                            j = ((Long) obj).longValue();
                        } else if (obj != null && (obj instanceof Integer)) {
                            j = ((Integer) obj).intValue();
                        }
                        if (j + 86400000 > System.currentTimeMillis() && m11988b().m11991a(fromJson2) && C5741aa.m12650a().m12631b("use_config", true)) {
                            C5957b.m11958a().m11954b("TP cf cc : " + m12632b);
                            return;
                        }
                    }
                }
            } catch (Throwable th) {
                C5957b.m11958a().m11954b(th.getMessage());
            }
            try {
                HashMap hashMap = (HashMap) C5747b.m12583a("sti", (Object) null);
                if (hashMap != null && !hashMap.isEmpty()) {
                    fromJson = new HashMap<>();
                    fromJson.put("code", 200);
                    fromJson.put("data", hashMap);
                } else {
                    NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
                    networkTimeOut.readTimout = 10000;
                    networkTimeOut.connectionTimeout = 5000;
                    HashMap<String, Object> hashMap2 = new HashMap<>();
                    hashMap2.put("appkey", this.f14630q);
                    hashMap2.put("pushId", m11970i());
                    hashMap2.put("products", m11969j());
                    String str = C5847i.m12275a().m12272a("tcig") + "/tcp/config/init";
                    String httpPostNew = this.f14627n.httpPostNew(str, hashMap2, null, networkTimeOut);
                    C5957b.m11958a().m11954b("TP cf url : " + str + " -> rp : " + httpPostNew);
                    HashonHelper hashonHelper2 = this.f14628o;
                    fromJson = HashonHelper.fromJson(httpPostNew);
                }
                fromJson.put("requestTimes", Long.valueOf(System.currentTimeMillis()));
                if (m11988b().m11991a(fromJson)) {
                    C5741aa.m12650a().m12640a("use_config", true);
                    C5741aa m12650a = C5741aa.m12650a();
                    HashonHelper hashonHelper3 = this.f14628o;
                    m12650a.m12641a("tcp_config", HashonHelper.fromHashMap(fromJson));
                }
            } catch (Throwable th2) {
                C5957b.m11958a().m11954b(th2.getMessage());
            }
        }
    }

    /* renamed from: j */
    private String m11969j() {
        ArrayList<MobProduct> m12121b = C5895z.m12121b();
        HashMap<String, Object> m12198b = C5869r.m12202a().m12198b();
        StringBuilder sb = new StringBuilder("COMMON;" + MobSDK.SDK_VERSION_CODE);
        int size = m12121b.size();
        for (int i = 0; i < size; i++) {
            try {
                MobProduct mobProduct = m12121b.get(i);
                if (!TextUtils.equals("COMMON", mobProduct.getProductTag())) {
                    sb.append(",");
                    sb.append(mobProduct.getProductTag());
                    sb.append(";");
                    sb.append(mobProduct.getSdkver());
                    sb.append(";");
                    sb.append(m12198b.get(mobProduct.getProductTag()));
                }
            } catch (Throwable unused) {
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    private boolean m11991a(HashMap<String, Object> hashMap) {
        try {
            this.f14626l = false;
            HashMap<String, Object> m11980b = m11980b(hashMap);
            if (m11980b.containsKey("domains") && m11980b.containsKey("uniqueId") && m11980b.containsKey("uniqueKey")) {
                this.f14618d = (ArrayList) m11980b.get("domains");
                this.f14615a = ((Long) m11980b.get("uniqueId")).longValue();
                this.f14616b = (String) m11980b.get("uniqueKey");
                this.f14619e = m11990a(m11980b, "tick", this.f14619e);
                this.f14620f = m11990a(m11980b, "globalSwitch", 0) == 1;
                this.f14621g = m11990a(m11980b, "connectSwitch", 0) == 1;
                this.f14622h = m11990a(m11980b, "forwardSwitch", 0) == 1;
                this.f14623i = m11990a(m11980b, "bindRequestSwitch", 0) == 1;
                if (m11980b.containsKey("determineDomain")) {
                    String str = (String) m11980b.get("determineDomain");
                    if (!TextUtils.isEmpty(str)) {
                        if (this.f14618d == null) {
                            this.f14618d = new ArrayList<>();
                        }
                        this.f14618d.remove(str);
                        this.f14618d.add(0, str);
                    }
                }
                if (this.f14618d != null && this.f14618d.size() > 0) {
                    if (!TextUtils.isEmpty(this.f14616b)) {
                        return true;
                    }
                }
            }
        } catch (Throwable th) {
            C5957b.m11958a().m11955a(th);
        }
        return false;
    }

    /* renamed from: a */
    public void m11997a(AbstractC6201c<Boolean> abstractC6201c) {
        m12013a(5000, abstractC6201c);
    }

    /* renamed from: a */
    public synchronized void m12013a(final int i, final AbstractC6201c<Boolean> abstractC6201c) {
        if (m11975d()) {
            m11977c(new AbstractC6201c<String>() { // from class: com.mob.mcl.c.h.6
                @Override // com.mob.tools.utils.AbstractC6201c
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo11088a(String str) {
                    C5941h c5941h = C5941h.this;
                    boolean m11989a = c5941h.m11989a(c5941h.f14626l, C5941h.this.f14618d.get(0), 0, str, i);
                    AbstractC6201c abstractC6201c2 = abstractC6201c;
                    if (abstractC6201c2 != null) {
                        abstractC6201c2.mo11088a(Boolean.valueOf(m11989a));
                    }
                }
            });
        } else if (abstractC6201c != null) {
            abstractC6201c.mo11088a(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public synchronized boolean m11989a(boolean z, String str, int i, String str2, int i2) {
        try {
            if (i < this.f14618d.size() && i < 3) {
                C5957b m11958a = C5957b.m11958a();
                m11958a.m11954b("TP rg domain : " + str + " count : " + i);
                HashMap<String, Object> m11992a = m11992a(str, str2, i2);
                if (m11992a != null && m11992a.containsKey("type")) {
                    int intValue = ((Integer) m11992a.get("type")).intValue();
                    if (intValue == 1 && m11992a.containsKey("token")) {
                        this.f14617c.set(((Long) m11992a.get("token")).longValue());
                        C5926b.m12045a().m12040b();
                        C5957b.m11958a().m11957a("TP register success");
                        m11966m();
                        return true;
                    } else if (intValue == 2 && m11992a.containsKey("domain")) {
                        String str3 = (String) m11992a.get("domain");
                        if (!TextUtils.isEmpty(str3)) {
                            return m11989a(true, str3, 2, str2, i2);
                        }
                    } else if (intValue == 3) {
                        this.f14635v = true;
                        this.f14629p.m12026a();
                        m11966m();
                        return false;
                    }
                }
                int i3 = i + 1;
                if (i3 < this.f14618d.size() && !z) {
                    return m11989a(false, this.f14618d.get(i3), i3, str2, i2);
                }
            }
            C5741aa.m12650a().m12641a("tcp_config", (String) null);
            this.f14618d = null;
            m11966m();
            return false;
        }
    }

    /* renamed from: c */
    private void m11977c(final AbstractC6201c<String> abstractC6201c) {
        final HashMap hashMap = new HashMap();
        hashMap.put("appkey", this.f14630q);
        hashMap.put("apppkg", this.f14632s.getPackageName());
        hashMap.put("plat", 1);
        hashMap.put("pushId", m11970i());
        hashMap.put("guardId", this.f14624j);
        try {
            C6152DH.requester(this.f14632s).getMpfo(this.f14632s.getPackageName(), 128).request(new C6152DH.DHResponder() { // from class: com.mob.mcl.c.h.7
                @Override // com.mob.tools.utils.C6152DH.DHResponder
                public void onResponse(C6152DH.DHResponse dHResponse) {
                    ApplicationInfo m11360a;
                    Bundle bundle;
                    Object obj;
                    Object mpfo = dHResponse.getMpfo(new int[0]);
                    if (mpfo != null && (m11360a = C6122c.m11360a(mpfo, C5941h.this.f14632s.getPackageName())) != null && (bundle = m11360a.metaData) != null && !bundle.isEmpty() && (obj = bundle.get("mob_id_ver")) != null) {
                        hashMap.put("version", String.valueOf(obj));
                    }
                    HashonHelper unused = C5941h.this.f14628o;
                    String fromHashMap = HashonHelper.fromHashMap(hashMap);
                    AbstractC6201c abstractC6201c2 = abstractC6201c;
                    if (abstractC6201c2 != null) {
                        abstractC6201c2.mo11088a(fromHashMap);
                    }
                }
            });
        } catch (Throwable th) {
            C5957b.m11958a().m11955a(th);
            HashonHelper hashonHelper = this.f14628o;
            String fromHashMap = HashonHelper.fromHashMap(hashMap);
            if (abstractC6201c != null) {
                abstractC6201c.mo11088a(fromHashMap);
            }
        }
    }

    /* renamed from: g */
    public void m11972g() {
        if (TextUtils.isEmpty(this.f14624j) || this.f14625k <= 0) {
            String m12632b = C5741aa.m12650a().m12632b("suid", "");
            long m12634b = C5741aa.m12650a().m12634b("create_suid_time", 0L);
            if (TextUtils.isEmpty(m12632b)) {
                m12632b = UUID.randomUUID().toString();
            }
            if (m12634b <= 0) {
                m12634b = System.currentTimeMillis();
            }
            m11994a(m12632b, m12634b);
        }
    }

    /* renamed from: a */
    public synchronized void m11994a(String str, long j) {
        if (this.f14637x != null && !String.valueOf(this.f14624j).equals(str)) {
            this.f14637x.onChanged(this.f14624j, str);
        }
        this.f14624j = str;
        this.f14625k = j;
        C5741aa.m12650a().m12641a("suid", this.f14624j);
        C5741aa.m12650a().m12643a("create_suid_time", this.f14625k);
    }

    /* renamed from: b */
    public void m11983b(final AbstractC6201c<Boolean> abstractC6201c) {
        m11977c(new AbstractC6201c<String>() { // from class: com.mob.mcl.c.h.8
            @Override // com.mob.tools.utils.AbstractC6201c
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo11088a(String str) {
                if (C5941h.this.m12012a(1003, str) != null) {
                    AbstractC6201c abstractC6201c2 = abstractC6201c;
                    if (abstractC6201c2 != null) {
                        abstractC6201c2.mo11088a(true);
                        return;
                    }
                    return;
                }
                AbstractC6201c abstractC6201c3 = abstractC6201c;
                if (abstractC6201c3 != null) {
                    abstractC6201c3.mo11088a(false);
                }
            }
        });
    }

    @Override // com.mob.mcl.p235c.InterfaceC5937d
    /* renamed from: a */
    public void mo12003a(C5924a c5924a, C5938e c5938e) {
        try {
            if (TextUtils.isEmpty(c5938e.f14608d)) {
                return;
            }
            if (this.f14617c.get() == 0) {
                C5957b.m11958a().m11954b("TP received push msg, but send token is 0");
                return;
            }
            c5938e.f14608d = m11993a(m11987b(this.f14617c.get()), c5938e.f14608d);
            if (c5938e.f14606b == 9001) {
                C5957b m11958a = C5957b.m11958a();
                m11958a.m11954b("TP msg push msgType: " + c5938e.f14606b + " body = " + c5938e.f14608d);
                m12011a(c5938e.f14607c);
                HashMap<String, Object> m11982b = m11982b(c5938e.f14608d);
                if (m11982b.containsKey("data")) {
                    int m11990a = m11990a(m11982b, "expire", 0);
                    String str = (String) m11982b.get("workId");
                    String str2 = (String) m11982b.get("data");
                    boolean z = m11990a(m11982b, "needRepeat", 0) == 1;
                    int m11990a2 = m11990a(m11982b, "type", 0);
                    if (m11990a2 != 1 && m11990a2 != 2) {
                        boolean m12010a = m12010a(c5938e.f14607c, str, m11990a, m11990a2, str2);
                        if (z) {
                            m11986b(c5938e.f14607c, m12010a);
                            return;
                        }
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("data", str2);
                    bundle.putInt("expire", m11990a);
                    bundle.putString("workId", str);
                    bundle.putLong("uniqueId", c5938e.f14607c);
                    bundle.putInt("msgType", m11990a2);
                    boolean z2 = m12007a(bundle) == 1;
                    if (z) {
                        m11986b(c5938e.f14607c, z2);
                    }
                }
            } else if (c5938e.f14606b == 9002) {
                final String str3 = (String) m11982b(c5938e.f14608d).get("domain");
                if (TextUtils.isEmpty(str3)) {
                    return;
                }
                this.f14626l = true;
                m11977c(new AbstractC6201c<String>() { // from class: com.mob.mcl.c.h.9
                    @Override // com.mob.tools.utils.AbstractC6201c
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void mo11088a(String str4) {
                        C5941h.this.m11989a(true, str3, 2, str4, 5000);
                    }
                });
            } else if (c5938e.f14606b == 9004) {
                C5957b m11958a2 = C5957b.m11958a();
                m11958a2.m11954b("TP mg ty: " + c5938e.f14606b + " bo = " + c5938e.f14608d);
                m12011a(c5938e.f14607c);
                HashMap<String, Object> m11982b2 = m11982b(c5938e.f14608d);
                if (m11982b2.containsKey("data") && m11982b2.containsKey("targetPackage")) {
                    String str4 = (String) m11982b2.get("targetPackage");
                    String str5 = (String) m11982b2.get("data");
                    int m11990a3 = m11990a(m11982b2, "logicTimeout", 1000);
                    if (TextUtils.isEmpty(str5) || TextUtils.isEmpty(str4)) {
                        return;
                    }
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("data", str5);
                    bundle2.putLong("uniqueId", c5938e.f14607c);
                    C5677a m12108a = C5903a.m12109a().m12108a(9004, bundle2, str4, m11990a3);
                    if (m12108a != null && m12108a.f14000e != null) {
                        m12009a(c5938e.f14607c, true);
                        return;
                    }
                    C5957b.m11958a().m11954b("TP apc fw rp mg is null");
                    m12009a(c5938e.f14607c, false);
                }
            }
        } catch (Throwable th) {
            C5957b.m11958a().m11955a(th);
        }
    }

    /* renamed from: a */
    public int m12007a(Bundle bundle) {
        if (this.f14633t != null) {
            if (m11995a(bundle.getString("workId"), bundle.getInt("expire"))) {
                return 1;
            }
            return this.f14633t.messageReceived(bundle) ? 1 : 0;
        }
        return -1;
    }

    /* renamed from: a */
    private synchronized boolean m11995a(String str, int i) {
        if (i != 0) {
            if (!TextUtils.isEmpty(str)) {
                if (System.currentTimeMillis() <= this.f14636w.m11952a(str)) {
                    return true;
                }
                this.f14636w.m11951a(str, System.currentTimeMillis() + (i * 1000));
            }
        }
        return false;
    }

    /* renamed from: a */
    public boolean m12010a(long j, final String str, int i, int i2, String str2) {
        try {
        } catch (Throwable th) {
            C5957b.m11958a().m11955a(th);
        }
        if (m11995a(str, i)) {
            return true;
        }
        HashonHelper hashonHelper = this.f14628o;
        HashMap fromJson = HashonHelper.fromJson(str2);
        fromJson.put("uniqueId", Long.valueOf(j));
        HashonHelper hashonHelper2 = this.f14628o;
        final String fromHashMap = HashonHelper.fromHashMap(fromJson);
        final Integer valueOf = Integer.valueOf(i2);
        if (this.f14634u.containsKey(valueOf)) {
            C5957b m11958a = C5957b.m11958a();
            m11958a.m11954b("[dealBusinessMsg]TP Biz msg listener detected, callback directly. bisType: " + valueOf);
            Iterator<BusinessMessageListener> it = this.f14634u.get(valueOf).iterator();
            while (it.hasNext()) {
                final BusinessMessageListener next = it.next();
                UIHandler.sendEmptyMessage(0, new Handler.Callback() { // from class: com.mob.mcl.c.h.10
                    @Override // android.os.Handler.Callback
                    public boolean handleMessage(Message message) {
                        if (next != null) {
                            C5957b m11958a2 = C5957b.m11958a();
                            m11958a2.m11954b("[dealBusinessMsg]TP callback to messageReceived. bisType: " + valueOf + ", workId: " + str + ", msg: " + fromHashMap);
                            next.messageReceived(valueOf.intValue(), str, fromHashMap);
                            return false;
                        }
                        return false;
                    }
                });
            }
        } else {
            C5957b m11958a2 = C5957b.m11958a();
            m11958a2.m11954b("[dealBusinessMsg]TP No biz msg listener detected, cache msg. bisType: " + valueOf);
            HashMap hashMap = new HashMap();
            hashMap.put("bisType", valueOf);
            hashMap.put("workId", str);
            hashMap.put("json", fromHashMap);
            C5940g.m12022a().m12021a(hashMap);
        }
        return false;
    }

    @Override // com.mob.mcl.p235c.InterfaceC5937d
    /* renamed from: a */
    public void mo12002a(C5924a c5924a, Throwable th) {
        C5957b m11958a = C5957b.m11958a();
        StringBuilder sb = new StringBuilder();
        sb.append("TP exceptionCaught : ");
        sb.append(th != null ? th.getMessage() : "");
        m11958a.m11954b(sb.toString());
    }

    @Override // com.mob.mcl.p235c.InterfaceC5937d
    /* renamed from: a */
    public void mo12004a(C5924a c5924a) {
        C5957b.m11958a().m11954b("TP sessionOpened");
    }

    @Override // com.mob.mcl.p235c.InterfaceC5937d
    /* renamed from: a */
    public void mo12001a(C5924a c5924a, boolean z) {
        C5957b m11958a = C5957b.m11958a();
        m11958a.m11954b("TP sessionClosed " + z);
        C5926b.m12045a().m12039c();
        if (z) {
            m11967l();
        }
    }

    /* renamed from: a */
    public String m11993a(String str, String str2) throws Throwable {
        return Data.AES128Decode(str, Base64.decode(str2, 2));
    }

    /* renamed from: c */
    private String m11976c(String str, String str2) throws Throwable {
        return Base64.encodeToString(Data.AES128Encode(str, str2), 2);
    }

    /* renamed from: b */
    private String m11987b(long j) {
        return String.format("%16s", Integer.valueOf(Math.abs(Arrays.hashCode(new long[]{j})))).replaceAll(" ", "0").substring(0, 16);
    }

    /* renamed from: h */
    public String m11971h() {
        return String.format("%16s", Integer.valueOf(Math.abs(Arrays.hashCode(new Object[]{this.f14630q, m11970i()})))).replaceAll(" ", "0").substring(0, 16);
    }

    /* renamed from: b */
    private HashMap<String, Object> m11980b(HashMap<String, Object> hashMap) {
        return (m11990a(hashMap, "code", 0) == 200 && hashMap.containsKey("data")) ? (HashMap) hashMap.get("data") : new HashMap<>();
    }

    /* renamed from: b */
    private HashMap<String, Object> m11982b(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            if (!TextUtils.isEmpty(str) && str.startsWith("{")) {
                C5957b.m11958a().m11954b(str);
                HashonHelper hashonHelper = this.f14628o;
                return m11980b(HashonHelper.fromJson(str));
            }
        } catch (Throwable th) {
            C5957b.m11958a().m11955a(th);
        }
        return hashMap;
    }

    /* renamed from: a */
    public static int m11990a(HashMap<String, Object> hashMap, String str, int i) {
        if (hashMap != null && hashMap.containsKey(str)) {
            Object obj = hashMap.get(str);
            if (obj instanceof Integer) {
                return ((Integer) obj).intValue();
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public void m11968k() {
        if (m11979c()) {
            return;
        }
        m11967l();
    }

    /* renamed from: l */
    private void m11967l() {
        C5906a.f14560a.execute(new Runnable() { // from class: com.mob.mcl.c.h.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (C5941h.this.m11979c()) {
                        return;
                    }
                    if (!C5941h.this.m11975d()) {
                        C5941h.this.m11973f();
                    }
                    C5941h.this.m11997a(new AbstractC6201c<Boolean>() { // from class: com.mob.mcl.c.h.2.1
                        @Override // com.mob.tools.utils.AbstractC6201c
                        /* renamed from: a  reason: avoid collision after fix types in other method */
                        public void mo11088a(Boolean bool) {
                        }
                    });
                } catch (Throwable unused) {
                }
            }
        });
    }

    /* renamed from: b */
    public void m11981b(String str, String str2) {
        int i;
        try {
            NetworkHelper networkHelper = new NetworkHelper();
            NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
            networkTimeOut.connectionTimeout = 2000;
            networkTimeOut.readTimout = 5000;
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("appkey", C5871t.m12196a());
            hashMap.put("pkg", C6152DH.SyncMtd.getPackageName());
            hashMap.put("duidOld", str2);
            hashMap.put("duidNew", str);
            hashMap.put("appVer", C6152DH.SyncMtd.getAppVersionName());
            hashMap.put("plat", Integer.valueOf(C6152DH.SyncMtd.getPlatformCode()));
            String str3 = C5847i.m12275a().m12272a("tcig") + "/tcp/push/pbsd";
            C5957b.m11958a().m11954b("[Request]TP url = " + str3 + "\nheaders = " + ((Object) null) + "\nvalues = " + hashMap);
            String httpPostNew = networkHelper.httpPostNew(str3, hashMap, null, networkTimeOut);
            C5957b.m11958a().m11954b("[Response]TP url = " + str3 + "\nresp = " + httpPostNew);
            HashMap fromJson = HashonHelper.fromJson(httpPostNew);
            if (fromJson != null && !fromJson.isEmpty() && !"200".equals(String.valueOf(fromJson.get("code")))) {
                throw new Throwable("Req failed: " + httpPostNew);
            }
            this.f14638y = 1;
        } catch (Throwable th) {
            C5957b.m11958a().m11955a(th);
            if (this.f14638y < 3) {
                try {
                    Thread.sleep(i * 1000);
                } catch (InterruptedException unused) {
                    C5957b.m11958a().m11955a(th);
                }
                this.f14638y++;
                m11981b(str, str2);
                return;
            }
            this.f14638y = 1;
        }
    }

    /* renamed from: m */
    private void m11966m() {
        if (C5995f.m11854a().m11850d() && this.f14639z.compareAndSet(false, true)) {
            m11988b().m11981b(C5995f.m11854a().m11851c(), C5995f.m11854a().m11849e());
        }
    }
}
