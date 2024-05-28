package com.mob.tools.p237a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import com.mob.commons.C5829d;
import com.mob.commons.CSCenter;
import com.mob.commons.p229a.C5731l;
import com.mob.tools.MobLog;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.C6202d;
import com.mob.tools.utils.ReflectHelper;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.mob.tools.a.e */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C6034e {

    /* renamed from: b */
    private static C6034e f14856b;

    /* renamed from: a */
    private Context f14857a;

    /* renamed from: c */
    private Object f14858c;

    /* renamed from: d */
    private PackageManager f14859d;

    /* renamed from: e */
    private ConcurrentHashMap<String, Object> f14860e = new ConcurrentHashMap<>();

    /* renamed from: f */
    private ConcurrentHashMap<String, Integer> f14861f = new ConcurrentHashMap<>();

    /* renamed from: g */
    private ConcurrentHashMap<String, Long> f14862g = new ConcurrentHashMap<>();

    /* renamed from: h */
    private String f14863h;

    private C6034e(Context context) {
        this.f14857a = context;
        this.f14863h = context.getPackageName();
        try {
            m11663a(this.f14863h, 193);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    /* renamed from: a */
    public static C6034e m11667a(Context context) {
        if (f14856b == null) {
            synchronized (C6034e.class) {
                if (f14856b == null) {
                    f14856b = new C6034e(context);
                }
            }
        }
        return f14856b;
    }

    /* renamed from: a */
    public String m11664a(String str) {
        return m11659a(str, "");
    }

    /* renamed from: a */
    public String m11659a(String str, String str2) {
        Object invokeStaticMethodNoThrow = ReflectHelper.invokeStaticMethodNoThrow(ReflectHelper.importClassNoThrow(C5731l.m12674a("027efGedekfeejedgefegigefkelgi:jgMeghlekfeIkg)ek%j%ej6g gi"), null), C5731l.m12674a("003Jff=gj"), str2, str);
        return invokeStaticMethodNoThrow != null ? String.valueOf(invokeStaticMethodNoThrow) : str2;
    }

    /* renamed from: a */
    public Enumeration<NetworkInterface> m11668a() {
        try {
            return NetworkInterface.getNetworkInterfaces();
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return null;
        }
    }

    /* renamed from: a */
    public List<ResolveInfo> m11666a(Intent intent, int i) {
        if (C5829d.m12330b()) {
            return (List) ReflectHelper.invokeInstanceMethod(this.f14857a.getPackageManager(), C5731l.m12674a("019*efehDgVekelfj%fjgfjBfkMgRekeeej!dg4gi"), new Object[]{intent, Integer.valueOf(i)}, new Class[]{Intent.class, Integer.TYPE}, null);
        }
        return null;
    }

    /* renamed from: b */
    public ResolveInfo m11656b(Intent intent, int i) {
        if (C5829d.m12330b()) {
            return (ResolveInfo) ReflectHelper.invokeInstanceMethod(this.f14857a.getPackageManager(), C5731l.m12674a("015NekQg:gifeHh3ee<gFfm[dj=ejeeej6j!el"), new Object[]{intent, Integer.valueOf(i)}, new Class[]{Intent.class, Integer.TYPE}, null);
        }
        return null;
    }

    /* renamed from: a */
    public Object m11663a(final String str, final int i) throws Throwable {
        if (this.f14859d == null) {
            this.f14859d = this.f14857a.getPackageManager();
        }
        boolean equals = str.equals(this.f14863h);
        if (equals || C5829d.m12330b()) {
            if (equals) {
                final int i2 = (i == 0 || i == 1 || i == 128 || i == 64) ? 193 : i;
                Object m11660a = m11660a("gpisys-" + str + "-" + i2, (AbstractC6037a<AbstractC6037a<Object>>) new AbstractC6037a<Object>() { // from class: com.mob.tools.a.e.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super();
                    }

                    @Override // com.mob.tools.p237a.C6034e.AbstractC6037a
                    /* renamed from: a */
                    protected Object mo11650a() throws Throwable {
                        return C6034e.this.m11652c(str, i2);
                    }
                }, (AbstractC6037a<Object>) null);
                if (m11660a == null && i2 == 193) {
                    return m11660a("gpisys-" + str + "-" + i, (AbstractC6037a<AbstractC6037a<Object>>) new AbstractC6037a<Object>() { // from class: com.mob.tools.a.e.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super();
                        }

                        @Override // com.mob.tools.p237a.C6034e.AbstractC6037a
                        /* renamed from: a */
                        protected Object mo11650a() throws Throwable {
                            return C6034e.this.m11652c(str, i);
                        }
                    }, (AbstractC6037a<Object>) null);
                }
                return m11660a;
            }
            return m11652c(str, i);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public Object m11652c(String str, int i) throws Throwable {
        if (Build.VERSION.SDK_INT <= 25) {
            return C6152DH.SyncMtd.invokeInstanceMethod(this.f14859d, C5731l.m12674a("014%ff0gj+hl$ed*em e-ff@g*fj-f2fgfe"), new Object[]{str, Integer.valueOf(i)}, new Class[]{String.class, Integer.TYPE});
        }
        return C6117k.m11373a(this.f14857a, str, i);
    }

    /* renamed from: a */
    public void m11662a(String str, long j, float f, Object obj) {
        if (C5829d.m12327e()) {
            try {
                if (C6202d.m11087a().m11078a(str)) {
                    Object systemServiceSafe = C6152DH.SyncMtd.getSystemServiceSafe(C5731l.m12674a("008h%fe,dej,ejfe$f"));
                    Class<?> cls = Class.forName(C5731l.m12674a("033efSedekfeejedge=h_fe dejZejfe9fGgegdfe=dej;ejfe'f?gdejgiAjgfg'ek"));
                    if (systemServiceSafe != null) {
                        ReflectHelper.invokeInstanceMethod(systemServiceSafe, C5731l.m12674a("022OekCgMefehQgUgiXjCgdfe(dejJejfe;fKfh:kJed ejg+gi"), new Object[]{str, Long.valueOf(j), Float.valueOf(f), obj, C5731l.m12681a().m12670c()}, new Class[]{String.class, Long.TYPE, Float.TYPE, cls, Looper.class});
                    }
                }
            } catch (Throwable th) {
                MobLog.getInstance().m11325w(th);
            }
        }
    }

    /* renamed from: b */
    public Object m11655b(String str) {
        Object systemServiceSafe;
        if (C5829d.m12326f() && C6202d.m11087a().m11078a(str) && (systemServiceSafe = C6152DH.SyncMtd.getSystemServiceSafe(C5731l.m12674a("008h(fe4dejXejfe!f"))) != null) {
            return ReflectHelper.invokeInstanceMethodNoThrow(systemServiceSafe, C5731l.m12674a("020(ff*gjBgd8eWgi-j*jdZf^fegg:f[gdfeDdejZejfe_f"), null, str);
        }
        return null;
    }

    /* renamed from: b */
    public int m11657b() {
        if (C6031c.m11708a(this.f14857a).m11704d().mo11530e(C5731l.m12674a("035efBedekfeejedge,kgIekegejgigiejfeCfLgehihhfmgleihlgkhmfihheifkflfmflhh"))) {
            if (CSCenter.getInstance().isPhoneStateDataEnable()) {
                if (this.f14858c == null) {
                    this.f14858c = C6152DH.SyncMtd.getSystemServiceSafe(C5731l.m12674a("005kiMfeVfg"));
                }
                return ((Integer) ReflectHelper.invokeInstanceMethodNoThrow(this.f14858c, C5731l.m12674a("014Fff6gj$fi0gj6ggfeekemflelNkg"), -1, new Object[0])).intValue();
            }
            return CSCenter.getInstance().getNetworkType();
        }
        return -1;
    }

    /* renamed from: c */
    public int m11653c() {
        if (Build.VERSION.SDK_INT < 24 || !C6152DH.SyncMtd.checkPermission(C5731l.m12674a("035ef-edekfeejedgeFkg=ekegejgigiejfe9fHgehihhfmgleihlgkhmfihheifkflfmflhh"))) {
            return -1;
        }
        if (CSCenter.getInstance().isPhoneStateDataEnable()) {
            if (this.f14858c == null) {
                this.f14858c = C6152DH.SyncMtd.getSystemServiceSafe(C5731l.m12674a("005ki!feZfg"));
            }
            return ((Integer) ReflectHelper.invokeInstanceMethodNoThrow(this.f14858c, C5731l.m12674a("018AffUgjNgl.ejeZfiCgj6ggfeekemflelPkg"), -1, new Object[0])).intValue();
        }
        return CSCenter.getInstance().getNetworkType();
    }

    /* renamed from: a */
    public Enumeration<InetAddress> m11658a(NetworkInterface networkInterface) {
        return (Enumeration) ReflectHelper.invokeInstanceMethodNoThrow(networkInterface, C5731l.m12674a("016'ffOgj-fj^fgj_fmededekEgOgigi4g2gi"), null, new Object[0]);
    }

    /* renamed from: b */
    public ApplicationInfo m11654b(String str, int i) throws PackageManager.NameNotFoundException {
        if (this.f14859d == null) {
            this.f14859d = this.f14857a.getPackageManager();
        }
        if (TextUtils.equals(str, this.f14857a.getPackageName()) || C5829d.m12330b()) {
            return this.f14859d.getApplicationInfo(str, i);
        }
        return null;
    }

    /* renamed from: d */
    public ApplicationInfo m11651d() {
        return this.f14857a.getApplicationInfo();
    }

    /* renamed from: a */
    private <T> T m11660a(String str, AbstractC6037a<T> abstractC6037a, T t) {
        return (T) m11661a(str, abstractC6037a, 0L, t, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    private <T> T m11661a(String str, AbstractC6037a<T> abstractC6037a, long j, T t, boolean z) {
        T t2 = (T) null;
        try {
            if (str == null) {
                t2 = abstractC6037a.mo11650a();
            } else {
                Integer num = this.f14861f.get(str);
                if (num != null && (t2 = this.f14860e.get(str)) == null && !z) {
                    return t;
                }
                Long l = this.f14862g.get(str);
                boolean z2 = false;
                if (l != null && System.currentTimeMillis() >= l.longValue()) {
                    z2 = true;
                }
                if (t2 == null || z2 || z) {
                    t2 = abstractC6037a.mo11650a();
                    if (t2 != null) {
                        this.f14860e.put(str, t2);
                        if (j > 0) {
                            this.f14862g.put(str, Long.valueOf(System.currentTimeMillis() + j));
                        }
                    }
                    if (num == null) {
                        this.f14861f.put(str, 1);
                    } else {
                        this.f14861f.put(str, Integer.valueOf(num.intValue() + 1));
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
        return t2 == null ? t : t2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.tools.a.e$a */
    /* loaded from: E:\567196_dexfile_execute.dex */
    public static abstract class AbstractC6037a<T> {
        /* renamed from: a */
        protected abstract T mo11650a() throws Throwable;

        private AbstractC6037a() {
        }
    }
}
