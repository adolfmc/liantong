package com.xiaomi.push;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.service.XMJobService;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.dz */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C11311dz {

    /* renamed from: a */
    private static InterfaceC11312a f22038a;

    /* renamed from: a */
    private static final String f22039a = XMJobService.class.getCanonicalName();

    /* renamed from: a */
    private static int f22037a = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.dz$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11312a {
        /* renamed from: a */
        void mo4052a();

        /* renamed from: a */
        void mo4049a(boolean z);

        /* renamed from: a */
        boolean mo4051a();
    }

    /* renamed from: a */
    public static void m4066a(Context context) {
        boolean z;
        Context applicationContext = context.getApplicationContext();
        if ("com.xiaomi.xmsf".equals(applicationContext.getPackageName())) {
            f22038a = new C11314ea(applicationContext);
            return;
        }
        int i = 0;
        try {
            PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 4);
            if (packageInfo.services != null) {
                ServiceInfo[] serviceInfoArr = packageInfo.services;
                int length = serviceInfoArr.length;
                z = false;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    try {
                        ServiceInfo serviceInfo = serviceInfoArr[i];
                        if ("android.permission.BIND_JOB_SERVICE".equals(serviceInfo.permission)) {
                            if (f22039a.equals(serviceInfo.name)) {
                                z = true;
                            } else {
                                try {
                                    if (f22039a.equals(C11479r.m2929a(applicationContext, serviceInfo.name).getSuperclass().getCanonicalName())) {
                                        z = true;
                                    }
                                } catch (Exception unused) {
                                }
                            }
                            if (z) {
                                break;
                            }
                        }
                        if (f22039a.equals(serviceInfo.name) && "android.permission.BIND_JOB_SERVICE".equals(serviceInfo.permission)) {
                            z = true;
                            break;
                        }
                        i++;
                    } catch (Exception e) {
                        e = e;
                        AbstractC11049b.m5282a("check service err : " + e.getMessage());
                        if (z) {
                        }
                        int i2 = Build.VERSION.SDK_INT;
                        f22038a = new C11314ea(applicationContext);
                    }
                }
            } else {
                z = false;
            }
        } catch (Exception e2) {
            e = e2;
            z = false;
        }
        if (z && C11479r.m2930a(applicationContext)) {
            throw new RuntimeException("Should export service: " + f22039a + " with permission android.permission.BIND_JOB_SERVICE in AndroidManifest.xml file");
        }
        int i22 = Build.VERSION.SDK_INT;
        f22038a = new C11314ea(applicationContext);
    }

    /* renamed from: a */
    public static synchronized void m4065a(Context context, int i) {
        synchronized (C11311dz.class) {
            int i2 = f22037a;
            if (!"com.xiaomi.xmsf".equals(context.getPackageName())) {
                if (i == 2) {
                    f22037a = 2;
                } else {
                    f22037a = 0;
                }
            }
            if (i2 != f22037a && f22037a == 2) {
                m4068a();
                f22038a = new C11316ec(context);
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m4064a(boolean z) {
        synchronized (C11311dz.class) {
            if (f22038a == null) {
                AbstractC11049b.m5282a("timer is not initialized");
                return;
            }
            AbstractC11049b.m5282a("[Alarm] register alarm. (" + z + ")");
            f22038a.mo4049a(z);
        }
    }

    /* renamed from: a */
    public static synchronized void m4068a() {
        synchronized (C11311dz.class) {
            if (f22038a == null) {
                return;
            }
            AbstractC11049b.m5282a("[Alarm] stop alarm.");
            f22038a.mo4052a();
        }
    }

    /* renamed from: a */
    public static synchronized boolean m4067a() {
        synchronized (C11311dz.class) {
            if (f22038a == null) {
                return false;
            }
            return f22038a.mo4051a();
        }
    }
}
