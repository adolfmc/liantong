package com.mob.apc.p228a;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.apc.APCException;
import com.mob.apc.C5677a;
import com.mob.apc.C5688b;
import com.mob.commons.C5849j;
import com.mob.tools.utils.AbstractC6201c;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.ReflectHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.mob.apc.a.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5682c {

    /* renamed from: a */
    private static C5682c f14014a = new C5682c();

    /* renamed from: i */
    private static final String[] f14015i = {"com.mob.service.action.MOB_AC_SERVICE"};

    /* renamed from: f */
    private C5688b.InterfaceC5691c f14020f;

    /* renamed from: g */
    private Bundle f14021g;

    /* renamed from: h */
    private C5688b.InterfaceC5689a f14022h;

    /* renamed from: b */
    private HashMap<String, C5688b.InterfaceC5690b> f14016b = new HashMap<>();

    /* renamed from: c */
    private ServiceConnectionC5680b f14017c = new ServiceConnectionC5680b();

    /* renamed from: e */
    private byte[] f14019e = new byte[0];

    /* renamed from: d */
    private HashMap<String, C5686e> f14018d = new HashMap<>();

    private C5682c() {
    }

    /* renamed from: a */
    public static C5682c m12850a() {
        return f14014a;
    }

    /* renamed from: a */
    public void m12843a(String str, C5688b.InterfaceC5690b interfaceC5690b) {
        C5687f.m12837a().m12834b("[addMobIpcMsgListener] %s", str);
        this.f14016b.put(str, interfaceC5690b);
        synchronized (this.f14019e) {
            if (this.f14018d.containsKey(str)) {
                C5687f.m12837a().m12834b("[addMobIpcMsgListener] %s", "buf msg found, callback right now");
                C5686e remove = this.f14018d.remove(str);
                interfaceC5690b.mo11904a(remove.f14029c, remove.f14027a, remove.f14031e);
            }
        }
    }

    /* renamed from: a */
    public C5677a m12849a(int i, String str, String str2, C5677a c5677a, long j) throws Throwable {
        boolean m12253b = C5849j.m12264a().m12253b();
        C5687f m12837a = C5687f.m12837a();
        m12837a.m12836a("[EC] isClear snd mg: " + m12253b, new Object[0]);
        if (!m12253b) {
            throw new APCException("ec is not clear");
        }
        if (TextUtils.isEmpty(str)) {
            C5687f.m12837a().m12834b("[sendMessage] pkg not allowed null.", new Object[0]);
            throw new APCException("pkg not allowed null.");
        } else if (c5677a == null) {
            C5687f.m12837a().m12834b("[sendMessage] param not allowed null.", new Object[0]);
            throw new APCException("param not allowed null.");
        } else if (i == 1) {
            return this.f14017c.m12851a(str, str2, c5677a, j);
        } else {
            C5687f m12837a2 = C5687f.m12837a();
            m12837a2.m12834b("type " + i + " not support.", new Object[0]);
            throw new APCException("type " + i + " not support.");
        }
    }

    /* renamed from: a */
    public void m12844a(final AbstractC6201c<List<String>> abstractC6201c) {
        final ArrayList arrayList = new ArrayList();
        try {
            boolean m12253b = C5849j.m12264a().m12253b();
            C5687f m12837a = C5687f.m12837a();
            m12837a.m12836a("[EC] isClear apcsvcl: " + m12253b, new Object[0]);
            if (m12253b) {
                ReflectHelper.importClass("android.content.Intent");
                C6152DH.requester(MobSDK.getContext()).queryIntentServices((Intent) ReflectHelper.newInstance("Intent", f14015i[0]), 0).request(new C6152DH.DHResponder() { // from class: com.mob.apc.a.c.1
                    @Override // com.mob.tools.utils.C6152DH.DHResponder
                    public void onResponse(C6152DH.DHResponse dHResponse) {
                        List<ResolveInfo> queryIntentServices = dHResponse.queryIntentServices(new int[0]);
                        if (queryIntentServices != null) {
                            for (ResolveInfo resolveInfo : queryIntentServices) {
                                String str = resolveInfo.serviceInfo.packageName;
                                if (resolveInfo.serviceInfo.exported && !C5688b.m12833a().getPackageName().equals(str)) {
                                    arrayList.add(resolveInfo.serviceInfo.packageName);
                                }
                            }
                        }
                        C5687f.m12837a().m12834b("[getMAPCServiceList] list: %s", arrayList);
                        AbstractC6201c abstractC6201c2 = abstractC6201c;
                        if (abstractC6201c2 != null) {
                            abstractC6201c2.mo11088a(arrayList);
                        }
                    }
                });
            } else if (abstractC6201c != null) {
                abstractC6201c.mo11088a(arrayList);
            }
        } catch (Throwable th) {
            C5687f.m12837a().m12835a(th);
            if (abstractC6201c != null) {
                abstractC6201c.mo11088a(arrayList);
            }
        }
    }

    /* renamed from: a */
    public C5686e m12847a(C5686e c5686e) {
        try {
            C5688b.InterfaceC5690b interfaceC5690b = this.f14016b.get(c5686e.f14028b);
            C5687f.m12837a().m12834b("[onAIDLMessageReceive] innerMessage: %s, listener: %s", c5686e, interfaceC5690b);
            if (interfaceC5690b != null) {
                C5677a mo11904a = interfaceC5690b.mo11904a(c5686e.f14029c, c5686e.f14027a, c5686e.f14031e);
                C5687f.m12837a().m12834b("[onAIDLMessageReceive] listener apcMessage: %s", mo11904a);
                return new C5686e(mo11904a, c5686e.f14028b, c5686e.f14031e);
            }
            C5687f.m12837a().m12834b("[onAIDLMessageReceive] No listener detected, buffer this msg", new Object[0]);
            this.f14018d.put(c5686e.f14028b, c5686e);
            return null;
        } catch (Throwable th) {
            C5687f.m12837a().m12834b("[onAIDLMessageReceive] exception %s", th.getMessage());
            C5687f.m12837a().m12835a(th);
            return null;
        }
    }

    /* renamed from: a */
    public void m12845a(C5688b.InterfaceC5691c interfaceC5691c) {
        C5687f.m12837a().m12834b("[addOnACServiceListener] %s", "done");
        this.f14020f = interfaceC5691c;
        if (this.f14021g != null) {
            C5687f.m12837a().m12834b("[addOnACServiceListener] %s", "bufBundle detected, callback");
            this.f14020f.mo11917a(new Bundle(this.f14021g));
            this.f14020f = null;
            return;
        }
        C5687f.m12837a().m12834b("[addOnACServiceListener] %s", "no bufBundle, nothing to do");
    }

    /* renamed from: a */
    public void m12846a(C5688b.InterfaceC5689a interfaceC5689a) {
        C5687f.m12837a().m12834b("[addMgsRequestListener] %s", "done");
        this.f14022h = interfaceC5689a;
    }

    /* renamed from: b */
    public C5688b.InterfaceC5689a m12842b() {
        return this.f14022h;
    }

    /* renamed from: a */
    public void m12848a(Bundle bundle) {
        if (this.f14020f != null) {
            C5687f.m12837a().m12834b("[onACServiceAct] %s", "listener detected, callback");
            this.f14020f.mo11917a(bundle);
            return;
        }
        C5687f.m12837a().m12834b("[onACServiceAct] %s", "no listener detected, cache");
        this.f14021g = new Bundle(bundle);
    }
}
