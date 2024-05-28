package com.mob.tools.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.GnssStatus;
import android.os.Build;
import android.os.Handler;
import com.mob.MobSDK;
import com.mob.commons.C5829d;
import com.mob.commons.C5855l;
import com.mob.commons.CSCenter;
import com.mob.commons.p229a.C5731l;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.p237a.C6034e;
import com.mob.tools.p237a.C6044i;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.ReflectHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint({"MissingPermission"})
/* renamed from: com.mob.tools.utils.d */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C6202d {

    /* renamed from: a */
    private static C6202d f15300a;

    /* renamed from: b */
    private volatile Object f15301b;

    /* renamed from: c */
    private volatile Object f15302c;

    /* renamed from: d */
    private volatile Object f15303d;

    /* renamed from: e */
    private volatile Object f15304e = m11066f();

    /* renamed from: f */
    private volatile Class<?> f15305f;

    /* renamed from: g */
    private long f15306g;

    /* renamed from: h */
    private InterfaceC6211a f15307h;

    /* renamed from: i */
    private volatile Object f15308i;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.tools.utils.d$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC6211a {
        /* renamed from: a */
        void mo11054a();
    }

    private C6202d() {
    }

    /* renamed from: a */
    public static C6202d m11087a() {
        if (f15300a == null) {
            synchronized (C6202d.class) {
                if (f15300a == null) {
                    f15300a = new C6202d();
                }
            }
        }
        return f15300a;
    }

    /* renamed from: a */
    public Object m11086a(Context context, int i, int i2, boolean z) {
        return m11085a(context, i, i2, z, false);
    }

    /* renamed from: a */
    public void m11083a(InterfaceC6211a interfaceC6211a) {
        this.f15307h = interfaceC6211a;
        m11069d();
    }

    /* renamed from: d */
    private void m11069d() {
        if (C5829d.m12325g()) {
            try {
                if (C6152DH.SyncMtd.checkPermission(C5855l.m12238a("039fg!feflgffkfehf?lhRflfhfkhjhjfkgf_g7hfgnililiiglglfjhngkgjiifjheinilgngmgkingj"))) {
                    if (this.f15303d == null) {
                        this.f15303d = C6152DH.SyncMtd.getSystemServiceSafe(C5855l.m12238a("008iVgf(efk*fkgfUg"));
                    }
                    if (this.f15303d == null) {
                        return;
                    }
                    if (Build.VERSION.SDK_INT < 31) {
                        C5731l.m12681a().m12673b().post(new Runnable() { // from class: com.mob.tools.utils.d.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    Object obj = C6202d.this.f15303d;
                                    String m12238a = C5855l.m12238a("020f'fefekfMl)hjgl'kfk^fihjhefkhj$khgh^fl");
                                    Object[] objArr = {C6202d.this.m11067e()};
                                    ReflectHelper.invokeInstanceMethod(obj, m12238a, objArr, new Class[]{Class.forName(C5855l.m12238a("026fg>feflgffkfehf2iOgf'efkYfkgf6g>hfkf?lShjgl>kfk$fihj") + "$" + C5855l.m12238a("008GhefkhjPkhghFfl"))});
                                    MobLog.getInstance().m11342d("[212] rg < 31", new Object[0]);
                                } catch (Throwable th) {
                                    MobLog.getInstance().m11340d(th, "%s", "[cl]");
                                }
                            }
                        });
                        return;
                    }
                    ReflectHelper.invokeInstanceMethod(this.f15303d, C5855l.m12238a("026Pfl5h0ggfkhj)khDflkf gFhjhjgl2kfk+fihjilLfiiChgHfe3fn"), new Object[]{m11065g(), C5731l.m12681a().m12673b()}, new Class[]{GnssStatus.Callback.class, Handler.class});
                    MobLog.getInstance().m11342d("[212] rg >= 31", new Object[0]);
                }
            } catch (Throwable th) {
                MobLog.getInstance().m11340d(th, "%s", "[212]");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public Object m11067e() throws Throwable {
        HashMap hashMap = new HashMap();
        final int identityHashCode = System.identityHashCode(hashMap);
        hashMap.put(C5855l.m12238a("018_gf3gWkf8l5hjgl!kfkYfihjil6jfgYggJhMfe"), new ReflectHelper.InterfaceC6184a<Object[], Object>() { // from class: com.mob.tools.utils.d.2
            @Override // com.mob.tools.utils.ReflectHelper.InterfaceC6184a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public Object mo11056a(Object[] objArr) {
                if (objArr == null || ((Integer) objArr[0]).intValue() != 4) {
                    return null;
                }
                C6202d.this.m11064h();
                return null;
            }
        });
        hashMap.put("equals", new ReflectHelper.InterfaceC6184a<Object[], Object>() { // from class: com.mob.tools.utils.d.3
            @Override // com.mob.tools.utils.ReflectHelper.InterfaceC6184a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public Object mo11056a(Object[] objArr) {
                if (objArr == null || objArr[0] == null) {
                    return false;
                }
                return Boolean.valueOf(objArr[0].hashCode() == identityHashCode);
            }
        });
        hashMap.put(C5855l.m12238a("008jf%hj)j=ilgffeRh"), new ReflectHelper.InterfaceC6184a<Object[], Object>() { // from class: com.mob.tools.utils.d.4
            @Override // com.mob.tools.utils.ReflectHelper.InterfaceC6184a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public Object mo11056a(Object[] objArr) {
                return Integer.valueOf(identityHashCode);
            }
        });
        return ReflectHelper.createProxy((Map<String, ReflectHelper.InterfaceC6184a<Object[], Object>>) hashMap, Class.forName(C5855l.m12238a("026fg7feflgffkfehf,iIgf'efkMfkgfKg(hfkfWl$hjgl$kfk:fihj") + "$" + C5855l.m12238a("008'hefkhj*khgh]fl")));
    }

    /* renamed from: f */
    private Object m11066f() {
        HashMap hashMap = new HashMap();
        final int identityHashCode = System.identityHashCode(hashMap);
        try {
            hashMap.put(C5855l.m12238a("017)gf9gVhegf8efk;fkgfVg5ilWjfgNggWh$fe"), new ReflectHelper.InterfaceC6184a<Object[], Object>() { // from class: com.mob.tools.utils.d.5
                @Override // com.mob.tools.utils.ReflectHelper.InterfaceC6184a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public Object mo11056a(Object[] objArr) {
                    if (objArr != null) {
                        try {
                            if (objArr.length > 0) {
                                NLog mobLog = MobLog.getInstance();
                                mobLog.m11342d("[212] oncge" + objArr[0], new Object[0]);
                                if (!(objArr[0] instanceof List) || ((List) objArr[0]).size() <= 0) {
                                    C6202d.this.f15302c = objArr[0];
                                } else {
                                    List list = (List) objArr[0];
                                    C6202d.this.f15302c = list.get(list.size() - 1);
                                }
                            }
                        } catch (Throwable th) {
                            try {
                                MobLog.getInstance().m11341d(th);
                                synchronized (C6202d.this) {
                                    notifyAll();
                                    return null;
                                }
                            } catch (Throwable th2) {
                                synchronized (C6202d.this) {
                                    notifyAll();
                                    throw th2;
                                }
                            }
                        }
                    }
                    C6202d.this.m11063i();
                    synchronized (C6202d.this) {
                        notifyAll();
                    }
                    return null;
                }
            });
            hashMap.put("equals", new ReflectHelper.InterfaceC6184a<Object[], Object>() { // from class: com.mob.tools.utils.d.6
                @Override // com.mob.tools.utils.ReflectHelper.InterfaceC6184a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public Object mo11056a(Object[] objArr) {
                    NLog mobLog = MobLog.getInstance();
                    mobLog.m11342d("equals " + objArr, new Object[0]);
                    if (objArr == null || objArr[0] == null) {
                        return false;
                    }
                    return Boolean.valueOf(objArr[0].hashCode() == identityHashCode);
                }
            });
            hashMap.put(C5855l.m12238a("008jf2hjIj?ilgffe>h"), new ReflectHelper.InterfaceC6184a<Object[], Object>() { // from class: com.mob.tools.utils.d.7
                @Override // com.mob.tools.utils.ReflectHelper.InterfaceC6184a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public Object mo11056a(Object[] objArr) {
                    MobLog.getInstance().m11342d(C5855l.m12238a("008jf_hj+j3ilgffeSh"), new Object[0]);
                    return Integer.valueOf(identityHashCode);
                }
            });
            return ReflectHelper.createProxy((Map<String, ReflectHelper.InterfaceC6184a<Object[], Object>>) hashMap, m11062j());
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return null;
        }
    }

    /* renamed from: g */
    private GnssStatus.Callback m11065g() {
        if (Build.VERSION.SDK_INT >= 31) {
            return new GnssStatus.Callback() { // from class: com.mob.tools.utils.d.8
                @Override // android.location.GnssStatus.Callback
                public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
                    super.onSatelliteStatusChanged(gnssStatus);
                    C6202d.this.m11064h();
                }
            };
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public void m11064h() {
        try {
            if (this.f15307h != null) {
                this.f15307h.mo11054a();
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11340d(th, "%s", "[cl]");
        }
    }

    /* renamed from: a */
    public Object m11085a(Context context, int i, int i2, boolean z, boolean z2) {
        if (CSCenter.getInstance().isLocationDataEnable()) {
            Object m11077a = m11077a(z2);
            if (m11077a == null) {
                synchronized (C6202d.class) {
                    Object m11077a2 = m11077a(z2);
                    m11077a = m11077a2 == null ? m11075b(context, i, i2, z) : m11077a2;
                }
            }
            return m11077a;
        }
        return CSCenter.getInstance().getLocation();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0021 A[Catch: Throwable -> 0x00df, TryCatch #3 {Throwable -> 0x00df, blocks: (B:3:0x0001, B:5:0x000f, B:11:0x0021, B:13:0x0025, B:14:0x0031, B:17:0x0036, B:38:0x0080, B:41:0x0086, B:43:0x0096, B:44:0x00a2, B:46:0x00a6, B:22:0x003e, B:24:0x004c, B:31:0x0062, B:33:0x0070, B:34:0x007c), top: B:57:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0096 A[Catch: Throwable -> 0x00df, TryCatch #3 {Throwable -> 0x00df, blocks: (B:3:0x0001, B:5:0x000f, B:11:0x0021, B:13:0x0025, B:14:0x0031, B:17:0x0036, B:38:0x0080, B:41:0x0086, B:43:0x0096, B:44:0x00a2, B:46:0x00a6, B:22:0x003e, B:24:0x004c, B:31:0x0062, B:33:0x0070, B:34:0x007c), top: B:57:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a6 A[Catch: Throwable -> 0x00df, TRY_LEAVE, TryCatch #3 {Throwable -> 0x00df, blocks: (B:3:0x0001, B:5:0x000f, B:11:0x0021, B:13:0x0025, B:14:0x0031, B:17:0x0036, B:38:0x0080, B:41:0x0086, B:43:0x0096, B:44:0x00a2, B:46:0x00a6, B:22:0x003e, B:24:0x004c, B:31:0x0062, B:33:0x0070, B:34:0x007c), top: B:57:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00dd  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.Object m11075b(android.content.Context r7, int r8, int r9, boolean r10) {
        /*
            Method dump skipped, instructions count: 233
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.C6202d.m11075b(android.content.Context, int, int, boolean):java.lang.Object");
    }

    /* renamed from: b */
    private Object m11072b(String str) {
        if (Build.VERSION.SDK_INT <= 25) {
            return C6034e.m11667a(MobSDK.getContext()).m11655b(str);
        }
        try {
            return C6044i.m11632a(MobSDK.getContext(), str);
        } catch (Throwable unused) {
            return C6034e.m11667a(MobSDK.getContext()).m11655b(str);
        }
    }

    /* renamed from: a */
    private void m11084a(Context context, String str, long j) {
        if (Build.VERSION.SDK_INT <= 25) {
            m11074b(context, str, j);
            return;
        }
        try {
            Object m11631a = C6044i.m11631a(context, str, j);
            if (m11631a != null) {
                this.f15302c = m11631a;
            }
        } catch (Throwable th) {
            NLog mobLog = MobLog.getInstance();
            mobLog.m11342d("[212] cur err " + th, new Object[0]);
            m11074b(context, str, j);
        }
    }

    /* renamed from: b */
    private void m11074b(Context context, String str, long j) {
        if (C5829d.m12327e()) {
            try {
                C6034e.m11667a(context).m11662a(str, 1000L, 0.0f, this.f15304e);
                wait(j);
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
            m11063i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public void m11063i() {
        if (this.f15304e != null) {
            ReflectHelper.invokeInstanceMethod(this.f15303d, C5855l.m12238a("013,fl7h?fhgfff*hGgiRl0fe.fkh<hj"), new Object[]{this.f15304e}, new Class[]{m11062j()}, null);
        }
    }

    /* renamed from: a */
    private boolean m11079a(Object obj, String str) {
        return C5829d.m12327e() && ((Boolean) ReflectHelper.invokeInstanceMethodNoThrow(obj, C5855l.m12238a("017!fkhjimflgffffkfe1h2flii'gf-hg5ih+fe"), false, str)).booleanValue();
    }

    /* renamed from: j */
    private Class<?> m11062j() {
        if (this.f15305f == null) {
            try {
                this.f15305f = Class.forName(C5855l.m12238a("033fg7feflgffkfehfOi)gfBefkDfkgf6g:hfhegfWefkIfkgf]g?hefkhjPkhghYfl"));
            } catch (Throwable unused) {
            }
        }
        return this.f15305f;
    }

    /* renamed from: a */
    private Object m11077a(boolean z) {
        if (z) {
            return null;
        }
        try {
            if (this.f15301b == null || System.currentTimeMillis() - this.f15306g > 180000) {
                return null;
            }
            return ReflectHelper.newInstance(ReflectHelper.importClass(C5855l.m12238a("025fgOfeflgffkfehfSi>gf$efkKfkgfEg7hfhegf-efk*fkgfHg")), this.f15301b);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return null;
        }
    }

    /* renamed from: a */
    public void m11080a(Object obj) {
        if (obj != null) {
            this.f15308i = obj;
        }
    }

    /* renamed from: b */
    public Object m11076b() {
        return this.f15308i;
    }

    /* renamed from: c */
    public Object m11071c() {
        return m11072b(C5855l.m12238a("003EggWlAhj"));
    }

    /* renamed from: a */
    public boolean m11078a(String str) {
        return (C5855l.m12238a("003Xgg'l hj").equalsIgnoreCase(str) && C6152DH.SyncMtd.checkPermission(C5855l.m12238a("039fg9feflgffkfehf3lh-flfhfkhjhjfkgfHgJhfgnililiiglglfjhngkgjiifjheinilgngmgkingj"))) || (C5855l.m12238a("007ghkNhhgfflfn").equalsIgnoreCase(str) && C6152DH.SyncMtd.checkPermission(C5855l.m12238a("039fgKfeflgffkfehf%lh>flfhfkhjhjfkgf*g,hfgnililiiglglfjhngkgjiifjheinilgngmgkingj"))) || (C5855l.m12238a("007ghkKhhgfflfn").equalsIgnoreCase(str) && C6152DH.SyncMtd.checkPermission(C5855l.m12238a("041fg,feflgffkfehfVlhAflfhfkhjhjfkgf;g.hfgnililiiglglfjilingnijgliifjheinilgngmgkingj")));
    }
}
