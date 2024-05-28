package com.mob.mcl.p233a;

import android.content.Context;
import android.os.Bundle;
import com.mob.MobSDK;
import com.mob.apc.C5677a;
import com.mob.apc.C5688b;
import com.mob.mcl.p235c.C5941h;
import com.mob.mcl.p236d.C5957b;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.StringPart;
import com.mob.tools.utils.AbstractC6201c;
import com.mob.tools.utils.C6152DH;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* renamed from: com.mob.mcl.a.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5903a {

    /* renamed from: a */
    private static volatile C5903a f14547a;

    /* renamed from: b */
    private Set<String> f14548b;

    /* renamed from: c */
    private String f14549c;

    /* renamed from: a */
    public static C5903a m12109a() {
        if (f14547a == null) {
            synchronized (C5903a.class) {
                if (f14547a == null) {
                    f14547a = new C5903a();
                }
            }
        }
        return f14547a;
    }

    private C5903a() {
    }

    /* renamed from: a */
    public void m12107a(Context context, C5688b.InterfaceC5690b interfaceC5690b) {
        this.f14549c = "MobMCL";
        C5688b.m12831a(context);
        boolean isInMainProcess = C6152DH.SyncMtd.isInMainProcess();
        C5957b m11958a = C5957b.m11958a();
        m11958a.m11954b("init apc, main p: " + isInMainProcess);
        if (isInMainProcess) {
            C5688b.m12827a(this.f14549c, interfaceC5690b);
        }
    }

    /* renamed from: b */
    public boolean m12101b() {
        Set<String> set = this.f14548b;
        return set != null && set.size() > 0;
    }

    /* renamed from: a */
    public void m12104a(final AbstractC6201c<Void> abstractC6201c) {
        if (!C5941h.m11988b().f14623i) {
            if (abstractC6201c != null) {
                abstractC6201c.mo11088a(null);
                return;
            }
            return;
        }
        final ArrayList arrayList = new ArrayList();
        boolean isInMainProcess = C6152DH.SyncMtd.isInMainProcess();
        C5957b m11958a = C5957b.m11958a();
        m11958a.m11954b("qy tp svc, main p: " + isInMainProcess);
        if (!isInMainProcess) {
            arrayList.add(MobSDK.getContext().getPackageName());
        }
        C5688b.m12828a(new AbstractC6201c<List<String>>() { // from class: com.mob.mcl.a.a.1
            @Override // com.mob.tools.utils.AbstractC6201c
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo11088a(List<String> list) {
                arrayList.addAll(list);
                C5957b m11958a2 = C5957b.m11958a();
                m11958a2.m11954b("qy : " + arrayList.toString());
                C5903a.this.f14548b = new LinkedHashSet();
                for (String str : arrayList) {
                    C5677a c5677a = new C5677a();
                    c5677a.f13996a = 1;
                    try {
                        C5957b m11958a3 = C5957b.m11958a();
                        m11958a3.m11954b("sd apc mg : " + c5677a.toString() + " to ->" + str);
                        C5677a m12832a = C5688b.m12832a(1, str, C5903a.this.f14549c, c5677a, 5000L);
                        if (m12832a != null && m12832a.f14000e != null && m12832a.f13996a == 1 && m12832a.f14000e.getBoolean("isTcpAvailable")) {
                            C5903a.this.f14548b.add(str);
                        }
                    } catch (Throwable th) {
                        C5957b m11958a4 = C5957b.m11958a();
                        m11958a4.m11954b("query tcp exp : " + th.getMessage());
                    }
                }
                C5957b m11958a5 = C5957b.m11958a();
                m11958a5.m11954b("apc available pg : " + C5903a.this.f14548b.toString());
                AbstractC6201c abstractC6201c2 = abstractC6201c;
                if (abstractC6201c2 != null) {
                    abstractC6201c2.mo11088a(null);
                }
            }
        });
    }

    /* renamed from: a */
    public C5677a m12108a(int i, Bundle bundle, String str, int i2) {
        try {
            C5677a c5677a = new C5677a();
            c5677a.f13996a = i;
            c5677a.f14000e = bundle;
            C5957b m11958a = C5957b.m11958a();
            m11958a.m11954b("apc fw mg : " + i + " " + c5677a.toString() + " to ->" + str);
            return C5688b.m12832a(1, str, this.f14549c, c5677a, i2);
        } catch (Throwable th) {
            C5957b.m11958a().m11955a(th);
            return null;
        }
    }

    /* renamed from: a */
    public String m12102a(String str, String str2, HashMap<String, String> hashMap, StringPart stringPart, int i, NetworkHelper.NetworkTimeOut networkTimeOut) throws Throwable {
        C5677a m12832a;
        if (m12101b()) {
            ArrayList<String> arrayList = new ArrayList();
            arrayList.addAll(this.f14548b);
            for (String str3 : arrayList) {
                C5677a c5677a = new C5677a();
                c5677a.f13996a = 2;
                c5677a.f14000e = C5905b.m12095a(str, str2, hashMap, stringPart, i, networkTimeOut);
                try {
                    C5957b m11958a = C5957b.m11958a();
                    m11958a.m11954b("apc sd mg : " + c5677a.toString() + " to ->" + str3);
                    m12832a = C5688b.m12832a(1, str3, this.f14549c, c5677a, (long) networkTimeOut.readTimout);
                } catch (Throwable th) {
                    C5957b.m11958a().m11955a(th);
                }
                if (m12832a != null && m12832a.f13996a == 2 && m12832a.f14000e != null) {
                    Bundle bundle = m12832a.f14000e;
                    C5957b m11958a2 = C5957b.m11958a();
                    m11958a2.m11954b("apc receive rp mg : " + bundle.getString("data"));
                    str = bundle.getString("data");
                    return str;
                }
                C5957b m11958a3 = C5957b.m11958a();
                m11958a3.m11954b("apc receive rp : " + m12832a);
            }
            return null;
        }
        C5957b.m11958a().m11957a("apc list is null");
        return null;
    }

    /* renamed from: a */
    public C5677a m12103a(String str, C5677a c5677a) {
        C5957b m11958a = C5957b.m11958a();
        m11958a.m11957a("apc received mg " + c5677a + " from -> " + str);
        if (c5677a != null) {
            C5677a c5677a2 = new C5677a();
            c5677a2.f13996a = c5677a.f13996a;
            if (c5677a.f13996a == 1) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("isTcpAvailable", C5941h.m11988b().m11974e());
                c5677a2.f14000e = bundle;
                return c5677a2;
            } else if (c5677a.f13996a == 2) {
                c5677a2.f13999d = C5905b.m12097a(c5677a.f14000e);
                return c5677a2;
            } else if (c5677a.f13996a == 9004) {
                return c5677a2;
            } else {
                return null;
            }
        }
        return null;
    }

    /* renamed from: b */
    public void m12099b(String str, C5677a c5677a) {
        if (str != null) {
            Bundle bundle = new Bundle();
            bundle.putString("data", str);
            c5677a.f14000e = bundle;
        }
    }
}
