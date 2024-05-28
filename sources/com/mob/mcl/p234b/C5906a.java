package com.mob.mcl.p234b;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.apc.C5677a;
import com.mob.apc.C5688b;
import com.mob.commons.C5741aa;
import com.mob.mcl.BusinessMessageListener;
import com.mob.mcl.C5901a;
import com.mob.mcl.MobMCL;
import com.mob.mcl.p233a.C5903a;
import com.mob.mcl.p233a.C5905b;
import com.mob.mcl.p235c.C5926b;
import com.mob.mcl.p235c.C5941h;
import com.mob.mcl.p236d.C5957b;
import com.mob.mgs.OnIdChangeListener;
import com.mob.tools.network.HttpResponseCallback;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.StringPart;
import com.mob.tools.utils.AbstractC6201c;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.HashonHelper;
import com.mob.tools.utils.UIHandler;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.mcl.b.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5906a {

    /* renamed from: a */
    public static final ExecutorService f14560a = Executors.newSingleThreadExecutor();

    /* renamed from: b */
    private static AtomicBoolean f14561b = new AtomicBoolean(false);

    /* renamed from: c */
    private static NetworkHelper f14562c = new NetworkHelper();

    /* renamed from: d */
    private static CopyOnWriteArraySet<String> f14563d = new CopyOnWriteArraySet<>();

    /* renamed from: a */
    public static void m12085a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f14563d.add(str);
    }

    /* renamed from: b */
    public static void m12073b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f14563d.remove(str);
    }

    /* renamed from: a */
    public static void m12089a(Context context, String str, String str2) {
        Context applicationContext;
        C5957b.m11958a().m11957a("mcl ini");
        if (context == null) {
            applicationContext = MobSDK.getContext();
        } else {
            applicationContext = context.getApplicationContext();
        }
        C5941h.m11988b().m12008a(applicationContext, str, str2);
        C5903a.m12109a().m12107a(applicationContext, new C5922a());
        C5741aa.m12650a().m12640a("use_config", false);
        m12075b(0, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m12075b(final int i, int i2) {
        UIHandler.sendEmptyMessageDelayed(0, i2 * 1000, new Handler.Callback() { // from class: com.mob.mcl.b.a.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                C5906a.f14560a.execute(C5906a.m12071c(i));
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static Runnable m12071c(final int i) {
        return new Runnable() { // from class: com.mob.mcl.b.a.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (C5941h.m11988b().m11979c()) {
                        return;
                    }
                    C5906a.m12069d(i);
                } catch (Throwable th) {
                    C5957b.m11958a().m11955a(th);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static void m12069d(final int i) {
        if (i < 6) {
            boolean isInMainProcess = C6152DH.SyncMtd.isInMainProcess();
            C5957b m11958a = C5957b.m11958a();
            m11958a.m11954b("tp rgs, main p: " + isInMainProcess);
            final boolean z = !isInMainProcess && C5903a.m12109a().m12101b();
            m12090a(5000, new AbstractC6201c<Boolean>() { // from class: com.mob.mcl.b.a.4
                @Override // com.mob.tools.utils.AbstractC6201c
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo11088a(Boolean bool) {
                    if (z || bool.booleanValue() || C5941h.m11988b().m12017a()) {
                        return;
                    }
                    int i2 = i;
                    if (i2 == 0) {
                        C5906a.m12075b(i2 + 1, 10);
                    } else if (i2 == 1) {
                        C5906a.m12075b(i2 + 1, 30);
                    } else if (i2 == 2) {
                        C5906a.m12075b(i2 + 1, 60);
                    } else if (i2 == 3) {
                        C5906a.m12075b(i2 + 1, 180);
                    } else if (i2 == 4) {
                        C5906a.m12075b(i2 + 1, 300);
                    }
                }
            });
        }
    }

    /* renamed from: a */
    private static void m12090a(int i, final AbstractC6201c<Boolean> abstractC6201c) {
        if (f14561b.getAndSet(true)) {
            if (abstractC6201c != null) {
                abstractC6201c.mo11088a(false);
                return;
            }
            return;
        }
        try {
            boolean isInMainProcess = C6152DH.SyncMtd.isInMainProcess();
            C5957b m11958a = C5957b.m11958a();
            m11958a.m11954b("init tp, main p: " + isInMainProcess);
            if (isInMainProcess) {
                C5941h.m11988b().m11973f();
                if (!C5941h.m11988b().f14620f) {
                    f14561b.set(false);
                    if (abstractC6201c != null) {
                        abstractC6201c.mo11088a(false);
                    }
                    return;
                } else if (C5941h.m11988b().m11975d()) {
                    C5941h.m11988b().m12013a(i, new AbstractC6201c<Boolean>() { // from class: com.mob.mcl.b.a.5
                        @Override // com.mob.tools.utils.AbstractC6201c
                        /* renamed from: a  reason: avoid collision after fix types in other method */
                        public void mo11088a(Boolean bool) {
                            if (bool.booleanValue()) {
                                C5926b.m12045a();
                                if (!C5941h.m11988b().f14622h) {
                                    C5906a.m12074b(new AbstractC6201c<Void>() { // from class: com.mob.mcl.b.a.5.1
                                        @Override // com.mob.tools.utils.AbstractC6201c
                                        /* renamed from: a  reason: avoid collision after fix types in other method */
                                        public void mo11088a(Void r2) {
                                            C5906a.f14561b.set(false);
                                            if (AbstractC6201c.this != null) {
                                                AbstractC6201c.this.mo11088a(true);
                                            }
                                        }
                                    });
                                    return;
                                }
                                C5906a.f14561b.set(false);
                                AbstractC6201c abstractC6201c2 = AbstractC6201c.this;
                                if (abstractC6201c2 != null) {
                                    abstractC6201c2.mo11088a(true);
                                    return;
                                }
                                return;
                            }
                            C5957b.m11958a().m11957a("tp reg failed");
                            C5906a.m12074b(new AbstractC6201c<Void>() { // from class: com.mob.mcl.b.a.5.2
                                @Override // com.mob.tools.utils.AbstractC6201c
                                /* renamed from: a  reason: avoid collision after fix types in other method */
                                public void mo11088a(Void r2) {
                                    C5906a.f14561b.set(false);
                                    if (AbstractC6201c.this != null) {
                                        AbstractC6201c.this.mo11088a(false);
                                    }
                                }
                            });
                        }
                    });
                } else {
                    C5957b.m11958a().m11957a("tp reg avail false");
                }
            }
            m12074b(new AbstractC6201c<Void>() { // from class: com.mob.mcl.b.a.6
                @Override // com.mob.tools.utils.AbstractC6201c
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo11088a(Void r2) {
                    C5906a.f14561b.set(false);
                    AbstractC6201c abstractC6201c2 = AbstractC6201c.this;
                    if (abstractC6201c2 != null) {
                        abstractC6201c2.mo11088a(false);
                    }
                }
            });
        } finally {
            try {
            } finally {
            }
        }
    }

    /* renamed from: d */
    private static void m12070d() {
        try {
        } finally {
            try {
            } finally {
            }
        }
        if (f14561b.getAndSet(true)) {
            return;
        }
        boolean isInMainProcess = C6152DH.SyncMtd.isInMainProcess();
        C5957b m11958a = C5957b.m11958a();
        m11958a.m11954b("rgs tp, main p: " + isInMainProcess);
        if (isInMainProcess) {
            if (!C5941h.m11988b().f14620f) {
                f14561b.set(false);
            } else if (C5941h.m11988b().m11975d()) {
                C5941h.m11988b().m12013a(3000, new AbstractC6201c<Boolean>() { // from class: com.mob.mcl.b.a.7
                    @Override // com.mob.tools.utils.AbstractC6201c
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void mo11088a(Boolean bool) {
                        if (bool.booleanValue()) {
                            C5926b.m12045a();
                            C5906a.f14561b.set(false);
                            return;
                        }
                        C5957b.m11958a().m11957a("tp reg failed");
                    }
                });
            } else {
                f14560a.execute(new Runnable() { // from class: com.mob.mcl.b.a.8
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (C5941h.m11988b().m11979c()) {
                                return;
                            }
                            if (!C5941h.m11988b().m11975d()) {
                                C5941h.m11988b().m11973f();
                            }
                            C5941h.m11988b().m11997a(new AbstractC6201c<Boolean>() { // from class: com.mob.mcl.b.a.8.1
                                @Override // com.mob.tools.utils.AbstractC6201c
                                /* renamed from: a  reason: avoid collision after fix types in other method */
                                public void mo11088a(Boolean bool) {
                                    if (C5941h.m11988b().f14622h) {
                                        return;
                                    }
                                    C5906a.m12074b(new AbstractC6201c<Void>() { // from class: com.mob.mcl.b.a.8.1.1
                                        @Override // com.mob.tools.utils.AbstractC6201c
                                        /* renamed from: a  reason: avoid collision after fix types in other method */
                                        public void mo11088a(Void r1) {
                                        }
                                    });
                                }
                            });
                        } catch (Throwable unused) {
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m12074b(final AbstractC6201c<Void> abstractC6201c) {
        if (!C5903a.m12109a().m12101b()) {
            C5903a.m12109a().m12104a(new AbstractC6201c<Void>() { // from class: com.mob.mcl.b.a.9
                @Override // com.mob.tools.utils.AbstractC6201c
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo11088a(Void r2) {
                    AbstractC6201c abstractC6201c2 = AbstractC6201c.this;
                    if (abstractC6201c2 != null) {
                        abstractC6201c2.mo11088a(null);
                    }
                }
            });
        } else if (abstractC6201c != null) {
            abstractC6201c.mo11088a(null);
        }
    }

    /* renamed from: a */
    public static String m12078a(boolean z, String str, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2, NetworkHelper.NetworkTimeOut networkTimeOut) throws Throwable {
        CopyOnWriteArraySet<String> copyOnWriteArraySet;
        if (!z && (copyOnWriteArraySet = f14563d) != null) {
            Iterator<String> it = copyOnWriteArraySet.iterator();
            while (it.hasNext()) {
                if (str.equals(it.next())) {
                    String httpGetNew = f14562c.httpGetNew(str, hashMap, hashMap2, networkTimeOut);
                    C5957b.m11958a().m11957a("mcl htp");
                    return httpGetNew;
                }
            }
        }
        if (hashMap != null) {
            String m12080a = m12080a(hashMap);
            if (m12080a.length() > 0) {
                str = str + "?" + m12080a;
            }
            hashMap = null;
        }
        if (C5941h.m11988b().m11974e()) {
            if (!C5941h.m11988b().m11979c()) {
                m12070d();
            }
            if (C5941h.m11988b().m11979c()) {
                HashMap<String, Object> m12015a = C5941h.m11988b().m12015a(1004, networkTimeOut.readTimout, m12083a("GET", str, hashMap2, null));
                if (m12015a != null) {
                    C5957b.m11958a().m11957a("mcl tp");
                    return HashonHelper.fromHashMap(m12015a);
                } else if (!z) {
                    String httpGetNew2 = f14562c.httpGetNew(str, hashMap, hashMap2, networkTimeOut);
                    C5957b.m11958a().m11957a("mcl htp");
                    return httpGetNew2;
                }
            }
        }
        if (z) {
            return null;
        }
        if (!C5903a.m12109a().m12101b()) {
            m12075b(5, 0);
        }
        return m12082a(str, hashMap2, networkTimeOut);
    }

    /* renamed from: a */
    public static void m12079a(boolean z, String str, HashMap<String, String> hashMap, StringPart stringPart, int i, HttpResponseCallback httpResponseCallback, NetworkHelper.NetworkTimeOut networkTimeOut) throws Throwable {
        CopyOnWriteArraySet<String> copyOnWriteArraySet;
        if (!z && (copyOnWriteArraySet = f14563d) != null) {
            Iterator<String> it = copyOnWriteArraySet.iterator();
            while (it.hasNext()) {
                if (str.equals(it.next())) {
                    f14562c.rawPost(str, hashMap, stringPart, i, httpResponseCallback, networkTimeOut);
                    return;
                }
            }
        }
        if (C5941h.m11988b().m11974e()) {
            if (!C5941h.m11988b().m11979c()) {
                m12070d();
            }
            if (C5941h.m11988b().m11979c()) {
                HashMap<String, Object> m12015a = C5941h.m11988b().m12015a(1004, networkTimeOut.readTimout, m12083a("POST", str, hashMap, stringPart));
                if (m12015a != null) {
                    httpResponseCallback.onResponse(C5901a.m12111a(new C5923b(m12015a)));
                    return;
                } else if (!z) {
                    f14562c.rawPost(str, hashMap, stringPart, i, httpResponseCallback, networkTimeOut);
                    return;
                }
            }
        }
        if (z) {
            httpResponseCallback.onResponse(null);
            return;
        }
        if (!C5903a.m12109a().m12101b()) {
            m12075b(5, 0);
        }
        m12081a(str, hashMap, stringPart, i, httpResponseCallback, networkTimeOut);
    }

    /* renamed from: a */
    private static String m12083a(String str, String str2, HashMap<String, String> hashMap, StringPart stringPart) {
        HashMap hashMap2 = new HashMap();
        hashMap2.put("type", str);
        hashMap2.put("url", str2);
        HashMap hashMap3 = new HashMap();
        if (hashMap != null) {
            hashMap3.putAll(hashMap);
        }
        hashMap2.put("headers", hashMap3);
        if (stringPart != null) {
            hashMap2.put("body", stringPart.toString());
        }
        return HashonHelper.fromHashMap(hashMap2);
    }

    /* renamed from: a */
    private static String m12082a(String str, HashMap<String, String> hashMap, NetworkHelper.NetworkTimeOut networkTimeOut) throws Throwable {
        String m12102a = C5903a.m12109a().m12102a("GET", str, hashMap, null, 0, networkTimeOut);
        if (!TextUtils.isEmpty(m12102a)) {
            C5957b.m11958a().m11957a("mcl apc");
            return m12102a;
        }
        String httpGetNew = f14562c.httpGetNew(str, null, hashMap, networkTimeOut);
        C5957b.m11958a().m11957a("mcl htp");
        return httpGetNew;
    }

    /* renamed from: a */
    private static void m12081a(String str, HashMap<String, String> hashMap, StringPart stringPart, int i, HttpResponseCallback httpResponseCallback, NetworkHelper.NetworkTimeOut networkTimeOut) throws Throwable {
        String m12102a = C5903a.m12109a().m12102a("POST", str, hashMap, stringPart, i, networkTimeOut);
        if (!TextUtils.isEmpty(m12102a)) {
            httpResponseCallback.onResponse(C5901a.m12111a(new C5923b(HashonHelper.fromJson(m12102a), true)));
        } else {
            f14562c.rawPost(str, hashMap, stringPart, i, httpResponseCallback, networkTimeOut);
        }
    }

    /* renamed from: a */
    private static String m12080a(HashMap<String, Object> hashMap) throws Throwable {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            String urlEncode = Data.urlEncode(entry.getKey(), "utf-8");
            String urlEncode2 = entry.getValue() == null ? "" : Data.urlEncode(String.valueOf(entry.getValue()), "utf-8");
            if (sb.length() > 0) {
                sb.append('&');
            }
            sb.append(urlEncode);
            sb.append('=');
            sb.append(urlEncode2);
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static String m12094a() {
        C5941h.m11988b().m11972g();
        return C5941h.m11988b().f14624j;
    }

    /* renamed from: a */
    public static void m12087a(OnIdChangeListener onIdChangeListener) {
        C5941h.m11988b().m11998a(onIdChangeListener);
        C5941h.m11988b().m11972g();
    }

    /* renamed from: b */
    public static long m12077b() {
        C5941h.m11988b().m11972g();
        return C5941h.m11988b().f14625k;
    }

    /* renamed from: a */
    public static void m12084a(String str, long j, final AbstractC6201c<Boolean> abstractC6201c) {
        C5941h.m11988b().m11994a(str, j);
        try {
            if (C5941h.m11988b().m11975d()) {
                if (!C5941h.m11988b().m11979c()) {
                    m12090a(3000, new AbstractC6201c<Boolean>() { // from class: com.mob.mcl.b.a.10
                        @Override // com.mob.tools.utils.AbstractC6201c
                        /* renamed from: a  reason: avoid collision after fix types in other method */
                        public void mo11088a(Boolean bool) {
                            C5941h.m11988b().m11983b(new AbstractC6201c<Boolean>() { // from class: com.mob.mcl.b.a.10.1
                                @Override // com.mob.tools.utils.AbstractC6201c
                                /* renamed from: a  reason: avoid collision after fix types in other method */
                                public void mo11088a(Boolean bool2) {
                                    if (AbstractC6201c.this != null) {
                                        AbstractC6201c.this.mo11088a(bool2);
                                    }
                                }
                            });
                        }
                    });
                } else {
                    C5941h.m11988b().m11983b(new AbstractC6201c<Boolean>() { // from class: com.mob.mcl.b.a.2
                        @Override // com.mob.tools.utils.AbstractC6201c
                        /* renamed from: a  reason: avoid collision after fix types in other method */
                        public void mo11088a(Boolean bool) {
                            AbstractC6201c abstractC6201c2 = AbstractC6201c.this;
                            if (abstractC6201c2 != null) {
                                abstractC6201c2.mo11088a(bool);
                            }
                        }
                    });
                }
            } else if (abstractC6201c != null) {
                abstractC6201c.mo11088a(false);
            }
        } catch (Throwable th) {
            C5957b.m11958a().m11955a(th);
            if (abstractC6201c != null) {
                abstractC6201c.mo11088a(false);
            }
        }
    }

    /* renamed from: a */
    public static void m12088a(MobMCL.ELPMessageListener eLPMessageListener) {
        C5941h.m11988b().m12005a(eLPMessageListener);
    }

    /* renamed from: a */
    public static void m12091a(int i, BusinessMessageListener businessMessageListener) {
        C5941h.m11988b().m12014a(i, businessMessageListener);
    }

    /* renamed from: com.mob.mcl.b.a$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class C5922a implements C5688b.InterfaceC5690b {
        private C5922a() {
        }

        @Override // com.mob.apc.C5688b.InterfaceC5690b
        /* renamed from: a */
        public C5677a mo11904a(String str, C5677a c5677a, long j) {
            C5677a m12103a = C5903a.m12109a().m12103a(str, c5677a);
            if (m12103a != null) {
                try {
                    int i = 1;
                    if (m12103a.f13999d != null && (m12103a.f13999d instanceof C5905b)) {
                        C5905b c5905b = (C5905b) m12103a.f13999d;
                        NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
                        networkTimeOut.readTimout = c5905b.f14558f;
                        networkTimeOut.connectionTimeout = c5905b.f14559g;
                        if ("POST".equals(c5905b.f14553a)) {
                            m12103a.f13999d = null;
                            C5906a.m12079a(true, c5905b.f14554b, C5905b.m12096a(c5905b.f14555c), new StringPart().append(c5905b.f14556d), c5905b.f14557e, C5901a.m12110a(c5905b.f14554b, m12103a), networkTimeOut);
                        } else if ("GET".equals(c5905b.f14553a)) {
                            C5903a.m12109a().m12099b(C5906a.m12078a(true, c5905b.f14554b, null, C5905b.m12096a(c5905b.f14555c), networkTimeOut), m12103a);
                        }
                    } else if (m12103a.f13996a == 9004) {
                        String string = c5677a.f14000e.getString("data");
                        long j2 = c5677a.f14000e.getLong("uniqueId");
                        if (!TextUtils.isEmpty(string)) {
                            HashMap fromJson = HashonHelper.fromJson(C5941h.m11988b().m11993a(C5941h.m11988b().m11971h(), string));
                            String str2 = (String) fromJson.get("workId");
                            int m11990a = C5941h.m11990a(fromJson, "expire", 0);
                            boolean z = C5941h.m11990a(fromJson, "needRepeat", 0) == 1;
                            int m11990a2 = C5941h.m11990a(fromJson, "type", 0);
                            String str3 = (String) fromJson.get("data");
                            if (m11990a2 != 1 && m11990a2 != 2) {
                                boolean m12010a = C5941h.m11988b().m12010a(j2, str2, m11990a, m11990a2, str3);
                                Bundle bundle = new Bundle();
                                bundle.putBoolean("needRepeat", z);
                                if (!m12010a) {
                                    i = 0;
                                }
                                bundle.putInt("repeat", i);
                                m12103a.f14000e = bundle;
                            }
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("data", str3);
                            bundle2.putString("workId", str2);
                            bundle2.putLong("uniqueId", c5677a.f14000e.getLong("uniqueId"));
                            bundle2.putInt("expire", m11990a);
                            bundle2.putInt("msgType", m11990a2);
                            int m12007a = C5941h.m11988b().m12007a(bundle2);
                            Bundle bundle3 = new Bundle();
                            bundle3.putBoolean("needRepeat", z);
                            bundle3.putInt("repeat", m12007a);
                            m12103a.f14000e = bundle3;
                        }
                    }
                } catch (Throwable th) {
                    C5957b.m11958a().m11955a(th);
                }
            }
            return m12103a;
        }
    }
}
