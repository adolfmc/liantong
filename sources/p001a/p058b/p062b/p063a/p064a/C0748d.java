package p001a.p058b.p062b.p063a.p064a;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.baidu.uaq.agent.android.InterfaceC3316b;
import com.baidu.uaq.agent.android.UAQ;
import com.baidu.uaq.agent.android.crashes.InterfaceC3317d;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import p001a.p002a.p003a.p004a.outline;
import p001a.p058b.p062b.p063a.p064a.p065a.C0712d;
import p001a.p058b.p062b.p063a.p064a.p065a.C0717g;
import p001a.p058b.p062b.p063a.p064a.p065a.RunnableC0711c;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0724a;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0726c;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0727d;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;
import p001a.p058b.p062b.p063a.p064a.p074f.C0756a;
import p001a.p058b.p062b.p063a.p064a.p074f.RunnableC0757b;
import p001a.p058b.p062b.p063a.p064a.p081k.C0774f;

/* renamed from: a.b.b.a.a.d */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0748d implements InterfaceC3316b {

    /* renamed from: a */
    public static final InterfaceC3321a f2292a = C0749a.f2299a;

    /* renamed from: b */
    public static final UAQ f2293b = UAQ.getInstance();

    /* renamed from: c */
    public final Context f2294c;

    /* renamed from: d */
    public C0755f f2295d;

    /* renamed from: e */
    public C0726c f2296e;

    /* renamed from: f */
    public C0724a f2297f;

    /* renamed from: g */
    public InterfaceC3317d f2298g;

    public C0748d(Context context) {
        C0755f c0755f;
        if (!TextUtils.isEmpty(f2293b.getConfig().getAPIKey())) {
            this.f2294c = context instanceof Application ? context : context.getApplicationContext();
            this.f2295d = new C0755f(this.f2294c);
            if (!f2293b.getConfig().getAPIKey().equals(this.f2295d.m22266a("appToken"))) {
                f2292a.mo17428D("License key has changed. Clearing saved state.");
                c0755f = this.f2295d;
                c0755f.f2316e.lock();
                try {
                    try {
                        if (c0755f.f2315d == null) {
                            c0755f.f2315d = c0755f.f2314c.edit();
                        }
                        c0755f.f2315d.clear();
                        c0755f.f2315d.apply();
                    } catch (Exception e) {
                        C0755f.f2312a.mo17426a("Caught error while clear SavedState: ", e);
                    }
                    c0755f.f2316e.unlock();
                    this.f2295d.m22264b(f2293b.getConfig().getDataReportLimit());
                    this.f2295d.m22263c(System.currentTimeMillis());
                } finally {
                }
            }
            if (f2293b.getConfig().isNativeControlDRP()) {
                long dataReportPeriod = f2293b.getConfig().getDataReportPeriod();
                C0755f c0755f2 = this.f2295d;
                if (dataReportPeriod != c0755f2.f2318g) {
                    c0755f2.m22267a(f2293b.getConfig().getDataReportPeriod());
                    this.f2295d.m22268a();
                }
            }
            c0755f = this.f2295d;
            String aPIKey = f2293b.getConfig().getAPIKey();
            c0755f.f2316e.lock();
            try {
                try {
                    if (c0755f.f2315d == null) {
                        c0755f.f2315d = c0755f.f2314c.edit();
                    }
                    c0755f.f2315d.putString("appToken", aPIKey);
                    c0755f.f2315d.apply();
                } finally {
                }
            } catch (Exception e2) {
                C0755f.f2312a.mo17426a("Caught error while SavedState save: ", e2);
            }
            c0755f.f2316e.unlock();
            this.f2298g = new C0717g(this.f2294c);
            return;
        }
        f2292a.mo17428D("License key invalid cannot start.");
        throw new C0722c("This license key is null");
    }

    /* renamed from: a */
    public static void m22271a(Context context) {
        try {
            C0719b.m22325a(new C0748d(context));
            C0719b.m22326a().start();
        } catch (C0722c e) {
            f2292a.mo17426a("Failed to initialize the agent: ", e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0084, code lost:
        if (android.text.TextUtils.isEmpty(p001a.p058b.p062b.p063a.p064a.p081k.C0769b.f2377b) != false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009a, code lost:
        if (android.text.TextUtils.isEmpty(p001a.p058b.p062b.p063a.p064a.p081k.C0769b.f2377b) == false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x009c, code lost:
        r1 = p001a.p002a.p003a.p004a.outline.m24437a("TS");
        r1.append(java.lang.System.currentTimeMillis());
        p001a.p058b.p062b.p063a.p064a.p081k.C0769b.f2377b = r1.toString();
     */
    @Override // com.baidu.uaq.agent.android.InterfaceC3316b
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0726c mo17442e() {
        /*
            Method dump skipped, instructions count: 257
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p001a.p058b.p062b.p063a.p064a.C0748d.mo17442e():a.b.b.a.a.c.a.c");
    }

    @Override // com.baidu.uaq.agent.android.InterfaceC3316b
    /* renamed from: f */
    public C0724a mo17441f() {
        String str;
        String str2;
        C0724a c0724a = this.f2297f;
        if (c0724a != null) {
            return c0724a;
        }
        String packageName = this.f2294c.getPackageName();
        PackageManager packageManager = this.f2294c.getPackageManager();
        String str3 = "";
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            if (packageInfo != null && (str2 = packageInfo.versionName) != null && str2.length() > 0) {
                str3 = packageInfo.versionName;
            }
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            str = applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo).toString() : packageName;
        } catch (PackageManager.NameNotFoundException | SecurityException e) {
            f2292a.mo17426a("Caught error while getApplicationInformation: ", e);
            C0735a.m22302a(e);
            str = "";
        }
        this.f2297f = new C0724a(str, str3, packageName);
        return this.f2297f;
    }

    @Override // com.baidu.uaq.agent.android.InterfaceC3316b
    /* renamed from: g */
    public String mo17440g() {
        return C0774f.m22236b(this.f2294c);
    }

    @Override // com.baidu.uaq.agent.android.InterfaceC3316b
    /* renamed from: h */
    public String mo17439h() {
        return C0774f.m22238a(this.f2294c);
    }

    @Override // com.baidu.uaq.agent.android.InterfaceC3316b
    /* renamed from: i */
    public C0727d mo17438i() {
        C0727d c0727d = new C0727d();
        ActivityManager activityManager = (ActivityManager) this.f2294c.getSystemService("activity");
        long[] jArr = new long[2];
        StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        StatFs statFs2 = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        try {
            jArr[0] = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
            jArr[1] = statFs2.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        } catch (Exception e) {
            f2292a.mo17426a("Caught error while getEnvironmentInformation: ", e);
            C0735a.m22302a(e);
        }
        if (jArr[0] < 0) {
            jArr[0] = 0;
        }
        if (jArr[1] < 0) {
            jArr[1] = 0;
        }
        c0727d.f2225d = jArr;
        c0727d.f2222a = 0L;
        c0727d.f2223b = this.f2294c.getResources().getConfiguration().orientation;
        c0727d.f2224c = C0774f.m22238a(this.f2294c);
        C0774f.m22236b(this.f2294c);
        return c0727d;
    }

    @Override // com.baidu.uaq.agent.android.InterfaceC3316b
    public void shutdown() {
        InterfaceC3321a interfaceC3321a;
        String str;
        InterfaceC3321a interfaceC3321a2 = C0756a.f2320b;
        StringBuilder m24437a = outline.m24437a("Sampler shutdown sampleLock");
        m24437a.append(C0756a.f2321c == null);
        interfaceC3321a2.mo17428D(m24437a.toString());
        C0756a.f2321c.lock();
        try {
            if (C0756a.f2322d != null) {
                C0756a.f2321c.lock();
                if (C0756a.f2322d != null) {
                    C0756a.f2322d.m22261a(true);
                    C0756a.f2320b.mo17428D("Sampler hard stopped");
                }
                C0756a.f2321c.unlock();
                C0756a.f2322d = null;
                C0756a.f2320b.mo17428D("Sampler shutdown");
            }
            C0756a.f2321c.unlock();
            if (f2293b.getConfig().isThingsMonitor()) {
                RunnableC0757b.f2334d.lock();
                try {
                    if (RunnableC0757b.f2335e != null) {
                        RunnableC0757b.f2334d.lock();
                        if (RunnableC0757b.f2335e != null) {
                            RunnableC0757b.f2335e.m22258a(true);
                            RunnableC0757b.f2331a.mo17428D("SamplerCommon hard stopped");
                        }
                        RunnableC0757b.f2334d.unlock();
                        RunnableC0757b.f2335e = null;
                        interfaceC3321a = RunnableC0757b.f2331a;
                        str = "SamplerCommon shutdown";
                    } else {
                        interfaceC3321a = RunnableC0757b.f2331a;
                        str = "SamplerCommon shutdown start, sampler null!";
                    }
                    interfaceC3321a.mo17428D(str);
                } finally {
                    RunnableC0757b.f2334d.unlock();
                }
            }
            C0762h.m22247b();
        } catch (Throwable th) {
            C0756a.f2321c.unlock();
            throw th;
        }
    }

    @Override // com.baidu.uaq.agent.android.InterfaceC3316b
    public void start() {
        Context context = this.f2294c;
        InterfaceC3321a interfaceC3321a = C0756a.f2320b;
        StringBuilder m24437a = outline.m24437a("Sampler init sampleLock");
        m24437a.append(C0756a.f2321c == null);
        interfaceC3321a.mo17428D(m24437a.toString());
        C0756a.f2321c.lock();
        try {
            try {
                if (C0756a.f2322d == null) {
                    C0756a.f2322d = new C0756a(context);
                }
            } catch (Exception e) {
                C0756a.f2320b.mo17426a("Caught error while Sampler init: ", e);
                C0735a.m22302a(e);
            }
            C0756a.f2321c.unlock();
            if (f2293b.getConfig().isThingsMonitor()) {
                Context context2 = this.f2294c;
                RunnableC0757b.f2334d.lock();
                try {
                    if (RunnableC0757b.f2335e == null) {
                        RunnableC0757b.f2332b = context2;
                        RunnableC0757b.f2335e = new RunnableC0757b();
                        RunnableC0757b.f2334d.lock();
                        RunnableC0757b.f2331a.mo17428D("SamplerCommon start!");
                        RunnableC0757b.f2335e.m22260a();
                        RunnableC0757b.f2334d.unlock();
                    } else {
                        RunnableC0757b.f2331a.mo17428D("sampler not null when init samplerCommon!");
                    }
                } finally {
                    RunnableC0757b.f2334d.unlock();
                }
            }
            if (f2293b.getConfig().isReportCrashes()) {
                InterfaceC3317d interfaceC3317d = this.f2298g;
                C0712d.f2153c.f2158h = true;
                if (C0712d.f2155e.compareAndSet(false, true)) {
                    C0712d c0712d = C0712d.f2153c;
                    C0712d.f2154d = interfaceC3317d;
                    C0712d.f2156f.submit(new RunnableC0711c());
                    C0712d.f2153c.m22334b();
                }
            }
        } catch (Throwable th) {
            C0756a.f2321c.unlock();
            throw th;
        }
    }

    @Override // com.baidu.uaq.agent.android.InterfaceC3316b
    public void stop() {
        C0762h.m22247b();
    }
}
