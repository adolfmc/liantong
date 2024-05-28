package com.mob.commons.p231cc;

import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import com.mob.commons.C5868q;
import com.mob.commons.p231cc.C5802j;
import com.mob.commons.p231cc.C5816u;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.network.NetCommunicator;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.MobPersistence;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.commons.cc.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5791a {

    /* renamed from: a */
    private static final C5809o f14274a = new C5809o();

    /* renamed from: b */
    private static final C5799g f14275b = new C5799g();

    /* renamed from: c */
    private static final C5806l f14276c = new C5806l();

    /* renamed from: d */
    private static volatile C5802j f14277d;

    /* renamed from: e */
    private static volatile C5802j f14278e;

    static {
        try {
            f14277d = new C5802j(new C5802j.InterfaceC5803a() { // from class: com.mob.commons.cc.a.1
                @Override // com.mob.commons.p231cc.C5802j.InterfaceC5803a
                /* renamed from: a */
                public Object mo12440a(String str, ArrayList<Object> arrayList) {
                    try {
                        if (C5791a.f14278e != null) {
                            return C5791a.f14278e.m12441a(str, arrayList);
                        }
                        return null;
                    } catch (Throwable unused) {
                        return null;
                    }
                }
            });
            f14278e = new C5802j(new C5802j.InterfaceC5803a() { // from class: com.mob.commons.cc.a.2
                @Override // com.mob.commons.p231cc.C5802j.InterfaceC5803a
                /* renamed from: a */
                public Object mo12440a(String str, ArrayList<Object> arrayList) {
                    return str + "" + arrayList;
                }
            });
            f14277d.m12441a("tt", null);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public static int m12462a() {
        return C5816u.m12390a();
    }

    /* renamed from: a */
    public static void m12459a(Context context, byte[] bArr, String str, Method method) throws Throwable {
        m12458a(C5816u.m12387a(bArr), context, str, method);
    }

    /* renamed from: a */
    public static void m12461a(Context context, String str, String str2, Method method) throws Throwable {
        m12458a(C5816u.m12388a(str), context, str2, method);
    }

    /* renamed from: a */
    public static void m12460a(Context context, String str, String str2, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) throws Throwable {
        C5816u.C5820c m12388a = C5816u.m12388a(str);
        m12388a.m12379a("ss_dhMap", hashMap).m12371a("ss_dataMaps", hashMap2);
        m12458a(m12388a, context, str2, (Method) null);
    }

    /* renamed from: a */
    public static LinkedList<Object> m12457a(Object obj, Object... objArr) throws Throwable {
        return ((C5824w) obj).mo12344b(objArr);
    }

    /* renamed from: a */
    private static void m12458a(C5816u.C5820c c5820c, Context context, String str, Method method) throws Throwable {
        c5820c.m12380a(C5868q.m12203b("012Pfk8ieYci^ch(chdcEd!diZeh"), C5799g.class).m12372a(C5868q.m12203b("003>gbdgdj"), C5795c.class).m12372a("SBSP", C5806l.class).m12372a(C5868q.m12203b("0156dieheiNcd.cbXfe(cidjTgNci?ec!cb"), MobHandlerThread.class).m12372a(C5868q.m12203b("019Ydiehehcidc!c]cb9bc^egAhFfg:ebe@chcc e2ci"), C5798f.class).m12372a(C5868q.m12203b("017HdiehfidcJdhedhWfgUe0egdc$f;cc-e+ci"), C5801i.class).m12372a(C5868q.m12203b("019QdiehdiSe=ciccch%be[fidcPddebhBchdc7d"), ServiceConnectionC5807m.class).m12372a(C5868q.m12203b("0177diehfidcCdhedhWfkedegDe>ciccTe6ci"), C5800h.class).m12372a(C5868q.m12203b("017Qdiehdg.eh3eedccickfiVcff!ed+cb<ck"), C5804k.class).m12372a(C5868q.m12203b("0094dieheiNcdMcbIfeXci"), C5802j.class).m12372a(C5868q.m12203b("0033gbdgfi"), NetCommunicator.class).m12372a(C5868q.m12203b("004Jgbdgdjfk"), NetworkHelper.NetworkTimeOut.class).m12372a("NoVaDataException", MobPersistence.NoValidDataException.class).m12375a(C5798f.class, C5798f.class).m12375a(C5800h.class, C5800h.class).m12375a(ServiceConnectionC5807m.class, C5808n.class).m12375a(C5804k.class, C5804k.class).m12375a(C5809o.class, C5809o.class).m12375a(C5799g.class, C5799g.class).m12375a(C5795c.class, C5795c.class).m12375a(Context.class, C5794b.class).m12375a(PackageManager.class, C5797e.class).m12375a(NotificationManager.class, C5796d.class).m12375a(C5806l.class, C5806l.class).m12371a("ss_sdh", f14276c).m12371a("ss_opSet", f14275b).m12371a("ss_suls", f14274a).m12371a(C5868q.m12203b("0151egegcgHb*dcEdheSdb4h6fj8cLciBcDce"), context).m12371a(C5868q.m12203b("014Jegegcgeg8hc,ciRhNfj+c]ciGcIceeg"), str).m12371a(C5868q.m12203b("012Cegegcgeg_hc$ci4h@djchce1e"), Long.valueOf(System.currentTimeMillis())).m12371a(C5868q.m12203b("006Zegegcgcecb;i"), method).m12373a(C5868q.m12203b("016b4dccecedcYdci'ecegcbckec-bFcb*b"));
        c5820c.m12382a();
    }
}
